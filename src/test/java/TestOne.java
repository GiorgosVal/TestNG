
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestOne extends AbstractTest {

    private SoftAssert softAssert = new SoftAssert();

    @Test
    public void testOne() {

        System.out.println(this.getClass().getSimpleName() + " :> Test 1");
    }

    @Test(groups = "defect.fix")
    public void testTwo() {

        softAssert.assertTrue(true, "assertion 1 failed");
        System.out.println(this.getClass().getSimpleName() + " :> Test 2 :> After soft assertion 1");
        softAssert.assertTrue(false, "assertion 2 failed");
        System.out.println(this.getClass().getSimpleName() + " :> Test 2 :> After soft assertion 2");
        softAssert.assertTrue(true, "assertion 3 failed");
        System.out.println(this.getClass().getSimpleName() + " :> Test 2 :> After soft assertion 3");
        softAssert.assertTrue(false, "assertion 4 failed");
        System.out.println(this.getClass().getSimpleName() + " :> Test 2 :> After soft assertion 4");
        softAssert.assertAll();
    }

}
