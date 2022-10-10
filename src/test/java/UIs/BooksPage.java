package UIs;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BooksPage extends CourseBP{

    @FindBy (xpath = "//*[@id='login']")
    WebElement loginButton;
    @FindBy(xpath = "//*[@id='see-book-Git Pocket Guide']/a")
    WebElement gitPocketGuide;
    @FindBy(xpath = "//*[@id='submit']")
    WebElement logOutButton;

    public BooksPage(WebDriver driver){
        super(driver);
    }
    public void clickLoginButton(){
        clickButton(loginButton);
    }
    public void verifyThatBookIsPresent(){
        assert gitPocketGuide.isDisplayed();
    }
    public void clickLogoutButton(){
        clickButton(logOutButton);
    }

}
