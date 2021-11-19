package com.junit_demo.app.java8.model.pattern;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/19 10:07
 */
public class BinOp extends Expr {
    String opname;
    Expr left, right;

    public BinOp(String opname, Expr left, Expr right) {
        this.opname = opname;
        this.left = left;
        this.right = right;
    }

    /**
     * @author     : 清风
     * <p>description :
     *      利用访问者简化
     * <p>create time : 10:25 2021/11/19
     *
     */
    public Expr accept(SimplifyExprVisitor v) {
        return v.visit(this);
    }

    public String getOpname() {
        return opname;
    }

    public void setOpname(String opname) {
        this.opname = opname;
    }

    public Expr getLeft() {
        return left;
    }

    public void setLeft(Expr left) {
        this.left = left;
    }

    public Expr getRight() {
        return right;
    }

    public void setRight(Expr right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinOp{" +
                "opname='" + opname + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
