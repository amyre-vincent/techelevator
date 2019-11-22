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

import com.techelevator.npgeek.JdbcParkDao;
import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.ParkDao;

public class ParkIntegrationTest {

	
	private static SingleConnectionDataSource dataSource;
	private ParkDao parkDao;
	
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
		parkDao = new JdbcParkDao(dataSource);
	}

	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void test_list_of_parks_returns_all_parks_with_correct_info() {
			List<Park> testParkList = parkDao.listAllParks();
			assertEquals(10, testParkList.size());
			assertEquals("GNP",testParkList.get(2).getParkCode());
			assertEquals("Yellowstone National Park", testParkList.get(8).getName());
	}
	@Test		
	public void test_of_parks_returns_correct_park_with_correct_info_by_park_code() {
			Park newNationalPark = parkDao.getParksByParkCode("GNP");
			assertEquals(6646,newNationalPark.getElevation());
			assertEquals(923, newNationalPark.getNumberOfCampsites());
	}
	
	@Test
	//This test assumes that at least 5 different parks have at least 1 completed survey
	public void test_of_favorite_parks_list_size() {
		List<Park> testFavoriteParkList = parkDao.listFavoriteParks();
		assertEquals(5, testFavoriteParkList.size());	
	}
	

	public DataSource getDataSource() {
		return dataSource;
	}
}

