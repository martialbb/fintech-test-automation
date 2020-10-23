package org.example.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PersonalInfoPage {

    WebDriver driver;

    @FindBy(how = How.NAME, using="borrowerFirstName")
    WebElement firstName;
    @FindBy(how = How.NAME, using="borrowerLastName")
    WebElement lastName;
    @FindBy(how = How.ID, using="borrowerStreet")
    WebElement street;
    @FindBy(how = How.NAME, using="borrowerCity")
    WebElement city;
    @FindBy(how = How.NAME, using="borrowerState")
    WebElement state;
    @FindBy(how = How.NAME, using="borrowerZipCode")
    WebElement zipcode;
    @FindBy(how = How.NAME, using="borrowerDateOfBirth")
    WebElement dateOfBirth;
    @FindBy(how = How.CSS, using="button[type='submit']")
    WebElement continueBtn;
    @FindBy(how = How.NAME, using="borrowerIncome")
    WebElement income;
    @FindBy(how = How.NAME, using="borrowerAdditionalIncome")
    WebElement additionalIncome;
    @FindBy(how = How.NAME, using="username")
    WebElement email;
    @FindBy(how = How.NAME, using="password")
    WebElement password;
    @FindBy(how = How.CSS, using=".sc-kYQaHc")
    WebElement checkBox;
    @FindBy(how = How.CSS, using="button[type='submit'][data-auto='submitPersonalInfo']")
    WebElement submitPersonalInfo;



    public PersonalInfoPage(WebDriver driver){
        this.driver = driver;
    }

    public void setFirstName(String fName) {
        firstName.sendKeys(fName);
    }

    public void setLastName(String lName) {
        lastName.sendKeys(lName);
    }

    public void setStreet(String address) {
       street.sendKeys(address);
    }

    public void setCity(String mycity) {
        city.sendKeys(mycity);
    }

    public void setState(String mystate) {
        state.sendKeys(mystate);
    }

    public void setZipcode(String zip) {
        zipcode.sendKeys(zip);
    }

    public void setDateOfBirth(String dob) {
       dateOfBirth.sendKeys(dob);
    }

    public void clickContinueBtn() {
        continueBtn.click();
        //return null;
    }

    public void setIncome(String borrowerIncome) {
        income.sendKeys(borrowerIncome);
    }

    public void setAdditionalIncome(String borrowerAdditionalIncome) {
        additionalIncome.sendKeys(borrowerAdditionalIncome);
    }

    public void setEmail(String emailAddress) {
        email.sendKeys(emailAddress);
    }

    public void setPassword(String passwd) {
        password.sendKeys(passwd);
    }

    public void clickCheckBox() {
        checkBox.click();
    }

    public OfferPage setSubmitPersonalInfo() {
        submitPersonalInfo.click();
        return PageFactory.initElements(driver,OfferPage.class);
    }
}
