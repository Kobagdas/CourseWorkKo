package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;

public class TokenTest {
    public String url;
    public String name;
    public String password;

    public String create_token(String url, String name, String password){

        RequestSpecification req = RestAssured.given();
        req.header("Content-Type", "application/json");
        JSONObject Params = new JSONObject();
        Params.put("userName", name);
        Params.put("password", password);
        req.body(Params.toString());

        Response resp = req.post(url);
        resp.then().statusCode(200);
        String token = resp.then().extract().path("token");
        Assert.assertNotNull(token);
        return token;
    }
}
