package com.fci.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fci.models.Diseases;;

@Repository
public interface DiseasesRepository extends JpaRepository<Diseases, Long> {

	Diseases findByName(String name);

	 

}