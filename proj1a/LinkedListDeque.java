/**
 * Double ended link list class.
 * @param <datatype> type of item in the link list
 * @author Li Gongheng
 */

public class LinkedListDeque<datatype>{
    private int size;
    private Node sentinel;

    private class Node{
        public Node prev;
        public datatype item;
        public Node next;

        public Node(){}

        public Node(Node previous_node, datatype node_item, Node next_node){
            prev = previous_node;
            item = node_item;
            next = next_node;
        }
    }

    public LinkedListDeque(){
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(datatype x){
        sentinel.next = new Node(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public datatype removeFirst(){
        datatype temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return temp;
    }

    public void addLast(datatype x){
        sentinel.prev = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public datatype removeLast(){
        datatype temp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return temp;
    }

    public datatype get(int index){
        Node p = sentinel.next;
        while(index != 0){
            p = p.next;
            index--;
        }
        return p.item;
    }

    private datatype getRecursiveFromNode(int index, Node n){
        if(index == 0){
            return n.item;
        }
        return getRecursiveFromNode(index-1, n.next);
    }

    public datatype getRecursive(int index){
        return getRecursiveFromNode(index, sentinel.next);
    }

    public void printDeque(){
        Node n = sentinel.next;
        if(n == sentinel){
            return;
        }
        System.out.print(n.item);
        n = n.next;
        while(n != sentinel){
            System.out.print(" " + n.item);
            n = n.next;
        }
        System.out.println();
    }
}