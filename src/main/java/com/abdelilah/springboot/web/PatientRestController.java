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

import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.service.IPatientService;

@RestController
public class PatientRestController {
	
	
	
	
	@Autowired
	private  IPatientService patientService;
	
	
	@GetMapping("/patients")
	public List<Patient> patientList(){
		return patientService.findAllPatients();
	}
	
    @PostMapping("/patients")
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }
    
    @GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable(value = "id") Long patientId){
		
    	return ResponseEntity.ok(patientService.findPatientById(patientId));
    			
					
	}
    
    @PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatientById(@PathVariable(value = "id") Long patientId, @RequestBody Patient patientDetails){
		
 	
    		return ResponseEntity.ok(patientService.updatePatient(patientDetails));
    		

					
	}
    
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable Long id){
    	patientService.deletePatient(id);
    	Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
    	
    }
    
   
    

    

}
