package org.example.web.pages;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class OfferPage {

    WebDriver driver;
    @FindBy(how = How.CSS, using="span[data-auto='userLoanAmount']")
    WebElement loanAmount;
    @FindBy(how = How.CSS, using="span[data-auto='defaultMonthlyPayment']")
    WebElement monthlyPayment;
    @FindBy(how = How.CSS, using="div[data-auto='defaultLoanTerm")
    WebElement term;
    @FindBy(how = How.CSS, using="div[data-auto='defaultLoanInterestRate']")
    WebElement interestRate;
    @FindBy(how = How.CSS, using=".sc-fIxnHU > div:nth-child(1)")
    WebElement apr;
    @FindBy(how = How.CSS, using="a[href='/funnel/logout']")
    WebElement signOut;

    public OfferPage(WebDriver driver){
        this.driver = driver;
    }

    public String getLoanAmount() {
        Wait<WebDriver> wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class);
        wait.until(ExpectedConditions. visibilityOf(loanAmount));
        return loanAmount.getText();
    }

    public String getMonthlyPayment() {
        return monthlyPayment.getText();
    }

    public String getTerm() {
        return term.getText();
    }

    public String getInterestRate() {
        return interestRate.getText();
    }

    public String getApr() {
        return apr.getText();
    }

    public void clickSignOut(){
        Actions actions = new Actions(driver);
        actions.moveToElement(signOut).click();
    }
}
