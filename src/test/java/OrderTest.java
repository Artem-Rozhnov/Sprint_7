import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.example.order.Data;
import org.example.order.Order;
import org.example.order.OrderAssertions;
import org.example.order.OrderClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertNotEquals;
import java.util.Arrays;
import java.util.Collection;



@RunWith(Parameterized.class)
public class OrderTest {
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();
    private final String color;

    public OrderTest(String color) {
        this.color = color;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null}, // Нет цвета
                {"BLACK"}, // Цвет: BLACK
                {"GREY"}, // Цвет: GREY
                {"BLACK,GREY"} // Выбраны оба цвета
        });
    }

    @Description("Тест проводит проверку создания заказа")
    @Test
    public void testCreateOrder() {
        Data orderData = client.createOrderData();
        orderData.setColor(color);

        Order order = new Order();
        order.setData(orderData);

        ValidatableResponse response = client.createOrder(order);

        int trackNumber = check.createdOrderSuccessfully(response);
        assertNotEquals(trackNumber,0);

    }

}