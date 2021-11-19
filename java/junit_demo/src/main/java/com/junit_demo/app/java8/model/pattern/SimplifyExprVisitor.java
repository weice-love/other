package com.junit_demo.app.java8.model.pattern;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/19 10:12
 */
public class SimplifyExprVisitor {

    public Expr visit(BinOp e) {
        if ("+".equals(e.opname) && e.right instanceof Number && ((Number) e.right).val == 0) {
            return e.left;
        }
        if ("*".equals(e.opname) && e.right instanceof Number && ((Number) e.right).val == 1) {
            return e.left;
        }
        if ("/".equals(e.opname) && e.right instanceof Number && ((Number) e.right).val == 1) {
            return e.left;
        }
        return e;
    }
}
