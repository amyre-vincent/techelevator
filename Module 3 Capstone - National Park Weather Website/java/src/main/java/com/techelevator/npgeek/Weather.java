package com.techelevator.npgeek;

import java.text.DecimalFormat;

public class Weather {

	private String parkCode;
	private int forecastDay;
	private Double fahrenheitLow;
	private Double fahrenheitHigh;
	private Double celsiusLow;
	private Double celsiusHigh;
	private String forecast;
	private String advisory;

	
	
	public Double getFahrenheitLow() {
		return fahrenheitLow;
	}

	public void setFahrenheitLow(Double fahrenheitLow) {
		this.fahrenheitLow = fahrenheitLow;
	}

	public Double getFahrenheitHigh() {
		return fahrenheitHigh;
	}

	public void setFahrenheitHigh(Double fahrenheitHigh) {
		this.fahrenheitHigh = fahrenheitHigh;
	}

	public Double getCelsiusLow() {
		
		DecimalFormat df = new DecimalFormat("#.##");      
		Double results =(fahrenheitLow - 32.0) * (5.0 / 9.0);
		results = Double.valueOf(df.format(results));
		
		return results;
	}

	public void setCelsiusLow(Double celsiusLow) {
		this.celsiusLow = celsiusLow;
	}

	public Double getCelsiusHigh() {
		DecimalFormat df = new DecimalFormat("#.##");      
		Double result =(fahrenheitHigh - 32.0) * (5.0 / 9.0);
		result = Double.valueOf(df.format(result));

		return result;
	}

	public void setCelsiusHigh(Double celsiusHigh) {
		this.celsiusHigh = celsiusHigh;
	}

	public String getParkCode() {
		return parkCode;
	}

	public String getAdvisory() {
		return advisory;
	}

	public void setAdvisory(String advisory) {
		this.advisory = advisory;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public int getForecastDay() {
		return forecastDay;
	}

	public void setForecastDay(int forecastDay) {
		this.forecastDay = forecastDay;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}



}
