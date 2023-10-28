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

import com.abdelilah.springboot.entities.Consultation;
import com.abdelilah.springboot.service.IConsultationService;


@RestController
public class ConsultationRestController {
	@Autowired
	private IConsultationService consultationService;
	
	
	 @PostMapping("/consultations")
	    public Consultation saveConsultation(@RequestBody Consultation consultation) {
	        return consultationService.saveConsultation(consultation);
	    }
	 @GetMapping("/consultations")
	 public List<Consultation> consultationList(){
		 return consultationService.findAllConsultations();
	 }
	 
	 @GetMapping("/consultations/{id}")
		public ResponseEntity<Consultation> getMedecinById(@PathVariable(value = "id") Long consultationId){
			
	    	return ResponseEntity.ok(consultationService.findConsultationById(consultationId));
	    			
						
		}
	    
	    @PutMapping("/consultations/{id}")
		public ResponseEntity<Consultation> updateConsultation(@PathVariable(value = "id") Long consultationId, @RequestBody Consultation consultationDetails){
			
	 	
	    		return ResponseEntity.ok(consultationService.updateConsultation(consultationDetails));
	    		

						
		}
	    
	    @DeleteMapping("/consultations/{id}")
	    public ResponseEntity<Map<String, Boolean>> deleteConsultation(@PathVariable Long id){
	    	consultationService.deleteConsultation(id);
	    	Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
	    	
	    }
}
