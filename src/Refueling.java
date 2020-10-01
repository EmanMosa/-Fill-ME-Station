
public class Refueling extends  Vehicle {
	private int RefuelingTime;
	private boolean statuse;
	
	public Refueling(int carNumber, service serviceType ,String carType, int refuelingTime, boolean statuse) {
		super( carNumber,  serviceType , carType);
		RefuelingTime = refuelingTime;
		this.statuse = statuse;
	}
	public int getRefuelingTime() {
		return RefuelingTime;
	}
	public void setRefuelingTime(int refuelingTime) {
		RefuelingTime = refuelingTime;
	}
	public boolean isStatuse() {
		return statuse;
	}
	public void setStatuse(boolean statuse) {
		this.statuse = statuse;
	}

	
	
	
	@Override
	public int getCleaningTime() {
		return 0;
	}
	@Override
	public void setCleaningTime(int cleaningTime) {
		
	}
}
