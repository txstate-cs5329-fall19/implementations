package algorithms;

import java.util.*;

/**
 *
 */
public class SortingMain {

    private static final int NUM_ITEMS = 100;  // modify to change size of test array

    /**
     * if {@code Sorting<Integer>} is declared as just {@code Sorting}, then compiler will issue warning
     * "unchecked call to member of raw type. More details here:
     * https://stackoverflow.com/questions/38036442/unchecked-call-to-member-of-raw-type?rq=1
     * @param args cli vars passed if needed
     */
    public static void main(String...args) {
        ArrayList<Integer> intList = new ArrayList<>();
        String sort = "";
        long duration;
        for (int i = 0; i < NUM_ITEMS; i++) {
            int x = generateRandomInt();
            intList.add(x);
        }
        ArrayList<Integer> unsortedList = new ArrayList<>(intList);  // preserve unsorted array for other tests
        Sorting<Integer> sorting = new Sorting<>(intList);

        System.out.println("unsorted list to preserve: " + unsortedList.toString() + "\n");

        // BUBBLE SORT
        sort = "bubble";
        System.out.println("original list: " + sorting.getObjList().toString());
        duration = sorting.bubbleSort();
        printSortResults(sort, duration, unsortedList, sorting);

        // INSERTION SORT
        sort = "insertion";
        System.out.println("original list: " + sorting.getObjList().toString());
        duration = sorting.insertionSort();
        printSortResults(sort, duration, unsortedList, sorting);

        // SELECTION SORT
        sort = "selection";
        System.out.println("original list: " + sorting.getObjList().toString());
        duration = sorting.insertionSort();
        printSortResults(sort, duration, unsortedList, sorting);

        // MERGE SORT
        sort = "merge";
        System.out.println("original list: " + sorting.getObjList().toString());
        duration = sorting.mergeSort(0, intList.size()-1);  // low is start index, high is size - 1
        printSortResults(sort, duration, unsortedList, sorting);

        // QUICK SORT
        sort = "quick";
        System.out.println("original list: " + sorting.getObjList().toString());
        duration = sorting.quickSort(0, intList.size() - 1);  // low is start index, high is size - 1
        printSortResults(sort, duration, unsortedList, sorting);

        // HEAP SORT
        sort = "heap";
        System.out.println("original list: " + sorting.getObjList().toString());
        duration = sorting.heapSort();  // low is start index, high is size - 1
        printSortResults(sort, duration, unsortedList, sorting);

        // TODO: RADIX SORT


        List<Integer> ints = Arrays.asList(7, 4, 6, 3, 9, 1);
        int k = 2;

        System.out.println("K'th largest element in the array is " +
                FindKthLargest(ints, k));
        // 1,2,3,4 ,5 ,6
        // 1,3,6,10,15,21
        //

        int b = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <= i; j++) {
                b++;
            }
        }
        System.out.println(b);

    }

    private static void printSortResults(String sort, long duration, ArrayList<Integer> unsortedList, Sorting<Integer> sorting) {
        System.out.println(sort + " sorted list: " + sorting.getObjList().toString());
        System.out.println("duration: " + duration + "\n");
        sorting.setObjList(unsortedList);  // set back to unsorted list to clear
    }

    private static int generateRandomInt() {
        Random r = new Random(System.nanoTime());
        return r.nextInt(NUM_ITEMS) + 1;
    }

    private static int findMax(ArrayList<Integer> list) {
        int max = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }

        return max;
    }

    private static int findMin(ArrayList<Integer> list) {
        int min = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < min) {
                min = list.get(i);
            }
        }

        return min;
    }

    // Function to find the K'th largest element in the
    // array using max-heap
    public static int FindKthLargest(List<Integer> ints, int k)
    {
        // create an max-heap using PriorityQueue class from all
        // elements in the list
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        // or pass Comparator.reverseOrder()
        pq.addAll(ints);

        // pop from max-heap exactly (k-1) times
        while (--k > 0) {
            pq.poll();
        }

        // return the root of max-heap
        return pq.peek();
    }
}
