package com.abdelilah.springboot.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdelilah.springboot.entities.Medecin;


@RestController
public class MedecinRestController {
	 @PostMapping("/medecin")
	    public Medecin saveMedecin(@RequestBody Medecin medecin) {
	        return hospitalService.saveMedecin(medecin);
	    }

}
