package com.techelevator;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.JdbcWeatherDao;
import com.techelevator.npgeek.Weather;
import com.techelevator.npgeek.WeatherDao;

public class WeatherIntegrationTests {


	private static SingleConnectionDataSource dataSource;
	private WeatherDao weatherDao;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}

	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setUp() throws Exception {
		weatherDao = new JdbcWeatherDao(dataSource);
	}


	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void test() {
	List<Weather> testWeatherList = weatherDao.displayForecastForPark("CVNP");
	assertEquals(5, testWeatherList.size());
	String testForecastAdvisory = weatherDao.returnAdvisory(weatherDao.displayForecastForPark("CVNP"));
	assertEquals("Pack raingear and wear waterproof shoes.",testForecastAdvisory);
	String testTempAdvisory = weatherDao.returnTempAdvisory(weatherDao.displayForecastForPark("CVNP"));
	assertEquals("Wear breathable layers.", testTempAdvisory);
	String testTodaysWeather = weatherDao.todaysWeather(weatherDao.displayForecastForPark("CVNP"));
	assertEquals("Because today's weather calls for rain with a high of 62.0 and a low of "
			+ "38.0 we recommend the following: ", testTodaysWeather);
	}

	

	public DataSource getDataSource() {
		return dataSource;
	}
}
