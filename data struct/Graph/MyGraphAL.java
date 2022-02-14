package Lab20;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//MyGraphEdgeList
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
        return  getEdgeList().size();
    }

    @Override
    public Iterator<Edge<E, V>> edges() {
        return getEdgeList().iterator();
    }

    public List<Edge<E, V>> getEdgeList(){
        List<Edge<E, V>> mainEdgeList = new ArrayList<Edge<E, V>>();
        List<Edge<E, V>> validatedEdgeList = new ArrayList<Edge<E, V>>();
        Edge<E, V> edge;
        for(Vertex<V> vertex : vertices){
             validatedEdgeList = validateEdgeList(vertex.getEdges());
             mainEdgeList.addAll(validatedEdgeList);
        }
        displayListContents(mainEdgeList.iterator());
        return mainEdgeList;
    }

    public List<Edge<E, V>> validateEdgeList(List<Edge> el){
        List<Edge<E, V>> validEdgeList = new ArrayList<Edge<E, V>>();
        Edge<E, V> validatedEdge;
        for(Edge e : el){
            validatedEdge = validateEdge(e);
            validEdgeList.add(validatedEdge);
            System.out.println(validatedEdge);
        }
        return validEdgeList;
    }

    public void displayListContents(Iterator iterator){
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public Edge<E, V> validateEdge(Edge e){
        Vertex<V>[] ev = e.getVertices();
        Edge<E, V> validEdge = new Edge<E, V>((E) e.getElement(), ev[0], ev[1]);

        return validEdge;
    }


    @Override
    public Edge<E, V> getEdge(Vertex<V> u, Vertex<V> v) {
        for (Edge<E, V> edge: getEdgeList()){
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
        for (Edge edge: getEdgeList()){
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
        for (Edge edge: getEdgeList()){
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
            v.addEdge(new Edge(element, v, x));
            //getEdgeList().add(edge);
            return edge;
        }
        throw new IllegalArgumentException("There exists an edge.");
    }

    @Override
    public void removeVertex(Vertex<V> v) {
        int vertexOfEdge = vertices.indexOf(v);
        List<Edge<E, V>> edges = getEdgeList();
        Vertex<V> oppositeofV;
        for (int i = edges.size()-1; i >= 0 ; i--) {
            if (edges.get(i).getVertices()[0].equals(v)
                    || edges.get(i).getVertices()[1].equals(v)){

                //vertices.remove(vertices.get(vertexOfEdge));


                //edges.remove(edges.get(i));
                oppositeofV = opposite(v, edges.get(i));
                oppositeofV.removeEdge(edges.get(i));
            }

        }
        vertices.remove(v);
    }

    @Override
    public void removeEdge(Edge<E, V> e) {
        int vertexOfEdge = vertices.indexOf(e.getVertices()[0]);
        //System.out.println("VE: "+vertexOfEdge + vertices.get(vertexOfEdge));
        //vertices.remove(vertices.get(vertexOfEdge));
        vertices.get(vertexOfEdge).removeEdge(e);
        //displayListContents(edges());
//        vertices.get(vertexOfEdge).removeEdge(e);
//        displayListContents(getEdgeList().iterator());
//        e.setElement(null);
//        e.setVertices(null);
//        e = null;

//        System.out.println("ITER; ");
//        displayListContents(getEdgeList().iterator());
//        System.out.println(e.getVertices()[0]);
//        e.getVertices()[0].removeEdge(e);
//;
//        System.out.println("ITER; ");
//        displayListContents(getEdgeList().iterator());
//        e.setElement(null);
//        e.setVertices(null);
//        e = null;
        //System.out.println("[0]: " + e.getVertices()[0].getEdges());
    }

}
