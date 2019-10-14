package datastructures;

/**
 * Implemented from PluralSight:
 * https://app.pluralsight.com/library/courses/java-data-structures-implementing-understanding/
 * @param <T>
 */
public class BasicTree<T extends Comparable<? super T>> implements Tree<T> {

    private Node root;
    private int size;

    public BasicTree() {
        this.root = null;
    }

    public int size() {
        return size;
    }

    public void add(T item) {
        Node node = new Node(item);

        // if this is the first item (root is null), set as root
        if (root == null) {
            this.root = node;
            this.size++;
        }
        // otherwise we insert the item in the tree using the binary tree insert algorithm
        else {
            insert(this.root, node);
        }
    }

    public void insert(Node parent, Node child) {
        // if child < parent, it goes left
        if (child.getItem().compareTo(parent.getItem()) < 0) {
            // if the left node is null, we insert at this spot
            if (parent.getLeft() == null) {
                parent.setLeft(child);
                child.setParent(parent);
                this.size++;
            }
            // otherwise we call insert again and test for left or right
            else {
                insert(parent.getLeft(), child);
            }
        }
        // if the child > parent, it goes right
        else if (child.getItem().compareTo(parent.getItem()) > 0) {
            // if the right node is null, we insert at this spot
            if (parent.getRight() == null) {
                parent.setRight(child);
                child.setParent(parent);
                this.size++;
            }
            // otherwise we call insert again and test for left or right
            else {
                insert(parent.getRight(), child);
            }
        }

        // if child == parent, we do nothing; data is assumed to be unique and value exists
    }
}
