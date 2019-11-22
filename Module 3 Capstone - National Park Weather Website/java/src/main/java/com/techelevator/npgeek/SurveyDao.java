package com.techelevator.npgeek;

import java.util.List;

public interface SurveyDao {

	public Survey saveSurvey(Survey survey);
	
	
	public List<Survey> getSurveyByParkCode(String parkCode);
}
