package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String[] MONTHS = {"January","February","March","April","May","June","July","August","September","October","November","December"};   
	//This method will take date as a string and will return date object
	public static Date stringToDate(String dateAsString)
	{
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return df.parse(dateAsString);
		} catch (ParseException ex) {		 
			ex.printStackTrace();
			return null;
		}
	}
	
	//This method will return date object as a string 
	public static String dateToString(Date date)
	{
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}
	
	public static String getYearAndMonth(Date date)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy,MM");//Ex.2016,01, 2016,02
		return df.format(date);
	}
	
	
	public static Integer getYear(Date date)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return new Integer(df.format(date));
	}
	
	
	public static String getMonthName(Integer monthNo)
	{
		return MONTHS[monthNo-1];
	}
	
	

}
