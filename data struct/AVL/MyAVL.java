package Lab19;

import Lab17.MyBinTree;
import Lab17.Node;
import Lab17.Position;
import Lab18.Entry;
import Lab18.MyBST;

import java.util.Iterator;

/*
        Things I've learned while coding this nigga...
        make you're code flexible enough that you won't be afraid making changes.
        Your code should work, 100%. if you're not confident in something: messy function, messy framework,
            you should try your best to clean and clear those up. Because in a lengthy and complex project
            those little efforts will really payoff when bugs come to haunt you.

            Your debugging precautions will save you, don't delete them, comment them instead.
            Make every entry/action easy to trace as and concise possible, this will really help you when
                finding a bug.
               ABUSE Unit Tests, indulge in the process called TEST DRIVEN DEVELOPMENT.
 */

public class MyAVL<K, V> extends MyBST<K, V> {
    /**
     * Returns the height of the node.
     *
     * @param pos the node to be evaluated.
     * @return the height of pos.
     */
    private int getHeight(Position<Entry<K, V>> pos) {

        if (pos == null || pos.getElement() == null) {
            return 0;
            // throw new IllegalArgumentException("Position argument invalid.");
        }

        int height = 1;
        Position<Entry<K, V>> left = left(pos);
        Position<Entry<K, V>> right = right(pos);
        if (isExternal(pos)){
            return 1;
        }
        else if ((left == null || left.getElement()==null)){
            return 1 + nodeHeight(pos);
        }
        else if (right == null || right.getElement() == null){
            //System.out.println("\t" + validate(pos).getElement() + " HEIGHT: " + nodeHeight(pos));
            return 1 + nodeHeight(pos);
        }
        else {
            System.out.println("\tHAS CHILDREN");
            return 1 + Math.max(nodeHeight(left(pos))+1, nodeHeight(right(pos))+1);

        }

    }

    public int nodeHeight(Position<Entry<K, V>> p) {
        int h = 0;
        for (Position<Entry<K, V>> c : children(p)) {
            h = Math.max(h, 1 + height(c));
        }
        return h;
    }

    /**
     * In cases that the height of the children of pos exceeds one, do one of the following:
     * zig left, zig right, zig zag left, and zig zag right
     *
     * @param pos the position to be evaluated and, if possible, operated into
     */
    private void postprocess(Position<Entry<K, V>> pos) {
        System.out.println("\t[ POST PROCESSING ]");
        Node<Entry<K, V>> upLink = null;
        boolean nodeSide = false;   //reference position of the former node (left or right)
        Node<Entry<K, V>> node = super.validate(pos);
        System.out.println("\tINSERT NODE: " + node.getElement() + "\tHeight: " + getHeight(node));

        while (true){
            int balance = getBalance(node);
            System.out.println("\tBalance: " + balance);
            if (balance > 1 && node.getElement().getKey().hashCode() > node.getLeftChild().getElement().getKey().hashCode() && getHeight(node.getLeftChild().getLeftChild()) == getHeight(node.getLeftChild()) -1 ){
                System.out.println("\tLeft Left case - > right rotate");
                if (node != root){
                    upLink = node.getParent();
                    nodeSide = isLeft(upLink, node);
                }
                node = rightRotate(node);
                if (node != root){
                    link(upLink, nodeSide, node);
                }
                System.out.println("NODE: " + node.getElement());
            }

            else if (balance < -1 && node.getElement().getKey().hashCode() < node.getRightChild().getElement().getKey().hashCode() && getHeight(node.getRightChild().getRightChild()) == getHeight(node.getRightChild()) -1 ){
                System.out.println("\tRight Right case - > left rotate");
                System.out.println("NODE: " + node.getElement());
                if (node != root){
                    upLink = node.getParent();
                    nodeSide = isLeft(upLink, node);
                }

                node = leftRotate(node);

                if (node != root){
                    link(upLink, nodeSide, node);
                }
                System.out.println("NODE: " + node.getElement());
            }

            else if (balance > 1 && node.getElement().getKey().hashCode() > node.getLeftChild().getElement().getKey().hashCode()){
                //TODO configure uplink protocol when doing zig zags -- the parent of concerned node {4, "Four"} is still 3 instead of the upLink reference {2, "Two"}
                System.out.println("\tLeft Right case - >");
                System.out.println("\tNODE: " + node.getElement());
                System.out.println("\tRoot: "+super.root.getElement());
                super.dispInfo(node);

                if (node != root){
                    upLink = node.getParent();
                    nodeSide = isLeft(upLink, node);
                }
                node.setLeftChild(leftRotate(node.getLeftChild()));
                node = rightRotate(node);
                super.dispInfo(node);
                //TODO configure uplink protocol when doing zig zags -- the parent of concerned node {4, "Four"} is still 3 instead of the upLink reference {2, "Two"}
                if (node != root){
                    link(upLink, nodeSide, node);
                }
                super.dispInfo(node);
                System.out.println("\tNODE: " + node.getElement());
                System.out.println("\tRoot: "+super.root.getElement());

            }
            else if (balance < -1 && node.getElement().getKey().hashCode() < node.getRightChild().getElement().getKey().hashCode()){
                System.out.println("\tRight Left case - >");
                if (node != root){
                    upLink = node.getParent();
                    nodeSide = isLeft(upLink, node);
                }
                node.setRightChild(rightRotate(node.getRightChild()));
                node = leftRotate(node);
                if (node != root){
                    link(upLink, nodeSide, node);
                }
                System.out.println("\tNODE: " + node.getElement());
                System.out.println("\tRoot: "+super.root.getElement());
            }
            if (node == root){
                System.out.println("\t[Reached root]: BREAK");
                break;
            }
            System.out.println("\t[Moving up] Node before: " + node.getElement());
            dispInfo(node);
            if (node!=super.root && node.getParent() != null){
                node = node.getParent();
            }
            System.out.println("\t[Moving up] Node after: " + node.getElement());
        }
    }

    public boolean isBalanced(Position<Entry<K, V>> pos) {
        if (Math.abs(getHeight(left(pos)) - getHeight(right(pos))) <= 1) {
            return true;
        }
        return false;
    }

    public Node rightRotate(Node<Entry<K, V>> y){
        Node<Entry<K, V>> x = y.getLeftChild();
        Node<Entry<K, V>> T2 = x.getRightChild();

        x.setRightChild(y);
        y.setParent(x);
        y.setLeftChild(T2);
        if (T2!=null){
            T2.setParent(y);
        }
        if (y == super.root){
            super.root = x;
            x.setParent(null);
        }
        return x;
    }

    public Node leftRotate (Node<Entry<K, V>> x){
        Node<Entry<K, V>> y = x.getRightChild();
        Node T2 = y.getLeftChild();
        System.out.println();
        super.dispInfo(x);
        y.setLeftChild(x);
        x.setParent(y);
        x.setRightChild(T2);

        if (T2!=null){
            T2.setParent(x);
        }

        super.dispInfo(x);
        System.out.println();
        if (x == super.root){
            super.root = y;
            y.setParent(null);
        }
        return y;
    }


    public void link(Node<Entry<K, V>> parent, boolean isLeft, Node<Entry<K, V>> child){
        System.out.println("\tUp-linked");
        System.out.println("\tParent: " + parent.getElement() + " isLeft: " + isLeft + " Child: " + child.getElement() );
        if (isLeft){
            parent.setLeftChild(child);
        }
        else {
            parent.setRightChild(child);
        }
        child.setParent(parent);

    }

    public boolean isLeft(Node<Entry<K, V>> parent, Node<Entry<K, V>> child){
        if (parent.getLeftChild() == child){
            return true;
        }
        return false;
    }


    public int getBalance(Node<Entry<K, V>> node){
        if (node == null){
            return 0;
        }
        System.out.println("\tLeftHeight: " + getHeight(node.getLeftChild()) + "  RightHeight: " + getHeight(node.getRightChild()));
        return getHeight(node.getLeftChild()) - getHeight(node.getRightChild());
    }

    /**
     * Inserts the key-value pair into the tree.
     * If the entry with key k exists, return the old value and replace it with value v.
     *
     * @param key   the key of the entry.
     * @param value the value of the entry.
     * @return the value of an entry, if entry with key k already exists.
     */
    @Override
    protected V insert(K key, V value) {
        System.out.println("\tinsert -> {" + key +", "+ value);
        super.insert(key, value);
        Position<Entry<K, V>> pos = super.search(key);
        if (pos != super.root){
            postprocess(validate(pos).getParent());
        }
        return value;
    }

    /**
     * Removes the entry with key k.
     *
     * @param key the key of the entry to be removed.
     * @return the value of the entry.
     */
    @Override
    protected V remove(K key) {
        Node<Entry<K, V>> removedNode = super.validate(super.search(key));
        V value = super.remove(key);
        if (removedNode.getParent()!=null && removedNode != super.root){
            postprocess(removedNode.getParent());
        }
        return value;
    }

    public static void main(String[] args) {
        MyAVL<Integer, String> tree;
        tree = new MyAVL<>();
        Iterator iter;

        tree.insert(77, "Seventy seven");
        tree.insert(5, "Five");
        tree.insert(81, "Eighty one");
        tree.insert(1, "One");
        tree.remove(77);
        Node<Entry<Integer, String>> root = (Node<Entry<Integer, String>>) tree.root();
        Node<Entry<Integer, String>> left = root.getLeftChild();
        Node<Entry<Integer, String>> right = root.getRightChild();
        System.out.println("Five"+ root.getElement().getValue());
        System.out.println("One"+ left.getElement().getValue());
        System.out.println("Eighty one"+ right.getElement().getValue());
        tree.insert(12, "Twelve");
        tree.insert(53, "Fifty three");
        root = root.getRightChild();
        left = root.getLeftChild();
        right = root.getRightChild();
        System.out.println("Fifty three"+ root.getElement().getValue());
        System.out.println("Twelve"+ left.getElement().getValue());
        System.out.println("Eighty one"+ right.getElement().getValue());
        System.out.println("Five"+ root.getParent().getElement().getValue());
        tree.insert(97, "Ninety seven");
        root = (Node<Entry<Integer, String>>) tree.root();
        left = root.getLeftChild();
        right = root.getRightChild();
        Node<Entry<Integer, String>> left_right = left.getRightChild();
        System.out.println("Fifty three"+ root.getElement().getValue());
        System.out.println("Five"+ left.getElement().getValue());
        System.out.println("Eighty one"+ right.getElement().getValue());
        System.out.println("Twelve"+ left_right.getElement().getValue());
        tree.insert(64, "Sixty four");
        tree.insert(55, "Fifty five");
        tree.insert(17, "Seventeen");
        tree.remove(12);
        iter = tree.iterator();
        while (iter.hasNext()){
            System.out.println("Child: " + iter.next());
        }

        root = root.getLeftChild(); // root > left [as per line 258]
        right = root.getRightChild();
        System.out.println("Five"+ root.getElement().getValue());
        System.out.println("Seventeen"+ right.getElement().getValue());
        System.out.println("Five"+ right.getParent().getElement().getValue());
        tree.insert(23, "Twenty three");
        tree.insert(71, "Seventy one");
        tree.insert(47, "Forty seven");
        root = root.getRightChild(); // root > left > right [as per line 270]
        right = root.getRightChild();
        left = root.getLeftChild();
        System.out.println("Twenty three"+ root.getElement().getValue());
        System.out.println("Forty seven"+ right.getElement().getValue());
        System.out.println("Seventeen"+ left.getElement().getValue());
        tree.insert(9, "Nine");
        root = ((Node<Entry<Integer, String>>) tree.root()).getLeftChild();
        right = root.getRightChild();
        left = root.getLeftChild();
        System.out.println("Seventeen"+ root.getElement().getValue());
        System.out.println("Twenty three"+ right.getElement().getValue());
        System.out.println("Five"+ left.getElement().getValue());
        tree.remove(81);
        root = ((Node<Entry<Integer, String>>) tree.root()).getRightChild();
        left = root.getLeftChild();
        right = root.getRightChild();
        System.out.println("Seventy one"+ root.getElement().getValue());
        System.out.println("Ninety seven"+ right.getElement().getValue());
        System.out.println("Sixty four"+ left.getElement().getValue());
        tree.insert(39, "Thirty nine");
        root = ((Node<Entry<Integer, String>>) tree.root()).getLeftChild().getRightChild();
        left = root.getLeftChild();
        right = root.getRightChild();
        System.out.println("Thirty nine"+ root.getElement().getValue());
        System.out.println("Twenty three"+ left.getElement().getValue());
        System.out.println("Forty seven"+ right.getElement().getValue());
        tree.remove(53);
        root = ((Node<Entry<Integer, String>>) tree.root());
        left = root.getLeftChild();
        right = root.getRightChild();
        System.out.println("Forty seven"+ root.getElement().getValue());
        System.out.println("Seventeen"+ left.getElement().getValue());
        System.out.println("Seventy one"+ right.getElement().getValue());
        tree.remove(97);
        root = ((Node<Entry<Integer, String>>) tree.root()).getRightChild();
        left = root.getLeftChild();
        right = root.getRightChild();
        System.out.println("Sixty four"+ root.getElement().getValue());
        System.out.println("Fifty five"+ left.getElement().getValue());
        System.out.println("Seventy one"+ right.getElement().getValue());
        tree.insert(30, "Thirty");
        root = ((Node<Entry<Integer, String>>) tree.root()).getLeftChild().getRightChild();
        left = root.getLeftChild();
        right = root.getRightChild();
        System.out.println("Thirty"+ root.getElement().getValue());
        System.out.println("Twenty three"+ left.getElement().getValue());
        System.out.println("Thirty nine"+ right.getElement().getValue());

        iter = tree.iterator();
        System.out.println();
        while (iter.hasNext()){
            System.out.println("Child: " + iter.next());
        }
        System.out.println("\tROOT: "+tree.root().getElement());
    }
}