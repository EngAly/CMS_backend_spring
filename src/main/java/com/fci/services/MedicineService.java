package com.fci.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fci.dao.MedicineRepository;
import com.fci.interfaces.Diagnostics;
import com.fci.models.Medicine;
import com.fci.models.Patient;
import com.fci.utils.Utils;

@Service
public class MedicineService implements Diagnostics<Medicine> {

	@Autowired
	MedicineRepository repo;

	@Autowired
	PatientService patientService;

	Set<Medicine> records;

	String name;
	Map<String, Integer> statistics;

	@Override
	public Set<Medicine> getRecordByName(Patient patient) {
		records = new HashSet<Medicine>();
//		using complaint name get entirely complaint record
		Utils.getNames(patient.getMedicines()).forEach(name -> records.add(repo.findByName(name)));
		return records;
	}

	@Override
	public Map<String, Integer> getStatistics() {
		statistics = new HashMap<>();
		patientService.retrieveAll().stream().forEach(patient -> {
			patient.getMedicines().stream().forEach(peculiar -> {
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
