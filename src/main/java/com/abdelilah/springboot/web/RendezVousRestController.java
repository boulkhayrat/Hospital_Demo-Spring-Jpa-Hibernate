package com.abdelilah.springboot.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdelilah.springboot.entities.RendezVous;
import com.abdelilah.springboot.service.IRendezVousService;


@RestController
public class RendezVousRestController {
	
	
	@Autowired
	private IRendezVousService rendezVousService;
	
	
	@PostMapping("/rendezvous")
    public RendezVous saveRendezVous(@RequestBody RendezVous rendezVous) {
        return rendezVousService.saveRendezVous(rendezVous);
    }
	
	@GetMapping("/rendezVous")
	public List<RendezVous> rendezVousList(){
		return rendezVousService.findAllRendezVous();
	}
	
	@GetMapping("/rendezVous/{id}")
	public ResponseEntity<RendezVous> getRendezVousById(@PathVariable(value = "id") Long rendezVousId){
		return ResponseEntity.ok(rendezVousService.findRendezVousById(rendezVousId));
	}
	
	@PutMapping("/rendezVous/{id}")
	public ResponseEntity<RendezVous> updateRendezVous(@PathVariable(value= "id") Long rendezVousId, @RequestBody RendezVous rendezVous){
		return ResponseEntity.ok(rendezVousService.updateRendezVous(rendezVous));
	}
	
	@DeleteMapping("/rendezVous/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteRendezVous(@PathVariable(value ="id") Long rendezVousId){
		rendezVousService.deleteRendezVous(rendezVousId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
	
	
	
}
