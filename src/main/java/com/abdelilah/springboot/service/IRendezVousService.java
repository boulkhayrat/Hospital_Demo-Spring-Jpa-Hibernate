package com.abdelilah.springboot.service;

import java.util.List;

import com.abdelilah.springboot.entities.RendezVous;

public interface IRendezVousService {
	RendezVous saveRendezVous(RendezVous rendezVous);
	List<RendezVous> findAllRendezVous();
	RendezVous updateRendezVous(RendezVous rendezVous);
	RendezVous findRendezVousById(Long id);
	void deleteRendezVous(Long id);
}
