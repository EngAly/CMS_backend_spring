package com.fci.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fci.dao.PatientRepository;
import com.fci.models.Complaints;
import com.fci.models.Diseases;
import com.fci.models.Examination;
import com.fci.models.Habit;
import com.fci.models.Images;
import com.fci.models.Medicine;
import com.fci.models.Patient;
import com.fci.models.PatientData;
import com.fci.utils.Utils;

/**
 * all patient APIs to save and retrieve patient data(patient details + patient
 * images) or patient details without images only
 *
 */
@Service
public class PatientService {

	@Autowired
	PatientRepository repo;

	@Autowired
	ComplaintService complaintService;

	@Autowired
	DiseasesService diseasesService;

	@Autowired
	ExaminationService examService;

	@Autowired
	MedicineService medicineService;

	@Autowired
	HabitService habitService;

	/**
	 * ---- Save PatientData (i.e Patient + Images) To Database ---------<br>
	 * 
	 * @param patient
	 * @param files
	 * @return
	 */
	public ResponseEntity<Object> savePatientData(Patient patient, MultipartFile[] files) {
		Utils.savePatientImages(files, savePatient(patient));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * ------------- Save Patient Details To Database ---------------------<br>
	 * 
	 * @param patient : patient object that has all patient details without images
	 * @return : last inserted patient id
	 */
	public Long savePatient(Patient patient) {
		patient.setComplaints(Utils.<Complaints>getDiagnosticRecords(patient, complaintService));
		patient.setDiseases(Utils.<Diseases>getDiagnosticRecords(patient, diseasesService));
		patient.setExaminations(Utils.<Examination>getDiagnosticRecords(patient, examService));
		patient.setMedicines(Utils.<Medicine>getDiagnosticRecords(patient, medicineService));
		patient.setHabits(Utils.<Habit>getDiagnosticRecords(patient, habitService));

//		return last inserted Patient
		return repo.save(patient).getId();

	}

	/**
	 * ------------- Retrieve Patient Details + Images By Id ----------<br>
	 * Retrieve all images from hard drive (back end) to show it in front end
	 * 
	 * @param patientId
	 * @return
	 */
	public PatientData retrievePatientData(Long patientId) {
		PatientData patientData = new PatientData();
		patientData.setImages(Utils.retrievePatientImages(patientId));
//		handle two argument patient + patientImages to send back to Client
//		PatientData patientData = new PatientData(getPatientById(patientId), patientImages);
		patientData.setPatient(retrievePatientById(patientId));

		return patientData;
	}

	/**
	 * ------------- Retrieve Patient Details By Id---------------<br>
	 * get patient from database by specific id to show it in front end
	 * 
	 * @param patientId
	 * @return
	 */
	public Patient retrievePatientById(Long patientId) {
//		return patientRepo.findAll().stream().findFirst().get();
		return repo.findById(patientId).get();
	}

	/**
	 * retrieve all patient images via its id
	 * 
	 * @param patientId : defined patient id to get its images via its id
	 * @return
	 */
	public ArrayList<Images> retrievePatientImagesById(Long id) {
		return Utils.retrievePatientImages(id);
	}

	public List<Patient> retrieveAll() {
		return repo.findAll();
	}

	public Page<Patient> retrievePatientPage(int page) {
		return repo.findAll(PageRequest.of(page, 8));
	}

	public ResponseEntity<Object> deletePatientById(Long id) {
		repo.deleteById(id);
		Utils.deletePatientFolder(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public List<Patient> retrivePatientByName(String name) {
		return repo.getByFullNameContainingIgnoreCase(name);
	}

	public List<Patient> retrivePatientByAge(int start, int end) {
		start = start > end ? (start + end - (end = start)) : start;
//		System.out.println("Start" + start + ", End" + end);
		return repo.getByAgeGreaterThanEqualAndAgeLessThanEqual(start, end);
	}

	public List<Patient> retrivePatientByDate(Date start, Date end) {
//		start.getTime() get time with milliseconds to subtract time then convert return to Date again
		start = start.after(end) ? new Date(start.getTime() + end.getTime() - ((end = start).getTime())) : start;
//		System.out.println(start);
//		System.out.println(end);
		return repo.getByDateOfRegistrationGreaterThanEqualAndDateOfRegistrationLessThanEqual(start, end);
	}

}
