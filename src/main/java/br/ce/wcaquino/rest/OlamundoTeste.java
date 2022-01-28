package br.ce.wcaquino.rest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OlamundoTeste {
    @Test
    public void testeOlaMundo() {
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
        Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        Assert.assertTrue(response.statusCode() == 200);

        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);

    }

    @Test
    public void devoConhecerOutrasFormasRestAssured() {
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);
        RestAssured.get("https://restapi.wcaquino.me/ola").then().statusCode(200);
        given()
                //pre condição
                .when()//ação
                .get("https://restapi.wcaquino.me/ola")
                .then()//assertivas
                .statusCode(200);
    }

    @Test
    public void devoConhecerMatchersHamcrest() {

        Assert.assertThat("maria", is("maria"));
        Assert.assertThat(128, is(128));
        Assert.assertThat(128, Matchers.isA(Integer.class));
        Assert.assertThat(128d, Matchers.isA(Double.class));
        Assert.assertThat(128d, Matchers.greaterThan(120d));
        Assert.assertThat(128d, Matchers.lessThan(130d));

        List<Integer> impares = Arrays.asList(1, 3, 5, 7, 9);
        Assert.assertThat(impares, Matchers.hasSize(5));
        Assert.assertThat(impares, Matchers.contains(1, 3, 5, 7, 9));
        Assert.assertThat(impares, Matchers.containsInAnyOrder(1, 3, 5, 9, 7));
        Assert.assertThat(impares, Matchers.hasItem(1));
        Assert.assertThat(impares, Matchers.hasItems(1, 5));
        assertThat("maria", Matchers.not("joão"));

    }


    @Test
    public void devoValidarBody() {
        given()
                //pre condição
                .when()//ação
                .get("https://restapi.wcaquino.me/ola")
                .then()//assertivas
                .statusCode(200)
                .body(is("Ola Mundo!"))
                .body(containsString("Mundo"))
                .body(is(not(nullValue())));

        
    }

}


