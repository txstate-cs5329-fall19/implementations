package datastructures;

import java.util.ArrayList;
import java.util.Arrays;

public class BasicHeap<T extends Comparable<? super T>> implements Heap<T> {

    private ArrayList<T> heap;
    private int size;

    public BasicHeap(ArrayList<T> list) {
        this.heap = list;
        this.size = list.size();
    }

    @Override
    public void insert(T item) {
        heap.add(item);
        size++;
    }

    @Override
    public T pop() {
        T root = heap.get(0);
        deleteRoot();
        return root;
    }

    private void deleteRoot() {
        T lastItem = heap.get(getSize() - 1);
        heap.set(0, lastItem);
        size--;
        maxHeapify(0);
    }

    @Override
    public void maxHeapify(int index) {
        int l = left(index);
        int r = right(index);
        int largest = index;

        if (l < getSize() && heap.get(l).compareTo(heap.get(largest)) > 0) {
            largest = l;
        }

        if (r < getSize() && heap.get(r).compareTo(heap.get(largest)) > 0) {
            largest = r;
        }

        if (largest != index) {
            T tmp = heap.get(index);
            heap.set(index, heap.get(largest));
            heap.set(largest, tmp);
            maxHeapify(largest);
        }
    }

    @Override
    public void buildMaxHeap() {
        for (int i = getSize() / 2; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    @Override
    public int left(int i) {
        return (i * 2) + 1;
    }

    @Override
    public int right(int i) {
        return (i * 2) + 2;
    }

    @Override
    public int parent(int i) {
        return i / 2;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return Arrays.toString(heap.toArray());
    }

    public ArrayList<T> heapSort() {

        for (int i = getSize() - 1; i > 0; i--) {
            T tmp = heap.get(0);
            heap.set(0, heap.get(i));
            heap.set(i, tmp);
            setSize(getSize() - 1);
            maxHeapify(0);
        }

        return heap;
    }
} // end class
