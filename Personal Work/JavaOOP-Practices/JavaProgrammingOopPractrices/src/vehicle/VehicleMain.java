package vehicle;

public class VehicleMain {

	public static void main(String[] args) {
	 
		/*Vehicle vehicle1 = new Vehicle();//creating vehicle object
		vehicle1.setName("Honda");//setting the name of the vehicle
		System.out.println(vehicle1.getName());
		System.out.println(vehicle1.count);
		
		//calling the constructor with parameters
		Vehicle vehicle2 = new Vehicle("Toyota", "White","2017","Toyota","900");
		System.out.println(vehicle2.getColor());
		vehicle2.setColor("Blue");//changing the color of the vehicle		
		System.out.println(vehicle2.getColor());
		System.out.println(vehicle2.getSpeed());
		
		System.out.println(vehicle1.count);//it will show 2 even though i called vehicle1
		
		//I can call this function without calling any object 
		//I am calling directly Vehicle class as I have created static function of it in the Vehicle class.
		System.out.println(Vehicle.getVehicle());*/
		
		/*Car car1 = new Car("Toyota", "White","2017","Toyota","900",true, false);
		car1.setLedScreen(false);
		System.out.println(car1.getName());*/
		
		//polymorphism example
		
		Vehicle vehicle1 = new Vehicle("Toyota", "White","2017","Toyota","900");
		System.out.println(vehicle1.getInfo());
		
		Vehicle vehicle2 = new Car("Toyota", "White","2017","Toyota","900",true, false);
		System.out.println(vehicle2.getInfo());
		
		Vehicle vehicle3 = new Bike("Toyota", "White","2017","Toyota","900",true);
		System.out.println(vehicle3.getInfo());
	}

}
