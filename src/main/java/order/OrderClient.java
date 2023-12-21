package order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.order.SimpleExample;
import models.order.OrderData;


import static io.restassured.RestAssured.given;

public class OrderClient {

    private static final String CREATE_ORDER_URL = "/api/v1/orders";
    private static final String CANCEL_ORDER_URL = "/api/v1/orders/cancel";
    private static final String CHECK_ORDER_URL = "/api/v1/orders";

    @Step("Создание заказа")
    public Response create(OrderData orderData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(orderData)
                .when()
                .post(CREATE_ORDER_URL);
    }

    @Step("Отмена заказа")
    public Response cancel(OrderData orderData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(getOrderTrack(orderData))
                .when()
                .post(CANCEL_ORDER_URL);
    }

    public Integer getOrderTrack(OrderData orderData) {
        SimpleExample example = given()
                .header("Content-type", "application/json")
                .body(orderData)
                .post(CREATE_ORDER_URL)
                .as(SimpleExample.class);
        return example.getTrack();
    }

    @Step("Получение списка заказов")
    public Response getOrderList() {
        return given()
                .header("Content-type", "application/json")
                .get(CHECK_ORDER_URL);
    }
}