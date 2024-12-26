package es.codeurjc.bof;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

class ProductRestControllerTest {

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

        RestAssured.basePath = "/api/product";
        
    }

    @Test
    public void getAllProducts() {
        given()
            .when()
                .get("/")
            .then()
                .statusCode(HttpStatus.OK.value())
                .contentType("application/json")
                .body("$", not(empty()))
                .body("size()", greaterThan(0));
    }

    @Test
    public void getProduct() {
        long existingProductId = 2;

        given()
            .when()
                .get("/{id}", existingProductId)
            .then()
                .statusCode(HttpStatus.OK.value())
                .contentType("application/json")
                .body("id", equalTo((int) existingProductId))
                .body("name", notNullValue());
    }

    @Test
    public void getProduct_NotFound() {
        long nonExistingProductId = 99999;

        given()
            .when()
                .get("/{id}", nonExistingProductId)
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void addProduct() {
        String productJson = """
            {
                "name": "Test Rest Assured X3",
                "price": 10
            }
            """;

        given()
            .cookie("AuthToken", adminToken)
            .contentType("application/json")
            .body(productJson)
        .when()
            .post("/")
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deleteProduct() {
        long existingProductId = 1;
        given()
            .cookie("AuthToken", adminToken)
        .when()
            .delete("/{id}", existingProductId)
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType("application/json")
            .body("id", equalTo((int) existingProductId))
            .body("name", notNullValue());
    }

    @Test
    public void deleteProduct_NotFound() {
        long nonExistingProductId = 99999;

        given()
            .cookie("AuthToken", adminToken)
        .when()
            .delete("/{id}", nonExistingProductId)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
