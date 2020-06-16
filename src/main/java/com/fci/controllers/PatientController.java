package com.fci.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fci.models.Images;
import com.fci.models.Patient;
import com.fci.models.PatientData;
import com.fci.services.PatientService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "patient/")
public class PatientController {

	@Autowired
	PatientService patientService;

	/**
	 * save patient details + images to store them to database
	 * 
	 * @param files
	 * @param patient
	 * @return
	 */
	@PostMapping("addPatientData")
	public void addPatient(@RequestParam("file") MultipartFile[] files, @RequestPart("patient") Patient patient) {
		patientService.savePatientData(patient, files);
	}

	/**
	 * save patient details without images to database
	 * 
	 * @param patient
	 * @return
	 */
	@PostMapping("addPatientDetails")
	public void addPatient(@RequestBody Patient patient) {
		System.out.println(patient.toString());
		patientService.savePatient(patient);
	}

	/**
	 * ------------- Retrieve Patient Data To Front End ---------------<br>
	 * 
	 * @param patientId : set patient id that user want its data
	 * @return : object of patient data that has patient details + patient images
	 */
	@GetMapping(path = { "getPatientData/{patient_id}" })
	public PatientData getPatientData(@PathVariable("patient_id") Long patientId) {
		return patientService.retrievePatientData(patientId);
	}

	@GetMapping("patients")
	public List<Patient> getPatients() {
		return patientService.retrieveAll();
	}

	@GetMapping("patientPage")
	public Page<Patient> getPatientPage(@RequestParam(defaultValue = "0") int page) {
		return patientService.retrievePatientPage(page);
	}

	@GetMapping("patientImagesById/{id}")
	public ArrayList<Images> getPatientImagesById(@PathVariable("id") Long id) {
		return patientService.retrievePatientImagesById(id);
	}

	@DeleteMapping("deletePatient/{id}")
	public ResponseEntity<Object> deletePatientById(@PathVariable("id") Long id) {
		return patientService.deletePatientById(id);
	}

	@GetMapping("searchByName/{name}")
	public List<Patient> getPatientByName(@PathVariable("name") String name) {
		return patientService.retrivePatientByName(name);
	}

	@GetMapping("searchByAge")
	public List<Patient> getPatientByAge(@RequestParam int start, @RequestParam int end) {
		return patientService.retrivePatientByAge(start, end);
	}

	@GetMapping("searchByDate")
	public List<Patient> getPatientByDate(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date start, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date end) {
		return patientService.retrivePatientByDate(start, end);
	}

}
