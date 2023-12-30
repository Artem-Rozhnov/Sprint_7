package org.example.courier;

public class CredentialsWithoutLogin {
    private String password;

    public CredentialsWithoutLogin(){
    }

    public CredentialsWithoutLogin(String password) {
        this.password = password;
    }
    public static CredentialsWithoutLogin from(Courier courier){
        return new CredentialsWithoutLogin(courier.getPassword());
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}