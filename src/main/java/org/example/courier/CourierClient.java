package org.example.courier;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.Map;

public class CourierClient extends org.example.Client {

    public static final String COURIER_PATH = "/courier";
    @Step("Создание курьера")
    public ValidatableResponse create(Courier courier){
        return spec()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Создание курьера без ввода логина")
    public ValidatableResponse createWithoutLogin(CourierWithoutLogin courier1){
        return spec()
                .body(courier1)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Создание курьера без ввода пароля")
    public ValidatableResponse createWithoutPassword(CourierWithoutPassword courier2){
        return spec()
                .body(courier2)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Авторизация курьера")
    public ValidatableResponse login(Credentials creds){
        return spec()
                .body(creds)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    @Step("Удаление курьера")
    public ValidatableResponse delete(int courierId){
        return spec()
                .body(Map.of("id", String.valueOf(courierId)))
                .when()
                .delete(COURIER_PATH + "/" + courierId)
                .then().log().all();
    }

    @Step("Авторизация курьера без логина")
    public ValidatableResponse getLoginWithoutLogin(CredentialsWithoutLogin creds3){
        return spec()
                .body(creds3)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }
}