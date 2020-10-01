
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

enum service{R,C,RC}
public class FillMe {
	//number static variable for Vehicle number
	static int Number=0;
	//timer
	static int time=0;
	static List<Vehicle> VehicleList=new LinkedList<Vehicle>();  
	// current Vehicle that Refueling
	static Refueling	currentRef=new Refueling(0, null, null, 0, false);
	// current Vehicle that Cleaning
	static Cleaning 	currentClean=new Cleaning(0, null, null, 0, false);
	// next  Vehicle that waiting for Cleaning
	static LinkedList<Vehicle> nextClean=new LinkedList<Vehicle>();  
	// next  Vehicle that waiting for Refueling
	static LinkedList<Vehicle> nextRef=new LinkedList<Vehicle>();  
	//increment Timer 
	public static void incrementTime(int entryTime,int processTime  ){
		time=entryTime+processTime;
	   }
	public static void refuelingStarting(Vehicle vehicle) {
		 	currentRef.setStatuse(true);
			currentRef.setRefuelingTime(vehicle.getRefuelingTime());
			currentRef.setCarNumber(vehicle.getCarNumber());
			currentRef.setServiceType(vehicle.getServiceType());
			currentRef.setCarType(vehicle.getCarType());
			currentRef.setTempCount(vehicle.getTempCount()+1);
			VehicleList.get(currentRef.getCarNumber()).setTempCount(currentRef.getTempCount());
			currentRef.setEntryTime(time);
			System.out.println(" Vehicle "+vehicle.getCarNumber()+ " starts refueling in time "+ time+".");		
	}
	public static void cleaningStarting(Vehicle vehicle) {
		 currentClean.setStatuse(true);
		 currentClean.setCleaningTime(vehicle.getCleaningTime());
		 currentClean.setCarNumber(vehicle.getCarNumber());
		 currentClean.setServiceType(vehicle.getServiceType());
		 currentClean.setCarType(vehicle.getCarType());
		 currentClean.setTempCount(vehicle.getTempCount()+1);
		 VehicleList.get(currentClean.getCarNumber()).setTempCount(currentClean.getTempCount());
		 currentClean.setEntryTime(time);
		 System.out.println(" Vehicle "+vehicle.getCarNumber()+ " starts cleaning in time "+ time+".");	 
	}
	public static int  calculateRemainingTime(int processTime ,int entryTime){
		return processTime-(time -entryTime);
	}
	public static void vehicleTransmissionFromRefuelingToCleaning() {
		if(currentRef.getServiceType().equals(service.RC)&&currentRef.getTempCount()<2)
			if(currentClean.isStatuse()==false)
				cleaningStarting(VehicleList.get(currentRef.getCarNumber()));
			else
				nextClean.addFirst(VehicleList.get(currentRef.getCarNumber()));
	}
	public static void vehicleTransmissionFromCleaningToRefueling() {
		if(currentClean.getServiceType().equals(service.RC)&&currentClean.getTempCount()<2)
			if(currentRef.isStatuse()==false)
				refuelingStarting(VehicleList.get(currentClean.getCarNumber()));
			else
				nextRef.addFirst(VehicleList.get(currentClean.getCarNumber()));
	}
	public static void transmissionToTheNextRefuelingTurn() {
		
		currentRef.setStatuse(false);
		if(!nextRef.isEmpty()) {
			refuelingStarting(nextRef.getFirst());
			nextRef.removeFirst();
		}
	}
	public static void transmissionToTheNextCleaningTurn() {
		
		currentClean.setStatuse(false);
		if(!nextClean.isEmpty()) {
			cleaningStarting(nextClean.getFirst()) ;
			nextClean.removeFirst();
		}
	}
	public static void checkWhichVehicleIsFinish(){
		
				if(calculateRemainingTime(currentClean.getCleaningTime(),currentClean.getEntryTime())>calculateRemainingTime(currentRef.getRefuelingTime(),currentRef.getEntryTime())) 
					{
					incrementTime(currentRef.getEntryTime(),currentRef.getRefuelingTime());
					vehicleTransmissionFromRefuelingToCleaning();
					transmissionToTheNextRefuelingTurn();							
					}
				else 
					if(calculateRemainingTime(currentClean.getCleaningTime(),currentClean.getEntryTime())<calculateRemainingTime(currentRef.getRefuelingTime(),currentRef.getEntryTime())) 
						{incrementTime(currentClean.getEntryTime(),currentClean.getCleaningTime());
						vehicleTransmissionFromCleaningToRefueling();
						transmissionToTheNextCleaningTurn();		
						}
				else 
					if(calculateRemainingTime(currentClean.getCleaningTime(),currentClean.getEntryTime())==calculateRemainingTime(currentRef.getRefuelingTime(),currentRef.getEntryTime())) {
						incrementTime(currentClean.getEntryTime(),currentClean.getCleaningTime());
						vehicleTransmissionFromCleaningToRefueling();
						transmissionToTheNextRefuelingTurn();	
						transmissionToTheNextCleaningTurn();		

					
						
					}
				
		
}
	public static void witingVehicle() {
		while(nextClean.isEmpty()!=true||nextRef.isEmpty()!=true) {
			if(currentRef.isStatuse()==true && currentClean.isStatuse()==true) 
				checkWhichVehicleIsFinish();							
			else if(currentClean.isStatuse()==true)
			{
				incrementTime(currentClean.getEntryTime(),currentClean.getCleaningTime());
				vehicleTransmissionFromCleaningToRefueling();
					transmissionToTheNextCleaningTurn();
			}
			
			else if(currentRef.isStatuse()==true  ) {
				incrementTime(currentRef.getEntryTime(),currentRef.getRefuelingTime());
				vehicleTransmissionFromRefuelingToCleaning();
				transmissionToTheNextRefuelingTurn();}
		}

	}
	public static void WorkManagement(List<Vehicle> allVehicleList) {
		VehicleList.clear();
		VehicleList.addAll(allVehicleList);
		int i =0;
		while((VehicleList.size())>i) {
			if(currentRef.isStatuse()==true && currentClean.isStatuse()==true) 
				checkWhichVehicleIsFinish();
			if(VehicleList.get(i).getServiceType().equals(service.C)) {				
				 if(currentClean.isStatuse()==false) 
					cleaningStarting( VehicleList.get(i));																	
				 else 
					 nextClean.add(VehicleList.get(i));				 
			}
			else 
				if(VehicleList.get(i).getServiceType().equals(service.R)) {
					if(currentRef.isStatuse()==false) 
						refuelingStarting(  VehicleList.get(i));
					else 
						nextRef.add(VehicleList.get(i));
			}				
			else 
				if(VehicleList.get(i).getServiceType().equals(service.RC)) {
					if(currentRef.isStatuse()==false)
						refuelingStarting(  VehicleList.get(i));			
					else 
						if(currentClean.isStatuse()==false) 
							cleaningStarting( VehicleList.get(i));
					else { 
						 if(nextRef.size()<=nextClean.size()) 
							 nextRef.add(VehicleList.get(i));						
						else  
							nextClean.add(VehicleList.get(i));
					}
				}
	
			
			if (VehicleList.size()-1==i)
				witingVehicle();
			 
			i++;
		}
			
			

	}
	public static void runHomeWorkExample(List<Vehicle> newVehicleList) {
		System.out.println("***********************************");

		System.out.println("the beginning");
		newVehicleList.clear();
		Vehicle v0=new MCycle(Number++,service.RC);
		Vehicle v1=new Trailer(Number++,service.R);
		Vehicle v2=new MCycle(Number++,service.RC);
		Vehicle v3=new Private(Number++,service.C);
		Vehicle v4=new Private(Number++,service.C);
		Vehicle v5=new Private(Number++,service.R);
		Vehicle v6=new Private(Number++,service.R);
		Vehicle v7=new MCycle(Number++,service.R);
		Vehicle v8=new MCycle(Number++,service.R);
		Vehicle v9=new Private(Number++,service.RC);
		Vehicle v10=new Trailer(Number++,service.C);
		Vehicle v11=new MCycle(Number++,service.R);
		Vehicle v12=new Private(Number++,service.R);
		Vehicle v13=new Trailer(Number++,service.RC);
		Vehicle v14=new Private(Number++,service.R);
		
		newVehicleList.add(v0);
		newVehicleList.add(v1);
		newVehicleList.add(v2);
		newVehicleList.add(v3);
		newVehicleList.add(v4);
		newVehicleList.add(v5);
		newVehicleList.add(v6);
		newVehicleList.add(v7);
		newVehicleList.add(v8);
		newVehicleList.add(v9);
		newVehicleList.add(v10);
		newVehicleList.add(v11);
		newVehicleList.add(v12);
		newVehicleList.add(v13);
		newVehicleList.add(v14);
		ClearAllStaticVar();
		WorkManagement(newVehicleList);
		ClearAllStaticVar();
		System.out.println("The End");
		System.out.println("***********************************");
	}
	public static void fillMEStationFunction() {
		  List<Vehicle> fillMEStationList=new LinkedList<Vehicle>(); 

		 Scanner console = new Scanner(System.in);
		 System.out.println("***********************************");
		 System.out.println("Welcome to the 'Fill ME' Station\n1.Cleaning \n2.Refueling \n3.Refueling and Cleaning \n4.Exit\n-->");
		 int serviceChoice = console.nextInt();
		 service serviceTyp=service.R;
		 Vehicle var;
		 int vehicleChoice ;
		 if( serviceChoice ==4) { System.out.println("***********************************"); return;}
		 while(serviceChoice!=4) {
		
		 switch(serviceChoice) {
		 case 1:serviceTyp=service.C;break;
		 case 2:serviceTyp=service.R;break;
		 case 3:serviceTyp=service.RC;break;
		 default:
			    System.out.println("Wrong choice");
			    return;

		 }
		 
		 System.out.println("Vehicle Type ");
		 System.out.println("1. Private \n2. M.Cycle \n3. Trailer\n-->");
		  vehicleChoice = console.nextInt();
		 switch(vehicleChoice) {
		 case 1: var=new Private(Number++,serviceTyp);
		 fillMEStationList.add(var);
		 		System.out.println("*** Your Turn Number is "+(var.getCarNumber()+1)+"***");
				 break;
		 case 2:  var=new MCycle(Number++,serviceTyp);
		 fillMEStationList.add(var);
				 System.out.println("*** Your Turn Number is "+(var.getCarNumber()+1)+"***");
				 break;
		 case 3:  var=new Trailer(Number++,serviceTyp);
		 fillMEStationList.add(var);
		 		System.out.println("*** Your Turn Number is "+(var.getCarNumber()+1)+"***");
		 		break;
		 default:
			    System.out.println("Wrong choice");
			    return;
		 }
		 System.out.println("***********************************");
		 System.out.println("1.Another Service ?\n2.Exit\n-->");
		 int Choice = console.nextInt();
		 System.out.println("******Station Plan For Today********");
		 if(Choice==1) {
			  System.out.println("Welcome to the 'Fill ME' Station\n1.Cleaning \n2.Refueling \n3.Refueling and Cleaning \n4.Exit\n-->");
		  serviceChoice = console.nextInt();
		  if( serviceChoice ==4) {WorkManagement(fillMEStationList);System.out.println("***********************************");return;}
		 }else { WorkManagement(fillMEStationList);
		 System.out.println("***********************************");return;}
			 
		
		 }

		
		
		
	}
	static public void  ClearAllStaticVar() {
		VehicleList.clear();  
	  	currentRef=new Refueling(0, null, null, 0, false);
		currentClean=new Cleaning(0, null, null, 0, false);
		nextClean.clear();  
		nextRef.clear();
	 time=0;Number=0;
	}
	public static void main(String[] args) {
		 List<Vehicle> allVehicleList=new LinkedList<Vehicle>();  
		 Scanner console = new Scanner(System.in);
		 System.out.println("1. 'Fill ME' Station"); 
		 System.out.println("2. HomeWork Example 'Fill ME' Station");
		 System.out.println("3. Exit"); 
		 System.out.print("--> ");
		 int x = console.nextInt();
		 while(x!=3) {
		 if(x==1) {
			 ClearAllStaticVar();
			 fillMEStationFunction();
			 ClearAllStaticVar();
			 }
		 if(x==2) {
			 ClearAllStaticVar() ;
			 runHomeWorkExample(allVehicleList);}
			 
			
		 System.out.println("1.'Fill ME' Station"); 
		 System.out.println("2.HomeWork Example Fill ME Station");
		 System.out.println("3.Exit"); 
		 System.out.print("--> ");
		  x = console.nextInt();
		 
		 }
		   
		 
		 
		 System.out.println(":) exit  "); 

		
			
		

	}
	 

}
