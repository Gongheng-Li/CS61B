public class ArrayDeque<datatype>{
    private int size;
    private int front_ptr, end_ptr;
    private datatype[] items;

    public ArrayDeque(){
        items = (datatype[]) new Object[8];
        size = 0;
        front_ptr = 0;
        end_ptr = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void resize(int capacity){
        datatype[] new_items = (datatype[]) new Object[capacity];
        if(front_ptr < end_ptr){
            System.arraycopy(items, front_ptr, new_items, 0, end_ptr-front_ptr);
        }
        else{
            System.arraycopy(items, front_ptr, new_items, 0, items.length-front_ptr);
            System.arraycopy(items, 0, new_items, items.length-front_ptr, end_ptr);
        }
        items = new_items;
        front_ptr = 0;
        end_ptr = size;
    }

    public void addFirst(datatype x){
        if(size == items.length){
            resize(items.length * 2);
        }
        front_ptr -= 1;
        if(front_ptr < 0){
            front_ptr += items.length;
        }
        items[front_ptr] = x;
        size += 1;
    }

    public void addLast(datatype x){
        if(size == items.length){
            resize(items.length * 2);
        }
        items[end_ptr] = x;
        end_ptr +=1;
        if(end_ptr >= items.length){
            end_ptr -= items.length;
        }
        size += 1;
    }

    public datatype removeFirst(){
        datatype item = items[front_ptr];
        items[front_ptr] = null;
        front_ptr += 1;
        if(front_ptr >= items.length){
            front_ptr -= items.length;
        }
        size -= 1;
        double usage_factor = (size * 1.0) / items.length;
        if(usage_factor <= 0.25 && items.length >= 16){
            resize(items.length/2);
        }
        return item;
    }

    public datatype removeLast(){
        datatype item = items[end_ptr-1];
        items[end_ptr-1] = null;
        end_ptr -= 1;
        if(end_ptr < 0){
            end_ptr += items.length;
        }
        size -= 1;
        double usage_factor = (size * 1.0) / items.length;
        if(usage_factor <= 0.25 && items.length >= 16){
            resize(items.length/2);
        }
        return item;
    }

    public datatype get(int index){
        index = index + front_ptr;
        if(index >= items.length){
            index -= items.length;
        }
        return items[index];
    }

    public void printDeque(){
        int i = front_ptr;
        System.out.print(items[i]);
        i += 1;
        if (front_ptr >= end_ptr) {
            while (i < items.length) {
                System.out.print(" " + items[i]);
                i++;
            }
            i -= items.length;
        }
        while(i < end_ptr){
            System.out.print(" " + items[i]);
            i++;
        }
        System.out.println();
    }
}