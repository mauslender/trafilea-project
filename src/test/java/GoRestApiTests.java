import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoRestApiTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://gorest.co.in/public/v1";
    }

    @Test
    public void testGetActiveUserAndVerifyStatus() {
        // Step 1: Get a list of users
        Response response = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Step 2: Find the first active user
        int userId = response.jsonPath().getInt("data.find { it.status == 'active' }.id");

        // Step 3: Get details of the first active user
        given()
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("data.status", equalTo("active"));
    }

    @Test
    public void testModifyUserNameAndVerify() {
        // Step 1: Get a list of users
        Response response = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Step 2: Obtain the first user
        int userId = response.jsonPath().getInt("data[0].id");

        // Modify the user's name
        String newName = "SELECTED NAME";

        // Step 3: Modify the name using PATCH method
        given()
                .header("Authorization", "Bearer 55d6636b25b84832f383665a17f72117ee2b5e655a78ba968912c9a37d1c050f")
                .header("Content-Type", "application/json")
                .body("{\"name\": \"" + newName + "\", \"email\": \"jana.waters@hotmail.us\", \"status\": \"active\"}")
                .when()
                .patch("/users/" + userId)
                .then()
                .statusCode(200)
                .body("data.name", equalTo(newName));
    }
}
