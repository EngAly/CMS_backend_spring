package com.fci.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fci.dao.DiseasesRepository;
import com.fci.models.Diseases;
import com.fci.services.DiseasesService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "patient/disease/")
public class DiseaseController {

	@Autowired
	DiseasesRepository repo;

	@Autowired
	DiseasesService diseasesService;

	@PostMapping("addDisease")
	public void addDisease(@RequestBody Diseases disease) {
		repo.save(disease);
	}

	@GetMapping("diseases")
	public List<Diseases> getDiseases() {
		return repo.findAll();
	}
	
	@GetMapping("statistics")
	public Map<String, Integer> getDiseaseStatistics() {
 		return diseasesService.getStatistics();
 	}

}
