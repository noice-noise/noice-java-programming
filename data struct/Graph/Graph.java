package Lab20;

import java.util.Iterator;

public interface Graph<E, V> {
    int numVertices();
    Iterator<Vertex<V>> vertices();
    int numEdges();
    Iterator<Edge<E, V>> edges();
    Edge<E, V> getEdge(Vertex<V> u, Vertex<V> v);
    Vertex<V>[] endVertices(Edge<E, V> e);
    Vertex<V> opposite(Vertex<V> v, Edge<E, V> e);
    int outDegree(Vertex<V> v);
    int inDegree(Vertex<V> v);
    Iterator<Edge<E, V>> outgoingEdges(Vertex<V> v);
    Iterator<Edge<E, V>> incomingEdges(Vertex<V> v);
    Vertex<V> insertVertex(V element);
    Edge<E, V> insertEdge(E element, Vertex<V> v, Vertex<V> x);
    void removeVertex(Vertex<V> v);
    void removeEdge(Edge<E, V> e);
}
