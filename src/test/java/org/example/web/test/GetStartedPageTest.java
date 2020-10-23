package org.example.web.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;
import org.example.web.pages.GetStartedPage;
import org.example.web.pages.LoginPage;
import org.example.web.pages.OfferPage;
import org.example.web.pages.PersonalInfoPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetStartedPageTest {

    public static final String CREDIFY_TECH_PORTAL_LOGIN = "https://www.credify.tech/portal/login";
    public static final String BORROWER_STREET_ADDRESS = "275 Battery Street, 23rd Floor";
    public static final String BORROWER_CITY = "San Francisco";
    public static final String BORROWER_STATE = "CA";
    public static final String BORROWER_ZIPCODE = "94111";
    public static final String BORROWER_DOB = "01/01/1990";
    public static final String BORROWER_INCOME = "130000";
    public static final String BORROWER_ADDITIONAL_INCOME = "10000";
    public static final String CREDIFY_URL = "https://www.credify.tech/phone/nonDMFunnel";
    public static final String LOAN_AMOUNT = "2000";
    public static final String EMAIL = "candidate+" + RandomStringUtils.randomNumeric(5)+"@upgrade-challenge.com";
    public static final String PASSWORD = RandomStringUtils.random(10,true,true);

    static WebDriver driver = null;

    @BeforeAll
    public static void setUp(){



        try (InputStream inputStream = new FileInputStream("./src/test/resources/config.properties"))
        {
            Properties prop = new Properties();
            prop.load(inputStream);
            if (prop.getProperty("browser").equalsIgnoreCase("firefox")){
                driver = new FirefoxDriver();
            }
            else if (prop.getProperty("browser").equalsIgnoreCase("chrome")){
                driver = new ChromeDriver();
            }
            else if (prop.getProperty("browser") ==null || prop.getProperty("browser").equals("")){
                driver = new FirefoxDriver();
            }
            else{
                driver = new FirefoxDriver();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(CREDIFY_URL);
    }

    @Test
    public void webAutoTest(){
        GetStartedPage getStartedPage = PageFactory.initElements(driver,GetStartedPage.class);
        getStartedPage.setLoanAmount(LOAN_AMOUNT);
        getStartedPage.setLoanPurpose(LoanPurpose.HOME_IMPROVEMENT);
        PersonalInfoPage personalInfoPage= getStartedPage.checkRate();
        personalInfoPage.setFirstName(RandomStringUtils.randomAlphabetic(10));
        personalInfoPage.setLastName(RandomStringUtils.randomAlphabetic(10));
        personalInfoPage.setStreet(BORROWER_STREET_ADDRESS);
        personalInfoPage.setCity(BORROWER_CITY);
        personalInfoPage.setState(BORROWER_STATE);
        personalInfoPage.setZipcode(BORROWER_ZIPCODE);
        personalInfoPage.setDateOfBirth(BORROWER_DOB);
        personalInfoPage.clickContinueBtn();
        personalInfoPage.setIncome(BORROWER_INCOME);
        personalInfoPage.setAdditionalIncome(BORROWER_ADDITIONAL_INCOME);
        personalInfoPage.clickContinueBtn();
        personalInfoPage.clickContinueBtn();
        personalInfoPage.setEmail(EMAIL);
        personalInfoPage.setPassword(PASSWORD);
        personalInfoPage.clickCheckBox();
        OfferPage offerPageAfterDefault =  personalInfoPage.setSubmitPersonalInfo();
        offerPageAfterDefault.clickSignOut();
        LoginPage loginPage = navigateToLoginPage(CREDIFY_TECH_PORTAL_LOGIN);
        OfferPage offerPageAfterSignIn = loginPage.signIn(EMAIL, PASSWORD);
        validateOffer(offerPageAfterDefault, offerPageAfterSignIn);

    }

    private void validateOffer(OfferPage offerPageAfterDefault, OfferPage offerPageAfterSignIn) {
        assertEquals(offerPageAfterDefault.getLoanAmount(), offerPageAfterSignIn.getLoanAmount());
        assertEquals(offerPageAfterDefault.getMonthlyPayment(), offerPageAfterSignIn.getMonthlyPayment());
        assertEquals(offerPageAfterDefault.getTerm(), offerPageAfterSignIn.getTerm());
        assertEquals(offerPageAfterDefault.getInterestRate(), offerPageAfterSignIn.getInterestRate());
        assertEquals(offerPageAfterDefault.getApr(), offerPageAfterSignIn.getApr());
    }

    private static LoginPage navigateToLoginPage(String loginUrl){
        driver.navigate().to(loginUrl);
        return PageFactory.initElements(driver, LoginPage.class);
    }


    @AfterAll
    public static void tearDown(){

       driver.quit();
    }

}
