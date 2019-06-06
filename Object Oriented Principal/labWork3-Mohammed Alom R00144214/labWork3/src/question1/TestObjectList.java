package question1;

/**
 * Write a description of class TestObjectList here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestObjectList {

	public static void main(String[] args) {
		ObjectList ol = new ObjectList(3);

		String s = "Im Happy";
		Dog d = new Dog();// need Dog class
		DVD v = new DVD();// need DVD class
		Integer i = 1234;

		System.out.println(ol.add(s));
		System.out.println(ol.add(d));
		System.out.println(ol.add(v));

		// can't add because of no of object in the constructor
		System.out.println(ol.add(i));

		ol.remove(0);// will remove first index
		// now it will add Integer object
		System.out.println(ol.add(i));

		// have to add object reference ol on both methods.
		System.out.println("Is the list full? " + ol.isFull());
		System.out.println("Is the list empty? " + ol.isEmpty());

		// have to add object reference ol on methods.
		System.out.println("Total number of objects in the list: " + ol.getTotal());

		// have to casting and need to change index number to get the Dog object
		// as we deleted 0th index first now Dog is now in the 0th index.
		Dog g = (Dog) ol.getObject(0);
		g.bark();

	}
}
