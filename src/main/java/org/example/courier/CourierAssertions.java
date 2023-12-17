package org.example.courier;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class CourierAssertions {

    @Step("Проверка статуса ответа при валидном создании курьера")
    public void createdSuccessfully(ValidatableResponse response){
        boolean created = response
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok")
                ;
        assert created;
    }

    @Step("Проверка статуса ответа и получения id при авторизации курьера")
    public int loggedInSuccessfully(ValidatableResponse loginResponse){
        int id = loginResponse
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
        return id;
    }

    @Step("Проверка статуса ответа при удалении курьера")
    public void deleteSuccessfully(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(200);
    }
    @Step("Проверка статуса ответа при создании пользователя с логином, который уже есть ")
    public void checkError(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(409)
                .extract()
                .path("message")
                .equals("Этот логин уже используется");
    }
    @Step("Проверка статуса ответа при создании курьера без логина")
    public void checkClientsWithoutLogin(ValidatableResponse response){
        boolean created = response
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message")
                .equals("Недостаточно данных для создания учетной записи");
        assert created;
    }
    @Step("Проверка статуса ответа при авторизации под несуществующим пользователем, ")
    public void checkErrorLogin(ValidatableResponse loginResponse){
        loginResponse
                .assertThat()
                .statusCode(404)
                .extract()
                .path("message")
                .equals("Учетная запись не найдена");
    }
    @Step("Проверка статуса ответа авторизации без обязательного поля")
    public void checkErrorWithoutLogin(ValidatableResponse loginResponse){
        loginResponse
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message")
                .equals("Недостаточно данных для входа");
    }
}