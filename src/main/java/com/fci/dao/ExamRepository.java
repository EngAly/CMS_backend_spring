package com.fci.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Examination;

@Repository
public interface ExamRepository extends JpaRepository<Examination, Long> {

	Examination findByName(String name);
	
}