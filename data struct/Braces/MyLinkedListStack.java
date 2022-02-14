/**
 * @author Comeros, Raul Jr.
 */

package Lab12;

public class MyLinkedListStack {
    private Node head;
    private Node tail;
    private int size;

    /**
     * NOTE: A given method. Modify at your own risk.
     * Default constructor.
     */
    MyLinkedListStack() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * NOTE: A given method. Modify at your own risk.
     * Returns the first node (head) of the linked list.
     * @return the first node.
     */
    public Node getHead() {
        return head;
    }

    /**
     * NOTE: A given method. Modify at your own risk.
     * Returns the last node (tail) of the linked list.
     * @return the last node.
     */
    public Node getTail() {
        return tail;
    }

    /**
     * NOTE: A given method. Modify at your own risk.
     * Adds a node at the head.
     * @param str the element of the new node to be added.
     */
    public void addHead(String str) {
        Node node = new Node(str);
        if (head != null) {
            node.setNext(head);
        } else {
            tail = node;
        }
        head = node;
        size++;
    }

    /**
     * NOTE: A given method. Modify at your own risk.
     * Adds a node at the tail.
     * @param str the element of the new node to be added.
     */
    public void addTail(String str) {
        Node node = new Node(str);
        node.setNext(null);
        if (tail != null) {
            tail.setNext(node);
        } else {
            head = node;
        }
        tail = node;
        size++;
    }

    /**
     * NOTE: A given method. Modify at your own risk.
     * Removes the node being pointed by the head.
     * @throws Exception if head is pointing to null (i.e. there are no nodes).
     */
    public void removeHead() {
        if (head == null) {
            throw new IllegalArgumentException();
        }
        head = head.getNext();
        size--;
    }

    public void removeTail() throws Exception {
        if (tail == null) {
            throw new Exception("Tail has not been added yet");
        }
        Node n = head;
        while(n.getNext()==tail){
            n = n.getNext();
        }
        tail = n;
        size--;
    }



    /**
     * Adds a node at a particular position. For instance, the position is 1, the node should be the 1st node.
     * If position == 2, the node should be the 2nd node, and so on.
     * @param str the element of the node to be added.
     * @param position the supposed position of the newly added node in the list.
     * @throws IndexOutOfBoundsException if the position exceeds possible levels (i.e. if the size is 4, the maximum position must be 5 or last).
     */
    public void addNode(String str, int position) throws IndexOutOfBoundsException{
        int count = 1;
        Node node = new Node(str);
        if (position == 1){
            addHead(str);
            size++;
        } else if ((position-1) == size) {
            addTail(str);
            size++;
        } else if (position > 0 && (position-1) < size){
            Node n = head;
            Node n1 = head.getNext();
            while (count != (position-1)) {
                count++;
                n = n.getNext();
                n1 = n.getNext();
            }
            n.setNext(node);
            node.setNext(n1);
            size++;
        } else {
            System.out.println("size: "+size);
            System.out.println("pos: "+position);
            System.out.println("addNode invoked (3)...");
            throw new ArrayIndexOutOfBoundsException();
        }

        }


    /**
     * Removes the first instance of the node which bears the element str.
     * @param str the name which shall be deleted.
     * @throws Exception if the name does not exist.
     */
    public void remove(String str) throws Exception{
        boolean ok = false;
        Node node = head;
        Node node1 = node.getNext();
        int count = 0;

        while (count<size){
            if (node.getElement().equals(str)==true){
                head = node1;
                size--;
                break;
            }
            else if (node1.getElement().equals(str)==true){
               //System.out.println("Removed: "+node1.getElement());
                 if (node1 == tail){
                    node.setNext(null);
                    tail = node;
                    size--;
                    break;
                }
                else {
                    node.setNext(node1.getNext());
                    size--;
                     break;
                }
            }

            node = node.getNext();
            node1 = node.getNext();
            count++;

            if (count==(size)){
                throw new Exception("Element not found");
            }
        }



    }

    /**
     * Moves the first particular node which bears the element name to the specified new position.
     * @param str the element of the node to be moved.
     * @param position the position to which the node should be moved.
     * @throws Exception if the name does not exist.
     */
    public void move(String str, int position) throws Exception {

        if (position==1){
            System.out.println("head");
            remove(str);
            addHead(str);
        }
        else if (position==(size)){
            System.out.println("Tail");
            remove(str);
            addTail(str);
        }
        else if (position > 0 && position < (size)){
            System.out.println("add");
            remove(str);
            addNode(str, position);
        }
        else {
           throw new Exception("Element not found.");
        }
    }
/*

    public String traverse() {
        Node n = head;
        int count = 0;
        String str = "";

        while (n != null) {

            str = str.concat(n.getElement());
            if (n != tail){
                str = str.concat(", ");
            }
            //System.out.printf("Position %d: %s\n", count, n.getElement());
            //System.out.println("Str: "+str);
            n = n.getNext();
            count++;

        }
        return str;
    }*/

    public int getSize(){
        return this.size;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void decSize(){
        this.size -= size;
    }

    public static void main (String[] args) throws Exception {

        MyLinkedListStack sl = new MyLinkedListStack();


        sl.addHead("two");
        sl.addTail("three");
        sl.addHead("one");
        sl.addTail("four");
        sl.addTail("five");
        sl.addTail("six");


        sl.move("six", 1);
        sl.move("one", 6);

        //System.out.println("Traverse: "+sl.traverse());
        //System.out.println(sl.size);
    }
}
