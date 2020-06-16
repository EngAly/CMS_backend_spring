package com.fci.dao;

 import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	public List<Patient> getByFullNameContainingIgnoreCase(String fullName);

//	@Query("SELECT age FROM patient p WHERE p.eventsDate BETWEEN :startDate AND :endDate")

//	@Query("SELECT p FROM patient p WHERE p.age BETWEEN :start AND :end")
//	public List<Patient> getByAge(@Param("start") int start, @Param("end") int end);

	public List<Patient> getByAgeGreaterThanEqualAndAgeLessThanEqual(int start, int end);
	
	public List<Patient> getByDateOfRegistrationGreaterThanEqualAndDateOfRegistrationLessThanEqual(Date start, Date end);

}
