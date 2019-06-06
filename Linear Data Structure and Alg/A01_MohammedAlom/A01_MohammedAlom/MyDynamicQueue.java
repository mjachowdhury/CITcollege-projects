 

public class MyDynamicQueue {

	// --------------------------------------------------
	// Attributes
	// --------------------------------------------------

	MyNode first;
	MyNode last;
	int size;

	// -------------------------------------------------------------------
	// Basic Operation --> Construct MyDynamicQueue
	// -------------------------------------------------------------------
	public MyDynamicQueue() {
		this.first = null;
	}

	// -------------------------------------------------------------------
	// Basic Operation --> Check if MyDynamicQueue is empty: isEmpty
	// -------------------------------------------------------------------
	public boolean isEmpty() {
		return (first == null);
	}

	// -------------------------------------------------------------------
	// Basic Operation (Partial) --> Get first element from front of
	// MyDynamicQueue: first
	// -------------------------------------------------------------------
	public int first() {
		if (first == null)
			System.out.println("No ELEMENT");
		return (first.getInfo());
	}

	// -------------------------------------------------------------------
	// Basic Operation --> Add element to back of MyDynamicQueue: add
	// -------------------------------------------------------------------
	public void add(int element) {

		MyNode current = new MyNode(element, null);

		if (first == null) {
			first = current;
			last = first;
			System.out.println("ADDED ELEMENT : " + last.getInfo());

		} else {
			last.setNext(current);
			last = current;
			System.out.println("ADDED ELEMENT : " + last.getInfo());
		}
		size++;
	}//end of add method

	// -------------------------------------------------------------------
	// Basic Operation (Partial) --> Remove element from front of
	// MyDynamicQueue: remove
	// -------------------------------------------------------------------
	public void remove() {
		if (first == null) {
			System.out.println("NO ELEMENT LEFT TO REMOVE.");
		} else {
			if (first == last) {
				first = null;
				last = null;
			} else {
				first = first.getNext();
				System.out.println("REMOVED FROM FIRST ELEMENT: " + first.getInfo());
			}
		}//end of else

	}// end of remove method

}//end of class
