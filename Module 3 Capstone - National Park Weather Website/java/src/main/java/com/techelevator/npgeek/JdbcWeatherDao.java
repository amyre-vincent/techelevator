package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWeatherDao implements WeatherDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> displayForecastForPark(String parkCode) {
		List<Weather> fiveDayForecast = new ArrayList<>();
		String get5DayWeatherByPark = "SELECT * FROM weather WHERE parkcode=?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(get5DayWeatherByPark, parkCode);
		while (results.next()) {
			Weather someWeather = new Weather();
			someWeather.setParkCode(results.getString("parkcode"));
			someWeather.setForecastDay(results.getInt("fivedayforecastvalue"));
			someWeather.setFahrenheitLow(results.getDouble("low"));
			someWeather.setFahrenheitHigh(results.getDouble("high"));
			someWeather.setForecast(results.getString("forecast"));
			fiveDayForecast.add(someWeather);
		}
		return fiveDayForecast;
	}

	public String returnAdvisory(List<Weather> weatherList) {
		String advisory = null;
		for (Weather weather : weatherList) {
			if (weather.getForecastDay() == 1) {
				if (weather.getForecast().contentEquals("snow")) {
					advisory = "Pack your snowshoes.";
				} else if (weather.getForecast().contentEquals("rain")) {
					advisory = "Pack raingear and wear waterproof shoes.";
				} else if (weather.getForecast().contentEquals("thunderstorms")) {
					advisory = "Seek shelter and avoid hiking on exposed ridges.";
				} else if (weather.getForecast().contentEquals("sun")) {
					advisory = "Pack sunblock.";
				}

			} else {
				advisory = "";
			}
			return advisory;

		}
		return advisory;
	}

	public String returnTempAdvisory(List<Weather> nextWeatherList) {
		String tempAdvisory = null;
		for (Weather weather : nextWeatherList) {
			if (weather.getForecastDay() == 1) {
				if (weather.getFahrenheitHigh() > 75.0 || weather.getFahrenheitLow() > 75.0) {
					tempAdvisory = "Bring an extra gallon of water.";
				}
				if (weather.getFahrenheitHigh() - weather.getFahrenheitLow() >= 20.0
						|| weather.getFahrenheitLow() - weather.getFahrenheitHigh() >= 20.0) {
					tempAdvisory = "Wear breathable layers.";
				}
				if (weather.getFahrenheitHigh() < 20.0 || weather.getFahrenheitLow() < 20.0) {
					tempAdvisory = "Beware of the dangers of exposure to frigid temperatures";
				}
			} else {
				tempAdvisory = "";
			}
			return tempAdvisory;
		}
		return tempAdvisory;
	}

	public String todaysWeather(List<Weather> thirdWeatherList) {
		String todaysForecast = null;
		for (Weather weather : thirdWeatherList) {
			if (weather.getForecastDay() == 1) {
				todaysForecast = "Because today's weather calls for " + weather.getForecast() + " with a high of "
						+ weather.getFahrenheitHigh() + " and a low of " + weather.getFahrenheitLow()
						+ " we recommend the following: ";
			} else {
				todaysForecast = "";
			}
			return todaysForecast;
		}
		return todaysForecast;
	}
}