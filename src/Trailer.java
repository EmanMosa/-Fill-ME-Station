
public class Trailer extends  Vehicle {
	 private int refuelingTime;
	 private int cleaningTime;
	public Trailer(int carNumber, service serviceType) {
		super(carNumber, serviceType,"Trailer");
		this.refuelingTime=5;
		this.cleaningTime=6;
}
	public int getRefuelingTime() {
		return refuelingTime;
	}
	public void setRefuelingTime(int refuelingTime) {
		this.refuelingTime = refuelingTime;
	}
	public int getCleaningTime() {
		return cleaningTime;
	}
	public void setCleaningTime(int cleaningTime) {
		this.cleaningTime = cleaningTime;
	}
	
}
