import java.util.Comparator;

public class TotalRangeComparator implements Comparator<CarFunctions> {
    public int compare(CarFunctions a, CarFunctions b) {
		if (a.getTotalRangeInMiles() < b.getTotalRangeInMiles()){
			return -1;

		}
		if (a.getTotalRangeInMiles() > b.getTotalRangeInMiles()){
			return 1;
			
		}
		if (a.getId() < b.getId()){
			return -1;
			
		}
		if (a.getId() > b.getId()){
			return 1;

		}
		return 0;
    }
}