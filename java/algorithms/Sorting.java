package algorithms;

import datastructures.BasicHeap;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

/**
 *
 * {@code compareTo()} returns a negative int if this.object is less than, zero if equal, or a positive if greater than
 * the specified object.
 *
 * {@code <? super T>} T is is the lower bound for the wildcard ?
 * {@code extends Comparable<? super T>} type T must implement Comparable of T or one of its super classes
 *
 * Use @Override annotation to take advantage of the compiler checking to make sure you actually are overriding a method.
 *
 * @param <T>
 */
public class Sorting<T extends Comparable<? super T>>  implements SortingAlgorithms<T> {

    private ArrayList<T> objList;

    Sorting(ArrayList<T> unsortedList) {
        this.objList = unsortedList;
    }

    void setObjList(ArrayList<T> objList) {
        this.objList = new ArrayList<>(objList);
    }

    ArrayList getObjList() {
        return this.objList;
    }

    /**
     * O(n^2) sorting algorithm, due to number of passes
     */
    @Override
    public long bubbleSort() {
        Instant start = Instant.now();

        boolean swapPerformed;

        do {
            swapPerformed = false;

            for (int i = 0; i < objList.size() - 1; i++) {
                if (objList.get(i).compareTo(objList.get(i + 1)) > 0) {
                    T tmp = objList.get(i + 1);
                    objList.set(i + 1, objList.get(i));
                    objList.set(i, tmp);

                    swapPerformed = true;
                }
            }
        } while (swapPerformed);

        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }

    /**
     * O(n^2) sorting algorithm, due to nested loops
     */
    @Override
    public long insertionSort() {
        Instant start = Instant.now();
        for (int i = 1; i < objList.size(); i++) {
            T key = objList.get(i);
            int j = i - 1;

            while ((j >= 0) && objList.get(j).compareTo(key) > 0) {
                objList.set(j + 1, objList.get(j));
                j = j - 1;
            }
            objList.set(j + 1, key);
        }
        Instant finish = Instant.now();

        return Duration.between(start, finish).toMillis();
    }

    /**
     *
     */
    @Override
    public long selectionSort() {
        Instant start = Instant.now();
        for (int i = 0; i < objList.size()-1; i++) {
            int min = i;
            for (int j = i + 1; j < objList.size(); j++) {
                if (objList.get(j).compareTo(objList.get(min)) < 0) {
                    min = j;
                }
            }
            T tmp = objList.get(i);
            objList.set(i, objList.get(min));
            objList.set(min, tmp);
        }
        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }

    /**
     * O(n log n) divide and conquer algorithm
     * Data splitting means it can be made parallel
     */
    @Override
    public long mergeSort(int low, int high) {
        Instant start = Instant.now();
        long dur = 0L;
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(low, mid);
            mergeSort(mid + 1, high);
            dur = merge(low, mid, high);
        }
        Instant finish = Instant.now();

        return dur + Duration.between(start, finish).toMillis();
    }

    long merge(int low, int mid, int high) {
        Instant start = Instant.now();

        int N = high - low + 1;
        ArrayList<Integer> tmpList = new ArrayList<>(N); // init. capacity of N
        for (int i = 0; i < N; i++) { tmpList.add(0); }

        int left = low;
        int right = mid + 1;
        int bIdx = 0;

        while (left <= mid && right <= high) { // the merging
            int x = objList.get(left).compareTo(objList.get(right));
            //System.out.println(bIdx + "|" + objList.get(left) + "|" + objList.get(right) + "|" + x);  // for DEBUG
            tmpList.set(bIdx++, (Integer) (x <= 0 ? objList.get(left++) : objList.get(right++)));
        }
        while (left <= mid) {
            tmpList.set(bIdx++, (Integer) objList.get(left++));
        }
        while (right <= high) {
            tmpList.set(bIdx++, (Integer) objList.get(right++));
        }
        for (int k = 0; k < N; k++) {
            objList.set(low + k, (T) tmpList.get(k));
        }

        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }

    /**
     *
     * @param lowIndex
     * @param highIndex
     * @return
     */
    @Override
    public long quickSort(int lowIndex, int highIndex) {
        Instant start = Instant.now();
        if (lowIndex < highIndex) {
            int pivot = partition(lowIndex, highIndex);
            quickSort(lowIndex, pivot - 1);
            quickSort(pivot + 1, highIndex);
        }
        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }

    private int partition(int low, int high) {
        T x = objList.get(high);
        int i = low - 1;
        for (int j = low; j < high ; j++) {
            if (objList.get(j).compareTo(x) <= 0) {
                i++;
                T temp = objList.get(i);
                objList.set(i, objList.get(j));
                objList.set(j, temp);
            }
        }
        x = objList.get(i + 1);
        objList.set(i + 1, objList.get(high));
        objList.set(high, x);

        return i + 1;
    }


    /**
     * Sort an ArrayList using the heap datastructure
     * An in-place sorting algorithm with O(n log n) runtime
     * @return the duration in milliseconds
     */
    @Override
    public long heapSort() {
        Instant start = Instant.now();

        BasicHeap<T> heap = new BasicHeap<>(objList);
        heap.buildMaxHeap();
        System.out.println("heap: " + heap.toString());

        this.objList = heap.heapSort();

        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }

    /**
     *
     */
    @Override
    public void radixSort() {

    }
} // end class
