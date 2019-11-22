package com.techelevator.npgeek;

import java.util.List;

public interface WeatherDao {
	
	List<Weather> displayForecastForPark( String parkCode);
	
	String returnAdvisory(List<Weather> weatherList);
	
	String returnTempAdvisory(List<Weather> nextWeatherList);
	
	String todaysWeather(List<Weather> thirdWeatherList);
	
//	void tempConverter(String tempScale, List<Weather> weatherList);
	

}
