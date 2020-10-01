
public class MCycle extends  Vehicle{
	 private int refuelingTime;
	 private int cleaningTime;
	public MCycle(int carNumber, service serviceType) {
		super(carNumber, serviceType,"MCycle");
		this.refuelingTime=1;
		this.cleaningTime=2;
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
