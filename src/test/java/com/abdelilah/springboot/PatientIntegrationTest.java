package com.abdelilah.springboot;

import com.abdelilah.springboot.entities.Patient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientIntegrationTest {

    @BeforeEach
    public void setUp() {
        RestAssured.port = 8080;

    }

    @Test
    public void testSavePatient(){
        Patient patient = new Patient();
        patient.setNom("Abdelilah");

        given()
                .contentType("application/json")
                .body(patient)
                .when()
                .post("/patients")
                .then()
                .statusCode(200)
                .body("nom",equalTo("Abdelilah"));
    }



    @Test
    public void testGetPatients(){
        given()
                .when()
                .get("/patients")
                .then()
                .statusCode(200)
                .body("size",greaterThan(0));
    }



}
