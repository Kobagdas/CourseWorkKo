package UIs;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class UItests extends CourseBP{
    public UItests(){
        super();
    }

    @Test
    public void uiTests(){
        driver.get("https://demoqa.com/books");
        BooksPage booksPage = PageFactory.initElements(driver, BooksPage.class);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        booksPage.clickLoginButton();
        System.out.println("step 1 passed");
        loginPage.typeName("Kobagdas");
        System.out.println("step 2 passed");
        loginPage.typePassword("MickSw@gger2022");
        System.out.println("step 3 passed");
        loginPage.clickLoginButton();
        System.out.println("step 4 passed");
        booksPage.verifyThatBookIsPresent();
        System.out.println("step 5 passed");
        booksPage.clickLogoutButton();
        System.out.println("step 6 passed");
        loginPage.checkThatTextIsPresent("Login in Book Store");
        System.out.println("step 7 passed");

    }

}
