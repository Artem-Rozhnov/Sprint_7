import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.example.courier.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;


public class CourierTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    protected int courierId;
    private Courier courier;
    ValidatableResponse response;

    @Before
    public void generatorCourier(){
        courier = CourierGenerator.random();
        response = client.create(courier);
    }
    @After
    public void deleteCourier(){
        ValidatableResponse delete = client.delete(courierId);
        check.deleteSuccessfully(delete);
    }

    @Description("Тест проводит проверку создания курьера")
    @Test
    public void courier(){

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);
    }
    @Description("Тест проводит проверку авторизации курьера")
    @Test
    public void authorizationCourier(){
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
        assertNotEquals("Неверный id курьера",courierId, 0);
    }
    @Description("Тест проводит проверку создания курьера с уже существующим логином")
    @Test
    public void twoCourier() {
        courier.setFirstName("John");
        response = client.create(courier);
        check.checkError(response);
    }
    @Description("Тест проводит проверку создания курьера без логина")
    @Test
    public void notLoginCourier() {
        var courier1 = CourierWithoutLogin.from(courier);
        response = client.createWithoutLogin(courier1);
        check.checkClientsWithoutLogin(response);
    }
    @Description("Тест проводит проверку создания курьера без пароля")
    @Test
    public void notPasswordCourier() {
        var courier2 = CourierWithoutPassword.from(courier);
        response = client.createWithoutPassword(courier2);
        check.checkClientsWithoutLogin(response);
    }

}