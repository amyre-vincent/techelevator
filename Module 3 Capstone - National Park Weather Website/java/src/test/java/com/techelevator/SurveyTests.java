
package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.JdbcSurveyDao;
import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.SurveyDao;

public class SurveyTests {

	private static SingleConnectionDataSource dataSource;
	private SurveyDao surveyDao;

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
		surveyDao = new JdbcSurveyDao(dataSource);
	}

	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void test_save_survey_and_get_survey_list_by_park_code() {
		List<Survey> testSurveyList = surveyDao.getSurveyByParkCode("GNP");
		Survey testSurvey = surveyDao.saveSurvey(testSurveyList.get(0));
		
		assertEquals("GNP", testSurvey.getParkCode());
		
		
	}

	public DataSource getDataSource() {
		return dataSource;
	}
}
