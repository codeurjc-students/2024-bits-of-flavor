package es.codeurjc.bof;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class TicketRestControllerTest {
    
    private static String adminToken;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://localhost";
        RestAssured.port = 8443;
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.basePath = "/api";
        String loginRequestJson = """
            {
                "username": "admin",
                "password": "pass"
            }
            """;

            adminToken = given()
            .contentType(ContentType.JSON)
                .body(loginRequestJson)
            .when()
                .post("/login")
            .then()
                .statusCode(200)
                .extract()
                .cookie("AuthToken");

        RestAssured.basePath = "/api/ticket";
    }

    @Test
    public void processPayment () {
        long productId = 1;
        String paymentDate = LocalDate.now().plusDays(7).toString();

        given()
            .cookie("AuthToken", adminToken)
            .contentType(ContentType.JSON)
            .body("\""+paymentDate+"\"")
        .when()
            .post("/{id}", productId)
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("user.username", equalTo("admin"))
            .body("product.id", equalTo((int) productId));
    }

    @Test
    public void processPayment_Forbidden () {
        long productId = 1;
        String paymentDate = LocalDate.now().plusDays(7).toString();

        given()
            .contentType(ContentType.JSON)
            .body("\""+paymentDate+"\"")
        .when()
            .post("/{id}", productId)
        .then()
            .statusCode(401);
    }

    @Test
    public void getAllTickets() {
        given()
            .cookie("AuthToken", adminToken)
        .when()
            .get("/")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType("application/json")
            .body("$", not(empty()))
            .body("size()", greaterThan(0));
    }

    @Test
    public void getAllTickets_Forbidden() {
        given()

        .when()
            .get("/")
        .then()
            .statusCode(401);
    }
}
