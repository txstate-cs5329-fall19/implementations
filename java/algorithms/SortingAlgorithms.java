package algorithms;

import java.util.ArrayList;

public interface SortingAlgorithms<T> {
    long bubbleSort();
    long insertionSort();
    void selectionSort();
    long mergeSort(int low, int high);
    long quickSort(int lowIndex, int highIndex);
    void heapSort();
    void radixSort();


}
