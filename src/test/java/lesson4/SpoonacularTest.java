package lesson4;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import lesson3.AbstractTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


public class SpoonacularTest extends AbstractTest {
    @Test
    void getSpecifyingRequestDataTest() {
        //get
        given().spec(getRequestSpecification())
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "pizza")
                .queryParam("diet", "vegeterian")
                .queryParam("number", "3")
                .pathParam("id", 680975)
                .when()
                .get(getBaseUrl()+"recipes/{id}/information")
                .then()
                .spec(responseSpecification);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "Korean")
                .queryParam("titleMatch", "kimchi")
                .queryParam("number", "1")
                .pathParam("id", 665379)
                .when()
                .get(getBaseUrl()+"recipes/{id}/information")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("maxProtein", "500")
                .queryParam("includeIngredients", "lamb")
                .pathParam("id", 660290)
                .when()
                .get(getBaseUrl()+"recipes/{id}/information")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("instructionsRequired", "true")
                .queryParam("query", "soup")
                .pathParam("id", 655055)
                .when()
                .get(getBaseUrl()+"recipes/{id}/information")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("maxIron", "40")
                .queryParam("cuisine", "European")
                .pathParam("id", 715495)
                .when()
                .get(getBaseUrl()+"recipes/{id}/information")
                .then()
                .statusCode(200);
        //post
        given().spec(requestSpecification)
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Turkey Tomato Cheese Pizza")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Winter Kimchi")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Apple Pie Smoothie")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Slow Cooker Lamb Curry")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

}

    String id;
    @Test
    void getShoppingListTest() {
        given().spec(getRequestSpecification())
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", "c5a3c0c99ec647718d6a6b711bd9b103")
                .queryParam("aisle", "Baking")
                .pathParam("id", 115388)
                .when()
                .get(getBaseUrl()+"shopping-list/{id}/information")
                .then()
                .spec(responseSpecification);

    }

    private RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    void addShoppingListTest() {
        id = given().spec(requestSpecification)
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", "c5a3c0c99ec647718d6a6b711bd9b103")
                .body("{\n"
                        + " \"item\": 1 package baking powder,\n"
                        + " \"aisle\": Baking,\n"
                        + " \"parse\": true,\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/shoppinglist/geekbrains/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
    }


        @AfterEach
    void deleteShoppingList() {
        given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", "c5a3c0c99ec647718d6a6b711bd9b103")
                .pathParam("id", 115388)
                .delete("https://api.spoonacular.com/shoppinglist/geekbrains/items/" + id)
                .then()
                .statusCode(200);
    }


}
