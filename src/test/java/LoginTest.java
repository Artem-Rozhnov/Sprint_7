import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.example.courier.*;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

public class LoginTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    protected int courierId;

    @After
    public void deleteCourier(){
        client.delete(courierId);
    }

    @Description("Тест проводит проверку логина курьера в системе")
    @Test
    public void login() {
        var courier = CourierGenerator.random();
        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
        assertNotEquals(courierId,0);

        courier.setLogin("Ann");
        creds = Credentials.from(courier);
        loginResponse = client.login(creds);
        check.checkErrorLogin(loginResponse);

        var creds3 = CredentialsWithoutLogin.from(courier);
        loginResponse = client.getLoginWithoutLogin(creds3);
        check.checkErrorWithoutLogin(loginResponse);
    }
}