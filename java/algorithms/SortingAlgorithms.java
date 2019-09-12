package algorithms;

import java.util.ArrayList;

public interface SortingAlgorithms<T> {
    long bubbleSort();
    long insertionSort();
    void selectionSort();
    long mergeSort(int low, int high);
    void quickSort();
    void heapSort();
    void radixSort();


}
