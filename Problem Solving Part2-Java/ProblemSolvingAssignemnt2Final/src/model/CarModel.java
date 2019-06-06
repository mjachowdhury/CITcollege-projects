package model;

import java.io.Serializable;

public class CarModel implements Serializable 
{
	 
	private String driverName;
	int carDistance=0;
	 
	//constructor 
	public CarModel(String driverName) 
	{		 
		this.driverName = driverName;
		this.carDistance = 0;		 
	}
	
	//getter and setter
	public String getDriverNames() 
	{	
		return driverName;
	}

	public void setDriverNames(String driverNames) 
	{
		this.driverName = driverNames;
	}

	public int getCarDistance() 
	{
		return carDistance;
	}

	public void setCarDistance(int carDistance) 
	{
		this.carDistance = carDistance;
	}

	public String toString() 
	{
		return  this.driverName + "." ;
	}
}
