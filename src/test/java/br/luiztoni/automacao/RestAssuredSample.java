package br.luiztoni.automacao;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.get;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;

public class RestAssuredSample {

    private String url = "https://reqres.in/api/users";

    @Test
    public void getPageOneTest(){
        given().
                param("page", "1").
        when().
                get(url).
        then().
                statusCode(200).
                body("page", equalTo(1));
    }

    @Test
    public void getUserTest() {
        get(url + "/2").then().body("data.id", equalTo(2));
    }

    @Test
    public void postUserTest(){
    	Map<String, Object> requestParams = new HashMap<String, Object>();
        requestParams.put("name", "Alice");
        requestParams.put("job", "Dev");
        given()
                .contentType(ContentType.JSON).
                body(requestParams).
        when().
                post(url).
        then().
                statusCode(201).
                body(containsString("createdAt"));
    }

    @Test
    public void putUserTest(){
        Map<String, Object> requestParams = new HashMap<String, Object>();
        requestParams.put("name", "Bob");
        requestParams.put("job", "Dev");

        given()
                .contentType(ContentType.JSON).
                body(requestParams).
        when().
                put(url + "/2").
        then().
                statusCode(200).
                body(containsString("updatedAt"));
    }

    @Test
    public void deleteUserTest(){
        when().
                delete(url + "/2").
        then()
                .statusCode(204);
    }
}
