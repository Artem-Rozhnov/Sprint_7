package org.example.order;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderClient extends org.example.Client {
    public static final String ORDER_PATH = "/orders";
    @Step("Ввод валидных данных в обязательные поля при создании заказа")
    public Data createOrderData() {
        Data orderData = new Data();
        orderData.setFirstName("John");
        orderData.setLastName("Doe");
        orderData.setAddress("123 Main St");
        orderData.setMetroStation("Central Station");
        orderData.setPhone("1234567890");
        orderData.setRentTime("2 hours");
        orderData.setDeliveryDate("2023-10-07");
        orderData.setComment("Test order");
        orderData.setColor("BLACK");

        return orderData;
    }
    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }
}