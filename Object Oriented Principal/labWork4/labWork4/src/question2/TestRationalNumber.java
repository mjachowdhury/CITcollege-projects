package question2;

/*
 * This class will test the RationalNumber all the functions 
 */
public class TestRationalNumber {
	static public void main(String args[]) {

		System.out.println("==========Addition===============");

		// Calling RationalNumber and declaring reference
		RationalNumber a, b, c;

		a = new RationalNumber(2, 2);
		b = new RationalNumber(2, 3);

		c = a.add(b);
		System.out.println(a + " + " + b + " = " + c);
		System.out.println();

		System.out.println("==========Substraction============");
		// Calling RationalNumber and declaring reference
		RationalNumber d, e, f;

		d = new RationalNumber(2, 3);
		e = new RationalNumber(1, 8);

		f = d.substract(e);

		System.out.println(d + " - " + e + " = " + f);
		System.out.println();

		System.out.println("=========Multiplication============");
		// Calling RationalNumber and declaring reference
		RationalNumber g, h, i;

		g = new RationalNumber(5, 11);
		h = new RationalNumber(2, 7);

		i = g.multiply(h);

		System.out.println(g + " * " + h + " = " + i);
		System.out.println();

		System.out.println("=========Division==================");
		// Calling RationalNumber and declaring reference
		RationalNumber j, k, l;

		j = new RationalNumber(9, 13);
		k = new RationalNumber(3, 12);

		l = j.division(k);

		System.out.println(j + " / " + k + " = " + l);
	}
}