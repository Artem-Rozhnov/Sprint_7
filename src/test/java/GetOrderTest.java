import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.order.Data;
import org.example.order.Order;
import org.example.order.OrderAssertions;
import org.example.order.OrderClient;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;


public class GetOrderTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = client.BASE_URI;
    }
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Description("Тест проверяет получение списка заказов")
    @Test
    public void testGetOrder() {
        Data orderData = client.createOrderData();
        Order order = new Order();
        order.setData(orderData);

        ValidatableResponse response = client.createOrder(order);
        int trackNumber = check.createdOrderSuccessfully(response);
        assertNotEquals(trackNumber,0);
        Order order1 = given()
                .get(client.BASE_PATH + "/orders")
                .body().as(Order.class);
        MatcherAssert.assertThat(order1, notNullValue());
    }
}