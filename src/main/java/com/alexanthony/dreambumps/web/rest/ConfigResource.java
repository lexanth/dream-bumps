package com.alexanthony.dreambumps.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexanthony.dreambumps.config.Constants;
import com.alexanthony.dreambumps.service.dto.ConfigDTO;


@RestController
@RequestMapping("/api")
public class ConfigResource {
	private final Logger log = LoggerFactory.getLogger(ConfigResource.class);
	
	@GetMapping("/config")
	public ResponseEntity<ConfigDTO> getConfig() {
		ConfigDTO config = new ConfigDTO();
		config.setCrewsPerDivision(Constants.CREWS_PER_DIVISION);
		config.setMensCrews(Constants.MENS_CREWS);
		config.setWomensCrews(Constants.WOMENS_CREWS);
		return ResponseEntity.ok(config);
	}

}
