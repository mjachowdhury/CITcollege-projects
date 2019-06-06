package controller;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import model.daysCategory;
import model.WeatherData;
import model.Repository;

public class ReportService {
	//i can do static or without static
	//private static Repository repo = Repository.getRepository();
	private  Repository repo = Repository.getRepository();
	
	
	public Map<String,Float> calculateCategorizedTotal()
	{
		Map<String,Float> m = new TreeMap();
		for(WeatherData weatherData : repo.weatherList)
		{
			 Long categoryId = weatherData.getCategoryId();
			 String daysName = this.getCategoryNameById(categoryId);
			
			if(m.containsKey(daysName) )
			{
				//when weatherData is already present for a category
				Float averagetotal = m.get(daysName);
				averagetotal = averagetotal+weatherData.getHours();
				m.put(daysName, averagetotal);//new total; replace old total
			}
			else
			{
				//this category is not yet added , so add here
				m.put(daysName, weatherData.getHours());
			}
		}
		return m; // return final result as Map
		
	}
	
	//To get the category name by ID
		public String getCategoryNameById(Long categoryId)
		{
			for(daysCategory c : repo.catList)
			{
				if(c.getCategoryId().equals(categoryId))
				{
					return c.getName();
				}	
			}
			return null;//no such category id present
		}


}
