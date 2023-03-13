package PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomePO {
    private WebDriver driver;

    public HomePO(WebDriver driver){
        this.driver = driver;
    }
    private By petNameField = By.xpath("//input[@data-testid=\"pet\"]");
    private By ownerField = By.xpath("//input[@data-testid=\"owner\"]");
    private By dateField = By.xpath("//input[@data-testid=\"date\"]");
    private By timeField = By.xpath("//input[@data-testid=\"time\"]");
    private By symptonsField = By.xpath("//textarea[@data-testid=\"symptoms\"]");

    private By addAppointmentBtm = By.xpath("//button[@data-testid=\"btn-submit\"]");

    private By appointmentBox = By.xpath("//div[@data-testid=\"appointment\"]");

    private String detailsBox = "//div[@data-testid=\"appointment\"]";

    private By alertMessage = By.xpath("//p[@data-testid=\"alert\"]");
    public void goToURL(){
        driver.get("http://localhost:3000/");
    }

    public void setPetNameField(String petName){
        driver.findElement(petNameField).sendKeys(petName);
    }

    public void setOwnerField(String owner){
        driver.findElement(ownerField).sendKeys(owner);
    }

    public void setDateField(String date){
        driver.findElement(dateField).sendKeys(date);
    }

    public void setTimeField(String time){
        driver.findElement(timeField).sendKeys(time);
    }

    public void setSymptonsField(String sympton){
        driver.findElement(symptonsField).sendKeys(sympton);
    }

    public void clickAddAppointment(){
        driver.findElement(addAppointmentBtm).click();
    }

    public boolean verifyAlert(){
        return driver.findElement(alertMessage).isDisplayed();

    }

    public int getAppointment(String pet){
        int index = 0;
        List<WebElement> appointments = driver.findElements(appointmentBox);
        for (WebElement e: appointments) {
            if (e.getText().contains(pet)){
                index = appointments.indexOf(e);
            }
        }
        return index;
    }

    public String changeDateFormat(String sendFormat, String expectedFormat, String date) throws ParseException {
        DateFormat value1 = new SimpleDateFormat(sendFormat);
        DateFormat value2 = new SimpleDateFormat(expectedFormat);
        Date receive = value1.parse(date);
        System.out.println(value2.format(receive));
        return value2.format(receive);

    }


    public void clickDelete(String pet){
        int i = getAppointment(pet)+1;
        driver.findElement(By.xpath(detailsBox+"["+i+"]//button")).click();

    }



    public boolean verifyDetails(String pet, String owner, String date, String time, String symptons) throws ParseException {
        try {
            int i = getAppointment(pet) + 1;
            boolean verify = false;
            List<WebElement> details = driver.findElements(By.xpath(detailsBox + "[" + i + "]//p"));
            if (details.get(0).getText().contains(pet) &&
                    details.get(1).getText().contains(owner) &&
                    details.get(2).getText().contains(changeDateFormat("MM/dd/yyyy", "yyy-MM-dd", date)) &&
                    details.get(3).getText().contains(changeDateFormat("hh:mma", "HH:mm", time)) &&
                    details.get(4).getText().contains(symptons)) {
                verify = true;
            }

            return verify;
        }catch (Exception e){
            return false;
        }
    }

}
