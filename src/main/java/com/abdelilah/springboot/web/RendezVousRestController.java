package com.abdelilah.springboot.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdelilah.springboot.entities.RendezVous;


@RestController
public class RendezVousRestController {
	
	
	
	@PostMapping("/rendezvous")
    public RendezVous saveRendezVous(@RequestBody RendezVous rendezVous) {
        return rendezVousService.saveRdv(rendezVous);
    }
}
