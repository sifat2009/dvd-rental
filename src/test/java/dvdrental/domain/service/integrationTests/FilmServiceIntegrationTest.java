package dvdrental.domain.service.integrationTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Going to start the application in random part.
@TestPropertySource("classpath:application-test.properties")
public class FilmServiceIntegrationTest {

    @LocalServerPort
    int localServerPort;

    @BeforeEach
    public void beforeEach() {
        RestAssured.baseURI = "http://localhost:" + localServerPort;
    }

    @Test
    public void testingSingleFilm() {
        Response response = RestAssured.get("/film/30");
        response.prettyPrint();
        ResponseBody body = response.getBody();
        dvdrental.domain.dto.Response responseAs = body.as(dvdrental.domain.dto.Response.class);
        Map<String, Object> result = (Map<String, Object>)responseAs.getResult();
        System.out.println(responseAs);
        Assertions.assertEquals("SUCCESS", responseAs.getResponseStatus().name());
        Assertions.assertEquals("ANYTHING SAVANNAH", result.get("title"));
    }

    @Test
    public void testingAllFilm() {
        Response response = RestAssured.get("/film/all");
        response.prettyPrint();
        ResponseBody body = response.getBody();
        dvdrental.domain.dto.Response responseAs = body.as(dvdrental.domain.dto.Response.class);
        System.out.println(responseAs);
        System.out.println();
        Assertions.assertEquals("SUCCESS", responseAs.getResponseStatus().name());

    }
    //One unit test and one integration test for our project. Integration test command:
    // mvn clean test -Dtest='*IntegrationTests*'
}