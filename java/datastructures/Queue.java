package datastructures;

public interface Queue<T> {
    void enQueue(T item);
    T deQueue();
    boolean contains(T item);
    T access(int position);
    int size();
}
