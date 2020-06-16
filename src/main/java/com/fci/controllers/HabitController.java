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

import com.fci.dao.HabitRepository;
import com.fci.models.Habit;
import com.fci.services.HabitService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "patient/habit/")
public class HabitController {

	@Autowired
	HabitRepository repo;
	
	@Autowired
	HabitService habitService ;

	@PostMapping("addHabit")
	public void addHabit(@RequestBody Habit habit) {
		repo.save(habit);
	}

	@GetMapping("habits")
	public List<Habit> getHabits() {
		return repo.findAll();
	}
	
	@GetMapping("statistics")
	public Map<String, Integer> getHabitStatistics() {
		return habitService.getStatistics();
	}
}
