

public class MyStaticQueue {

	// --------------------------------------------------
	// Attributes
	// --------------------------------------------------
	private int[] queueArray;
	private int queueSize;
	private int front, rear, noOfItems = 0;

	// --------------------------------------------------
	// Constructor
	// --------------------------------------------------
	public MyStaticQueue(int size) {
		queueSize = size;
		queueArray = new int[size];
	}

	// -------------------------------------------------------------------
	// Basic Operation --> Check if MyQueue is empty: isEmpty
	// -------------------------------------------------------------------

	/**
	 * This method is use to check if queue element is empty or not
	 * 
	 * @return
	 */

	public boolean isEmpty() {
		if (noOfItems == 0) {
			return true;
		}
		return false;
	}

	// -------------------------------------------------------------------
	// Basic Operation (Partial) --> Get first element from front of MyQueue:
	// first
	// -------------------------------------------------------------------

	public int first() {
		return queueArray[front];
	}

	// -------------------------------------------------------------------
	// Basic Operation (Partial) --> Add element to back of MyQueue: add
	// -------------------------------------------------------------------
	
	/**
	 * this method is used to add element back in the queue
	 * 
	 * @param data
	 */
	public void add(int input) {
		if ((noOfItems + 1) <= queueSize) {
			queueArray[rear] = input;
			rear++;
			noOfItems++;
			System.out.println("Item " + input + " was added.");
		} else {
			System.out.println("There is no space for added.");
		}
	}// end of add method

	
	// -------------------------------------------------------------------
	// Basic Operation (Partial) --> Remove element from front of MyQueue:
	// remove
	// -------------------------------------------------------------------
	
	/**
	 * This method removes an element from the front of the queue
	 */
	public void remove() {
		if (noOfItems > 0) {
			System.out.println("Item " + queueArray[front] + " was removed.");
			queueArray[front] = 0;
			front++;
			noOfItems--;
		} else {
			System.out.println("There is no item for remove.");
		}
	}// end of remove method

}