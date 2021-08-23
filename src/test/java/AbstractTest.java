import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public abstract class AbstractTest {

    @BeforeSuite
    public void beforeSuite() {

        //System.out.println("BeforeSuite :> Suite setup - Chrome - Set up System Property");
    }

    @BeforeTest
    public void beforeTest() {

        //System.out.println("BeforeTest :> Open Chrome");
    }

    @BeforeClass
    public void beforeClass() {

        //System.out.println("BeforeClass :> Open Test Application");
    }

    @BeforeMethod
    public void beforeMethod() {

        //System.out.println("BeforeMethod :> Sign in");
    }

    @AfterMethod
    public void afterMethod() {

        //System.out.println("AfterMethod :> Sign out");
    }


    @AfterClass
    public void afterClass() {

        //System.out.println("AfterClass :> Close Test Application");
    }

    @AfterTest
    public void afterTest() {

        //System.out.println("AfterTest :> Close Chrome");
    }

    @AfterSuite
    public void afterSuite() {

        //System.out.println("AfterSuite :> Chrome - clean up cookies");
    }
}
