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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class RentADvdIntegrationTest {

    @LocalServerPort
    int localServerPort;

    @BeforeEach
    public void beforeEach(){
        RestAssured.baseURI = "http://localhost:" + localServerPort;
    }

    @Test
    public void testingDvdRental() {
        Response response = RestAssured.get("/dvd/rent");
        response.prettyPrint();
        ResponseBody body = response.getBody();
        dvdrental.domain.dto.Response responseAs = body.as(dvdrental.domain.dto.Response.class);
        System.out.println(responseAs);
        Assertions.assertEquals("SUCCESS", responseAs.getResponseStatus().name());
    }
}
