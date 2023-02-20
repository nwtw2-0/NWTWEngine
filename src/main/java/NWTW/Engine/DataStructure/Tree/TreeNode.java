package NWTW.Engine.DataStructure.Tree;

import org.jetbrains.annotations.NotNull;

public class TreeNode<T extends Comparable<T>> implements Comparable<T>{
    public T key;
    public int balance;
    public int height;
    public TreeNode<T> left, right, parent;
    TreeNode(T k, TreeNode<T> p) {
        key = k;
        parent = p;
    }

    @Override
    public int compareTo(@NotNull T o) {
        return key.compareTo(o);
    }
}
