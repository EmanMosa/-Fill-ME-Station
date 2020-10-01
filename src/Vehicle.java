
public abstract  class Vehicle   {
	private int carNumber;
	private String carType;
	 private service serviceType;
	 private int tempCount;
	 private int entryTime;
	 public Vehicle(Vehicle v) {
		 if(v!=null) {
		 this.carNumber =v.carNumber;
			this.serviceType =v. serviceType;
			this.carType=v.carType;
			this.setRefuelingTime(v.getRefuelingTime());
			this.setCleaningTime(v.getCleaningTime());
			this.entryTime=v.entryTime;
			this.tempCount=0;
		 }
	 }
	 public Vehicle(int carNumber, service serviceType ,String carType  ) {
			this.carNumber = carNumber;
			this.serviceType = serviceType;
			this.setCarType(carType);
			this.tempCount=0;

		}
	 public int getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(int carNumber) {
		this.carNumber = carNumber;
	}
	public service getServiceType() {
		return serviceType;
	}
	public void setServiceType(service serviceType) {
		this.serviceType = serviceType;
	}
	abstract public int getRefuelingTime();
	abstract public void setRefuelingTime(int refuelingTime);
	abstract	public int getCleaningTime();
	abstract	public void setCleaningTime(int cleaningTime);
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public int getTempCount() {
		return tempCount;
	}
	public void setTempCount(int TempCount) {
		
			this.tempCount=TempCount;
	}
	public int getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(int entryTime) {
		this.entryTime = entryTime;
	}

}
