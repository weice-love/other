package com.junit_demo.app.java8.union;

import com.junit_demo.app.java8.model.Tree;

/**
 * <p> @author     :  清风
 * <p> description :
 *      二叉查找树处理类
 * <p> create date :  2021/11/17 10:29
 */
public class TreeProcessor {

    /**
     * @author     : 清风
     * <p>description :
     *      使用函数式更新，会创建部分新对象
     * <p>create time : 10:38 2021/11/17
     *
     */
    public static Tree fupdate(String k, int newval, Tree t) {
        return (t == null) ?
                new Tree(k, newval, null, null) :
                k.equals(t.getKey()) ?
                        new Tree(k, newval, t.getLeft(), t.getRight()) :
                        k.compareTo(t.getKey()) < 0 ?
                                new Tree(t.getKey(), t.getVal(), fupdate(k,newval, t.getLeft()), t.getRight()) :
                                new Tree(t.getKey(), t.getVal(), t.getLeft(), fupdate(k,newval, t.getRight()));
    }

    /**
     * @author     : 清风
     * <p>description :
     *      会更新原树
     * <p>create time : 10:36 2021/11/17
     *
     */
    public static Tree update(String k, int newval, Tree t) {
        if (t == null)
            t = new Tree(k, newval, null, null);
        else if (k.equals(t.getKey()))
            t.setVal(newval);
        else if (k.compareTo(t.getKey()) < 0)
            t.setLeft(update(k, newval, t.getLeft()));
        else
            t.setRight(update(k, newval, t.getRight()));
        return t;
    }

    /**
     * @author     : 清风
     * <p>description :
     *      会更新原树（只能更新存在的键值对，无法新增）
     * <p>create time : 10:31 2021/11/17
     *
     */
    public static void updateVal(String k, int newval, Tree t) {
        if (t == null) { /* 应增加一个新的节点 */ }
        else if (k.equals(t.getKey())) t.setVal(newval);
        else updateVal(k, newval, k.compareTo(t.getKey()) < 0 ? t.getLeft() : t.getRight());
    }

    public static int lookup(String k, int defaultval, Tree t) {
        if (t == null) return defaultval;
        if (k.equals(t.getKey())) return t.getVal();
        return lookup(k, defaultval,
                k.compareTo(t.getKey()) < 0 ? t.getLeft() : t.getRight());
    }
}
