package question3;

public class TestPerson {

	public static void main(String[] args) {
		Person person = new Person(5, "123456", "Mohammed");//Creating new obj. of Person

		// String result = person.jump();
		System.out.println(person.jump());
		System.out.println("I jumped " + person.getStars() + " times.");

		System.out.println();
		person.print();

	}

}
