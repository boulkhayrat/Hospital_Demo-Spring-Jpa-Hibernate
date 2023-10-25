package com.abdelilah.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdelilah.springboot.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{


}
