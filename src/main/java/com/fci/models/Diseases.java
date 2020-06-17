package com.fci.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fci.interfaces.Peculiar;

@Entity
public class Diseases implements Peculiar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 120)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "diseases")
	Set<Patient> patient = new HashSet<>();

	public Diseases() {
	}

	/**
	 * @param name
	 */
	public Diseases(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public Set<Patient> getPatient() {
		return patient;
	}

	public void setPatient(Set<Patient> patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Diseases [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}

}
