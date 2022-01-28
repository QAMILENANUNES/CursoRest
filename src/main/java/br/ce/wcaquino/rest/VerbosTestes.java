package br.ce.wcaquino.rest;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class VerbosTestes {
    @Test
    public void deveSalvarUsuario() {
        RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .body("{\"name\":\"Jose\",\"age\":50}")
                .when()
                .post("https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", is(notNullValue()))
                .body("name", is("Jose"))
                .body("age", is(50));
    }

    @Test
    public void naoDeveSalvarUsuarioSemNome() {
        RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .body("{\"age\":50}")
                .when()
                .post("https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(400)
                .body("id", is(nullValue()))
                .body("error", is("Name é um atributo obrigatório"));

    }

    @Test
    public void deveSalvarUsuarioXml() {
        RestAssured
                .given()
                .log().all()
                .contentType("application/xml")
                .body("<user><name>Jose</name><age>50</age></user>")
                .when()
                .post("https://restapi.wcaquino.me/usersXML")
                .then()
                .log().all()
                .statusCode(201)
                .body("user.@id", is(notNullValue()))
                .body("user.name", is("Jose"))
                .body("user.age", is("50"));


    }
}
