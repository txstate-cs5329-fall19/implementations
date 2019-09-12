package datastructures;

public class BasicList<T> implements List<T> {
    private Node first;
    private Node last;
    private int nodeCount;

    public BasicList() {
        first = null;
        last = null;
        nodeCount = 0;
    }


    /**
     * appends new node to the end of the list
     * @param item
     */
    public void add(T item) {
        Node newNode = new Node(item);
        if (first == null && last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.setNextNode(newNode);
            last = newNode;
        }
        nodeCount++;
    }

    /**
     * Remove first node off the list
     * @return
     */
    public T remove() {
        if (first == null) {
            throw new IllegalStateException("The List is empty - no items to remove.");
        }

        T removedNodeItem = first.getNodeItem();
        first = first.getNextNode();
        nodeCount--;
        return removedNodeItem;
    }

    public void insert(T item, int position) {
        if (size() < position) {
            throw new IllegalStateException("Error: The List is smaller than the position you are attempting to access.");
        }

        Node currentNode = first;

        //start at 1 because we are on the first node
        for (int i = 1; i < position && currentNode != null; i++) {
            currentNode = currentNode.getNextNode();
        }

        //severs the link chain and reconnects with the new node
        Node newNode = new Node(item);
        Node nextNode = currentNode.getNextNode();
        currentNode.setNextNode(newNode);
        newNode.setNextNode(nextNode);
        nodeCount++;
    }

    public T removeAt(int position){
        if (first == null) {
            throw new IllegalStateException("Error: The List is empty. Nothing to remove.");
        } else if (size() < position) {
            throw new IllegalStateException("Error: The List is smaller than the position you are attempting to access.");
        }

        Node currentNode = first;
        Node prevNode = first;

        for (int i = 1; i < position && currentNode != null; i++) {
            prevNode = currentNode;
            currentNode = currentNode.getNextNode();
        }

        T nodeItem = currentNode.getNodeItem();
        prevNode.setNextNode(currentNode.getNextNode());
        nodeCount--;
        return nodeItem;
    }

    public T get(int position){
        if (first == null) {
            throw new IllegalStateException("Error: The List is empty. Nothing to remove.");
        } else if (size() < position) {
            throw new IllegalStateException("Error: The List is smaller than the position you are attempting to access.");
        }

        Node currentNode = first;
        for (int i = 1; i < size() && currentNode != null; i++) {
            if (i == position) {
                return currentNode.getNodeItem();
            }
        }

        return null; // did not find it
    }

    /**
     * returns the position of an item if it exists
     * @param item
     * @return
     */
    public int find(T item){
        if (first == null) {
            throw new IllegalStateException("Error: The List is empty. Nothing to remove.");
        }

        Node currentNode = first;
        for(int i = 1; i < size(); i++) {
            if (currentNode.getNodeItem().equals(item)) {
                return i;
            }
            currentNode = currentNode.getNextNode();
        }

        return -1; // did not find it
    }

    public int size(){
        return nodeCount;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();

        Node currentNode = first;
        returnString.append("[");
        for (int i = 1; i <= size(); i++) {
            returnString.append(currentNode.getNodeItem());
            currentNode = currentNode.getNextNode();
            if (i + 1 <= size()) {
                returnString.append(", ");
            } else {
                returnString.append("]");
            }
        }


        return returnString.toString();
    }

    private class Node {
        private Node nextNode;
        private T nodeItem;

        Node(T item) {
            this.nextNode = null;
            this.nodeItem = item;
        }

        void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        Node getNextNode() {
            return nextNode;
        }

        T getNodeItem() {
            return nodeItem;
        }
    }
}
