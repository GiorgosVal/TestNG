package dataproviders;

import org.testng.annotations.DataProvider;

public class UserDataProvider {

    @DataProvider (name = "UserDataProvider")
    public static Object[][] userDataProvider() {
        return new Object[][] {
                {"mario@email.com", "marioPassword1", "Mario", 33},
                {"luigi@email.com", "luigiPassword1", "Luigi", 30}
        };
    }

}
