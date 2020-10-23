package org.example.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver = null;
    @FindBy(how = How.NAME, using="username")
    WebElement email;
    @FindBy(how = How.NAME, using="password")
    WebElement password;
    @FindBy(how = How.CSS, using="button[type='submit'][data-auto='login']")
    WebElement signIn;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void setEmail(String emailAddress) {
        email.sendKeys(emailAddress);
    }

    public void setPassword(String passwd) {
        password.sendKeys(passwd);
    }

    public OfferPage signIn() {
      signIn.click();
      return PageFactory.initElements(driver,OfferPage.class);
    }
    public OfferPage signIn(String emailAddress, String passwd) {
        email.sendKeys(emailAddress);
        password.sendKeys(passwd);
        signIn.click();
        return PageFactory.initElements(driver,OfferPage.class);
    }
}
