package Lab12;

import Stack.Stack;

public class MyLLStack<E> implements Stack<E> {

    //public static final int CAPACITY = 1000;
    private E[] data;
    private int index = -1;


    private Node head;
    private Node tail;


    public MyLLStack(){
        head = null;
        tail = null;
    }



    @Override
    public void push(E element) {
        if (size() == data.length){
            throw new IllegalStateException();
        }
        data[++index] = element;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E top() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public static void main(String[] args) {

    }
}
