package com.chatwebsocket.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chatwebsocket.springboot.model.LocationStats;
import com.chatwebsocket.springboot.service.CoronaVirusDataService;

@Controller
public class HomeController {

	@Autowired
	CoronaVirusDataService coronaDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<LocationStats> allStats = coronaDataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		model.addAttribute("locStats", coronaDataService.getAllStats());
		model.addAttribute("totalReportedCases", totalCases);
		return "home";
	}
}
