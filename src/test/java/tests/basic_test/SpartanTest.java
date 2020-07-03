package tests.basic_test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://52.205.194.10";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        // this is how we can add basic auth for entire test
        RestAssured.authentication = basic("user", "user");

    }
    @Test
    public void test1() {
        //  give()  some request specification
        //  when()  sending a request with http verb
        //  then()  result should be as you expected
        given().
         log().all().//this is to get the details of our request in the console
        when().
                get("/hello").prettyPeek().
        then().
                //log().all().//This is to get the details of the response to our request
                statusCode(200);
    }

    @Test
    public void testHelloEndPoint2(){

        given()
                .log().all().
                when().
                get("/hello").
                then().
                log().all().
                statusCode(200).
                contentType(ContentType.TEXT).
                body(equalTo("Hello from Sparta"));


    }

    @Test
    public void testHelloEndPoint3() {

        given().
                log().all().
                when()
                        .get("/hello").
                then().
                log().all().
                statusCode(is(200)).
                header("Content-Type", "text/plain;charset=UTF-8").
                header("Date", not(nullValue())). // workaround for header exists
                header("NonExistingHeader", nullValue()).
                body(is("Hello from Sparta"));

    }
    }
