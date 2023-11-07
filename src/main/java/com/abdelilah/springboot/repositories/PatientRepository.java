package com.abdelilah.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdelilah.springboot.entities.Patient;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {


    @Query("select p from Patient p where p.nom=:nom")
    Patient findByNom(String nom);

}