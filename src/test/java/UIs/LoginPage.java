package UIs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class LoginPage extends CourseBP{
    @FindBy (xpath = "//*[@id='userName']")
    WebElement typeNameField;
    @FindBy (xpath = "//*[@id='password']")
    WebElement typePassField;
    @FindBy (xpath = "//*[@id='login']")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void typeName(String name){
        typeText(typeNameField, name);
    }
    public void typePassword(String pass) {
        typeText(typePassField, pass);
    }
    public void clickLoginButton(){
        clickButton(loginButton);
    }
    public void checkThatTextIsPresent(String text){
        driver.getPageSource().contains(text);
    }

}
