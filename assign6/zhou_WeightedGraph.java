import java.util.*;
import java.util.stream.Collectors;
public class zhou_WeightedGraph implements WeightedGraphFunctions{
    private final ArrayList<Integer> vertices;
    private final ArrayList<EdgeWithWeight> edges;
    private boolean debugOutput = false;
    public zhou_WeightedGraph(){
        vertices = new ArrayList<Integer>();
        edges = new ArrayList<EdgeWithWeight>();
    }
    public boolean hasPath(int fromVertex, int toVertex){
        return (boolean) getpath(fromVertex, toVertex, WeightedGraphReturnType.HAS_PATH);
    }
	public double getMinimumWeight(int fromVertex, int toVertex){
        return (double) getpath(fromVertex, toVertex, WeightedGraphReturnType.GET_MINIMUM_WEIGHT);
    }
	public EdgeWithWeight[] getPath(int fromVertex, int toVertex){
        return (EdgeWithWeight[]) getpath(fromVertex, toVertex, WeightedGraphReturnType.GET_PATH);
    }
    private Object getpath(int fromVertex, int toVertex, WeightedGraphReturnType typeOfInfo){
        // does not contain fromVertex or toVertex
        if(!vertices.contains(fromVertex) || !vertices.contains(toVertex)){
        //if(true){
            switch(typeOfInfo){
                case HAS_PATH: return false;
                case GET_MINIMUM_WEIGHT: return Double.NaN;
                case GET_PATH: return new EdgeWithWeight[0];

            }
        }
        //vertices size
        int size = vertices.size();
        //vertexCost
        VertexWithWeight[] cost = new VertexWithWeight[size];
        //priorityqueue
        PriorityQueue<VertexWithWeight> priQue = new PriorityQueue<>(size, new VertexWithWeightWeightComparator());
        //int[] parent
        int[] parent = new int[size];

        //for loop to make all vertex costs equal to infinity
        for (int i = 0; i<size; i++){
            parent[i] = -1;
            cost[i] = new VertexWithWeight(vertices.get(i), Double.POSITIVE_INFINITY);
        }

        //from index, to index
        int fromIndex = getIndex(fromVertex);
        int toIndex = getIndex(toVertex);
        //parent of from index is from vertex
        parent[fromIndex] = fromVertex;
        //set the cost of vertexCost for from index to 0
        cost[fromIndex].setWeight(0.0);
        //add all of vertexCost to the priority queue
        priQue.addAll(Arrays.asList(cost));

        //purpose:solve for how hard it is to get to each index
        //while loop for priorityqueue
        while(!priQue.isEmpty()){
            VertexWithWeight currentWeight = priQue.poll();
            int v = currentWeight.getVertex();
            if (parent[getIndex(v)] == -1 || currentWeight.getWeight() == Double.POSITIVE_INFINITY){
                break;
            }
            if(v == toVertex){
                break;
            }

            for(EdgeWithWeight e : edges){
                if(e.fromVertex.equals(v)){
                    int uldx = getIndex(e.toVertex);
                    double newWeight = currentWeight.getWeight() + e.weight;
                    if(newWeight < cost[uldx].getWeight()){
                        priQue.remove(cost[uldx]);
                        cost[uldx].setWeight(newWeight);
                        priQue.add(cost[uldx]);
                        parent[uldx] = v;
                    }

                }
            }

        }
        if (parent[toIndex] == -1){
            switch(typeOfInfo){
                case HAS_PATH: return false;
                case GET_MINIMUM_WEIGHT: return Double.NaN;
                case GET_PATH: return new EdgeWithWeight[0];
            }
        }
        if (typeOfInfo == WeightedGraphReturnType.HAS_PATH) return true;
        if (typeOfInfo == WeightedGraphReturnType.GET_MINIMUM_WEIGHT) return cost[toIndex].getWeight();

        //get the current vertex and weight
        //check if we succeeded or if its not posible to reach the source(val of vertex is not infinity)
        //another for loop through all the edges to find the from vertex that is equal to the current vertex
        //solve for a new weight using the current weight and the weight of the edge
        //if the new weight is less than the current weight than update the current weight to be the newweight and update parent of the index to be the current vertex

        //if parent of the toVertex is -1 than it has not been reached so return thatever we threw origionally

        //return true and weight if thats what the return type ased for

        ArrayList<Integer> reversePath = new ArrayList<>();
        int p = toVertex;
        reversePath.add(p);
        while (p != fromVertex) {
            p = parent[getIndex(p)];
            reversePath.add(p);
        }
        Collections.reverse(reversePath);
        //if it asked for a getPath
        //create an arraylist
        //while loop that starts at the end vertex or to vertex and solve backwards until you arrive to the origional and then reverse the arraylist
        EdgeWithWeight[] returnable = new EdgeWithWeight[reversePath.size() - 1];
        for (int i = 0; i < reversePath.size() - 1; i++) {
            int a = reversePath.get(i);
            int b = reversePath.get(i + 1);
            double w = 0;
            for (EdgeWithWeight e : edges) {
                if (e.fromVertex.equals(a) && e.toVertex.equals(b)) {
                    w = e.weight;
                    break;
                }
            }
            returnable[i] = new EdgeWithWeight(a, b, w);
        }
        //returnable edgeWithWeight[]
        //loop through arraylist
        //create each individual edgewithweight using the path and the edges
        return returnable;
        //return the returnable
    }
    private int getIndex(int v) {
        return vertices.indexOf(v);
    }
	public boolean addVertex(int v){
        if (vertices.contains(v)){
            return false;
        }
        vertices.add(v);
        return true;
    }
	public boolean addWeightedEdge(int from, int to, double weight){
        for(int i = 0; i < edges.size(); i++){
            if((edges.get(i).fromVertex.equals(from)) && (edges.get(i).toVertex.equals(to))){
                return false;
            }
        }
        edges.add(new EdgeWithWeight(from, to, weight));
        return true;
    }
	public String toString(){
        String vStr = vertices.stream().map(Object::toString).collect(Collectors.joining(","));
        String eStr = edges.stream().map(Object::toString).collect(Collectors.joining(","));
        return "G = (V, E)\nV = {" + vStr + "}\nE = {" + eStr + "}";
    }
}
