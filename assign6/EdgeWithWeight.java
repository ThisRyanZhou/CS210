public class EdgeWithWeight implements EdgeWithWeightFunctions{
    Integer fromVertex;
    Integer toVertex;
    Double weight;
        
    public EdgeWithWeight(Integer fromVertex, Integer toVertex, Double weight){
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.weight = weight;
    }
    public String toString(){
        return "(" + fromVertex + "," + toVertex + "," + weight + ")";
    }
}
