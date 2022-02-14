package Lab19;
/*
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyGraphAL<E, V> implements Graph<E, V> {
    private List<Vertex<V>> vertices;

    public MyGraphAL() {
        vertices = new ArrayList<>();
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
        int edgeCounter = 0;
        for (Vertex<V> vertex: vertices){
            vertex.numEdges();
        }
        return edgeCounter;
    }

    @Override
    public Iterator<Edge<E, V>> edges() {
        List<Edge<E, V>> edges = new ArrayList<>();
        for(Vertex<V> vertex: vertices){
            edges = addEdgesToList(vertex.getEdges(), edges);
        }
        displayEdges(edges.iterator());
        return edges.iterator();
    }

    public List<Edge<E, V>> addEdgesToList(List<Edge> smallerCollection, List<Edge<E, V>> largerCollection){
        for(Edge<E, V> edge: smallerCollection){
            largerCollection.add(edge);
        }
        return largerCollection;
    }

    public Edge<E, V> convertEdge(Edge edge){
        return (Edge<E, V>) edge;
    }


    @Override
    public Edge<E, V> getEdge(Vertex<V> u, Vertex<V> v) {
        for (Edge<E, V> edge: edges()){
            Vertex<V>[] vertex = endVertices(edge);
            if(vertex[0].equals(u) && vertex[1].equals(v)){
                return edge;
            }
        }
        return null;
    }

    @Override
    public Vertex<V>[] endVertices(Edge<E, V> e) {
        return new Vertex[0];
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) {
        return null;
    }

    @Override
    public int outDegree(Vertex<V> v) {
        return 0;
    }

    @Override
    public int inDegree(Vertex<V> v) {
        return 0;
    }

    @Override
    public Iterator<Edge<E, V>> outgoingEdges(Vertex<V> v) {
        return null;
    }

    @Override
    public Iterator<Edge<E, V>> incomingEdges(Vertex<V> v) {
        return null;
    }

    @Override
    public Vertex<V> insertVertex(V element) {
        return null;
    }

    @Override
    public Edge<E, V> insertEdge(E element, Vertex<V> v, Vertex<V> x) {
        return null;
    }

    @Override
    public void removeVertex(Vertex<V> v) {

    }

    @Override
    public void removeEdge(Edge<E, V> e) {

    }

    public void displayEdges(Iterator<Edge<E, V>> edgeIterator){
        System.out.println("\tDisplay Edges: ");
        while (edgeIterator.hasNext()){
            System.out.println("\tEdge: "+edgeIterator.next());
        }
    }

    public static void main(String[] args) {
        MyGraphAL<Integer, Character> graph =  new MyGraphAL<>();
        graph.displayEdges(graph.edges());
    }
}
*/