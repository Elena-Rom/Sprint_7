package order;

import models.order.OrderData;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class OrdersParamsTests {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    OrderData orderData = new OrderData();
    OrderClient orderClient = new OrderClient();
    private List<String> color;

    public OrdersParamsTests(List<String> color){
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getDataForOrder() {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("GREY", "BLACK")},
                {List.of()}
        };
    }
    @Before
    public void setUp() {RestAssured.baseURI = BASE_URL;}

    @After
    public void tearDown(){orderClient.cancel(orderData);}

    @Test
    public void createOrder(){
        orderData.setColor(color);
        int track = orderClient.create(orderData)
                .then()
                .assertThat()
                .statusCode(201)
                .extract().path("track");
        assertNotNull(track);
    }
}

