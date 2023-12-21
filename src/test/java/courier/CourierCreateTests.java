package courier;


import models.courier.CourierData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static courier.CourierGenerator.randomCourier;
import static courier.CourierGenerator.randomCourierNoLogin;
import static org.hamcrest.CoreMatchers.equalTo;
import static utils.Utils.randomString;


public class CourierCreateTests {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    private String password;
    private  String firstName;


    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }


    @Test
    @DisplayName("Создание курьера")
    public void createCourier() {
        CourierData courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier)
                .then()
                .assertThat()
                .statusCode(201);

        courierClient.delete(courier);

    }

    @Test
    @DisplayName("Корректный ответ при создании курьера")
    public void createCourierReturnCorrectBody() {
        CourierData courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier)
                .then()
                .assertThat()
                .body("ok", equalTo(true));

        courierClient.delete(courier);

    }

    @Test
    @DisplayName("Создание двух одинаковых курьера")
    public void createDoubleCourier() {
        CourierData courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        courierClient.create(courier)
                .then()
                .assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        courierClient.delete(courier);

    }

    @Test
    @DisplayName("Создание курьеров с одинаковыми логинами")
    public void createDoubleLoginCourier() {
        password = randomString(16);
        firstName = randomString(10);
        CourierData courier = randomCourier();
        CourierData courier2 = new CourierData(courier.getLogin(), password, firstName);
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        courierClient.create(courier2)
                .then()
                .assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        courierClient.delete(courier);
    }

    @Test
    @DisplayName("Создание курьера без login")
    public void createCourierWithNotLogin() {
        CourierData courier = randomCourierNoLogin();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier)
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

        courierClient.delete(courier);

    }
}

