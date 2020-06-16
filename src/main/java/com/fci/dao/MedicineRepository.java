package com.fci.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
	Medicine findByName(String name);
}
