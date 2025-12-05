import java.util.*;
import java.io.*;

public class zhou_ManageCarData implements ManageCarDataFunctions
{
    private final HashMap<Integer, CarFunctions> carList;
    private final PriorityQueue<CarFunctions> carListByTotalRange;
    private final PriorityQueue<CarFunctions> carListByRemainingRange;
    private final ArrayList<Integer> carIdList;
    private final ArrayList<Integer> carIdListSorted;

    public zhou_ManageCarData()
    {
        carList = new HashMap<>();
        carListByTotalRange = new PriorityQueue<>(new TotalRangeComparator());
        carListByRemainingRange = new PriorityQueue<>(new RemainingRangeComparator());
        carIdList = new ArrayList<>();
        carIdListSorted = new ArrayList<>();
    }

    public void readData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                
                String[] parts = line.split("\\t+");
                if (parts.length < 5)
                    parts = line.split("\\s+");

                if (parts.length < 5) continue; 

                Integer id = Integer.parseInt(parts[0]);
                Integer mpg = Integer.parseInt(parts[1]);
                Integer capacity = Integer.parseInt(parts[2]);
                Double currentFuel = Double.parseDouble(parts[3]);
                Double idleRate = Double.parseDouble(parts[4]);

                CarFunctions car = new zhou_Car(id, mpg, capacity, currentFuel, idleRate);

                carList.put(id, car);
                carListByTotalRange.add(car);
                carListByRemainingRange.add(car);
                carIdList.add(id);
                carIdListSorted.add(id);
            }
            selectionSort(carIdListSorted);
        } catch (Exception e){
            System.err.println("Exception reading data file '" + filename + "': " + e.toString());
            e.printStackTrace(System.err);
        }
    }

    private void selectionSort(ArrayList<Integer> a)
    {
        for (int i = 0; i < a.size(); i++)
        {
            int min = i;
            for (int j = i + 1; j < a.size(); j++)
            {
                if (a.get(j) < a.get(min))
                    min = j;
            }
            int temp = a.get(i);
            a.set(i, a.get(min));
            a.set(min, temp);
        }
    }

    public ArrayList<CarFunctions> getCarList()
    {
        ArrayList<CarFunctions> out = new ArrayList<>();

        for (Integer id : carIdListSorted)
            out.add(carList.get(id));

        return out;
    }

    public PriorityQueue<CarFunctions> getCarListByTotalRange()
    {
        PriorityQueue<CarFunctions> copy = new PriorityQueue<>(new TotalRangeComparator());

        for (CarFunctions c : carList.values())
            copy.add(c);

        return copy;
    }

    public ArrayList<CarFunctions> getCarListByTotalRangeUsingIterator()
    {
        ArrayList<CarFunctions> out = new ArrayList<>();
        Iterator<CarFunctions> it = carListByTotalRange.iterator();

        while (it.hasNext())
            out.add(it.next());

        return out;
    }

    public PriorityQueue<CarFunctions> getCarListByRemainingRange()
    {
        PriorityQueue<CarFunctions> copy = new PriorityQueue<>(new RemainingRangeComparator());

        for (CarFunctions c : carList.values())
            copy.add(c);

        return copy;
    }

    public ArrayList<CarFunctions> getCarListByRemainingRangeUsingIterator()
    {
        ArrayList<CarFunctions> out = new ArrayList<>();
        Iterator<CarFunctions> it = carListByRemainingRange.iterator();

        while (it.hasNext())
            out.add(it.next());

        return out;
    }

    public ArrayList<String> getCarListByTotalRangeViaPoll(double minTotalRange, double maxTotalRange){
        ArrayList<String> returnable = new ArrayList<String>();
        ArrayList<CarFunctions> addBack = new ArrayList<CarFunctions>();
        while(!carListByTotalRange.isEmpty()){
            CarFunctions c = carListByTotalRange.poll();
            addBack.add(c);
            double con = c.getTotalRangeInMiles();
            if(con >= minTotalRange && con <= maxTotalRange){
                String addAble = buildStringWithIndices(c);
                returnable.add(addAble);
            }
        }
        for(CarFunctions a: addBack){
            carListByTotalRange.add(a);
        }
        return returnable;
    }

    public ArrayList<String> getCarListByRemainingRangeViaPoll(double minRemainingRange, double maxRemainingRange){
        ArrayList<String> returnable = new ArrayList<String>();
        ArrayList<CarFunctions> addBack = new ArrayList<CarFunctions>();
        while(!carListByRemainingRange.isEmpty()){
            CarFunctions c = carListByRemainingRange.poll();
            addBack.add(c);
            double con = c.getRemainingRangeInMiles();
            if(con >= minRemainingRange && con <= maxRemainingRange){
                String addAble = buildStringWithIndices(c);
                returnable.add(addAble);
            }
        }
        for(CarFunctions a: addBack){
            carListByRemainingRange.add(a);
        }
        return returnable;
    }

    private String buildStringWithIndices(CarFunctions c)
    {
        String s = c.toString();

        int equalIndex = carIdList.indexOf(c.getId());
        StringBuilder fuelMatches = new StringBuilder();

        for (int i = 0; i < carIdList.size(); i++)
        {
            CarFunctions other = carList.get(carIdList.get(i));
            if (other.getFuelEconomyInMilesPerGallon().equals(c.getFuelEconomyInMilesPerGallon()))
                fuelMatches.append("\t").append(i);
        }

        return s + "\t" + equalIndex + "\t" + fuelMatches.toString().trim();
    }
    public String drive(Integer id, int time, int speed)
    {
        try
        {
            CarFunctions car = carList.get(id);
            if (car == null)
                return "";

            car.drive(time, speed);
            carListByTotalRange.remove(car);
            carListByRemainingRange.remove(car);
            carListByTotalRange.add(car);
            carListByRemainingRange.add(car);

            return "";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }
    public String drive(Integer id, double dist)
    {
        try
        {
            CarFunctions car = carList.get(id);
            if (car == null)
                return "";

            car.drive(dist);
            carListByTotalRange.remove(car);
            carListByRemainingRange.remove(car);
            carListByTotalRange.add(car);
            carListByRemainingRange.add(car);

            return "";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }

    public String idle(Integer id, int minutes)
    {
        try
        {
            CarFunctions car = carList.get(id);
            if (car == null)
                return "";

            car.idle(minutes);
            carListByTotalRange.remove(car);
            carListByRemainingRange.remove(car);
            carListByTotalRange.add(car);
            carListByRemainingRange.add(car);

            return "";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }
}
