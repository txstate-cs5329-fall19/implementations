package algorithms;

public interface SortingAlgorithms<T> {
    long bubbleSort();
    long insertionSort();
    long selectionSort();
    long mergeSort(int low, int high);
    long quickSort(int lowIndex, int highIndex);
    long heapSort();
    void radixSort();


}
