package es.codeurjc.bof;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UserRestControllerTest {

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

        RestAssured.basePath = "/api/user";
    }

    @Test
    public void me() {
        given()
            .cookie("AuthToken", adminToken)
        .when()
            .get("/me")
        .then()
            .statusCode(200)
            .body("username", equalTo("admin"));
    }

    @Test
    public void me_Forbidden() {
        given()
        .when()
            .get("/me")
        .then()
            .statusCode(401);
    }

    @Test
    public void addUser () {
        String validRegistrationData = """
        {
            "username": "AssuredUserTest1",
            "encodedPassword": "securepassword123",
            "email": "newuser@example.com",
            "phoneNumber": "123456789"
        }
            """;

        given()
            .contentType(ContentType.JSON)
            .body(validRegistrationData)
        .when()
            .post("/")
        .then()
            .statusCode(201)
            .body("username", equalTo("AssuredUserTest1"))
            .body("email", equalTo("newuser@example.com"));
    }

    @Test
    public void addUser_NotFound () {
        String validRegistrationData = """
        {
            "username": "AssuredUserTest1",
            "encodedPassword": "securepassword123",
            "email": "newuser@example.com",
            "phoneNumber": "123456789"
        }
            """;
    
            given()
                .contentType(ContentType.JSON)
                .body(validRegistrationData)
            .when()
                .post("/")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
