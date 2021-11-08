package com.junit_demo.app.util;

import com.alibaba.fastjson.JSON;
import com.junit_demo.app.java8.model.Shop;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/8 14:03
 */
public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    public static void main(String[] args) {
        List<Shop> shops = IntStream.rangeClosed(1, 9)
                .boxed()
                .map(a -> new Shop(RandomStringUtils.randomAlphabetic(5)))
                .collect(Collectors.toList());
        String content = JSON.toJSONString(shops);
        log.info("{}", content);
//        createFile("shop_8.json", content);
    }

    public static void createFile(String filename, String content) {
        // todo 如何直接往resources下创建文件
        File file = new File(FileUtil.class.getResource("/").getPath() + filename);
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {

        }
    }
}
