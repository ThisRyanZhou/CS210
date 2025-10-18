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
        if (vertices.contains(v)){
            throw new GraphException("already exists");
        }
        else{
            vertices.add(v);
        }
    }
	public void addEdge(int from, int to) throws GraphException{
        //check to see if there is from and to
        if (!(vertices.contains(from) && vertices.contains(to))){
            throw new GraphException("vertex doesnt exist");
        }
        //check to see if there is already from and to
        for(Edge a : edges){
            if (isDirected){
                if (a.fromVertex() == from && a.toVertex() == to){
                    throw new GraphException("already has edge");
                }
            }
            else{
                if((a.fromVertex() == from && a.toVertex() == to) || (a.toVertex() == from && a.fromVertex() == to)){
                    throw new GraphException("already has edge");
                }

            }
        }
        edges.add(new Edge(from, to));
    }
	public String toString() {
        StringBuilder sb = new StringBuilder();

        // Line 1
        sb.append("G = (V, E)\n");

        // Line 2 – list of vertices
        sb.append("V = {");
        for (int i = 0; i < vertices.size(); i++) {
            sb.append(vertices.get(i));
            if (i < vertices.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("}\n");

        // Line 3 – list of edges
        sb.append("E = {");
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            sb.append("(")
            .append(e.fromVertex())
            .append(",")
            .append(e.toVertex())
            .append(")");
            if (i < edges.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("}");

        return sb.toString();
    }
    
	public boolean isConnected(int startingVertex){
        if (!vertices.contains(startingVertex)){
            return false;
        }
        return getConnectedSet(startingVertex).size() == vertices.size();
    }
	// public java.util.HashSet<Integer> getConnectedSet(int startingVertex){
    //     HashSet<Integer> connectedSubset = new HashSet<>();
    //     connectedSubset.add(startingVertex);
    //     ArrayDeque<Integer> newlyAddedVertices = new ArrayDeque<>();
    //     newlyAddedVertices.add(startingVertex);
    //     while(newlyAddedVertices.isEmpty() == false){
    //         int currentVertex = newlyAddedVertices.pollFirst();
    //         for(Edge e : edges){
    //             boolean worked = false;
    //             int neighbor = 0;
    //             if(e.fromVertex() == currentVertex){
    //                 worked = true;
    //                 neighbor = e.toVertex();
    //             }
    //             if(e.toVertex() == currentVertex){
    //                 worked = true;
    //                 neighbor = e.fromVertex();
    //             }
    //             if(worked && !connectedSubset.contains(neighbor)){
    //                 connectedSubset.add(neighbor);
    //                 newlyAddedVertices.add(neighbor);
    //             }
    //         }
    //     }

    //     HashSet<Integer> connectedSubset0 = new HashSet<>();
    //     connectedSubset0.add(startingVertex);
    //     ArrayDeque<Integer> newlyAddedVertices0 = new ArrayDeque<>();
    //     newlyAddedVertices0.add(startingVertex);
    //     while(newlyAddedVertices0.isEmpty()){
    //         int currentVertex = newlyAddedVertices0.pollFirst();
    //         for(Edge e : edges){
    //             if(e.fromVertex() == currentVertex){
    //                 int neighbor = e.toVertex();
    //                 if(!connectedSubset0.contains(neighbor)){
    //                     connectedSubset0.add(neighbor);
    //                     newlyAddedVertices0.add(neighbor);
    //                 }
    //             }
    //         }
    //     }

    //     HashSet<Integer> connectedSubset1 = new HashSet<>();
    //     connectedSubset1.add(startingVertex);
    //     ArrayDeque<Integer> newlyAddedVertices1 = new ArrayDeque<>();
    //     newlyAddedVertices1.add(startingVertex);
    //     while(newlyAddedVertices1.isEmpty()){
    //         int currentVertex = newlyAddedVertices1.pollFirst();
    //         for(Edge e : edges){
    //             if(e.toVertex() == currentVertex){
    //                 int neighbor = e.fromVertex();
    //                 if(!connectedSubset1.contains(neighbor)){
    //                     connectedSubset1.add(neighbor);
    //                     newlyAddedVertices1.add(neighbor);
    //                 }
    //             }
    //         }
    //     }

    //     HashSet<Integer> returnable = new HashSet<>();
    //     for(Integer i : connectedSubset0){
    //         if(connectedSubset1.contains(i)){
    //             returnable.add(i);
    //         }
    //     }
        
    //     if(isDirected){
    //         return returnable;
    //     }
    //     else{
    //         return connectedSubset;
    //     }
    // }

    public HashSet<Integer> getConnectedSet(int startingVertex){
        if (!vertices.contains(startingVertex)) {
            return new HashSet<>(); 
        }
        if (!isDirected) {
            return getConnectedSet(startingVertex, edges);
        }
        HashSet<Integer> reachableFromStart = getConnectedSet(startingVertex, edges);
        ArrayList<Edge> reversedEdges = new ArrayList<>();
        for (Edge e : edges) {
            reversedEdges.add(new Edge(e.toVertex(), e.fromVertex()));
        }
        HashSet<Integer> reachableToStart = getConnectedSet(startingVertex, reversedEdges);
        HashSet<Integer> intersection = new HashSet<>();
        for (int v : reachableFromStart) {
            if (reachableToStart.contains(v)) {
                intersection.add(v);
            }
        }

        return intersection;
    }

    private HashSet<Integer> getConnectedSet(int startingVertex, ArrayList<Edge> edgeList) {
        HashSet<Integer> connectedSubset = new HashSet<>();
        ArrayDeque<Integer> newlyAddedVertices = new ArrayDeque<>();

        connectedSubset.add(startingVertex);
        newlyAddedVertices.add(startingVertex);

        while (!newlyAddedVertices.isEmpty()) {
            int currentVertex = newlyAddedVertices.pollFirst();

            for (Edge e : edgeList) {
                int from = e.fromVertex();
                int to = e.toVertex();

                if (!isDirected) {
                    if (from == currentVertex && !connectedSubset.contains(to)) {
                        connectedSubset.add(to);
                        newlyAddedVertices.add(to);
                    } else if (to == currentVertex && !connectedSubset.contains(from)) {
                        connectedSubset.add(from);
                        newlyAddedVertices.add(from);
                    }
                } else {
                    if (from == currentVertex && !connectedSubset.contains(to)) {
                        connectedSubset.add(to);
                        newlyAddedVertices.add(to);
                    }
                }
            }
        }

        return connectedSubset;
    }
}
