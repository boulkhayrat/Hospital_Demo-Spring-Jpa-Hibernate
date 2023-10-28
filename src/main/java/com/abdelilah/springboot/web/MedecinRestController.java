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

import com.abdelilah.springboot.entities.Medecin;
import com.abdelilah.springboot.service.IMedecinService;


@RestController
public class MedecinRestController {
	
	@Autowired
	private IMedecinService medecinService;
	
	
	 @PostMapping("/medecins")
	    public Medecin saveMedecin(@RequestBody Medecin medecin) {
	        return medecinService.saveMedecin(medecin);
	    }
	 @GetMapping("/medecins")
	 public List<Medecin> medecinList(){
		 return medecinService.findAllMedecins();
	 }
	 
	 @GetMapping("/medecins/{id}")
		public ResponseEntity<Medecin> getMedecinById(@PathVariable(value = "id") Long medecinId){
			
	    	return ResponseEntity.ok(medecinService.findMedecinById(medecinId));
	    			
						
		}
	    
	    @PutMapping("/medecins/{id}")
		public ResponseEntity<Medecin> updateMedecinById(@PathVariable(value = "id") Long medecinId, @RequestBody Medecin medecinDetails){
			
	 	
	    		return ResponseEntity.ok(medecinService.updateMedecin(medecinDetails));
	    		

						
		}
	    
	    @DeleteMapping("/medecins/{id}")
	    public ResponseEntity<Map<String, Boolean>> deleteMedecin(@PathVariable Long id){
	    	medecinService.deleteMedecin(id);
	    	Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
	    	
	    }

}
