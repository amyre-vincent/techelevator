package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;



@Component
public class JdbcSurveyDao implements SurveyDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Survey saveSurvey(Survey survey) {
		String addNewSurvey = "INSERT INTO survey_result "
				+ "(parkcode, emailaddress, state, activitylevel) VALUES(?,?,?,?)";
		jdbcTemplate.update(addNewSurvey,  survey.getParkCode(), survey.getEmailAddress(),
				survey.getState(), survey.getActivityLevel());
		return survey;
	}
	
	@Override
	public List<Survey> getSurveyByParkCode(String parkCode) {
		List<Survey> surveyList = new ArrayList<>();
		String sqlSurveyByCode  = "SELECT * FROM survey_result WHERE parkcode=?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSurveyByCode, parkCode);
		while(results.next()) {
			Survey tempSurvey = new Survey();
			tempSurvey.setSurveyId(results.getLong("surveyid"));
			tempSurvey.setParkCode(results.getString("parkcode"));
			tempSurvey.setEmailAddress(results.getString("emailaddress"));
			tempSurvey.setState(results.getString("state"));
			tempSurvey.setActivityLevel(results.getString("activitylevel"));
			surveyList.add(tempSurvey);
			
		}
		return surveyList;
		
	}

}
