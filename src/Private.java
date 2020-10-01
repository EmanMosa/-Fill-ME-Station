
public class Private extends  Vehicle {
	 private int refuelingTime;
	 private int cleaningTime;
	public Private(int carNumber, service serviceType) {
		super(carNumber, serviceType,"Private");
		this.refuelingTime=3;
	this.cleaningTime=4;
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
