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

import com.fci.dao.MedicineRepository;
import com.fci.models.Medicine;
import com.fci.services.MedicineService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "patient/medicine/")
public class MedicineController {

	@Autowired
	MedicineRepository repo;

	@Autowired
	MedicineService medicineService;

	@PostMapping("addMedicine")
	public void addMedicine(@RequestBody Medicine medicine) {
		repo.save(medicine);
	}

	@GetMapping("medicines")
	public List<Medicine> getMedicines() {
		return repo.findAll();
	}

	@GetMapping("statistics")
	public Map<String, Integer> getMedicineStatistics() {
		return medicineService.getStatistics();
	}
}
