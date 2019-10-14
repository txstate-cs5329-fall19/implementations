package datastructures;

public class Node<T extends Comparable<? super T>> {
    Node left;
    Node right;
    Node parent;
    boolean color;
    T item;

    public Node() {
        this.item = null;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = false; // black
    }

    public Node(T item) {
        this();
        this.item = item;
    }

    public Node(T item, boolean color) {
        this(item);
        this.color = color;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
