package com.abdelilah.springboot;

import com.abdelilah.springboot.entities.RendezVous;
import com.abdelilah.springboot.entities.StatusRDV;
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
public class RendezVousIntegrationTest {

    @BeforeEach
    public void setUp() {
        RestAssured.port = 8080;

    }

    @Test
    public void testSaveRendezVous(){
        RendezVous rendezVous = new RendezVous();
        rendezVous.setStatus(StatusRDV.DONE);
        rendezVous.setDate(new Date());

        given()
                .contentType("application/json")
                .body(rendezVous)
                .when()
                .post("/rendezVouss")
                .then()
                .statusCode(200)
                .body("nom",equalTo("Abdelilah"));
    }



    @Test
    public void testGetAllRendezVouss() {

        RestAssured.baseURI = "http://localhost:8080";


        when()
                .get("/rendezVouss")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testGetRendezVousById() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/rendezVouss/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void testUpdateRendezVous() {
        RendezVous rendezVous1 = new RendezVous();
        rendezVous1.setId(1L);
        rendezVous1.setStatus(StatusRDV.CANCELED);

        given()
                .log().all()
                .pathParam("id", 1)
                .contentType("application/json")
                .body(rendezVous1)
                .when()
                .put("/rendezVouss/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("nom", equalTo("Anas"));
    }

    @Test
    public void testDeleteRendezVous() {
        given()
                .log().all()
                .pathParam("id", 1)
                .when()
                .delete("/rendezVouss/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("deleted", equalTo(true));
    }

}
