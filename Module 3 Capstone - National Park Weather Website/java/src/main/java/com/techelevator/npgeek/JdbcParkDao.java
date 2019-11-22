package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDao implements ParkDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> listAllParks() {
		List<Park> allParks = new ArrayList<>();
		String sqlSelectAllParks = "SELECT parkcode, parkname, parkdescription FROM park GROUP BY parkname, "
				+ "parkcode, parkdescription ORDER BY parkname ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParks);
		while (results.next()) {
			Park somePark = new Park();
			somePark.setParkCode(results.getString("parkcode"));
			somePark.setName(results.getString("parkname"));
			somePark.setDescription(results.getString("parkdescription"));
			allParks.add(somePark);
		}
		return allParks;
	}

	@Override
	public Park getParksByParkCode(String parkCode) {

		String sqlSelectParkByParkCode = "SELECT * FROM park WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectParkByParkCode, parkCode);
		Park p = new Park();
		if (results.next()) {

			p.setName(results.getString("parkname"));
			p.setDescription(results.getString("parkdescription"));
			p.setParkCode(results.getString("parkcode"));
			p.setState(results.getString("state"));
			p.setAcreage(results.getLong("acreage"));
			p.setElevation(results.getInt("elevationInFeet"));
			p.setMilesOfTrail(results.getDouble("milesoftrail"));
			p.setNumberOfCampsites(results.getInt("numberofcampsites"));
			p.setClimate(results.getString("climate"));
			p.setYearFounded(results.getInt("yearfounded"));
			p.setAnnualVisitorCount(results.getLong("annualvisitorcount"));
			p.setInspirationalQuote(results.getString("inspirationalquote"));
			p.setInspirationalQuoteSource(results.getString("inspirationalquotesource"));
			p.setEntryFee(results.getBigDecimal("entryfee"));
			p.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));

		}
		return p;
	}

	@Override
	public List<Park> listFavoriteParks() {
		List<Park> favoriteParks = new ArrayList<>();
		String sqlSelectFavoriteParks = "SELECT * FROM (SELECT B.parkname, B.parkcode, COUNT(surveyid) AS surveycount FROM survey_result A "
				+ "INNER JOIN park B ON A.parkcode = B.parkcode GROUP BY B.parkname, B.parkcode ORDER BY COUNT(surveyid) DESC, "
				+ "B.parkname ASC) AS parkinfo WHERE surveycount > 0 LIMIT 5";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectFavoriteParks);
		while(results.next()) {
			Park favePark = new Park();
			favePark.setName(results.getString("parkname"));
			favePark.setNumOfSurvey(results.getInt("surveycount"));
			favePark.setParkCode(results.getString("parkcode"));
			favoriteParks.add(favePark);
		}
		return favoriteParks;
	}

}
