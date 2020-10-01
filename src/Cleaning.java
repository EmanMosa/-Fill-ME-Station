
public class Cleaning extends  Vehicle  {  
	private int CleaningTime;
	private boolean statuse;
	public Cleaning( int carNumber, service serviceType ,String carType,int cleaningTime, boolean statuse) {
		super( carNumber,  serviceType , carType);
		CleaningTime = cleaningTime;
		this.statuse = statuse;
	}
	public int getCleaningTime() {
		return CleaningTime;
	}
	public void setCleaningTime(int cleaningTime) {
		CleaningTime = cleaningTime;
	}
	public boolean isStatuse() {
		return statuse;
	}
	public void setStatuse(boolean statuse) {
		this.statuse = statuse;
	}
	
	
	
	@Override
	public int getRefuelingTime() {
		return 0;
	}
	@Override
	public void setRefuelingTime(int refuelingTime) {
		
	}

}
