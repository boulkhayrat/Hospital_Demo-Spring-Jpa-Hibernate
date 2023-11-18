package com.abdelilah.springboot.restAssured;

import com.abdelilah.springboot.entities.Consultation;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsultationIntegrationTest {

    @BeforeEach
    public void setUp() {
        RestAssured.port = 8080;

    }

    @Test
    public void testSaveConsultation(){
        Consultation consultation = new Consultation();
        consultation.setRapport("this is Rapport1 ...");
        consultation.setDateConsultation(new Date());

        given()
                .contentType("application/json")
                .body(consultation)
                .when()
                .post("/consultations")
                .then()
                .statusCode(200)
                .body("rapport",equalTo("this is Rapport1 ..."));
    }



    @Test
    public void testGetAllConsultations() {

        RestAssured.baseURI = "http://localhost:8080";


        when()
                .get("/consultations")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testGetConsultationById() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/consultations/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void testUpdateConsultation() {
        Consultation consultation1 = new Consultation();
        consultation1.setId(1L);
        consultation1.setDateConsultation(new Date());
        consultation1.setRapport("this is the modified version of rapport .. ");

        given()
                .log().all()
                .pathParam("id", 1)
                .contentType("application/json")
                .body(consultation1)
                .when()
                .put("/consultations/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("rapport", equalTo("this is the modified version of rapport .. "));
    }

    @Test
    public void testDeleteConsultation() {
        given()
                .log().all()
                .pathParam("id", 1)
                .when()
                .delete("/consultations/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("deleted", equalTo(true));
    }

}
