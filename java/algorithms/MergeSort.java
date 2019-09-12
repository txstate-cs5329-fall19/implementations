package algorithms;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Based on https://www.ibm.com/developerworks/java/library/j-jtp03048/index.html
 * Fork-join framework by Doug Lea: http://gee.cs.oswego.edu/dl/papers/fj.pdf
 * Code from: http://blog.quibb.org/2010/03/jsr-166-the-java-forkjoin-framework/
 */
public class MergeSort {

    private static final ForkJoinPool threadPool = new ForkJoinPool();
    private static final int SIZE_THRESHOLD = 16;

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi - lo < SIZE_THRESHOLD) {
            insertionsort(a, lo, hi);
            return;
        }

        Comparable[] tmp = new Comparable[a.length];
        threadPool.invoke(new SortTask(a, tmp, lo, hi));
    }

    /**
     * This class replaces the recursive function that was
     * previously here.
     */
    static class SortTask extends RecursiveAction {
        Comparable[] a;
        Comparable[] tmp;
        int lo, hi;
        public SortTask(Comparable[] a, Comparable[] tmp, int lo, int hi) {
            this.a = a;
            this.lo = lo;
            this.hi = hi;
            this.tmp = tmp;
        }

        @Override
        protected void compute() {
            if (hi - lo < SIZE_THRESHOLD) {
                insertionsort(a, lo, hi);
                return;
            }

            int m = (lo + hi) / 2;
            // the two recursive calls are replaced by a call to invokeAll
            invokeAll(new SortTask(a, tmp, lo, m), new SortTask(a, tmp, m+1, hi));
            merge(a, tmp, lo, m, hi);
        }
    }

    private static void merge(Comparable[] a, Comparable[] b, int lo, int m, int hi) {
        if (a[m].compareTo(a[m+1]) <= 0)
            return;

        System.arraycopy(a, lo, b, lo, m-lo+1);

        int i = lo;
        int j = m+1;
        int k = lo;

        // copy back next-greatest element at each time
        while (k < j && j <= hi) {
            if (b[i].compareTo(a[j]) <= 0) {
                a[k++] = b[i++];
            } else {
                a[k++] = a[j++];
            }
        }

        // copy back remaining elements of first half (if any)
        System.arraycopy(b, i, a, k, j-k);

    }

    private static void insertionsort(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++) {
            int j = i;
            Comparable t = a[j];
            while (j > lo && t.compareTo(a[j - 1]) < 0) {
                a[j] = a[j - 1];
                --j;
            }
            a[j] = t;
        }
    }

    public static void main(String...args) {
        Integer[] unsortedList = {905, 677, 250, 89, 1000, 564, 791, 138, 400, 63, 577, 971, 296, 954, 630, 946, 745, 625,
                323, 779, 258, 262, 272, 485, 951, 72, 411, 890, 588, 815, 748, 308, 48, 675, 906, 955, 620, 715, 193,
                892, 470, 29, 971, 887, 8, 604, 168, 804, 336, 710, 847, 406, 278, 757, 852, 972, 451, 150, 52, 968, 229,
                145, 248, 848, 543, 276, 811, 835, 907, 721, 672, 898, 438, 952, 653, 140, 816, 731, 327, 692, 225, 258,
                420, 435, 556, 57, 563, 356, 168, 866, 368, 74, 576, 910, 159, 515, 213, 25, 421, 306, 257, 501, 966, 706,
                238, 289, 790, 52, 412, 656, 78, 150, 263, 173, 814, 897, 376, 916, 178, 679, 429, 501, 452, 526, 318, 966,
                675, 660, 109, 584, 833, 792, 41, 23, 272, 222, 327, 21, 3, 838, 797, 649, 596, 184, 626, 123, 678, 737,
                490, 1, 468, 589, 284, 187, 551, 53, 314, 775, 545, 696, 661, 719, 221, 364, 968, 18, 365, 592, 128, 822,
                328, 292, 541, 523, 358, 903, 566, 547, 58, 730, 852, 546, 83, 692, 877, 247, 748, 40, 434, 936, 642, 135,
                179, 366, 739, 615, 225, 907, 159, 286, 229, 175, 23, 624, 605, 854, 399, 346, 434, 929, 664, 309, 558, 522,
                50, 974, 844, 205, 489, 746, 851, 653, 740, 469, 557, 263, 368, 62, 44, 514, 243, 348, 394, 579, 743, 725,
                725, 830, 889, 723, 705, 540, 504, 447, 956, 639, 964, 325, 612, 450, 432, 284, 640, 142, 778, 608, 146,
                };
        sort(unsortedList);
        System.out.println(Arrays.toString(unsortedList));
    }
}