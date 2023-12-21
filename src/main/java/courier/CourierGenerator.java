package courier;

import models.courier.CourierData;

import static utils.Utils.randomString;

public class CourierGenerator {
//    private String login;
//    private String password;
//    private  String firstName;

    public static CourierData randomCourier() {
        return new CourierData()
                .withLogin(randomString(8))
                .withPassword(randomString(16))
                .withFirstName(randomString(10));
    }

    public static CourierData randomCourierNoLogin() {
        return new CourierData()
                .withPassword(randomString(16))
                .withFirstName(randomString(10));

    }

}
