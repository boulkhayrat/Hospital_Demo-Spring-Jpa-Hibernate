package com.abdelilah.springboot.restAssured;

import com.abdelilah.springboot.entities.Medecin;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
@AutoConfigureMockMvc
public class MedecinIntegrationTest {

    @BeforeEach
    public void setUp() {
        RestAssured.port = 8080;

    }

    @Test
    public void testSaveMedecin(){
        Medecin medecin = new Medecin();
        medecin.setNom("Abdelilah");

        given()
                .contentType("application/json")
                .body(medecin)
                .when()
                .post("/medecins")
                .then()
                .statusCode(200)
                .body("nom",equalTo("Abdelilah"));
    }



    @Test
    public void testGetAllMedecins() {

        RestAssured.baseURI = "http://localhost:8080";


        when()
                .get("/medecins")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testGetMedecinById() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/medecins/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void testUpdateMedecin() {
        Medecin medecin1 = new Medecin();
        medecin1.setId(1L);
        medecin1.setNom("Anas");

        given()
                .log().all()
                .pathParam("id", 1)
                .contentType("application/json")
                .body(medecin1)
                .when()
                .put("/medecins/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("nom", equalTo("Anas"));
    }

    @Test
    public void testDeleteMedecin() {
        given()
                .log().all()
                .pathParam("id", 1)
                .when()
                .delete("/medecins/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("deleted", equalTo(true));
    }

}
