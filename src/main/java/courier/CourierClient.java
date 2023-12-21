package courier;

import models.courier.CourierData;
import models.courier.CourierLogin;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.courier.SimpleExample;

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

    @Step("Авторизация курьером с кредами {courierCreds}")
    public Response login(CourierData courierData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierData)
                .when()
                .post(COURIER_LOGIN_URL);
    }
    @Step("Авторизация курьером с кредами {courierCreds}")
    public Response loginIn(CourierLogin courierLogin) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierLogin)
                .when()
                .post(COURIER_LOGIN_URL);
    }

    public Integer getCourierId(CourierData courierData){
        SimpleExample example = given()
                .header("Content-type", "application/json")
                .body(courierData)
                .post(COURIER_LOGIN_URL)
                .as(SimpleExample.class);
                return example.getId();

    }

    @Step("Удаление {courier}")
    public Response delete(CourierData courierData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(getCourierId(courierData))
                .when()
                .delete(COURIER_DELETE_URL);

    }


}

