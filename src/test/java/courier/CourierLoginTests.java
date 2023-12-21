package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import models.courier.CourierData;
import models.courier.CourierLogin;
import org.junit.Before;
import org.junit.Test;

import static courier.CourierGenerator.randomCourier;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static utils.Utils.randomString;

public class CourierLoginTests {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    private String login;
    private String password;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Авторизация курьером")
    public void loginCourierCorrectStatusCode() {
        CourierData courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        courierClient.login(courier)
                .then()
                .assertThat()
                .statusCode(200);

        courierClient.delete(courier);

    }

    @Test
    @DisplayName("Авторизация курьером")
    public void loginCourierCorrect() {
        CourierData courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        courierClient.login(courier)
                .then()
                .assertThat()
                .body("id", notNullValue());

        courierClient.delete(courier);

    }

    @Test
    @DisplayName("Авторизация курьером без логина")
    public void loginCourierNotLogin() {
        CourierData courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        courierClient.loginIn(new CourierLogin(null, courier.getPassword()))
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));

        courierClient.delete(courier);

    }

    @Test
    @DisplayName("Авторизация курьером с несуществующими логин/пароль")
    public void loginCourierNotLoginNotPassword() {
        login = randomString(8);
        password = randomString(16);
        CourierClient courierClient = new CourierClient();
        courierClient.loginIn(new CourierLogin(login, password))
                .then()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));

    }

    @Test
    @DisplayName("Авторизация курьером с неправильным паролем")
    public void loginCourierNoCorrectLogin() {
        password = randomString(16);
        CourierData courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        courierClient.loginIn(new CourierLogin(courier.getLogin(), password))
                .then()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));

        courierClient.delete(courier);

    }
}
