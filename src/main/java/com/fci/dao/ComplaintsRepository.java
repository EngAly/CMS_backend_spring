package com.fci.dao;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Complaints;
 
@Repository
public interface ComplaintsRepository extends JpaRepository<Complaints, Long> {

	
	Complaints findByName(String name);
	
}
