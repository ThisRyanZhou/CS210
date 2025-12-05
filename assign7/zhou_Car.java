class zhou_Car implements CarFunctions
{
	final Integer id;
	final Integer fuelEconomyInMilesPerGallon;
	private Double currentFuelInGallons;
	final Integer fuelCapacityInGallons;
	final Double idleBurnRateInGallonsPerHour;

	public zhou_Car(Integer id, Integer fuelEconomy, Integer fuelCapacity, Double currentFuel, Double idleBurnRate){
		this.id = id;
		this.fuelEconomyInMilesPerGallon = fuelEconomy;
		this.currentFuelInGallons = currentFuel;
		this.fuelCapacityInGallons = fuelCapacity;
		this.idleBurnRateInGallonsPerHour = idleBurnRate;
	}
	public Integer getFuelEconomyInMilesPerGallon(){
		return this.fuelEconomyInMilesPerGallon;
	}
	public Integer getFuelCapacityInGallons(){
		return this.fuelCapacityInGallons;
	}
	public Integer getId(){
		return this.id;
	}
	public Double getIdleBurnRateInGallonsPerHour(){
		return this.idleBurnRateInGallonsPerHour;
	}
	public Double getCurrentFuelInGallons(){
		return this.currentFuelInGallons;
	}
	public String toString()
	{
		return getId() + "\t" + getFuelEconomyInMilesPerGallon() + "\t" + getFuelCapacityInGallons() + "\t" + getCurrentFuelInGallons() + "\t" + getTotalRangeInMiles() + "\t" + getRemainingRangeInMiles() + "\t" + getIdleBurnRateInGallonsPerHour();
	}
	public void setCurrentFuelInGallons(Double v){
		this.currentFuelInGallons = v;
	}
	public Double getTotalRangeInMiles(){
		return (double)fuelEconomyInMilesPerGallon * fuelCapacityInGallons;
	}
	public Double getRemainingRangeInMiles(){
		return (double)currentFuelInGallons * fuelEconomyInMilesPerGallon;
	}
	public boolean equals(Object o){
		if (o == this) return true;
		if (o == null) return false;
		if (getClass() != o.getClass()) return false;

		zhou_Car other = (zhou_Car) o;
		if (this.id == null) return other.id == null;
		return this.id.equals(other.id);
	}
	public void drive(int timeInMinutes, int speedInMilesPerHour) throws FuelException{
		double distanceTraveled = ((double)speedInMilesPerHour) * (timeInMinutes / 60.0);
		double gallonsUsed = ((double)distanceTraveled) / fuelEconomyInMilesPerGallon;
		if (gallonsUsed >= currentFuelInGallons){
			currentFuelInGallons = 0.0;
			throw new FuelException("no fuel remaining for id = " + this.id);
		}
		currentFuelInGallons = currentFuelInGallons - gallonsUsed;
	}
	public void drive(double distanceInMiles) throws FuelException{	
		double gallonsUsed = distanceInMiles / fuelEconomyInMilesPerGallon;
		if (gallonsUsed >= currentFuelInGallons){
			currentFuelInGallons = 0.0;
			throw new FuelException("no fuel remaining for id = " + this.id);
		}
		currentFuelInGallons = currentFuelInGallons - gallonsUsed;
	}
	public void idle(int durationInMinutes) throws FuelException{
		double durationInHours = durationInMinutes / 60.0;
		double gallonsUsed = durationInHours * idleBurnRateInGallonsPerHour;
		if (gallonsUsed >= currentFuelInGallons){
			currentFuelInGallons = 0.0;
			throw new FuelException("no fuel remaining for id = " + this.id);
		}
		currentFuelInGallons = currentFuelInGallons - gallonsUsed;
	}
}