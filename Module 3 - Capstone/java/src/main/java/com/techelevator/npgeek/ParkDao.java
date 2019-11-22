package com.techelevator.npgeek;

import java.util.List;

public interface ParkDao {
	
	List<Park> listAllParks();
	public Park getParksByParkCode(String parkCode);
	List<Park> listFavoriteParks();
}

