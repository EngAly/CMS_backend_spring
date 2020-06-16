package com.fci.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fci.dao.ComplaintsRepository;
import com.fci.interfaces.Diagnostics;
import com.fci.models.Complaints;
import com.fci.models.Patient;
import com.fci.utils.Utils;

/**
 * important note :- ArrayList<String> complaintNames; when declare it with new
 * in function so that will be created when call function
 * 
 * ArrayList<String> complaintNames = new ArrayList<String>(); when declare it
 * in class will be call when application so that when add element will added to
 * each in each insert so that its life cycle with the class called
 */
@Service
public class ComplaintService implements Diagnostics<Complaints> {

	@Autowired
	ComplaintsRepository repo;

	@Autowired
	PatientService patientService;

	Set<Complaints> records;

	String name;
	Map<String, Integer> statistics;

	/**
	 * ------- Get Complaint Names From Complaint Objects From Patient---<br>
	 * get all complaint names properties from defined patient
	 * 
	 * @param patient
	 * @return
	 */
	public Set<Complaints> getRecordByName(Patient patient) {
		records = new HashSet<Complaints>();
//		using complaint name get entirely complaint record
		Utils.getNames(patient.getComplaints()).forEach(name -> records.add(repo.findByName(name)));
		return records;
	}

	@Override
	public Map<String, Integer> getStatistics() {
		statistics = new HashMap<>();
		patientService.retrieveAll().stream().forEach(patient -> {
			patient.getComplaints().stream().forEach(peculiar -> {
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
