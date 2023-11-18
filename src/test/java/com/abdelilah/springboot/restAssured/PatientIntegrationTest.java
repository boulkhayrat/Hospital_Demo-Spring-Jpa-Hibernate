package com.abdelilah.springboot.restAssured;

import com.abdelilah.springboot.entities.Patient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
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
    public void testGetAllPatients() {

        RestAssured.baseURI = "http://localhost:8080";


        when()
                .get("/patients")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testGetPatientById() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/patients/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void testUpdatePatient() {
        Patient patient1 = new Patient();
        patient1.setId(1L);
        patient1.setNom("Anas");

        given()
                .log().all()
                .pathParam("id", 1)
                .contentType("application/json")
                .body(patient1)
                .when()
                .put("/patients/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("nom", equalTo("Anas"));
    }

    @Test
    public void testDeletePatient() {
        given()
                .log().all()
                .pathParam("id", 1)
                .when()
                .delete("/patients/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("deleted", equalTo(true));
    }

}
