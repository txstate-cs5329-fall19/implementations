package datastructures;

public class DataStructuresMain {

    public static void main(String...args) {
        // BASIC LIST
        System.out.println("BASIC LIST");
        BasicList<Integer> myBasicList = new BasicList<>();
        myBasicList.add(1);
        myBasicList.add(3);
        myBasicList.add(4);
        myBasicList.add(5);
        myBasicList.add(6);

        System.out.println(myBasicList.toString());
        myBasicList.insert(2,1);
        System.out.println(myBasicList.toString());

        myBasicList.removeAt(5);
        System.out.println(myBasicList.toString());
        myBasicList.remove();
        System.out.println(myBasicList.toString());

        // BASIC QUEUE
        System.out.println("BASIC QUEUE");
        BasicQueue<Integer> myQueue = new BasicQueue<>(2);
        System.out.println("length: " + myQueue.getLength());
        System.out.println("size: " + myQueue.size());
        System.out.println("Print queue: " + myQueue.toString());
        myQueue.enQueue(1);
        myQueue.enQueue(2);
        System.out.println("Print queue: " + myQueue.toString());
        System.out.println("size: " + myQueue.size());
        System.out.println("contains 1? " + myQueue.contains(1));
        myQueue.deQueue();
        System.out.println("size: " + myQueue.size());
        int position = 1;
        System.out.println("item at pos." + position + " is: " + myQueue.access(position));
        System.out.println("contains 1? " + myQueue.contains(1));
    }
}
