import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import domain.models.Game;
import org.junit.Rule;
import org.junit.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class UserTest {

    @Rule
    public WireMockRule wiremockRule = new WireMockRule(8888);

    @Test
    public void CreateUser(){
        Game u = new Game();
        u.setPrice(10);

        WireMock wiremock = new WireMock(8888);

        wiremock.register(post(urlEqualTo("/call"))
                .withRequestBody(containing("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("")));

        given()
                .port(8888)
                .body(u)
                .when().post("/call").then()
                .statusCode(200);
        wiremock.verifyThat(WireMock.postRequestedFor(urlEqualTo("/call")));
    }
}