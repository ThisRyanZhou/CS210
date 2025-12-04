import java.util.Comparator;

public class RemainingRangeComparator implements Comparator<CarFunctions> {
    public int compare(CarFunctions a, CarFunctions b) {
		if (a.getRemainingRangeInMiles() < b.getRemainingRangeInMiles()){
			return -1;

		}
		if (a.getRemainingRangeInMiles() > b.getRemainingRangeInMiles()){
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