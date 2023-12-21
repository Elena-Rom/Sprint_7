package courier;


import models.courier.CourierData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static courier.CourierGenerator.randomCourier;
import static courier.CourierGenerator.randomCourierNoLogin;
import static org.hamcrest.CoreMatchers.equalTo;
import static utils.Utils.randomString;


public class CourierCreateTests {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    private String password;
    private String firstName;

   private int id;


    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }
    CourierClient courierClient = new CourierClient();

    @After
    public void tearDown() {
        courierClient.delete(id);
    }


    @Test
    @DisplayName("Создание курьера")
    public void createCourier() {
        CourierData courier = randomCourier();
        courierClient.create(courier)
                .then()
                .assertThat()
                .statusCode(201)
                .extract().path("id");
    }

    @Test
    @DisplayName("Корректный ответ при создании курьера")
    public void createCourierReturnCorrectBody() {
        CourierData courier = randomCourier();
        courierClient.create(courier)
                .then()
                .assertThat()
                .body("ok", equalTo(true))
                .extract().path("id");

    }

    @Test
    @DisplayName("Создание двух одинаковых курьера")
    public void createDoubleCourier() {
        CourierData courier = randomCourier();
        courierClient.create(courier);
        courierClient.create(courier)
                .then()
                .assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .extract().path("id");

    }

    @Test
    @DisplayName("Создание курьеров с одинаковыми логинами")
    public void createDoubleLoginCourier() {
        password = randomString(16);
        firstName = randomString(10);
        CourierData courier = randomCourier();
        CourierData courier2 = new CourierData(courier.getLogin(), password, firstName);
        courierClient.create(courier);
        courierClient.create(courier2)
                .then()
                .assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .extract().path("id");
    }

    @Test
    @DisplayName("Создание курьера без login")
    public void createCourierWithNotLogin() {
        CourierData courier = randomCourierNoLogin();
        courierClient.create(courier)
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}

