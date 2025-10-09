import java.util.*;

public class zhou_Graph implements ConnectedGraphFunctions{
    private final ArrayList<Integer> vertices;
    private final ArrayList<Edge> edges;
    private final boolean isDirected;

    zhou_Graph(){
        isDirected = false;
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    zhou_Graph(boolean a){
        isDirected = a;
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    public int getNumberOfVertices(){
        return vertices.size();
    }
	public int getNumberOfEdges(){
        return edges.size();
    }
	public boolean isDirected(){
        return isDirected;
    }
	public void addVertex(int v) throws GraphException{
    }
	public void addEdge(int from, int to) throws GraphException{
    }
	public String toString(){
        return "";
    }
	public boolean isConnected(int startingVertex){
        return true;
    }
	public java.util.HashSet<Integer> getConnectedSet(int startingVertex){
        HashSet<Integer> connectedSubset = new HashSet<>();
        connectedSubset.add(startingVertex);
        ArrayDeque<Integer> newlyAddedVertices = new ArrayDeque<>();
        newlyAddedVertices.add(startingVertex);
        while(newlyAddedVertices.isEmpty() == false){
            int currentVertex = newlyAddedVertices.pollFirst();
            
        }
        return new HashSet<>();
    }
}
