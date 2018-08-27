package question3;

import java.util.ArrayList;

public class ObjectList {

	private ArrayList<Object> oList;
	private int maxNumber;

	public ObjectList(int max) {
		oList = new ArrayList<Object>();
		this.maxNumber = max;
	}

	/*
	 * This method will add object
	 */
	/*
	 * public void add(Object o){ this.oList.add(o); }
	 */

	/*
	 * This method will add object and also will check with max number if its
	 * equals with max number will not add the object else will add
	 */
	public String add(Object o) {
		if (oList.size() >= maxNumber) {
			return "Not added the Object" + o.toString();
		} else {
			this.oList.add(o);
			return "Object added" + o.toString();
		}
	}

	/*
	 * This method will check whether its full or not with variable max size
	 */
	public String isFull() {
		if (oList.size() == maxNumber) {
			return "yes";
		} else {
			return "no";
		}
	}
	/*
	 * This method will check is full or not.
	 */
	/*
	 * public void isFull(){ if(this.oList.size() == 0){
	 * System.out.println("Its empty"); } else{
	 * System.out.println("Its not empty."); } }
	 */

	/*
	 * This method will check empty or not
	 */
	public String isEmpty() {
		if (oList.size() == 0) {
			return "Yes";
		} else {
			return "No";
		}
	}

	/*
	 * This method will return total size of the object
	 */
	public int getTotal() {
		return oList.size();
	}

	/*
	 * This method will remove object by its index wise.
	 */
	public void remove(int i) {
		if (!isEmpty().equals("Yes")) {
			this.oList.remove(i);
			System.out.println("Index " + i + "Removed");
		} else {
			System.out.println("Index " + i + "Not removed");
		}
	}

	/*
	 * This method will removed object by its reference
	 */
	public void remove(Object o) {
		if (!isEmpty().equals("Yes")) {
			if (oList.contains(o)) {
				this.oList.remove(o);
				System.out.println(o + "Object removed");
			} else {
				System.out.println(o + "Object not removed.");
			}
		}
	}

	/*
	 * This method will remove object
	 */
	/*
	 * public void remove(Object o){ this.oList.remove(o); }
	 */

	/**
	 * @return the oList
	 */
	public ArrayList<Object> getList() {
		return oList;
	}

	/*
	 * This method will return specific index wise object.
	 */
	public Object getObject(int i) {
		return oList.get(i);
	}

}
