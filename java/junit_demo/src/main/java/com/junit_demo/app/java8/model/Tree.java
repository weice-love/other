package com.junit_demo.app.java8.model;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/17 10:28
 */
public class Tree {

    private String key;
    private int val;
    private Tree left, right;

    public Tree(String key, int val, Tree left, Tree right) {
        this.key = key;
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public String getKey() {
        return key;
    }

    public int getVal() {
        return val;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public void setRight(Tree right) {
        this.right = right;
    }
}
