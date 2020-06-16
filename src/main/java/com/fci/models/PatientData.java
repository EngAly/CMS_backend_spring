package com.fci.models;

import java.util.ArrayList;

/**
 * API that content two primary objects<br>
 * => patient or (patient details) that contain all patient fields name, age,
 * BOD,...etc<br>
 * 
 * => images that content all patient images (i.e examination images + profile
 * images).
 * 
 */
public class PatientData {

	private Patient patient=new Patient();
	private ArrayList<Images> images;

	
	public PatientData() {
 	}

	/**
	 * @param patient
	 * @param images
	 */
	public PatientData(Patient patient, ArrayList<Images> images) {
		this.patient = patient;
		this.images = images;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ArrayList<Images> getImages() {
		return images;
	}

	public void setImages(ArrayList<Images> images) {
		this.images = images;
	}

}
