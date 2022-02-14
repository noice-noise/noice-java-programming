package Lab17;

import java.util.ArrayList;
import java.util.List;

public class Node<E> implements Position<E> {
    private E element;
    private Node<E> parent;
    private Node<E> leftChild;
    private Node<E> rightChild;

    public Node(E element) {
        this(element, null);
    }

    public Node(E element, Node<E> parent) {
        this.element = element;
        this.parent = parent;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    public Node<E> getParent() {
        return parent;
    }

    public Node<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<E> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<E> rightChild) {
        this.rightChild = rightChild;
    }


    @Override
    public E getElement() {
        return element;
    }
}
