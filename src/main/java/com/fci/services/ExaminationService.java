package com.fci.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fci.dao.ExamRepository;
import com.fci.interfaces.Diagnostics;
import com.fci.models.Examination;
import com.fci.models.Patient;
import com.fci.utils.Utils;

@Service
public class ExaminationService implements Diagnostics<Examination> {

	@Autowired
	ExamRepository repo;

	@Autowired
	PatientService patientService;

	Set<Examination> records;

	String name;
	Map<String, Integer> statistics;

	public Set<Examination> getRecordByName(Patient patient) {
		records = new HashSet<Examination>();
//		using complaint name get entirely complaint record
		Utils.getNames(patient.getExaminations()).forEach(name -> records.add(repo.findByName(name)));
		return records;
	}

	@Override
	public Map<String, Integer> getStatistics() {
		statistics = new HashMap<>();
		patientService.retrieveAll().stream().forEach(patient -> {
			patient.getExaminations().stream().forEach(peculiar -> {
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
