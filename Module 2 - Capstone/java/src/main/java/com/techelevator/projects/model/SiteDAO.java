package com.techelevator.projects.model;

import java.util.Date;
import java.util.List;

public interface SiteDAO {
	
	public List<Site> getAllSitesByCampgroundId(int campgroundId);
	public List<Site> searchAvailableSitesByCampgroundId(int campgroundId, Date sqlArrivalDate, Date sqlDepartureDate);
	
}
