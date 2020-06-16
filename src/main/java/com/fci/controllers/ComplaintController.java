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
import com.fci.dao.ComplaintsRepository;
import com.fci.models.Complaints;
import com.fci.services.ComplaintService;
 
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "patient/complaint/")
public class ComplaintController {

	@Autowired
	ComplaintsRepository repo;
	
	@Autowired
	ComplaintService complaintService;

	@PostMapping("addComplaint")
	public void addComplaint(@RequestBody Complaints complaint) {
		repo.save(complaint);
	}

	@GetMapping("complaints")
	public List<Complaints> getComplaints() {
 		return repo.findAll();
	}
	
	@GetMapping("statistics")
	public Map<String, Integer> getComplaintStatistics() {
		System.out.println(complaintService.getStatistics());
		return complaintService.getStatistics();
 	}

}
