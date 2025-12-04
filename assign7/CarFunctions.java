interface CarFunctions
{
	// return the fuel economy in miles per gallon of the car
	default public Integer getFuelEconomyInMilesPerGallon()
	{
		return 0;
	}
	
	// return the fuel capacity in gallons of the car
	default public Integer getFuelCapacityInGallons()
	{
		return 0;
	}
	
	// return the current gallons of fuel of the car
	default public Double getCurrentFuelInGallons()
	{
		return 0.0;
	}
	
	// return the id of the car
	default public Integer getId()
	{
		return 0;
	}
	
	// return the string represention of the car 
	public String toString();
	
	// set the current gallons of fuel of the car
	default public void setCurrentFuelInGallons(Double v)
	{
	}
	
	// get the idle burn rate
	default public Double getIdleBurnRateInGallonsPerHour()
	{
		return 0.0;
	}
	
	// get the total range of the car in miles 
	default public Double getTotalRangeInMiles()
	{
		return getFuelCapacityInGallons().doubleValue()*getFuelEconomyInMilesPerGallon().doubleValue();
	}
	
	// get the remaining range of the car in miles
	default public Double getRemainingRangeInMiles()
	{
		return getCurrentFuelInGallons().doubleValue()*getFuelEconomyInMilesPerGallon().doubleValue();
	}
	
	public boolean equals(Object o);
	
	// method to update the current fuel based on driving at a specified 
	// speed for a specified amount of time
	default public void drive(int timeInMinutes, int speedInMilesPerHour) throws FuelException
	{	
	}
	
	// method to update the current fuel based on driving a specified distance
	default public void drive(double distanceInMiles) throws FuelException
	{	
	}
	
	// method to update the current fuel based on idling a specified duration
	default public void idle(int durationInMinutes) throws FuelException
	{
	}
}
