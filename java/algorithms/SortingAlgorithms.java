package algorithms;

public interface SortingAlgorithms<T> {
    long bubbleSort();
    long insertionSort();
    long recursiveInsertionSort(int n);
    long selectionSort();
    long mergeSort(int low, int high);
    long quickSort(int lowIndex, int highIndex);
    long heapSort();
    void radixSort();


}
