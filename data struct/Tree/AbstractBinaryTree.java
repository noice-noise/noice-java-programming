package Lab17;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    @Override
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        Position<E> parent = parent(p);
        if (parent != null) {
            if (p == left(parent)) {
                return right(parent);
            }
            return left(parent);
        }
        return null;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        List<Position<E>> children = new ArrayList<>();
        if (left(p) != null) {
            children.add(left(p));
        }
        if (right(p) != null) {
            children.add(right(p));
        }
        return children;
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        int ctr = 0;
        if (left(p) != null) {
            ctr++;
        }
        if (right(p) != null) {
            ctr++;
        }
        return ctr;
    }
}
