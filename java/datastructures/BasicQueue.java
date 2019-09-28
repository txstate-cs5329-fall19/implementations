package datastructures;

import java.util.ArrayList;

/**
 * First-In, First-Out data structure
 * @param <T>
 */
public class BasicQueue<T extends Comparable<? super T>> implements Queue<T> {
    private T[] data;
    private int front;
    private int end;

    public BasicQueue() {
        this(1000);
    }

    public BasicQueue(int size) {
        this.front = -1;
        this.end = -1;
        // https://stackoverflow.com/questions/34827626/cannot-be-cast-to-ljava-lang-comparable
        // must be of type comparable
        data = (T[]) new Comparable[size];
    }

    public int size() {
        if (front == -1 && end == -1) {
            return 0;
        } else {
            return end - front + 1;
        }
    }

    public void enQueue(T item) {
        if ((end + 1) % data.length == front) {
            System.out.println("The BasicQueue is full!");
        } else if (size() == 0) {
            front++;
            end++;
            data[end] = item;
        }
        else {
            end++;
            data[end] = item;

        }
    }

    public T deQueue() {
        T item = null;

        if (size() == 0) {
           System.out.println("Can't dequeue because queue is empty!");
        } else if (front == end) {
            item = data[front];
            front = -1;
            end = -1;
        }
        else {
            item = data[front++];
        }

        return item;
    }

    int getLength() {
        return data.length;
    }

    public boolean contains (T item) {
        for (int i = front; i < getLength(); i++) {
            if (data[i] == item) {
                return true;
            }
        }
        return false;
    }

    public T access (int position) {
        if (position < 0 || position >= size()) {
            System.out.println("invalid position");
            return null;
        } else {
            return data[front + position];
        }
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder();

        if (front == -1 && end == -1) {
            listString = new StringBuilder("The BasicQueue is empty.");
        } else {
            listString.append("[");
            for (int i = 0; i < size(); i++) {
                listString.append(data[i]);
                if (i + 1 != size()) {
                    listString.append(", ");
                } else {
                    listString.append("]");
                }
            }
        }

        return listString.toString();
    }
}
