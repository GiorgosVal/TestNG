import org.testng.annotations.Test;

public class TestTwo extends AbstractTest {

    @Test (groups = "init")
    public void testOne() {

        System.out.println(this.getClass().getSimpleName() + " :> Test 1");
    }

    @Test (dependsOnGroups = "init")
    public void testTwo() {

        System.out.println(this.getClass().getSimpleName() + " :> Test 2");
    }

}
