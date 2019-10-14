package datastructures;

import java.util.ArrayList;

/**
 * Here we leverage the Node members directly for readability, as relying on getters/setters
 * makes the code harder to read.
 * @param <T> generic item must extend Comparable
 */
public class RedBlackTree<T extends Comparable<? super T>> implements Tree {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private static final Node SPECIAL_NODE = new Node<>(null, false);

    Node nil = SPECIAL_NODE;
    Node root = SPECIAL_NODE;

    /**
     * We do not pass a tree as in the CLRS algorithm. Instead we pass in the root as a node.
     * We always insert the new node as a RED node, then call insert-fixup to correct any violations
     * that may occur.
     * initial call: {@code insert(nodeToInsert); }
     * Note that we do not need to pass in an instance of RedBlackTree as in CLRS pseudocode;
     * we may leverage the {@code this} keyword.
     * @param z the node we wish to insert
     */
    public void insert(T item) {
        Node z = new Node<>(item);
        Node y = this.nil;
        Node x = this.root;

        while (x != this.nil) {
            y = x;
            if (z.item.compareTo(x.item) < 0)
                x = x.left;
            else
                x = x.right;
        }
        z.parent = y;
        if (y == this.nil) {
            this.root = z;
            z.parent = nil;
        }
        else if (z.item.compareTo(y.item) < 0)
            y.left = z;
        else
            y.right = z;
        z.left = this.nil;
        z.right = this.nil;
        z.color = RED;
        insertFixup(z);
    }

    /**
     *
     * @param
     * @param z check and fix violations for newly inserted node z
     */
    private void insertFixup(Node z) {
        while (z.parent.color == RED) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right;
                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                Node y = z.parent.parent.left;
                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {

                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    leftRotate(z.parent.parent);
                }
            }

        }
        this.root.color = BLACK;
    }

    /**
     * Rotate, x becomes parent and y becomes right child.
     * X gets it's left subtree as left subtree still.
     * Y is X's right child. Y keeps it's original right subtree.
     * Y inherits x's right subtree as it's (Y's) left subtree now.
     * THIS
     *        Y
     *     X     i
     *  a    b
     *
     * BECOMES THIS
     *       X
     *    a     Y
     *        b   i
     *
     * @param x left child of y
     */
    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != this.nil)
            y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == this.nil)
            this.root = y;
        else if (x == x.parent.right)
            x.parent.right = y;
        else
            x.parent.left = y;
        y.right = x;
        x.parent = y;
    }

    /**
     * X becomes the left child of Y.
     * X keeps it's left subtree.
     * Y becomes parent, keeps it's left subtree.
     * X's right subtree now used to be Y's left subtree before.
     *
     * THIS
     *       X
     *    a     Y
     *        b   i
     * BECOMES THIS
     *        Y
     *     X     i
     *  a    b
     *
     * @param x is the parent of Y, and Y is right child.
     */
    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != this.nil)
            y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == this.nil)
            this.root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    public void inOrderTraversal() {
        printInOrder(this.root, 1);
    }

    private void printInOrder(Node node, int i) {
        if (node != null && node.item != null) {
            printInOrder(node.left, i);
            System.out.println(node.item.toString() + " color: " + node.isColor());
            printInOrder(node.right, i);
        }
    }

    void levelOrder(ArrayList<Node> n) {
        ArrayList<Node> next = new ArrayList<>();
        if (n.isEmpty()) return;

        for(Node t : n) {
            if (t.item == null) {
                System.out.print("n ");
            } else {
                System.out.print(t.item.toString() + "  ");
                next.add(t.left);
                next.add(t.right);
            }
        }
        System.out.println();
        levelOrder(next);
    }

    /**
     * Expected output:
     * RBT in order (false = BLACK, true = RED)
     * 1 color: false
     * 2 color: false
     * 3 color: false
     * 4 color: false
     * 5 color: true
     * 91 color: false
     * 92 color: true
     * 93 color: true
     * 94 color: false
     * 95 color: false
     * 96 color: false
     * 97 color: true
     * 98 color: false
     * 99 color: false
     * 100 color: false
     * @param args
     */
    public static void main(String...args) {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        for(Integer i = 1; i <= 5; i++) rbt.insert(i);
        for(Integer j = 100; j > 90; j--) rbt.insert(j);

        System.out.println("RBT in order. (false = BLACK, true = RED): ");
        rbt.inOrderTraversal();

        ArrayList<Node> list = new ArrayList<>();
        list.add(rbt.root);
        rbt.levelOrder(list);
    }

} // end class
