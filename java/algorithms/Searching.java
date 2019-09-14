package algorithms;

import java.util.ArrayList;

public class Searching<T extends Comparable<? super T>> implements SearchingAlgorithms<T> {
    private ArrayList<T> list;
    Sorting<T> sorting;

    Searching(ArrayList<T> list) {
        this.list = list;
        sorting = new Sorting<T>(list);
        sorting.mergeSort(0, list.size()-1);
    }

    /**
     * O(log n) recursive search algorithm
     * @param leftIndex
     * @param rightIndex
     * @param searchItem
     * @return
     */
    @Override
    public int binarySearch(int leftIndex, int rightIndex, T searchItem) {

        if (rightIndex >= leftIndex) {
            int mid = ((rightIndex - 1) + leftIndex) / 2; // minus 1 b/c of 0 based indexing

            // base case if found
            if(list.get(mid).compareTo(searchItem) == 0) {
                return mid;
            } else if (list.get(mid).compareTo(searchItem) > 0) {
                return binarySearch(leftIndex, mid - 1, searchItem);
            } else {
                return binarySearch(mid + 1, rightIndex, searchItem);
            }
        }

        return -1; // not found
    }
}
