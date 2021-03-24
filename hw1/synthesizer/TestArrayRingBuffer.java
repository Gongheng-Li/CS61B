package synthesizer;

import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnqueueDequeuePeek() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        assertEquals((Integer) 2, arb.peek());
        assertEquals((Integer) 2, arb.dequeue());
        arb.enqueue(5);
        assertEquals(3, arb.fillCount());
        assertEquals((Integer) 3, arb.dequeue());
    }

    @Test
    public void testEmpty() {
        ArrayRingBuffer<String> strDequeue = new ArrayRingBuffer<>(10);
        String peekEmptyError1="", peekEmptyError2="", dequeueEmptyError1="", dequeueEmptyError2="";
        try {
            strDequeue.peek();
        } catch (RuntimeException e) {
            peekEmptyError1 = e.getMessage();
        }
        try {
            strDequeue.dequeue();
        } catch (RuntimeException e) {
            dequeueEmptyError1 = e.getMessage();
        }
        assertEquals("Ring Buffer Underflow", peekEmptyError1);
        assertEquals("Ring Buffer Underflow", dequeueEmptyError1);

        strDequeue.enqueue("Hello");
        strDequeue.enqueue("World!");
        strDequeue.dequeue();
        strDequeue.dequeue();
        try {
            strDequeue.peek();
        } catch (RuntimeException e) {
            peekEmptyError2 = e.getMessage();
        }
        try {
            strDequeue.dequeue();
        } catch (RuntimeException e) {
            dequeueEmptyError2 = e.getMessage();
        }
        assertEquals("Ring Buffer Underflow", peekEmptyError2);
        assertEquals("Ring Buffer Underflow", dequeueEmptyError2);
    }

    @Test
    public void testFull() {
        String fullError = "No Error.";
        ArrayRingBuffer<Integer> intArray = new ArrayRingBuffer<>(3);
        intArray.enqueue(5);
        try {
            intArray.enqueue(6);
        } catch (RuntimeException e) {
            fullError = e.getMessage();
        }
        assertEquals("No Error.", fullError);
        intArray.enqueue(7);
        try {
            intArray.enqueue(8);
        } catch (RuntimeException e) {
            fullError = e.getMessage();
        }
        assertEquals("Ring Buffer Overflow", fullError);
    }

    @Test
    public void testIterator() {
        int[] expected = new int[]{2, 3, 4, 5};
        int i = 0;
        ArrayRingBuffer<Integer> intBuffer = new ArrayRingBuffer<>(10);
        intBuffer.enqueue(2);
        intBuffer.enqueue(3);
        intBuffer.enqueue(4);
        intBuffer.enqueue(5);

        for(int elem : intBuffer) {
            assertEquals(expected[i], elem);
            i += 1;
        }
    }

//    /** Calls tests for ArrayRingBuffer. */
//    public static void main(String[] args) {
//        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
//    }
} 
