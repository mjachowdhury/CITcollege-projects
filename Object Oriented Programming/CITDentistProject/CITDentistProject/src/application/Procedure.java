package application;

import java.io.Serializable;

public class Procedure implements Serializable {
	static int COUNTER = 1;
	private final int procNo;
	private String procName;
	private double procCost;

	public Procedure() {
		procNo = COUNTER++;
	}

	public Procedure(String procName, double procCost) {
		procNo = COUNTER++;
		this.procName = procName;
		this.procCost = procCost;
	}

	public int getProcNo() {
		return procNo;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public double getProcCost() {
		return procCost;
	}

	public void setProcCost(double procCost) {
		this.procCost = procCost;
	}

	public String toString() {
		return getProcName();
	}
 
	/* 
	public String toString1() {
		return "Procedure [procNo=" + procNo + ", procName=" + procName + ", procCost=" + procCost + "]";
	}*/

	public void print() {
		System.out.println(toString());
	}
}
