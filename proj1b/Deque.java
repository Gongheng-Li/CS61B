public interface Deque<T> {

    public boolean isEmpty();

    public int size();

    public void addFirst(T x);

    public void addLast(T x);

    public T removeFirst();

    public T removeLast();

    public T get(int index);

    public void printDeque();
}
