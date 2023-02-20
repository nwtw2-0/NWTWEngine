package NWTW.Engine.DataStructure.Tree;

import org.jetbrains.annotations.NotNull;

public class TreeNode<T extends Comparable<T>,E> implements Comparable<T>{
    public T key;
    public E value;
    public int balance;
    public int height;
    public TreeNode<T,E> left, right, parent;
    TreeNode(T k,E v, TreeNode<T,E> p) {
        key = k;
        value = v;
        parent = p;
    }

    @Override
    public int compareTo(@NotNull T o) {
        return key.compareTo(o);
    }
}
