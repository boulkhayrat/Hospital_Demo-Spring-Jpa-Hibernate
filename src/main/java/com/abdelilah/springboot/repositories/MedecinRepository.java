package com.abdelilah.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdelilah.springboot.entities.Medecin;


public interface MedecinRepository extends JpaRepository<Medecin, Long> {


}
