package com.techelevator.projects.model.jdbc;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.techelevator.projects.model.Site;

public class SiteIntegrationTest extends DAOIntegrationTest {
	private JDBCSiteDAO sdao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setupDataSource();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		closeDataSource();
	}

	@Before
	public void setUp() throws Exception {
		sdao = new JDBCSiteDAO(getDataSource());
	}

	@After
	public void tearDown() throws Exception {
		rollback();
	}

	@Test
	public void getAllSitesByCampgroundIdShouldReturnAListWithRightLength() {
		// You will need to change the testCampgroundId to a campground id in database
		int testCampgroundId = 5;
		List<Site> actualResults = sdao.getAllSitesByCampgroundId(testCampgroundId);

		// You will need to set the expectedResultLength to the number of sites by the
		// testCampgroundId
		int expectedResultsLength = 1;
		assertEquals(expectedResultsLength, actualResults.size());

	}

	@Test
	public void searchAvailableSitesByCampgroundIdShouldReturn5AvailableCampsites() {
		// You will need to change the testCampgroundId, testSqlArrivalDate and
		// testSqlDepartureDate
		int testCampgroundId = 1;
		Date testSqlArrivalDate = Date.valueOf(LocalDate.of(2019, 10, 27));
		Date testSqlDepartureDate = Date.valueOf(LocalDate.of(2019, 10, 30));
		List<Site> actualResults = sdao.searchAvailableSitesByCampgroundId(testCampgroundId, testSqlArrivalDate,
				testSqlDepartureDate);
		
		// Note you need to change the expectedSiteId to a siteId in the database
		int expectedSiteId = 1;
		assertEquals(testCampgroundId, actualResults.get(0).getSiteCampgroundId());
		assertEquals(expectedSiteId, actualResults.get(0).getSiteId());
		
		assertEquals(5, actualResults.size());
	}
}
