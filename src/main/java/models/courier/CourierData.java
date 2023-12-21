package models.courier;

public class CourierData {
    private String login;
    private String password;
    private String firstName;

    public CourierData(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CourierData(){
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public CourierData withLogin(String login) {
        this.login = login;
        return this;
    }

    public CourierData withPassword(String password) {
        this.password = password;
        return this;
    }

    public CourierData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
