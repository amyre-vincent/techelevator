package com.techelevator.npgeek;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ParkController {

	@Autowired
	private ParkDao parkDao;
	@Autowired
	private WeatherDao weatherDao;
	@Autowired
	private SurveyDao surveyDao;
	
	

	@RequestMapping("/")
	public String showAllParks(ModelMap map) {
		map.put("parks", parkDao.listAllParks());
		return "ParkList";
	}

	@RequestMapping(path = "/ParkDetailList", method = RequestMethod.GET)
	public String showParkDetailList(ModelMap map, @RequestParam String parkCode) {
		map.put("park", parkDao.getParksByParkCode(parkCode));
		map.put("weathers", weatherDao.displayForecastForPark(parkCode));
		map.put("advisory", weatherDao.returnAdvisory(weatherDao.displayForecastForPark(parkCode)));
		map.put("tempAdvisory", weatherDao.returnTempAdvisory(weatherDao.displayForecastForPark(parkCode)));
		map.put("forecasts", weatherDao.todaysWeather(weatherDao.displayForecastForPark(parkCode)));
		
		
		return "ParkDetailList";
	}
	
	@RequestMapping(path = "/ParkDetailList", method = RequestMethod.POST)
	public String changeTemp(@RequestParam Boolean tempScale, @RequestParam String oldParkCode, HttpSession session) {
		session.setAttribute("inCelcius", tempScale);
		return "redirect:/ParkDetailList?parkCode=" + oldParkCode;
	}

	@RequestMapping(path = "/SurveyForm", method = RequestMethod.GET)
	public String displaySurveyForm(ModelMap models) {
		if(!models.containsAttribute("SurveyForm"))
			models.addAttribute("SurveyForm", new Survey());
		
		return "SurveyForm";
	}
	
	@RequestMapping(path = "/SurveyForm", method = RequestMethod.POST)
	public String submitSurveyForm(
		@Valid @ModelAttribute Survey SurveyForm, 
		BindingResult result, 
		RedirectAttributes flash) {
	if (result.hasErrors()) {
		flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "SurveyForm", result);
		flash.addFlashAttribute("SurveyForm", SurveyForm);
		return "redirect:/SurveyForm";
		}
		surveyDao.saveSurvey(SurveyForm);
		return "redirect:/FavoriteParksList";
		
	}
	
	@RequestMapping("/FavoriteParksList")
	public String showFavoriteParks(ModelMap map) {
		map.put("faveParks", parkDao.listFavoriteParks());
		return "FavoriteParksList";
	}

}
