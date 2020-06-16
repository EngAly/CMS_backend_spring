package com.fci.models;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;

/**
 * class that contain all patient field that client can retrieve it to get
 * information about specific patient either userId or userName
 * 
 * difference between java.sql.Date and java.util.Date <br>
 * java.sql.Date just represent DATE without time information while
 * java.util.Date represent both Date and Time information. This is the major
 * differences why java.util.Date can not directly map to java.sql.Date.
 *
 * 
 * 
 */

@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fullName, maritalStatus, occupation, ttttPlane, report;

	@Column(length = 10)
	private String gender;

	@Column(length = 5)
	private String operations, allergy;

	private int age, numberOfChildren;
	private double examCost, slapCost, followUpCost, operativeCost;
	private Date dateOfBirth, dateOfRegistration, dateOfFollowUp;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Complaints.class)
	@JoinTable(name = "patient_complaint", joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "complaint_id"))
	Set<Complaints> complaints = new HashSet<>();;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Diseases.class)
	@JoinTable(name = "patient_disease", joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "disease_id"))
	Set<Diseases> diseases = new HashSet<>();;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Examination.class)
	@JoinTable(name = "patient_examination", joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "exam_id"))
	Set<Examination> examinations = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Medicine.class)
	@JoinTable(name = "patient_medicine", joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "medicine_id"))
	Set<Medicine> medicines = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Habit.class)
	@JoinTable(name = "patient_habit", joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "habit_id"))
	Set<Habit> habits = new HashSet<>();

	public Patient() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOperations() {
		return operations;
	}

	public void setOperations(String operations) {
		this.operations = operations;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getTtttPlane() {
		return ttttPlane;
	}

	public void setTtttPlane(String ttttPlane) {
		this.ttttPlane = ttttPlane;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public double getExamCost() {
		return examCost;
	}

	public void setExamCost(double examCost) {
		this.examCost = examCost;
	}

	public double getSlapCost() {
		return slapCost;
	}

	public void setSlapCost(double slapCost) {
		this.slapCost = slapCost;
	}

	public double getFollowUpCost() {
		return followUpCost;
	}

	public void setFollowUpCost(double followUpCost) {
		this.followUpCost = followUpCost;
	}

	public double getOperativeCost() {
		return operativeCost;
	}

	public void setOperativeCost(double operativeCost) {
		this.operativeCost = operativeCost;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	public Date getDateOfFollowUp() {
		return dateOfFollowUp;
	}

	public void setDateOfFollowUp(Date dateOfFollowUp) {
		this.dateOfFollowUp = dateOfFollowUp;
	}

	public Set<Complaints> getComplaints() {
		return complaints;
	}

	public void setComplaints(Set<Complaints> complaints) {
		this.complaints = complaints;
	}

	public Set<Diseases> getDiseases() {
		return diseases;
	}

	public void setDiseases(Set<Diseases> diseases) {
		this.diseases = diseases;
	}

	public Set<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(Set<Examination> examinations) {
		this.examinations = examinations;
	}

	public Set<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}

	public Set<Habit> getHabits() {
		return habits;
	}

	public void setHabits(Set<Habit> habits) {
		this.habits = habits;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Patient [id=").append(id).append(", fullName=").append(fullName).append(", maritalStatus=")
				.append(maritalStatus).append(", occupation=").append(occupation).append(", ttttPlane=")
				.append(ttttPlane).append(", report=").append(report).append(", gender=").append(gender)
				.append(", operations=").append(operations).append(", allergy=").append(allergy).append(", age=")
				.append(age).append(", numberOfChildren=").append(numberOfChildren).append(", examCost=")
				.append(examCost).append(", slapCost=").append(slapCost).append(", followUpCost=").append(followUpCost)
				.append(", operativeCost=").append(operativeCost).append(", dateOfBirth=").append(dateOfBirth)
				.append(", dateOfRegistration=").append(dateOfRegistration).append(", dateOfFollowUp=")
				.append(dateOfFollowUp).append(", complaints=").append(complaints).append(", diseases=")
				.append(diseases).append(", examinations=").append(examinations).append(", medicines=")
				.append(medicines).append(", habits=").append(habits).append("]");
		return builder.toString();
	}

}

/**
 * info
 * 
 * @Transient : to exclude field to insert to database
 */
