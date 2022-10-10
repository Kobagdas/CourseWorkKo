package APIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utils.TokenTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.when;


public class ApiCourse1  extends TokenTest {
    String name = "Kobagdas123";
    String password = "Sw@gger321";
    @Test
    public void create_new_user(){
        //User creation
        String url = "https://demoqa.com/Account/v1/User";
        RequestSpecification req = RestAssured.given();
        req.header("Content-Type", "application/json");
        JSONObject Params = new JSONObject();
        Params.put("userName", name);
        Params.put("password", password);
        req.body(Params.toString());
        Response resp = req.post(url);
        resp.then().statusCode(201);
        System.out.println("New user created");

        //Getting user ID
        String usId = resp.then().extract().path("userID");
        System.out.println("This is user ID: "+usId);

        // Token generation
        TokenTest tokenTest = new TokenTest();
        String urlToken = "https://demoqa.com/Account/v1/GenerateToken";
        String token = tokenTest.create_token(urlToken, name, password);
        System.out.println("This is token: "+token);

        //Getting account by token
        String urlGet = "https://demoqa.com/Account/v1/User/"+usId;

        RequestSpecification reqToken = RestAssured.given();
        reqToken.header("Authorization","Bearer "+token).header("Content-Type", "application/json");
        Response respToken = reqToken.get(urlGet);
        respToken.then().statusCode(200);
        String actUsername = respToken.then().extract().path("username");
        System.out.println("Now we got username");

        //Verifying that username is correct
        Assert.assertEquals(name,actUsername);
        System.out.println("Our username is: "+actUsername);
    }

    @Test
    public void get_books(){
        //New token creation
        String name2 = "Kobagdas";
        String password2 = "MickSw@gger2022";
        String urlToken2 = "https://demoqa.com/Account/v1/GenerateToken";
        TokenTest tokenTest = new TokenTest();
        String token2 = tokenTest.create_token(urlToken2, name2, password2);
        System.out.println("This is token for second API part: "+token2);

        //Books getting
        String urlBooks = "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification reqToken = RestAssured.given();
        reqToken.header("Authorization","Bearer "+token2).header("Content-Type", "application/json");
        Response respToken = reqToken.get(urlBooks);
        respToken.then().statusCode(200);
        ArrayList books = respToken.then().extract().path("books");
        System.out.println("Books list: "+books);

        //Number of books
        Assert.assertEquals(8, books.size());
        System.out.println("Number of books: "+books.size());

        //Checking one of this books
        String title = "Git Pocket Guide";
        String isbn = "9781449325862";
        for(int i = 0; i<books.size(); i++) {
            LinkedHashMap book = (LinkedHashMap) books.get(i);
            String a = (String) book.get("isbn");
            if (a.equals(isbn)) {
                String actualTitle = (String) book.get("title");
                Assert.assertEquals(title, actualTitle);
                System.out.println("The title is: " + actualTitle);
                break;
            }
            else{
                System.out.println("Some shit happened");
            }
        }
    }
}

