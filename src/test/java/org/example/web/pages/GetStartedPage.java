package org.example.web.pages;

import org.example.web.test.LoanPurpose;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class GetStartedPage {

    WebDriver driver;

    @FindBy(how = How.NAME, using="desiredAmount")
    WebElement desiredAmount;
    @FindBy(how = How.TAG_NAME, using="select")
    WebElement loanPurpose;
    @FindBy(how = How.CSS, using="button[type='submit']")
    WebElement checkRateBtn;



    public GetStartedPage(WebDriver driver){
        this.driver = driver;
    }

    public void setLoanAmount(String amount){
        desiredAmount.sendKeys(amount);
    }

    public void setLoanPurpose(LoanPurpose loanPurposeEnum){
        Select myLoanPurpose =  new Select(loanPurpose);
        myLoanPurpose.selectByValue(loanPurposeEnum.name());
    }

    public PersonalInfoPage checkRate(){
        checkRateBtn.click();
        return PageFactory.initElements(driver,PersonalInfoPage.class);

    }

}
