public class VertexWithWeight{
    private final Integer vertex;
    private Double weight;

    public VertexWithWeight(int v, double w){
        vertex = v;
        weight = w;
    }

    public Integer getVertex(){
        return this.vertex;
    }

    public Double getWeight(){
        return this.weight;
    }

    public void setWeight(Double a){
        weight = a;
    }

    public String toString(){
        return "(" + vertex + "," + weight + ")";
    }
    
    public boolean equals(Object o){
        if(o == null){
            return false;
        }

        if( this == o){
            return true;
        }
        
        if( getClass() == o.getClass()){
            if(vertex.intValue() == ((VertexWithWeight) o).getVertex().intValue()){
                return true;
            }
        }

        return false;
    }

}