package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.anything;

public class ListOrderTests {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";


    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;}

    @Test
    @DisplayName("Получение списка заказов")
    public void getOrderList() {
        OrderClient orderClient = new OrderClient();
        orderClient.check()
                .then()
                .assertThat()
                .body(anything("orders"))
                .statusCode(200);
    }

}
