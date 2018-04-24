package com.jml.demo_hotel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.jml.demo_hotel.rest.ResourceConstants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JmlDemoHotelSpringServerApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationResourceTest {

    private static final Integer EXPECTED_ITEM_ID_FOR_GET = 1;

    @LocalServerPort
    private int port = 8080;

    @Before
    public void setUp() throws Exception {

        RestAssured.port = Integer.valueOf(port);
        RestAssured.basePath = ResourceConstants.ROOM_RESERVATION_V1;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void getAvailableRooms() throws Exception {
        given().when().get("/" + EXPECTED_ITEM_ID_FOR_GET).then()
                .statusCode(200);
    }

}
