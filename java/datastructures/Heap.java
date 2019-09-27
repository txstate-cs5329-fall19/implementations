package datastructures;

public interface Heap<T> {
    void insert(T item);
    T pop();

    void maxHeapify(int index);
    void buildMaxHeap();
    int left(int i);
    int right(int i);
    int parent(int i);
    int getSize();
    void setSize(int size);
}
