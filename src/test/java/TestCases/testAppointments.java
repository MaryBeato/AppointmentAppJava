package TestCases;

import Data.TestData;
import PO.HomePO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.time.Duration;

public class testAppointments {
    private WebDriver driver;
    private HomePO homePO;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePO = new HomePO(driver);
    }

    @Test (dataProvider = "PetAppointment", dataProviderClass = TestData.class)
    public void testAddAppointment(String pet, String owner, String date, String time, String symptons) throws ParseException {
        homePO.goToURL();
        homePO.setPetNameField(pet);
        homePO.setOwnerField(owner);
        homePO.setDateField(date);
        homePO.setTimeField(time);
        homePO.setSymptonsField(symptons);
        homePO.clickAddAppointment();
        Assert.assertTrue(homePO.verifyDetails(pet,owner,date,time,symptons));


    }

    @Test(dataProvider = "PetAppointment", dataProviderClass = TestData.class)
    public void testDelete(String pet, String owner, String date, String time, String symptons) throws ParseException {
        Assert.assertTrue(homePO.verifyDetails(pet,owner,date,time,symptons));
        homePO.clickDelete(pet);
        Assert.assertFalse(homePO.verifyDetails(pet,owner,date,time,symptons));

    }

    @Test
    public void verifyRequiredFields(){
        homePO.clickAddAppointment();
        Assert.assertTrue(homePO.verifyAlert());
    }

    @AfterTest
    public void quit(){
        driver.quit();
    }

}
