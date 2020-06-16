package com.fci.interfaces;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fci.models.Patient;
import com.fci.services.PatientService;

public interface Diagnostics<T> {

//	@Autowired
//	public static final PatientService patientService  ;
	
	public Set<T> getRecordByName(Patient patient);

	public Map<String, Integer> getStatistics();
}
