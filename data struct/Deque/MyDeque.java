import java.util.Iterator;

public class MyDeque<E> implements Deque<E> {
    public static final int CAPACITY = 10;
    private E[] array;
    private int size;
    private int index = -1;

    public MyDeque(int capacity) {
        array = (E[]) new Object[capacity];
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0){
            return true;
        }
        else {
            return false;
        }


    }

    @Override
    public E first() {
        return array[0];
    }

    @Override
    public E last() {
        return array[size-1];
    }

    @Override
    public void addFirst(E element) {
        /*
        if (size >= array.length) {
            E[] arrayB = (E[]) new Object[size*2];
            for (int j = 0; j < size; j++) {
                arrayB[j] = array[j];
            }
            array = arrayB;
        }*/
        if (array.length == size){
            throw new IllegalStateException();
        }

        if (!isEmpty()){
            for (int k = size - 1; k >= 0; k--) {
                array[k+1] = array[k];
            }
        }

        array[0] = element;
        size++;

    }

    @Override
    public void addLast(E element) {

        if (array.length == size){
            throw new IllegalStateException();
        }
        array[size] = element;
        size++;
    }



    @Override
    public E removeFirst() {
        if (isEmpty()){
            throw new IllegalArgumentException("Empty");
        }
        E data = array[0];
        for (int k = 0; k < size; k++) {
            array[k] = array[k+1];
        }
        array[size] = null;
        size--;
        return data;
    }

    @Override
    public E removeLast() {
        if (isEmpty()){
            throw new IllegalArgumentException("Empty.");
        }
        E data = array[size-1];
        array[size] = null;
        size--;
        return data;
    }

    // Checks if an index input is valid
    private void checkInd(int i, int n) {
        if (i < 0 || i > n) {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    private class ArrayIterator implements Iterator<E> {
        private int j = 0;

        @Override
        public boolean hasNext() {
            return j < size;
        }

        @Override
        public E next() {
            return array[j++];
        }
    }

    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

/*
    public static void main(String[] args) {
        MyDeque<String> deque = new MyDeque<String>(5);

//
//
//        System.out.println("Size: "+deque.size());
//        deque.addFirst("One");
//        deque.addLast("Zeee");
//        System.out.println("First: " + deque.first());
//        System.out.println("Last: " + deque.last());
//        System.out.println("Size: "+deque.size());
//        // System.out.println(deque.);


//        deque.addFirst("Bascos");
//        deque.addLast("Cabelin");
//        sout



    }
*/

}
