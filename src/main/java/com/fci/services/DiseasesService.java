package com.fci.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fci.dao.DiseasesRepository;
import com.fci.interfaces.Diagnostics;
import com.fci.models.Diseases;
import com.fci.models.Patient;
import com.fci.utils.Utils;

@Service
public class DiseasesService implements Diagnostics<Diseases> {

	@Autowired
	DiseasesRepository repo;

	@Autowired
	PatientService patientService;

	Set<Diseases> records;

	String name;
	Map<String, Integer> statistics;

	public Set<Diseases> getRecordByName(Patient patient) {
		records = new HashSet<Diseases>();
//		using complaint name get entirely complaint record
		Utils.getNames(patient.getDiseases()).forEach(name -> records.add(repo.findByName(name)));
		return records;
	}

//	public Map<String, Integer> getStatistics() {
//		String name;
//		statistics = new HashMap<>();
//		for (Iterator<Patient> patient = patientService.retrieveAll().iterator(); patient.hasNext();) {
//			for (Iterator<Diseases> disease = patient.next().getDiseases().iterator(); disease.hasNext();) {
//				name = disease.next().getName();
//				if (statistics.get(name) == null) {
//					statistics.put(name, 1);
//				} else {
//					statistics.put(name, statistics.get(name) + 1);
//				}
//			}
//		}
//		return statistics;
//	}

//	public Map<String, Integer> getStatistics() {
//		statistics = new HashMap<>();
//		patientService.retrieveAll().spliterator().forEachRemaining(patient -> {
//			patient.getDiseases().spliterator().forEachRemaining((peculiar) -> {
//				name = peculiar.getName();
//				if (statistics.get(name) == null) {
//					statistics.put(name, 1);
//				} else {
//					statistics.put(name, statistics.get(name) + 1);
//				}
//			});
//		});
//		return statistics;
//	}

	public Map<String, Integer> getStatistics() {
		statistics = new HashMap<>();
		patientService.retrieveAll().stream().forEach(patient -> {
			patient.getDiseases().stream().forEach(peculiar -> {
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
