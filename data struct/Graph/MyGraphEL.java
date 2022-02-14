package Lab20;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//MyGraphEdgeList
public class MyGraphEL<E, V> implements Graph<E, V> {
    private List<Vertex<V>> vertices;
    private List<Edge<E, V>> edges;

    public MyGraphEL() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public Iterator<Vertex<V>> vertices() {
        return vertices.iterator();
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    @Override
    public Iterator<Edge<E, V>> edges() {
        return edges.iterator();
    }

    @Override
    public Edge<E, V> getEdge(Vertex<V> u, Vertex<V> v) {
        for (Edge<E, V> edge: edges){
            Vertex<V>[] vertex = endVertices(edge);
            if(vertex[0].equals(u) && vertex[1].equals(v)){
                return edge;
            }
        }
        return null;
    }

    @Override
    public Vertex<V>[] endVertices(Edge<E, V> e) {
        return e.getVertices();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) {
        Vertex<V>[] vertex = endVertices(e);
        if (vertex[0].equals(v)){
            return vertex[1];
        } else if (vertex[1].equals(v)){
            return vertex[0];
        }
        throw new IllegalArgumentException("v is not incident of e.");
    }

    @Override
    public int outDegree(Vertex<V> v) {
        return outside(v).size();
    }

    private List<Edge<E, V>> outside(Vertex<V> v){
        List<Edge<E, V>> out = new ArrayList<>();
        for (Edge edge: edges){
            if (edge.getVertices()[0].equals(v)){
                out.add(edge);
            }
        }
        return out;
    }

    @Override
    public int inDegree(Vertex<V> v) {
        return inside(v).size();
    }

    private List<Edge<E, V>> inside(Vertex<V> v){
        List<Edge<E, V>> out = new ArrayList<>();
        for (Edge edge: edges){
            if (edge.getVertices()[1].equals(v)){
                out.add(edge);
            }
        }
        return out;
    }

    @Override
    public Iterator<Edge<E, V>> outgoingEdges(Vertex<V> v) {
        return outside(v).iterator();
    }

    @Override
    public Iterator<Edge<E, V>> incomingEdges(Vertex<V> v) {
        return inside(v).iterator();
    }

    @Override
    public Vertex<V> insertVertex(V element) {
        Vertex<V> vertex = new Vertex<>(element);
        vertices.add(vertex);
        return vertex;
    }

    @Override
    public Edge<E, V> insertEdge(E element, Vertex<V> v, Vertex<V> x) {
        if(getEdge(v,x) == null){
            Edge<E, V> edge = new Edge<>(element, v, x);
            edges.add(edge);
            return edge;
        }
        throw new IllegalArgumentException("There exists an edge.");
    }

    @Override
    public void removeVertex(Vertex<V> v) {
        for (int i = edges.size()-1; i >= 0 ; i--) {
            if (edges.get(i).getVertices()[0].equals(v)
                    || edges.get(i).getVertices()[1].equals(v)){
                edges.remove(edges.get(i));
            }
        }
        vertices.remove(v);
    }

    @Override
    public void removeEdge(Edge<E, V> e) {
        edges.remove(e);
    }
}
