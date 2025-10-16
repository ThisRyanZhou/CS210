import java.util.*;

public class zhou_lab7{
    public static void main(String[] args){
        PriorityQueue<VertexWithWeight> p = new PriorityQueue<>(new VertexWithWeightWeightComparator());
        VertexWithWeight[] inputs = new VertexWithWeight[10];
        inputs[0] = new VertexWithWeight(0, 0.0);
        inputs[1] = new VertexWithWeight(1, 1.0);
        inputs[2] = new VertexWithWeight(2, 2.0);
        inputs[3] = new VertexWithWeight(3, 3.0);
        inputs[4] = new VertexWithWeight(4, 4.0);
        inputs[5] = new VertexWithWeight(5, 0.0);
        inputs[6] = new VertexWithWeight(6, 1.0);
        inputs[7] = new VertexWithWeight(7, 2.0);
        inputs[8] = new VertexWithWeight(8, 3.0);
        inputs[9] = new VertexWithWeight(9, 4.0);
        for(int i = 0; i < inputs.length; i++){
            p.add(inputs[i]);
        }
        System.out.println("Iterator");
        Iterator<VertexWithWeight> iterator = p.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        System.out.println("toArray");
        VertexWithWeight[] a = p.toArray(new VertexWithWeight[0]);
        for(int i = 0; i<a.length; i++){
            System.out.println(a[i].toString());
        }
        System.out.println("polling");
        while(!p.isEmpty()){
            System.out.println(p.poll());
        }

        //readded and then set weight to 10
        System.out.println("set to 10 and readd");
        for(int i = 0; i < inputs.length; i++){
            p.add(inputs[i]);
        }
        inputs[0].setWeight(10.0);
        System.out.println("Iterator");
        iterator = p.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        System.out.println("toArray");
        a = p.toArray(new VertexWithWeight[0]);
        for(int i = 0; i<a.length; i++){
            System.out.println(a[i].toString());
        }
        System.out.println("polling");
        while(!p.isEmpty()){
            System.out.println(p.poll());
        }

        System.out.println("removed, changed, and readded");
        inputs[0].setWeight(0.0);
        for(int i = 0; i < inputs.length; i++){
            p.add(inputs[i]);
        }
        p.remove(inputs[0]);
        inputs[0].setWeight(10.0);
        p.add(inputs[0]);
        System.out.println("polling");
        while(!p.isEmpty()){
            System.out.println(p.poll());
        }
    }
}