package com.junit_demo.app.java8.union;

import com.junit_demo.app.java8.model.pattern.BinOp;
import com.junit_demo.app.java8.model.pattern.Expr;
import com.junit_demo.app.java8.model.pattern.Number;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p> @author     :  清风
 * <p> description :
 *      模式匹配
 * <p> create date :  2021/11/17 16:31
 */
public class PatternTest {

    private static final Logger log = LoggerFactory.getLogger(PatternTest.class);

    @DisplayName("模式匹配调用")
    @Test
    public void test() {
        log.info("simplify: BinOp(\"+\", new Number(5), new Number(0)) -> {}", simplify(new BinOp("+", new Number(5), new Number(0))));
        log.info("simplify: BinOp(\"*\", new Number(10), new Number(1)) -> {}", simplify(new BinOp("*", new Number(10), new Number(1))));
        log.info("simplify: BinOp(\"/\", new Number(10), new Number(1)) -> {}", simplify(new BinOp("/", new Number(10), new Number(1))));
    }

    public static Expr simplify(Expr e) {
        TriFunction<String, Expr, Expr, Expr> binopcase = (opname, left, right) -> {
            if ("+".equals(opname)) {
                if (left instanceof Number && ((Number) left).getVal() == 0) {
                    return right;
                }
                if (right instanceof Number && ((Number) right).getVal() == 0) {
                    return left;
                }
            }
            if ("*".equals(opname)) {
                if (left instanceof Number && ((Number) left).getVal() == 1) {
                    return right;
                }
                if (right instanceof Number && ((Number) right).getVal() == 1) {
                    return left;
                }
            }
            return new BinOp(opname, left, right);
        };

        Function<Integer, Expr> numcase = val -> new Number(val);
        Supplier<Expr> defaultcase = () -> new Number(0);
        return patternMatchExpr(e, binopcase, numcase, defaultcase);
    }

    interface TriFunction<S, T, U, R> {
        R apply(S s, T t, U u);
    }
    static <T> T patternMatchExpr(Expr e, TriFunction<String, Expr, Expr, T> binopcase, Function<Integer, T> numcase, Supplier<T> defaultcase) {
        return (e instanceof BinOp) ? binopcase.apply(((BinOp) e).getOpname(), ((BinOp) e).getLeft(), ((BinOp) e).getRight()) :
                (e instanceof Number) ? numcase.apply(((Number) e).getVal()): defaultcase.get();
    }


}
