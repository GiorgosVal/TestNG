import dataproviders.UserDataProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestThree extends AbstractTest {

    @Test(dataProvider = "myProvider", groups = "init")
    public void testOne(String email, int anInteger, boolean isTrue, Class aClass, ExampleClass exampleClass) {

        System.out.println(this.getClass().getSimpleName() + " :> " + email);
        System.out.println(this.getClass().getSimpleName() + " :> " + anInteger);
        System.out.println(this.getClass().getSimpleName() + " :> " + isTrue);
        System.out.println(this.getClass().getSimpleName() + " :> " + aClass.getSimpleName());
        System.out.println(this.getClass().getSimpleName() + " :> " + exampleClass.anInteger);
    }

    @Test(dataProvider = "UserDataProvider", dataProviderClass = UserDataProvider.class, groups = "init")
    public void testTwo(String email, String password, String name, int age) {

        System.out.println(this.getClass().getSimpleName() + " :> " + email);
        System.out.println(this.getClass().getSimpleName() + " :> " + password);
        System.out.println(this.getClass().getSimpleName() + " :> " + name);
        System.out.println(this.getClass().getSimpleName() + " :> " + age);
    }

    @DataProvider(name = "myProvider")
    public Object[][] data() {

        return new Object[][] {
                {"johnDoe@email.com", 5, true, String.class, new ExampleClass(13)},
                {"maryJane@email.com", 10, false, Object.class, new ExampleClass(11)},
                {"fooBar@email.com", 4, false, Object.class, new ExampleClass(10)}
        };
    }

    @Test
    @Parameters({"URL", "BrowserType"})
    public void testThree(String url, String browserType) {
        System.out.println(this.getClass().getSimpleName() + " :> " + url);
        System.out.println(this.getClass().getSimpleName() + " :> " + browserType);
    }

    class ExampleClass {

        private int anInteger;

        ExampleClass(int anInteger) {

            this.anInteger = anInteger;
        }
    }
}
