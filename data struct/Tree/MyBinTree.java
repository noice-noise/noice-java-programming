package Lab17;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class MyBinTree<E> extends AbstractBinaryTree<E> {

    protected Position<E> root;
    private int size = 0;
    //Stack<Position<E>> posStack = new Stack<>();


    /**
     * Returns the left child of the node in the position p.
     * @param p the node whose left child is to be returned.
     * @return the position of the left child.
     * @throws IllegalArgumentException if p is invalid.
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        if (p==null || p.getElement()==null){
            throw new IllegalArgumentException("Position invalid.");
        }

        Node pos = validate(p);
        return pos.getLeftChild();
    }

    /**
     * Returns the right child of the node in the position p.
     * @param p the node whose right child is to be returned.
     * @return the position of the right child.
     * @throws IllegalArgumentException if p is invalid.
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        if (p==null || p.getElement()==null){
            throw new IllegalArgumentException("Position invalid.");
        }

        Node pos = validate(p);
        return pos.getRightChild();
    }

    /**
     * Returns the root of the current tree.
     * @return the root of the tree, null if the tree is empty.
     */
    @Override
    public Position<E> root() {
        return this.root;
    }

    /**
     * Returns the parent of the node in the position p.
     * @param p the node whose parent is to be returned.
     * @return the position of the parent.
     * @throws IllegalArgumentException if p is invalid.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        if (p==null || p.getElement()==null){
            throw new IllegalArgumentException("Position invalid.");
        }

        Node pos = validate(p);
        return pos.getParent();
    }

    /**
     * Returns the size or the total number of nodes in the current tree.
     * @return the size of the tree.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns an Iterator instance that would iterate through the tree in an inorder sequence.
     * @return an Iterator instance.
     */
    @Override
    public Iterator<E> iterator() {
        System.out.println("rooot: " + root().getElement());
        System.out.println("SIZE: " + size());
        Iterator iterator = new Iterator() {
            ArrayList<Position<E>> positions = (ArrayList<Position<E>>) positions();
            int count = - 1;
            @Override
            public boolean hasNext() {
                if (count < positions.size() - 1){
                    return true;
                }
                return false;
            }

            @Override
            public Object next() {
                System.out.println("POSIZE: " +positions.size());
                return positions.get(++count).getElement();
            }
        };
        return iterator;
    }

    /**
     * Returns an Iterable (perhaps an @ArrayList) that contains the positions or nodes of the tree in an inorder sequence.
     * @return an Iterable instance.
     */
    @Override
    public Iterable<Position<E>> positions() {
        ArrayList<Position<E>> positions = new ArrayList<>();
        positions = inorder(root, positions);
        return positions;
    }


    public ArrayList<Position<E>> inorder(Position<E> p, ArrayList<Position<E>> posList){
        Node pos = validate(p);
        if(p==null){
            return posList;
        }

        if (p!=null && pos.getLeftChild()!=null && pos.getLeftChild().getElement()!=null){
            inorder(left(p), posList);
        }
        System.out.print(p.getElement() + " |");
        posList.add(p);
        if (right(p)!=null){
            inorder(right(p), posList);
        }
        return posList;
    }

    public int getEval(Node<E> node){
        if (node == null){
            return 0;
        }

        //base case: if external or both child is null
        if (node.getLeftChild()==null && node.getRightChild() == null){
            return Integer.parseInt((String) node.getElement());
        }

        int left = getEval(node.getLeftChild());
        int right = getEval(node.getRightChild());

        if (node.getElement().equals("+")){
            return left + right;
        }
        else if (node.getElement().equals("-")){
            return left - right;
        }
        else if (node.getElement().equals("*")){
            return left * right;
        }
        else if (node.getElement().equals("/")){
            return left / right;
        }
        return 0;
    }


    public boolean isOperand(String o){
        if (o.equals("+") || o.equals("-") || o.equals("*") || o.equals("/")){
            return true;
        }
        return false;
    }

    public int getResult(String s1, String o, String s2){
        int num1 = Integer.parseInt(s1);
        int num2 = Integer.parseInt(s2);
        if (o.equals("+")){
            return num1 + num2;
        }
        else if (o.equals("-")){
            return num1 - num2;
        }
        else if (o.equals("*")){
            return num1 * num2;
        }
        else if (o.equals("/")){
            return num1 / num2;
        }
        throw new IllegalArgumentException("Parameters invalid.");
    }



    /**
     * GIVEN METHOD. Modify at your own risk.
     * Checks whether the particular position is a Node that has been defined in the package.
     * @param p the position to be checked.
     * @return the Node instance of the position.
     */
    protected Node<E> validate(Position<E> p) {
        if (p instanceof Node && p.getElement() != null) {
            return (Node<E>) p;
        }
        throw new IllegalArgumentException();
    }

    /**
     * Adds the first element to the tree.
     * @param element the data of the first node of the tree.
     * @return the position of the said root.
     * @throws IllegalArgumentException if a root exists.
     */
    protected Position<E> addRoot(E element) {
        System.out.println("\taddRoot...");
        if (root!=null && root.getElement()!=null){
            throw new IllegalArgumentException("Root already existed. ");
        }

        if (isEmpty()==true && root()==null){
            root = new Node<>(element, null);
            size++;
        }
        System.out.println("AddedRoot: "+root.getElement());
        return root;
    }

    /**
     * Adds a node of the given element to be the left child of the node in position p.
     * @param p the parent of the to-be-added node.
     * @param element the data of the to-be-added node.
     * @return the Position of the newly-created node.
     * @throws IllegalArgumentException if the parent already has a left child.
     */
    protected Position<E> addLeft(Position<E> p, E element) {
        Node pos = validate(p);
        //System.out.println("\tPOSITION: " + pos.getElement());
        if (pos.getLeftChild()==null || pos.getLeftChild().getElement()==null){
            Node newPos = new Node(element);
            pos.setLeftChild(newPos);
            newPos.setParent(pos);
            //System.out.println("\tadded: "+pos.getLeftChild().getElement());
            size++;
            return newPos;
        }
        throw new IllegalArgumentException("Requested parent has already a left child.");


    }

    /**
     * Adds a node of the given element to be the right child of the node in position p.
     * @param p the parent of the to-be-added node.
     * @param element the data of the to-be-added node.
     * @return the Position of the newly-created node.
     * @throws IllegalArgumentException if the parent already has a right child.
     */
    protected Position<E> addRight(Position<E> p, E element) {
        Node pos = validate(p);
        //System.out.println("\tPOSITION: " + pos.getElement());
        if (pos.getRightChild()==null || pos.getRightChild().getElement()==null){
            Node newPos = new Node(element);
            pos.setRightChild(newPos);
            newPos.setParent(pos);
            //System.out.println("\tadded: "+pos.getLeftChild().getElement());
            size++;
            return newPos;
        }
        throw new IllegalArgumentException("Requested parent has already a left child.");

    }

    /**
     * Sets the data of the parent to the given element.
     * @param p the node or the position to be set value to.
     * @param element the data to be replaced.
     * @return the replaced element.
     * @throws IllegalArgumentException if the position is invalid.
     */
    E set(Position<E> p, E element) {
        if (p==null || p.getElement()==null){
            throw new IllegalArgumentException("Position invalid.");
        }

        Node pos = validate(p);
        E prev = (E)pos.getElement();
        pos.setElement(element);
        return prev;
    }

    /**
     * Sets the left child of node p to t1 and the right child to t2, attaching trees to a node.
     * @param p the node to be attached the trees to.
     * @param t1 the first tree to be added as a left child.
     * @param t2 the second tree to be added as a right child.
     * @throws IllegalArgumentException if the p is invalid or the p has at least oen children.
     */
    void attach(Position<E> p, MyBinTree<E> t1, MyBinTree<E> t2) {
//        Node<E> node = validate(p);
//        if (isInternal(p)){
//            throw new IllegalArgumentException("Position must be leaf.");
//        }
//        size += t1.size() + t2.size();
//        if (!t1.isEmpty()){
//            validate(t1.root).setParent(node);
//            node.setLeftChild(validate(t1.root));
//            validate(t1.root).setElement(null);
//            t1.root = null;
//            t1.size = 0;
//        }
//
//        if (!t2.isEmpty()){
//            validate(t2.root).setParent(node);
//            node.setRightChild(validate(t2.root));
//            validate(t2.root).setElement(null);
//            t1.root = null;
//            t1.size = 0;
//        }
        Node<E> node = validate(p);
        if (isInternal(p)){
            throw new IllegalArgumentException("Position must be a leaf.");
        }
        this.size += t1.size() + t2.size();
        if (!t1.isEmpty()){
            validate(t1.root).setParent(node);
            node.setLeftChild(validate(t1.root()));
            t1.root = null;
            t1.size = 0;
        }

        if (!t2.isEmpty()){
            validate(t2.root).setParent(node);
            node.setRightChild(validate(t2.root));
            t2.root = null;
            t2.size = 0;
        }
    }

    /**
     * Removes the node specified, to be replaced by its only child, if any.
     * @param p the position to be removed.
     * @return the element stored in the position.
     * @throws IllegalArgumentException if p is invalid or if p has 2 children.
     */
    protected E remove(Position<E> p) {
        System.out.println("\tremove..." + p.getElement());
        E prev = p.getElement();
        Node pos = validate(p);
        Node node;
        Node child;


        if (p==null || p.getElement()==null){
            throw new IllegalArgumentException("Invalid position.");
        }

        if (isExternal(p)){
            if (pos.getParent().getLeftChild()==pos){
                pos.getParent().setLeftChild(null);
            }
            else {
                pos.getParent().setRightChild(null);
            }
            pos.setElement(null);
            size--;
            return prev;
        }

        if (isInternal(p) && pos.getLeftChild()!=null && pos.getRightChild()!=null){
            System.out.println("Node: " + p.getElement() + "LeftChild: " + pos.getLeftChild().getElement() + " RightChild: " + pos.getRightChild().getElement());
            throw new IllegalArgumentException("Node has two children. ");
        }

        if (pos.getLeftChild()!=null){
            child = pos.getLeftChild();
            child.setParent(pos.getParent()); // child’s grandparent becomes its parent
        }
        else {
            child = pos.getRightChild();
            child.setParent(pos.getParent()); // child’s grandparent becomes its parent
        }

//        if (child != null) {
////            child.setParent(pos.getParent()); // child’s grandparent becomes its parent
////        }

        if (pos == root){
            root = child; // child becomes root
            size--;
            return prev;
        }
        else {
            Node<E> parent = pos.getParent();
            if (pos == parent.getLeftChild()){
                parent.setLeftChild(child);
            }
            else{
                parent.setRightChild(child);
            }

            System.out.println("size: "+size);
            E temp = (E)pos.getElement();
            prev = temp;
            pos.setLeftChild(null);
            pos.setRightChild(null);
            pos = null;
 //           pos.setParent(pos);
            //return temp;
        }
        this.size--;
        return prev;
    }

    /**
     * Calculates the binary tree given that the internal nodes are operators and the external nodes are integers.
     * @return the result of the calculation.
     */
    int evaluate() {
        return getEval(validate(root));
    }



    public static void main(String[] args) {
        MyBinTree<String> tree = new MyBinTree<String>();

        Position<String> minus = tree.addRoot("-");
        Position<String> times1 = tree.addLeft(minus, "*");
        Position<String> minus1 = tree.addRight(minus, "-");
        tree.addLeft(times1, "3");
        tree.addRight(times1, "7");
        tree.addLeft(minus1, "16");
        tree.addRight(minus1, "3");
        tree.positions();
        System.out.println("8: "+ tree.evaluate());

    }

}

