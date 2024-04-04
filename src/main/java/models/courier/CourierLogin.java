package models.courier;

public class CourierLogin {
    private final String login;
    private final String password;

    public CourierLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }


//    public static CourierLogin fromCourier(CourierData courier) {
//        return new CourierLogin(courier.getLogin(), courier.getPassword());
//    }

    @Override
    public String toString() {
        return "CourierCreds{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}

