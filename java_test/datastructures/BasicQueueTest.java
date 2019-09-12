package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Generic type arguments must be reference types.
 * Since primitives do not extend Object they cannot be used as generic type arguments for a parametrized type.
 *
 * Junit5: https://junit.org/junit5/docs/current/user-guide/
 */
class BasicQueueTest {
    private BasicQueue<Integer> myQueue;

    @BeforeEach
    void setUp() {
        myQueue = new BasicQueue<>(5);
        myQueue.enQueue(5);
        myQueue.enQueue(6);
        myQueue.enQueue(10);
    }

    @Test
    void testEmptySize() {
        while (myQueue.size() > 0) {
            myQueue.deQueue();
        }
        assertEquals(0, myQueue.size());
    }

    @Test
    void testEnqueueAndSize() {

        assertEquals(3, myQueue.size());
    }

    @Test
    void testDequeue() {
        int x = myQueue.deQueue();
        assertEquals(5, x);
    }

    @Test
    void testContains() {
        assertTrue(myQueue.contains(10));
    }

    @Test
    void testAccess() {
        int x = myQueue.access(1);
        assertEquals(6, x);
    }

    @Test
    void testToString() {
        assertEquals("[5, 6, 10]", myQueue.toString());
    }
}