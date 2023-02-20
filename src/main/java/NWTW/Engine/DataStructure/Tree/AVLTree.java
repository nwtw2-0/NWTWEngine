package NWTW.Engine.DataStructure.Tree;

import org.checkerframework.checker.units.qual.A;

public class AVLTree<T extends Comparable<T>,E> {
    private TreeNode<T,E> root;
    public boolean insert(T key,E value) {
        if (root == null) {
            root = new TreeNode<>(key,value,null);
        } else {
            TreeNode<T,E> n = root;
            TreeNode<T,E> parent;
            while (true) {
                if (n.key == key) {
                    return false;
                }

                parent = n;

                boolean goLeft = n.compareTo(key) > 0;
                n = goLeft ? n.left : n.right;

                if (n == null) {
                    if (goLeft) {
                        parent.left = new TreeNode<>(key,value, parent);
                    } else {
                        parent.right = new TreeNode<>(key,value, parent);
                    }
                    rebalance(parent);
                    break;
                }
            }
        }
        return true;
    }

    private void delete(TreeNode<T,E> node) {
        if (node.left == null && node.right == null) {
            if (node.parent == null) {
                root = null;
            } else {
                TreeNode<T,E> parent = node.parent;
                if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                rebalance(parent);
            }
            return;
        }
        if (node.left != null) {
            TreeNode<T,E> child = node.left;
            while (child.right != null) {
                child = child.right;
            }
            node.key = child.key;
            delete(child);
        } else {
            TreeNode<T,E> child = node.right;
            while (child.left != null) {
                child = child.left;
            }
            node.key = child.key;
            delete(child);
        }
    }

    public void delete(T delKey) {
        if (root == null) {
            return;
        }
        TreeNode<T,E> node = root;
        TreeNode<T,E> child = root;

        while (child != null) {
            node = child;
            child = delKey.compareTo(node.key) >= 0 ? node.right : node.left;
            if (delKey == node.key) {
                delete(node);
                return;
            }
        }
    }

    private void rebalance(TreeNode<T,E> n) {
        setBalance(n);

        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right)) {
                n = rotateRight(n);
            } else {
                n = rotateLeftThenRight(n);
            }
        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left)) {
                n = rotateLeft(n);
            } else {
                n = rotateRightThenLeft(n);
            }
        }

        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }

    private TreeNode<T,E> rotateLeft(TreeNode<T,E> a) {
        TreeNode<T,E> b = a.right;
        b.parent = a.parent;

        a.right = b.left;

        if (a.right != null) {
            a.right.parent = a;
        }

        b.left = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private TreeNode<T,E> rotateRight(TreeNode<T,E> a) {
        TreeNode<T,E> b = a.left;
        b.parent = a.parent;

        a.left = b.right;

        if (a.left != null) {
            a.left.parent = a;
        }

        b.right = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private TreeNode<T,E> rotateLeftThenRight(TreeNode<T,E> n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }

    private TreeNode<T,E> rotateRightThenLeft(TreeNode<T,E> n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    private int height(TreeNode<T,E> n) {
        if (n == null) {
            return -1;
        }
        return n.height;
    }

    private void setBalance(TreeNode<T,E>... nodes) {
        for (TreeNode<T,E> n : nodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }

    public void printBalance() {
        printBalance(root);
    }

    private void printBalance(TreeNode<T,E> n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
            printBalance(n.right);
        }
    }

    private void reheight(TreeNode<T,E> node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    public boolean search(T key) {
        TreeNode<T,E> result = searchHelper(this.root, key);
        return result != null;
    }

    private TreeNode<T,E> searchHelper(TreeNode<T,E> root, T key) {
        // root is null or key is present at root
        if (root == null || root.key == key) {
            return root;
        }

        // key is greater than root's key
        if (root.compareTo(key) > 0) {
            return searchHelper(root.left, key); // call the function on the node's left child
        }
        // key is less than root's key then
        // call the function on the node's right child as it is greater
        return searchHelper(root.right, key);
    }
    public static void main(String[] args) {
        AVLTree<Character,Integer> tree = new AVLTree<>();

        System.out.println("Inserting values 1 to 10");
        for (int i = 1; i < 10; i++) {
            if (i %2 == 0)
                tree.insert((char) ('U'+i),i);
            else
                tree.insert((char) ('U'-i),i);
        }

        System.out.print("Printing balance: ");
        tree.printBalance();
    }
}
