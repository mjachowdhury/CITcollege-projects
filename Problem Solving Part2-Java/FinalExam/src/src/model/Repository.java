package model;
import java.util.ArrayList;
/**
 * The class is used as Database/Repository and its a singleton
 * Repository should not get multiple
 */
import java.util.List;

public class Repository {
	 
	public List<WeatherData> weatherList = new ArrayList();
	
	 
	public List<daysCategory> catList = new ArrayList();
	
	private static Repository repository;//creating object
	private Repository(){
		
	}
	
	//when we call getRepository will get one Repository object
	 
	public static Repository getRepository(){
		if(repository == null){//if repository null it will give new repository
			repository = new Repository();
		}
		return repository;
	}

}
