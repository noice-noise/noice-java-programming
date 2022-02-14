package Lab12;

public class MyStack<E> extends MyLinkedListStack implements Stack<E>  {

    private MyLinkedListStack data = new MyLinkedListStack();
    //private int index = -1;

    public MyStack(){

    }

    @Override
    public void push(E element) {
        //data[++index] = element;
        data.addHead((String)element);
        //System.out.println("Push: "+(String)element);
    }

    @Override
    public E pop() {
        if (isEmpty()){
            return null;
        }
        //E ret = data[index];
        //TODO Remove head to decrease size
        //data.removeHead((top());
        E ret = (E)data.getHead().getElement();
        data.removeHead();
        //System.out.println("Pop: "+ret);


        //data[index] = null;
        //index--;
       // System.out.println("ret: "+ret);
        //System.out.println("indexPop: " + index);

        //data.decSize();
        return ret;
    }

    @Override
    public E top() {
        if (isEmpty()){
            return null;
        }
        //return data[index];
        return (E)data.getHead().getElement();
    }

    @Override
    public int size() {
        //System.out.println("Size: "+data.getSize());
        return data.getSize();

    }

    @Override
    public boolean isEmpty() {
        if (size()==0){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        //MyStack<MyLinkedListStack> stack = new MyStack<MyLinkedListStack>();

        MyStack<String> stack = new MyStack<String>();

        stack.push("Annona");
        stack.push("Solanum");

        System.out.println(stack.pop());

        stack.push("Eve");
        stack.push("Gageue");

        System.out.println(stack.pop());

    }
}
