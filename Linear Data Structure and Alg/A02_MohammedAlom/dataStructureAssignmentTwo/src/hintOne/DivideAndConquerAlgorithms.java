package hintOne;

/**
 * The class contains the Divide and Conquer-based Algorithms we are using. 
 */
public class DivideAndConquerAlgorithms {

	// ----------------------------------------------
	// Class constructor
	// ----------------------------------------------
	/**
	 * Constructor of the class. Do not edit it.
	 */
	public DivideAndConquerAlgorithms() {
	}

	//-------------------------------------------------------------------
	// 0. iterativeDisplayElements --> Displays all elements of a MyDynamicList 
	//-------------------------------------------------------------------	
	/**
	 * Given a concrete MyDynamicList, this iterative algorithm displays its elements by screen (if any).
	 * @param m: The MyDynamicList we want to display its elements.	  
	 */	
	public void iterativeDisplayElements(MyDynamicList m) {
		// -----------------------------
		// SET OF OPS
		// -----------------------------

		// -----------------------------
		// I. SCENARIO IDENTIFICATION
		// -----------------------------
		int scenario = 0;

		// Rule 1. MyDynamicList is empty
		if (m.length() == 0)
			scenario = 1;
		// Rule 2. MyDynamicList is non-empty
		else
			scenario = 2;

		// -----------------------------
		// II. SCENARIO IMPLEMENTATION
		// -----------------------------
		switch (scenario) {

		// Rule 1. MyDynamicList is empty
		case 1:
			// 1. We print the empty message
			System.out.println("Empty MyDynamicList");

			break;

		// Rule 2. MyDynamicList is non-empty
		case 2:
			// 1. We print the initial message
			int size = m.length();
			System.out.println("MyDynamicList Contains the following " + size + " items: ");

			// 2. We traverse the items
			for (int i = 0; i < size; i++)
				System.out.println("Item " + i + ": " + m.getElement(i));

			break;

		}

	}

	//-------------------------------------------------------------------
	// 1. maxInt --> Computes the maximum item of MyDynamicList 
	//-------------------------------------------------------------------	
	/**
	 * The function computes the maximum item of m (-1 if m is empty). 
	 * @param m: The MyDynamicList we want to compute its maximum item.
	 * @return: The maximum item of MyDynamicList	  
	 */	
	
	public int maxInt(MyDynamicList m) 
	{
		int res = m.length();
		return res;
	}

	//-------------------------------------------------------------------
	// 2. isReverse --> Computes if MyDynamicList is sorted in decreasing order 
	//-------------------------------------------------------------------	
	/**
	 * The function computes whether m is sorted in decreasing order or not.  
	 * @param m: The MyDynamicList we want to check.
	 * @return: Whether m is sorted in decreasing order or not.  
	 */	
	
	public boolean isReverse(MyDynamicList m) {
		// -----------------------------
		// Output Variable --> InitialValue
		// -----------------------------
		boolean res = true;

		// -----------------------------
		// SET OF OPS
		// -----------------------------

		// -----------------------------
		// I. SCENARIO IDENTIFICATION
		// -----------------------------
		int scenario = 0;

		// Rule 1. MyDynamicList is empty or contains just one element
		if (m.length() <= 1)
			scenario = 1;
		// Rule 2. MyDynamicList has more than one element
		else
			scenario = 2;

		// -----------------------------
		// II. SCENARIO IMPLEMENTATION
		// -----------------------------
		switch (scenario) {

		// Rule 1. MyDynamicList is empty or contains just one element
		case 1:
			// 1. We assign res to true
			res = true;

			break;

		// Rule 2. MyDynamicList has more than one element
		case 2:
			// 1. We get the first two elements of MyList
			int e0 = m.getElement(0);
			int e1 = m.getElement(1);

			// 2. If the first one is smaller than the second, we keep testing
			// the rest of MyDynamicList
			if (e0 <= e1) {
				// 2.1. We remove the first element from MyDynamicList
				m.removeElement(0);

				// 2.2. We recursively solve the smaller problem
				res = isReverse(m);

				// 2.3. We also add elem0 back to m1, so as to not to modify its
				// original state
				m.addElement(0, e0);
			}
			// 3. If the first one is bigger than the second one, we can
			// conclude MyList is not sorted
			else
				res = false;

			break;
		}

		// -----------------------------
		// Output Variable --> Return FinalValue
		// -----------------------------
		return res;
	}

	//-------------------------------------------------------------------
	// 3. getNumAppearances --> Computes the amount of times that integer appears in MyDynamicList  
	//-------------------------------------------------------------------	
	/**
	 * The function computes the amount of times that the integer n appears in m.   
	 * @param m: The MyDynamicList we want to use.
	 * @param n: The number we want to compute its appearances for.
	 * @return: The amount of appearances of n into m  
	 */	
	public int getNumAppearances(MyDynamicList m, int n)
	{
		int res = m.countNumberOfApperence(n);
		return res;		
	}

	//-------------------------------------------------------------------
	// 4. power --> Computes the m-est power of n
	//-------------------------------------------------------------------	
	/**
	 * The function computes n to the power of m.
	 * @param n: The base number.
	 * @param m: The power of n we want to compute
	 * @return: n to the power of m.  
	 */	

	public int power(int n, int m) 
	{
		//if no power then return 1
		if (m == 0) 
		{
			return 1;
		} 
		//if base is divided and no reminder then
		//doing recursive calling power method and then 'm' is divided by 2 that result is multiplying 
		else if (n % 2 == 0) 
		{
			int res = power(n, m / 2);
			return res * res;
		} 
		//here base is multiplying by recursive method power with 'm' which is power subtract by 1
		else 
		{
			return n * power(n, m - 1);
		}
	}

	//-------------------------------------------------------------------
	// 5. lucas --> Computes the n-est term of the Lucas series
	//-------------------------------------------------------------------	
	/**
	 * The function computes the n-est term of the Lucas series
	 * @param n: The n-est term of the series we want to compute
	 * @return: The term being computed 
	 */	
	public int lucas(int n) {
		// -----------------------------
		// Output Variable --> InitialValue
		// -----------------------------
		int res = 0;

		// -----------------------------
		// SET OF OPS
		// -----------------------------

		// -----------------------------
		// I. SCENARIO IDENTIFICATION
		// -----------------------------
		int scenario = 0;

		// Rule 1. We look for the index 0
		if (n == 0)
			scenario = 1;
		else {
			// Rule 2. We look for the index 1
			if (n == 1)
				scenario = 2;
			// Rule 3. We look for an index bigger than 1
			else
				scenario = 3;
		}

		// -----------------------------
		// II. SCENARIO IMPLEMENTATION
		// -----------------------------
		switch (scenario) {

		// Rule 1. We look for the index 0
		case 1:
			// 1. We assign res to 0
			res = 2;
			break;

		// Rule 2. We look for the index 1
		case 2:
			// 1. We assign res to 1
			res = 1;

			break;

		// Rule 3. We look for an index bigger than 1
		case 3:

			// 1. We recursively solve the the smaller problem of computing n-1

			if (n == 0)
				return 2;
			if (n == 1)
				return 1;

			// 2. We recursively solve the the smaller problem of computing n-2

			int n1 = lucas(n - 1);
			int n2 = lucas(n - 2);

			// 3. We compute the final result
			res = n1 + n2;
			break;
		}

		// -----------------------------
		// Output Variable --> Return FinalValue
		// -----------------------------
		return res;
	}

	// -------------------------------------------------------------------
	// 6. drawImage --> Prints a pattern of a given length
	// -------------------------------------------------------------------
	/**
	 * The function prints prints a pattern of a given length. * ** *** ...
	 * @param n: The length of the desired pattern
	 */
	public void drawImage(int n) 
	{
		//if length is 0 then print nothing
		if (n == 0) 
		{
			System.out.println();
		} 
		else 
		{
			System.out.print("*");
			drawImage(n - 1);
		}
	}

	// -------------------------------------------------------------------
	// 7. drawLine --> Prints a line of a given length
	// -------------------------------------------------------------------
	public void drawLine(int n) 
	{
		//if length is 0 then print nothing
		if (n == 0) 
		{
			System.out.println();
		} 
		else 
		{
			System.out.print("-");
			drawLine(n - 1);
		}
	}
}
