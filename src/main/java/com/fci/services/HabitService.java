package com.fci.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fci.dao.HabitRepository;
import com.fci.interfaces.Diagnostics;
import com.fci.models.Habit;
import com.fci.models.Patient;
import com.fci.utils.Utils;

@Service
public class HabitService implements Diagnostics<Habit> {

	@Autowired
	HabitRepository repo;

	@Autowired
	PatientService patientService;

	String name;
	Map<String, Integer> statistics;

	Set<Habit> records;

	/**
	 * ------- Get Complaint Names From Complaint Objects From Patient---<br>
	 * get all complaint names properties from defined patient
	 * 
	 * @param patient
	 * @return
	 */
	public Set<Habit> getRecordByName(Patient patient) {
		records = new HashSet<Habit>();
//		using complaint name get entirely complaint record
		Utils.getNames(patient.getHabits()).forEach(name -> records.add(repo.findByName(name)));
		return records;
	}

	@Override
	public Map<String, Integer> getStatistics() {
		statistics = new HashMap<>();
		patientService.retrieveAll().stream().forEach(patient -> {
			patient.getHabits().stream().forEach(peculiar -> {
				name = peculiar.getName();
				if (statistics.get(name) == null) {
					statistics.put(name, 1);
				} else {
					statistics.put(name, statistics.get(name) + 1);
				}
			});
		});
		return statistics;
	}
}
