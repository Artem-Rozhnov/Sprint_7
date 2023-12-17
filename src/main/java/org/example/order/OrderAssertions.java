package org.example.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderAssertions {

    @Step("Проверка статуса ответа при успешном создании курьера")
    public int createdOrderSuccessfully(ValidatableResponse orderResponse){
        int track = orderResponse
                .assertThat()
                .statusCode(201)
                .extract()
                .path("track")
                ;
        return track;
    }


}