package com.techelevator.projects.model.jdbc;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.projects.model.Site;
import com.techelevator.projects.model.SiteDAO;

public class JDBCSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Site> getAllSitesByCampgroundId(int campgroundId) {
		String sqlQuery = "SELECT * FROM site WHERE campground_id = ?";

		return jdbcTemplate.query(sqlQuery,
				(rs, rowNum) -> new Site(rs.getInt("site_id"), rs.getInt("campground_id"), rs.getInt("site_number"),
						rs.getInt("max_occupancy"), rs.getBoolean("accessible"), rs.getInt("max_rv_length"),
						rs.getBoolean("utilities")),
				campgroundId);
	}

	@Override
	public List<Site> searchAvailableSitesByCampgroundId(int campgroundId, Date sqlArrivalDate,
			Date sqlDepartureDate) {
		String sqlQuery = "SELECT * FROM site WHERE site_id NOT IN\r\n" + "(SELECT site.site_id FROM campground\r\n"
				+ "INNER JOIN site \r\n" + "ON site.campground_id = campground.campground_id \r\n"
				+ "INNER JOIN reservation \r\n" + "ON reservation.site_id = site.site_id \r\n"
				+ "WHERE reservation.from_date <= ? AND reservation.to_date >= ? AND campground.campground_id = ?) LIMIT 5";

		return jdbcTemplate.query(sqlQuery,
				(rs, rowNum) -> new Site(rs.getInt("site_id"), rs.getInt("campground_id"), rs.getInt("site_number"),
						rs.getInt("max_occupancy"), rs.getBoolean("accessible"), rs.getInt("max_rv_length"),
						rs.getBoolean("utilities")),
				sqlDepartureDate, sqlArrivalDate, campgroundId);
	}

}
