package com.pranav.microServices.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.microServices.Configuration;
import com.pranav.microServices.bean.LimitsConfiguration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration config;
	
	@GetMapping("/limits")
	public LimitsConfiguration getLimitConfigurationDetails() {
		
		return new LimitsConfiguration(config.getMinimum(),config.getMaximum());
		
	}
}
