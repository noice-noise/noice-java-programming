package Lab18;

import Lab17.MyBinTree;
import Lab17.Position;
import Lab17.Node;

import java.util.Iterator;

public class MyBST<K, V> extends MyBinTree<Entry<K, V>> {

    //MyBinTree<Entry> binTree = new MyBinTree<>();
    //Entry<K, V> root;

    public MyBST(){

    }


    /**
     * This shall accept a key and shall return the Position of the entry in the binary tree.
     * If the key does not exist, it shall only return the last visited node.
     * @param key of the entry to be searched.
     * @return the position of the entry in the tree.
     */
    protected Position<Entry<K, V>> search(K key){
        Position<Entry<K, V>> p = super.root;
        //Entry<K, V> entry = new Entry(key, value);
        Node<Entry<K, V>> nodeEntry;
        Node<Entry<K, V>> lastVisited;
        Boolean done = false;
        V prev;


        if (root == null || isEmpty()){
            throw new IllegalStateException("Tree is empty.");
        }
        nodeEntry = validate(p);
        lastVisited = nodeEntry;
        while (done == false) {
            if (lastVisited==null){
                return null;
            }
            else if (key.hashCode() == lastVisited.getElement().getKey().hashCode()) {
                return lastVisited;
            } else if (key.hashCode() < lastVisited.getElement().getKey().hashCode()) {
                lastVisited = lastVisited.getLeftChild();
            } else if (key.hashCode() > lastVisited.getElement().getKey().hashCode()) {
                lastVisited = lastVisited.getRightChild();
            }
            else {
                return null;
            }

        }
        return null;
    }

    /**
     * This shall accept a key and a value to be placed as a single entry in the tree.
     * If a node with the same key exists in the tree, it will simply replace and return the old value.
     * @param key of the entry to be inserted in the tree.
     * @param value of the entry to be inserted, if not replaced, in the tree.
     * @return the old or replaced value if the entry of the parameter key exists, otherwise null.
     */
    protected V insert(K key, V value) {
        Position<Entry<K, V>> p = super.root;
        Entry<K, V> entry = new Entry(key, value);
        Node<Entry<K, V>> nodeEntry;
        Node<Entry<K, V>> lastVisited;
        Boolean done = false;
        V prev;

        if (super.size() == 0) {
            addRoot(entry);
        } else {
            nodeEntry = validate(p);
            lastVisited = nodeEntry;

            while (done == false) {
                if (entry.getKey().hashCode() == lastVisited.getElement().getKey().hashCode()) {
                    //if entryKey is equals to rootKey
                    //System.out.println("Replace by " + entry.getValue() + "..." + lastVisited.getElement());
                    prev = lastVisited.getElement().getValue();
                    lastVisited.getElement().setValue(entry.getValue());
                    //System.out.println("VAL: "+entry.getValue());
                    //System.out.println("LV: "+lastVisited.getElement().getValue());
                    return prev;
                    //super.addLeft(lastVisited, entry);
                } else if (entry.getKey().hashCode() < lastVisited.getElement().getKey().hashCode()) {
                    //if entryKey is less than rootKey
                    //System.out.println("goneLeft...");
                    if (lastVisited.getLeftChild() == null) {
                        //System.out.println("\taddedLeft: " );
                        dispInfo(super.addLeft(lastVisited, entry));
                        done = true;
                    }
                    lastVisited = lastVisited.getLeftChild();
                } else if (entry.getKey().hashCode() > lastVisited.getElement().getKey().hashCode()) {
                    //if entryKey is less than rootKey
                    //System.out.println("goneRight...");
                    if (lastVisited.getRightChild() == null) {
                        //System.out.println("\taddedRight: " );
                        dispInfo(super.addRight(lastVisited, entry));
                        done = true;
                    }
                    lastVisited = lastVisited.getRightChild();
                }

            }
            //System.out.println("[while loop out]");
            return entry.getValue();
        }

        return null;
    }


    public void dispInfo(Position<Entry<K, V>> p){
        Node<Entry<K, V>> entryNode = validate(p);
        if (entryNode.getParent()!=null){
            System.out.println("\t[PARENT]: " + entryNode.getParent().getElement());

        }
        System.out.println("\t[NODE]: " + entryNode.getElement());

        if (entryNode.getLeftChild()!=null){
            System.out.println("\t[LEFT]: " + entryNode.getLeftChild().getElement());

        }
        if (entryNode.getRightChild()!=null){
            System.out.println("\t[RIGHT]: " + entryNode.getRightChild().getElement());
        }


    }


    /**
     * This shall accept a key to find and remove the entry of the said key.
     * Should there be no entry of the key k, it shall return null.
     * @param key of the entry to be removed.
     * @return the value associated with the key, null if the entry of parameter key does not exist.
     */
    protected V remove(K key) {
        Position<Entry<K, V>> pos = search(key);
        if (pos==null || pos.getElement()==null || (pos.getElement().getKey()!=key)){
            return null;
        }
        Node<Entry<K, V>> entryNode = validate(pos);
        Entry<K, V> entryNode1;
        Node<Entry<K, V>> removedNode = validate(pos);
        Node<Entry<K, V>> parent, left, right;
        V ret = validate(pos).getElement().getValue();


        System.out.println("ToRemove: " + removedNode.getElement());
        if (removedNode==super.root && removedNode.getRightChild()==null){
            System.out.println("removed root");
            super.remove(removedNode);
        }
        else if (isExternal(pos)){
            System.out.println("MUCHOS");
            super.remove(removedNode);
            return ret;
        }
        else {
            System.out.println("simp");
            if (entryNode.getLeftChild()!=null){
                entryNode = entryNode.getLeftChild();
                while(pos.getElement().getKey().hashCode() > entryNode.getElement().getKey().hashCode()){

                    if (entryNode.getRightChild()==null){

                        //System.out.println("RET: " + super.remove(validate(pos)).getValue());
                        entryNode1 = entryNode.getElement();
                        //System.out.println("Entry node: " + entryNode.getElement().getValue());
                        super.remove(entryNode);    //removing the external r
                        //System.out.println("Entery node1 : " + entryNode);
                        entryNode = new Node(entryNode1);
                        System.out.println("Entry node: " + entryNode.getElement().getValue());

                        if (removedNode==super.root){
                            System.out.println("POSSS: "+entryNode.getElement());
                            //super.root = (Position<Entry<K,V>>) entryNode;
                            //System.out.println("ROOTS: " +validate(super.root).getRightChild());

                                entryNode.setRightChild(removedNode.getRightChild());
                                super.root = (Position<Entry<K,V>>) entryNode;

                                System.out.println("ROOTS: " +validate(super.root).getRightChild());

                            parent = null;
//                            parent.setElement(null);
                        }
                        else {
                            parent = removedNode.getParent();
                        }

                        left = removedNode.getLeftChild();
                        right = removedNode.getRightChild();


                        //attaching the r to the parent
                        if (parent!=null && parent.getLeftChild()==removedNode){
                            parent.setLeftChild(entryNode);
                            entryNode.setParent(parent);
                        }
                        else if (parent!=null && parent.getRightChild()==removedNode){
                            parent.setRightChild(entryNode);
                            entryNode.setParent(parent);
                        }


//                        if (parent!=null){
//                            entryNode.setParent(parent);
//                        }

                        // attaching the r to the left
                        if (left!=null){
                            System.out.println("l");
                            entryNode.setLeftChild(left);
                            left.setParent(entryNode);
                        }

                        if (right!=null){
                            System.out.println("r");
                            entryNode.setRightChild(right);
                            right.setParent(entryNode);
                        }
                        System.out.println("\tEntry: " + entryNode.getElement().getKey());
                        dispInfo(entryNode);

                        break;
                    }
                    entryNode = entryNode.getRightChild();
                    System.out.println("\tEntry: " + entryNode.getElement().getKey());


                }
                System.out.println("\tFINAL Entry: " + entryNode.getElement().getKey());

                //pos = pos.getElement()
            }
            else if (entryNode.getLeftChild()==null && entryNode.getRightChild()!=null){
                System.out.println("Left child is null..");
                super.remove(removedNode);
            }
            else {
                System.out.println("LAST ELSE");
                return null;
            }
        }
        System.out.println("RETT: "+ret);

        return ret;
    }

/*
    public static void main(String[] args) {
        MyBST<Integer, String> tree = new MyBST<>();

        tree.insert(52, "Fifty Two");
        System.out.println(("1: " + tree.size()));
//        Iterator<Entry<Integer, String>> iter = tree.iterator();
//        assertEquals(52, (int) iter.next().getKey());
//        assertFalse(iter.hasNext());


        tree.insert(52, "Fifty Two");
        tree.insert(27, "Twenty Seven");
        tree.insert(35, "Thirty Five");
        System.out.println("3" + tree.size());
        Iterator<Entry<Integer, String>> iter = tree.iterator();
        System.out.println("27:" + (int) iter.next().getKey());
        System.out.println("35: " + (int) iter.next().getKey());
        System.out.println("52:" + (int) iter.next().getKey());
        System.out.println("hasNext: " + iter.hasNext());

    }
 */
}