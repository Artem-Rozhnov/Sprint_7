package org.example.courier;
public class CourierWithoutLogin {
    private String password;
    private String firstName;

    public CourierWithoutLogin(){
    }

    public CourierWithoutLogin(String password, String firstName) {
        this.password = password;
        this.firstName = firstName;
    }
    public static CourierWithoutLogin from(Courier courier){
        return new CourierWithoutLogin(courier.getFirstName(), courier.getPassword());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}