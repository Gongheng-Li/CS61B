package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        } else {
            rb[last] = x;
            last += 1;
            if (last >= capacity) {
                last -= capacity;
            }
            fillCount += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        T returnValue = peek();
        first += 1;
        if (first >= capacity) {
            first -= capacity;
        }
        fillCount -= 1;
        return returnValue;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        } else {
            return rb[first];
        }
    }

    /**
     * Return an iterator of the ring buffer.
     */
    @Override
    public Iterator<T> iterator() {
        return new RingBufferIterator();
    }

    /**
     * An inner class that implements Iterator to make our own iterator for the ring buffer class.
     */
    private class RingBufferIterator implements Iterator<T> {

        private int ptr;

        public RingBufferIterator() {
            ptr = first;
        }

        @Override
        public boolean hasNext() {
            return !(ptr == last);
        }

        @Override
        public T next() {
            T returnValue = rb[ptr];
            ptr += 1;
            if (ptr >= capacity) {
                ptr -= capacity;
            }
            return returnValue;
        }
    }
}
