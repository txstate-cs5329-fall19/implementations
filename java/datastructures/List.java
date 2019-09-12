package datastructures;

interface List<T> {
    void add(T item);
    void insert(T item, int position);
    T remove();
    T removeAt(int position);
    T get(int position);
    int find(T item);
    int size();
}
