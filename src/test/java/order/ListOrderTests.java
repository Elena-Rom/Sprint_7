package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import models.order.OrderData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ListOrderTests {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    OrderClient orderClient = new OrderClient();
    OrderData orderData = new OrderData();

    @Before
    public void setUp() {RestAssured.baseURI = BASE_URL;}

    @After
    public void tearDown(){orderClient.cancel(orderData);}


    @Test
    @DisplayName("Получение списка заказов")
    public void getOrderList() {
        orderClient.create(orderData);
        orderClient.getOrderList()
                .then()
                .assertThat()
                .statusCode(200)
                .body("orders", notNullValue());

    }

}
