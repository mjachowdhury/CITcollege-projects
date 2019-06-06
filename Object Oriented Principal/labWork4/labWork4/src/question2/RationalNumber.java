package question2;
/*
 * a)	This class called RationalNumber to perform arithmetic 
 * with fractions. It encapsulates the concept of one rational number.
 *  Use integer attributes to represent the fraction’s numerator and 
 *  denominator. Write two constructors one no argument constructor 
 *  which sets the numerator and denominator to one. The other 
 *  constructor takes two integers used to set the denominator and 
 *  numerator. Provide public methods to perform the following 
 *  operations:
                Add two rational numbers 
                Subtract two rational numbers    
                Multiply two rational numbers
                Divide 
                toString “a  / b”
               	Print output the string and floating point (double) value.               
                Add ,subtract multiply and divide methods return a rational number object.            

 */

public class RationalNumber {
	int numerator;
	int denominator;

	public RationalNumber() {
		numerator = 1;
		denominator = 1;
	}

	public RationalNumber(int x, int y) {
		numerator = x; // The numerator
		denominator = y; // The denominator
	}

	public String toString() {
		return "(" + numerator + "/" + denominator + ")";
	}

	// this function will do the addition
	public RationalNumber add(RationalNumber a) {
		return new RationalNumber(numerator * a.denominator + a.numerator * denominator, denominator * a.denominator);
	}

	// this function will do the subtraction
	public RationalNumber substract(RationalNumber a) {
		return new RationalNumber(numerator * a.denominator - a.numerator * denominator, denominator * a.denominator);
	}

	// this function will do the multiplication
	public RationalNumber multiply(RationalNumber a) {
		return new RationalNumber(numerator * a.numerator, denominator * a.denominator);
	}

	// this function will do the division
	public RationalNumber division(RationalNumber a) {
		return multiply(a.reciprocal());
	}

	// this function will find our reciprocal
	public RationalNumber reciprocal() {
		return new RationalNumber(denominator, numerator);
	}
}