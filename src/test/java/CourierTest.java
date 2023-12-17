import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.example.courier.*;
import org.junit.After;
import org.junit.Test;


public class CourierTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    protected int courierId;

    @After
    public void deleteCourier(){
        ValidatableResponse delete = client.delete(courierId);
        check.deleteSuccessfully(delete);
    }

    @Description("Тест проводит проверку создания курьера")
    @Test
    public void courier(){
        var courier = CourierGenerator.random();
        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
        assert courierId != 0;

        courier.setFirstName("John");
        response = client.create(courier);
        check.checkError(response);

        var courier1 = CourierWithoutLogin.from(courier);
        response = client.createWithoutLogin(courier1);
        check.checkClientsWithoutLogin(response);

        var courier2 = CourierWithoutPassword.from(courier);;
        response = client.createWithoutPassword(courier2);
        check.checkClientsWithoutLogin(response);

    }
}