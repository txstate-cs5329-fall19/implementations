package algorithms;

import java.util.*;
import java.util.concurrent.*;

/**
 * Author: Jeffrey Phillips Freeman
 * Concurrent, in-place Java merge-sort algorithm using the fork-join paradigm (Java 8+)
 * https://stackoverflow.com/questions/50036207/how-to-implement-a-multi-threaded-mergesort-in-java
 * @param <N>
 */
public class ConcurrentMergeSort<N extends Comparable<N>> extends RecursiveTask<List<N>> {
    private List<N> elements;

    public ConcurrentMergeSort(List<N> elements) {
        this.elements = elements;
    }

    @Override
    protected List<N> compute() {
        if(this.elements.size() <= 1)
            return this.elements;
        else {
            final int pivot = this.elements.size() / 2;
            ConcurrentMergeSort<N> leftTask = new ConcurrentMergeSort<N>(this.elements.subList(0, pivot));
            ConcurrentMergeSort<N> rightTask = new ConcurrentMergeSort<N>(this.elements.subList(pivot, this.elements.size()));

            leftTask.fork();
            rightTask.fork();

            List<N> left = leftTask.join();
            List<N> right = rightTask.join();

            merge(left, right);
            return this.elements;
        }
    }

    private void merge(List<N> left, List<N> right) {
        int leftIndex = 0;
        int rightIndex = 0;
        while(leftIndex < left.size() ) {
            if(rightIndex == 0) {
                if( left.get(leftIndex).compareTo(right.get(rightIndex)) > 0 ) {
                    swap(left, leftIndex++, right, rightIndex++);
                } else {
                    leftIndex++;
                }
            } else {
                if(rightIndex >= right.size()) {
                    if(right.get(0).compareTo(left.get(left.size() - 1)) < 0 )
                        merge(left, right);
                    else
                        return;
                }
                else if( right.get(0).compareTo(right.get(rightIndex)) < 0 ) {
                    swap(left, leftIndex++, right, 0);
                } else {
                    swap(left, leftIndex++, right, rightIndex++);
                }
            }
        }

        if(rightIndex < right.size() && rightIndex != 0)
            merge(right.subList(0, rightIndex), right.subList(rightIndex, right.size()));
    }

    private void swap(List<N> left, int leftIndex, List<N> right, int rightIndex) {
        //N leftElement = left.get(leftIndex);
        left.set(leftIndex, right.set(rightIndex, left.get(leftIndex)));
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ArrayList<Integer> list = new ArrayList<>();
        Random r = new Random(System.nanoTime());
        for (int i = 0; i < 1000000; i++) {
            list.add(r.nextInt(1000000) + 1);
        }
        List<Integer> result = forkJoinPool.invoke(new ConcurrentMergeSort<Integer>(list));
        System.out.println("result: " + result);
    }
}