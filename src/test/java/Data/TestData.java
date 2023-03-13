package Data;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider (name = "PetAppointment")
    public Object[][] petData(){
        return new Object[][]{{"Ganon","MAry","05/23/2023","05:54PM","Testing"},{"Link","Mary","02/02/2023","05:54PM","Testing"}};
    }
}
