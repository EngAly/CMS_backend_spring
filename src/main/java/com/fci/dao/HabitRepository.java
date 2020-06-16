package com.fci.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Habit;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
	
	Habit findByName(String name);

}
