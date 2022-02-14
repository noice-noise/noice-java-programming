package Lab20;

import java.util.ArrayList;
import java.util.List;

public class Vertex<V> {
    private V element;
    private List<Edge> edges;

    public Vertex(V element) {
        this.element = element;
        edges = new ArrayList<>();
    }

    public V getElement() {
        return element;
    }

    public void setElement(V element) {
        this.element = element;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge e){
        edges.add(e);
    }

    public void removeEdge(Edge edge){
        System.out.println("Removing..." + edge);
        int indexOfEdge = getIndexOf(edge);
        System.out.println("Index: " + indexOfEdge);

        System.out.println("Contains: " + edges.contains(edge));
        if (contains(edge)){
            this.edges.remove(edges.get(indexOfEdge));
        }
    }

    public boolean contains(Edge edge){
        if (getIndexOf(edge) != -1){
            return true;
        }
        return false;
    }

    public int getIndexOf(Edge edge){
        int count = 0;
        for (Edge e : edges){
            if (e.getElement().equals(edge.getElement())){
                System.out.println("FOUND: " + e.getElement());
                System.out.println("Count: " + count);
                return count;
            }
        }
        return -1;
    }


    public boolean hasEdge(Edge edgeToFind){
        for(Edge edgeCounter: edges){
            if (edgeCounter == edgeToFind){
                return true;
            }
        }
        return false;
    }

    public int numEdges(){
        return edges.size();
    }

    @Override
    public String toString(){
        return "[ " + element.toString() + "]";
    }

}
