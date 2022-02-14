package Lab20;

public class Edge<E, V> {
    private E element;
    private Vertex<V>[] vertices;

    //v to where the edge comes from, w to where the edge will go
    //v outgoing, w incoming
    // v --> e --> w
    public Edge(E element, Vertex<V> v, Vertex<V> w) {
        this.element = element;
        this.vertices = new Vertex[]{v, w};
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setVertices(Vertex<V>[] vertices) {
        this.vertices = vertices;
    }

    public Vertex<V>[] getVertices() {
        return vertices;
    }

    @Override
    public String toString(){
        return "{" + element.toString() + ", " + vertices[0] + ", " + vertices[1] + "}";
    }
}
