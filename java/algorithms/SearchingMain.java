package algorithms;

import java.util.ArrayList;
import java.util.Random;

public class SearchingMain {

    public static void main(String...args) {
        ArrayList<Integer> list = new ArrayList<>();
        Random r = new Random(System.nanoTime());

        for (int i = 0; i < 1001; i++) {
            list.add(r.nextInt(1000) + 1);
        }

        System.out.println(list.toString()); // unsorted
        Searching<Integer> searching = new Searching<>(list);
        System.out.println(list.toString()); // sorted

        int searchItem = 598;
        int index = searching.binarySearch(0, list.size(), searchItem);
        System.out.println("Index is -1 if not found, other if found: " + index);
        System.out.println("Check list at index " + index + ", value is " + list.get(index) + ". Matches (true or false): " + (list.get(index) == searchItem ? "true" : "false"));
    }
}
