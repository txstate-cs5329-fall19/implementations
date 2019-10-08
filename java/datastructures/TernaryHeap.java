package datastructures;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @param <T>
 */
public class TernaryHeap<T extends Comparable<? super T>> implements Heap<T> {

    private static final int NUM_LEAFS = 3;
    private ArrayList<T> heap;
    private int size;

    public TernaryHeap(ArrayList<T> list) {
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
        int m = middle(index);
        int r = right(index);
        int largest = index;

        if (l < getSize() && heap.get(l).compareTo(heap.get(largest)) > 0) {
            largest = l;
        }

        if (m < getSize() && heap.get(m).compareTo(heap.get(largest)) > 0) {
            largest = m;
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
        for (int i = getSize() / NUM_LEAFS; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    public boolean isTernaryHeap(int i) {
        int n = this.heap.size() - 1;
        if (i > (n - NUM_LEAFS)/NUM_LEAFS)
            return true;

        if (this.heap.get(i).compareTo(this.heap.get(left(i))) >= 0
                && this.heap.get(i).compareTo(this.heap.get(middle(i))) >= 0
                && this.heap.get(i).compareTo(this.heap.get(right(i))) >= 0
                && isTernaryHeap(left(i))
                && isTernaryHeap(middle(i))
                && isTernaryHeap(right(i)))
            return true;

        return false;
    }

    @Override
    public int left(int i) {
        return NUM_LEAFS * i + 1;
    }

    public int middle(int i) {
        return NUM_LEAFS * i + 2;
    }

    @Override
    public int right(int i) {
        return NUM_LEAFS * i + NUM_LEAFS;
    }

    @Override
    public int parent(int i) {
        return (i - 1) / NUM_LEAFS;
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

    public static void main(String...args) {
        Integer[] array = {5, 7, 3, 9, 4, 1, 11, 15, 7, 8, 2, 10, 14};
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(array));

        TernaryHeap<Integer> heap = new TernaryHeap<>(arrayList);
        System.out.println(heap.toString());
        System.out.println(heap.isTernaryHeap(0));
        heap.buildMaxHeap();
        System.out.println(heap.toString());
        System.out.println(heap.isTernaryHeap(0));
    }
}
