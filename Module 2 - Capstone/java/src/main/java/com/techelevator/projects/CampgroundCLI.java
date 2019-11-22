package com.techelevator.projects;

import java.text.DateFormatSymbols;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.CampgroundDAO;
import com.techelevator.projects.model.Park;
import com.techelevator.projects.model.ParkDAO;
import com.techelevator.projects.model.ReservationDAO;
import com.techelevator.projects.model.Site;
import com.techelevator.projects.model.SiteDAO;
import com.techelevator.projects.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.projects.model.jdbc.JDBCParkDAO;
import com.techelevator.projects.model.jdbc.JDBCReservationDAO;
import com.techelevator.projects.model.jdbc.JDBCSiteDAO;
import com.techelevator.projects.view.Menu;

public class CampgroundCLI {
	private Menu menu;
	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private SiteDAO siteDAO;
	private ReservationDAO reservationDAO;
	private static final String regexMMDDYYYY = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";

	private static final String SPECIFIC_PARK_MENU_OPTION_CAMPGROUNDS = "View Campgrounds";
	private static final String SPECIFIC_PARK_MENU_OPTION_RESERVATIONS = "Search for Reservation";
	private static final String SPECIFIC_PARK_MENU_OPTION_PREVIOUS_SCREEN = "Return to Previous Screen";
	private static final String[] SPECIFIC_PARK_MENU_OPTIONS = new String[] { SPECIFIC_PARK_MENU_OPTION_CAMPGROUNDS,
			SPECIFIC_PARK_MENU_OPTION_RESERVATIONS, SPECIFIC_PARK_MENU_OPTION_PREVIOUS_SCREEN };

	private static final String CAMPGROUND_MENU_OPTION_RESERVATIONS = "Search for Available Reservations";
	private static final String CAMPGROUND_MENU_OPTION_PREVIOUS_SCREEN = "Return to Previous Screen";
	private static final String[] CAMPGROUND_MENU_OPTIONS = new String[] { CAMPGROUND_MENU_OPTION_RESERVATIONS,
			CAMPGROUND_MENU_OPTION_PREVIOUS_SCREEN };

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource dataSource) {
		this.menu = new Menu(System.in, System.out);

		parkDAO = new JDBCParkDAO(dataSource);
		campgroundDAO = new JDBCCampgroundDAO(dataSource);
		siteDAO = new JDBCSiteDAO(dataSource);
		reservationDAO = new JDBCReservationDAO(dataSource);
	}

	public void run() {
		displayApplicationBanner();

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(handleGetAllParkNames());

			if (choice.equals("Quit")) {
				System.exit(0);
			} else {
				int selectedParkId = handlePrintParkInfo(choice);
				handlePrintSpecificParkMenu(selectedParkId);
			}

		}
	}

	public String[] handleGetAllParkNames() {
		printHeading("View Parks Interface");

		List<Park> parks = parkDAO.getAllParks();
		List<String> parkNames = new ArrayList<>();

		for (Park park : parks) {
			parkNames.add(park.getParkName());
		}

		parkNames.add("Quit");

		return parkNames.toArray(new String[parks.size()]);
	}

	public int handlePrintParkInfo(String choice) {
		int sentenceLength = 17;
		Park selectedPark = parkDAO.searchParkByName(choice);

		printHeading("Park Information Screen");
		System.out.println(selectedPark.getParkName() + " National Park");
		System.out.println("Location:" + generateSpace("Location:", sentenceLength) + selectedPark.getParkLocation());
		System.out.println("Established:" + generateSpace("Established:", sentenceLength)
				+ formatDate(selectedPark.getParkEstablishDate()));
		System.out.println(
				"Area:" + generateSpace("Area:", sentenceLength) + formatInt(selectedPark.getParkArea()) + " sq km");
		System.out.println("Annual Visitors:" + generateSpace("Annual Visitors:", sentenceLength)
				+ formatInt(selectedPark.getParkNumberOfVisitors()));
		System.out.println();
		System.out.println(selectedPark.getParkDescription());
		return selectedPark.getParkId();
	}

	public void handlePrintSpecificParkMenu(int selectedParkId) {
		printHeading("Select a Command");
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(SPECIFIC_PARK_MENU_OPTIONS);

			if (choice.equals(SPECIFIC_PARK_MENU_OPTION_CAMPGROUNDS)) {
				handlePrintAllCampgroundsByParkId(selectedParkId);
				handlePrintCampgroundMenu(selectedParkId);
			} else if (choice.equals(SPECIFIC_PARK_MENU_OPTION_RESERVATIONS)) {
				// bonus
				System.out.println("This is the bonus section!");
			} else if (choice.equals(SPECIFIC_PARK_MENU_OPTION_PREVIOUS_SCREEN)) {
				return;
			}
		}
	}

	public List<Campground> handlePrintAllCampgroundsByParkId(int selectedParkId) {
		printHeading("Park Campgrounds");
		String parkName = parkDAO.getParkById(selectedParkId).getParkName();
		System.out.println(parkName + " National Park Campgrounds");
		System.out.println();

		List<Campground> campgrounds = campgroundDAO.getAllCampgroundsByParkId(selectedParkId);
		int blockLength = 20;

		System.out.print(generateSpace("", 6));
		System.out.print("Name" + generateSpace("Name", 35));
		System.out.print("Open" + generateSpace("Open", blockLength));
		System.out.print("Close" + generateSpace("Close", blockLength));
		System.out.print("Daily Fee" + generateSpace("DailyFee", blockLength));

		for (int i = 0; i < campgrounds.size(); i++) {
			int lineNum = i + 1;
			String campgroundName = campgrounds.get(i).getCampgroundName();
			String campgroundOpen = getMonth(Integer.parseInt(campgrounds.get(i).getCampgroundOpenFromMm()));
			String campgroundClose = getMonth(Integer.parseInt(campgrounds.get(i).getCampgroundOpenToMm()));
			String campgroundDailyFee = "$" + campgrounds.get(i).getCampgroundDailyFee();
			System.out.println();
			System.out.print("#" + lineNum + generateSpace(String.valueOf(lineNum), 6-String.valueOf(lineNum).length()));
			System.out.print(campgroundName + generateSpace(campgroundName, 35));
			System.out.print(campgroundOpen + generateSpace(campgroundOpen, blockLength));
			System.out.print(campgroundClose + generateSpace(campgroundClose, blockLength));
			System.out.print(campgroundDailyFee + generateSpace(campgroundDailyFee, blockLength));
		}

		System.out.println();
		return campgrounds;
	}

	public void handlePrintCampgroundMenu(int selectedParkId) {
		printHeading("Select a Command");
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(CAMPGROUND_MENU_OPTIONS);

			if (choice.equals(CAMPGROUND_MENU_OPTION_RESERVATIONS)) {
				handleSearchForCampgroundReservations(selectedParkId);
			} else if (choice.equals(CAMPGROUND_MENU_OPTION_PREVIOUS_SCREEN)) {
				return;
			}

		}
	}
	
	public void handleSearchForCampgroundReservations(int selectedParkId) {
		List<Campground> campgrounds = handlePrintAllCampgroundsByParkId(selectedParkId);
		System.out.println("Which campground (enter 0 to cancel)?");
		
		try {
			int choice = Integer.parseInt((String) menu.getUserInput());
			if (choice > campgrounds.size() || choice < 0) {
				throw new NumberFormatException();
			}
			
			if (choice == 0) {
				return;
			}
			
			Campground selectedCampground = campgrounds.get(choice-1);
			System.out.println(selectedCampground.getCampgroundName());
			
			System.out.println("What is the arrival date (yyyy-mm-dd)?");
			String sqlArrivalDate = (String) menu.getUserInput();
			if (!sqlArrivalDate.matches(regexMMDDYYYY)) {
				throw new DateTimeException("Please enter a valid date!");
			}
			
			System.out.println("What is the departure date (yyyy-mm-dd)?");
			String sqlDepartureDate = (String) menu.getUserInput();
			if (!sqlDepartureDate.matches(regexMMDDYYYY)) {
				throw new DateTimeException("Please enter a valid date!");
			}
			
			handlePrintAvailableSites(selectedCampground, convertStringToDate(sqlArrivalDate), convertStringToDate(sqlDepartureDate));
		} catch (NumberFormatException ne) {
			System.out.println("Please enter a number that is listed.");
		} catch (DateTimeException de) {
			System.out.println("Please enter a valid date!");
		}
	}
	
	public void handlePrintAvailableSites(Campground campground, Date sqlArrivalDate, Date sqlDepartureDate) {
		System.out.println(sqlArrivalDate);
		List<Site> sites = siteDAO.searchAvailableSitesByCampgroundId(campground.getCampgroundId(), sqlArrivalDate, sqlDepartureDate);
		System.out.println("Results Matching Your Search Criteria");
		for (Site site : sites) {
			System.out.print("Site No. " + site.getSiteId());
			System.out.print("Max Occp" + site.getSiteMaxOccupancy());
			System.out.println("Accessible" + site.isSiteAccessible());
			System.out.println("Max RV Length" + site.getSiteMaxRVLength());
			System.out.println("Utility" + site.isSiteUtilities());
			System.out.println("Cost" + campground.getCampgroundDailyFee());
		}
	}
	

	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	public static String generateSpace(String str, int totalSentenceLength) {
		int spaceLength = totalSentenceLength - str.length();
		String space = "";

		for (int i = 1; i <= spaceLength; i++) {
			space += " ";
		}

		return space;
	}

	public static String formatDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
		return formatter.format(date);
	}

	public static String formatInt(int num) {
		return String.format("%,d", num);
	}

	public static String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month - 1];
	}
	
	public static Date convertStringToDate(String str){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDateObj = LocalDate.parse(str, formatter);
		return Date.valueOf(localDateObj);
	}

	public void displayApplicationBanner() {
		System.out.println(
				"     c  c            \\\\\\    ///    ))          \\/       ))         .-.      wWw  wWw   \\\\\\  ///       _  \r\n"
						+ "     (OO)      /)    ((O)  (O))   (o0)-.      (OO)     (Oo)-.    c(O_O)c    (O)  (O)   ((O)(O))     _||\\ \r\n"
						+ "   ,'.--.)   (o)(O)   | \\  / |     | (_))   ,'.--.)     | (_))  ,'.---.`,   / )  ( \\    | \\ ||     (_'\\  \r\n"
						+ "  / //_|_\\    //\\\\    ||\\\\//||     | .-'   / /|_|_\\     |  .'  / /|_|_|\\ \\ / /    \\ \\   ||\\\\||     .'  | \r\n"
						+ "  | \\___     |(__)|   || \\/ ||     |(      | \\_.--.     )|\\\\   | \\_____/ | | \\____/ |   || \\ |    ((_) | \r\n"
						+ "  '.    )    /,-. |   ||    ||      \\)     '.   \\) \\   (/  \\)  '. `---' .` '. `--' .`   ||  ||     `-`.) \r\n"
						+ "    `-.'    -'   ''  (_/    \\_)     (        `-.(_.'    )        `-...-'     `-..-'    (_/  \\_)       (  ");
	}
}
