package com.junit_demo.app.java8.forkjoin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/10/18 14:25
 */
public class StringCountTest {

    private static final Logger log = LoggerFactory.getLogger(StringCountTest.class);

    @DisplayName("单词计数测试")
    @ValueSource(strings = { " Nel mezzo del cammin di nostra vita " +
            "mi ritrovai in una selva oscura" +
                    " ché la dritta via era smarrita "})
    @ParameterizedTest
    public void wordCount(String words) {
        Stream<Character> characterStream = IntStream.range(0, words.length()).mapToObj(words::charAt);
        log.info("words count: {}", wordscount(characterStream));
    }

    @DisplayName("单词计数测试(并行流是有问题的！！！)")
    @ValueSource(strings = { " Nel mezzo del cammin di nostra vita " +
            "mi ritrovai in una selva oscura" +
            " ché la dritta via era smarrita "})
    @ParameterizedTest
    public void wordCountParallel(String words) {
        Stream<Character> characterStream = IntStream.range(0, words.length()).mapToObj(words::charAt);
        log.info("words count: {}", wordscount(characterStream.parallel()));
    }

    @DisplayName("单词计数测试(并行流,使用拆分类)")
    @ValueSource(strings = { " Nel mezzo del cammin di nostra vita " +
            "mi ritrovai in una selva oscura" +
            " ché la dritta via era smarrita "})
    @ParameterizedTest
    public void wordCountParallelWithSpliterator(String words) {
        Stream<Character> stream = StreamSupport.stream(new WordCounterSpliterator(words), true);
        log.info("words count: {}", wordscount(stream));
    }

    private int wordscount(Stream<Character> stream) {
        WordCounter reduce = stream.reduce(new WordCounter(true, 0), WordCounter::accumulator, WordCounter::combiner);
        return reduce.getCounter();
    }

    /*
    使用详情，见附录C
     */
    public static class WordCounterSpliterator implements Spliterator<Character> {

        private final String string;
        private int currentChar = 0;

        public WordCounterSpliterator(String string) {
            this.string = string;
        }

        /**
         * @author     : 清风
         * <p>description : 处理当前字符，如果还有字符返回true
         * <p>create time : 15:19 2021/10/18
         *
         */
        @Override
        public boolean tryAdvance(Consumer<? super Character> consumer) {
            consumer.accept(string.charAt(currentChar++));
            return currentChar < string.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            int currentSize = string.length() - currentChar;
            // 返回null表示要解析的 String已经足够小，可以顺序处理
            if (currentSize < 10) {
                return null;
            }
            // 将试探拆分位置设定为要解析的String的中间
            for (int splitPos = (currentSize >> 2) + currentChar; splitPos < string.length(); splitPos ++) {
                // 让拆分位置前进直到下一个空格
                if (Character.isWhitespace(string.charAt(splitPos))) {
                    // 创建一个新WordCounterSpliterator来解析String从开始到拆分位置的部分
                    WordCounterSpliterator wordCounterSpliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                    // 将这个WordCounterSpliterator 的起始位置设为拆分位置
                    currentChar = splitPos;
                    return wordCounterSpliterator;
                }
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return string.length() - currentChar;
        }

        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }

    public static class WordCounter {
        private final boolean lastSpace;
        private final int counter;

        public WordCounter(boolean lastSpace, int counter) {
            this.lastSpace = lastSpace;
            this.counter = counter;
        }

        public WordCounter accumulator(Character character) {
            return Character.isWhitespace(character) ?
                    (lastSpace ? this : new WordCounter(true, counter))
                    : (lastSpace ? new WordCounter(false, counter + 1) : this);
        }


        public WordCounter combiner(WordCounter wordCounter) {
            return  new WordCounter(wordCounter.lastSpace, counter + wordCounter.counter);
        }

        public int getCounter() {
            return counter;
        }
    }
}
