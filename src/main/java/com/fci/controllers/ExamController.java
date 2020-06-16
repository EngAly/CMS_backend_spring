package com.fci.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fci.dao.ExamRepository;
import com.fci.models.Examination;
import com.fci.services.ExaminationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "patient/exam/")
public class ExamController {

	@Autowired
	ExamRepository repo;

	@Autowired
	ExaminationService examinationService;

	@PostMapping("addExam")
	public void addExamination(@RequestBody Examination examination) {
		repo.save(examination);
	}

	@GetMapping("exams")
	public List<Examination> getExams() {
		return repo.findAll();
	}

	@GetMapping("statistics")
	public Map<String, Integer> getExamStatistics() {
		return examinationService.getStatistics();
	}
}
