package courier;

import models.courier.CourierData;
import models.courier.CourierLogin;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {

    private static final String COURIER_URL = "api/v1/courier";
    private static final String COURIER_LOGIN_URL = "api/v1/courier/login";

    private static final String COURIER_DELETE_URL = "/api/v1/courier/";

    @Step("Создание курьера {courier}")
    public Response create(CourierData courierData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierData)
                .when()
                .post(COURIER_URL);
    }

    @Step("Авторизация курьером")
    public Response login(CourierData courierData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierData)
                .when()
                .post(COURIER_LOGIN_URL);
    }
    @Step("Авторизация курьером")
    public Response loginIn(CourierLogin courierLogin) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierLogin)
                .when()
                .post(COURIER_LOGIN_URL);
    }

    @Step("Удаление {courier}")
    public Response delete(int id) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(id)
                .when()
                .delete(COURIER_DELETE_URL);

    }


}

