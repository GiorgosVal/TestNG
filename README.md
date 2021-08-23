TestNG installation methods:
* [IntelliJ IDEA](https://testng.org/doc/idea.html)
* [Eclipse](https://testng.org/doc/eclipse.html)
* [Maven](https://testng.org/doc/maven.html)
* [Ant](https://testng.org/doc/ant.html)


# Annotations
## Definitions
- **Test Project:** A test project can have multiple Suites 
- **Suite:** A collection of tests
- **Test:** A collection of Test Classes
- **Class:** A java Test Class
- **Method:** A specific test method inside a Test Class

## TestNG annotations lifecycle
Below are the TestNG annotations, sorted by their execution order.

Pre-Conditions: run before the tests
* `@BeforeSuite`
* `@BeforeTest`
* `@BeforeClass`
* `@BeforeMethod`

Conditions: the tests
* `@Test`

Post_conditions: run after the tests
* `@AfterMethod`
* `@AfterClass`
* `@AfterTest`
* `@AfterSuite`

A more comprehensive example:

```java
public abstract class AbstractTest {

    @BeforeSuite
    public void beforeSuite() {

        System.out.println("BeforeSuite");
    }

    @BeforeTest
    public void beforeTest() {

        System.out.println("BeforeTest");
    }

    @BeforeClass
    public void beforeClass() {

        System.out.println("BeforeClass");
    }

    @BeforeMethod
    public void beforeMethod() {

        System.out.println("BeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {

        System.out.println("AfterMethod");
    }


    @AfterClass
    public void afterClass() {

        System.out.println("AfterClass");
    }

    @AfterTest
    public void afterTest() {

        System.out.println("AfterTest");
    }

    @AfterSuite
    public void afterSuite() {

        System.out.println("AfterSuite");
    }
}
```

We have two test classes:
```java
import org.testng.annotations.Test;

public class TestOne extends AbstractTest {

    @Test
    public void testOne() {

        System.out.println(this.getClass().getSimpleName() + " :> Test 1");
    }

    @Test
    public void testTwo() {

        System.out.println(this.getClass().getSimpleName() + " :> Test 2");
    }

}
```

```java
import org.testng.annotations.Test;

public class TestTwo extends AbstractTest {

    @Test
    public void testOne() {

        System.out.println(this.getClass().getSimpleName() + " :> Test 1");
    }

    @Test
    public void testTwo() {

        System.out.println(this.getClass().getSimpleName() + " :> Test 2");
    }

}
```

Having the following testng.xml configuration:
```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >

<suite name="Suite 1" verbose="1" >
  <test name="Smoke Tests" >
    <classes>
      <class name="TestOne" >
        <methods>
          <include name="testOne"/>
        </methods>
      </class>
      <class name="TestTwo" >
        <methods>
          <include name="testOne"/>
        </methods>
      </class>
    </classes>
  </test>

  <test name="Regression Tests">
    <classes>
      <class name="TestOne"/>
      <class name="TestTwo"/>
    </classes>
  </test>
</suite>
```

When we run the tests we'll see the following output:
```text
BeforeSuite
.  BeforeTest
.  .  BeforeClass
.  .  .  BeforeMethod
.  .  .  .  TestOne :> Test 1
.  .  .  AfterMethod
.  .  AfterClass
.  .  BeforeClass
.  .  .  BeforeMethod
.  .  .  .  TestTwo :> Test 1
.  .  .  AfterMethod
.  .  AfterClass
.  AfterTest
.  BeforeTest
.  .  BeforeClass
.  .  .  BeforeMethod
.  .  .  .  TestOne :> Test 1
.  .  .  AfterMethod
.  .  .  BeforeMethod
.  .  .  .  TestOne :> Test 2
.  .  .  AfterMethod
.  .  AfterClass
.  .  BeforeClass
.  .  .  BeforeMethod
.  .  .  .  TestTwo :> Test 1
.  .  .  AfterMethod
.  .  .  BeforeMethod
.  .  .  .  TestTwo :> Test 2
.  .  .  AfterMethod
.  .  AfterClass
.  AfterTest
AfterSuite


===============================================
Suite 1
Total tests run: 6, Failures: 0, Skips: 0
===============================================
```
# TestNG configuration
## The `testng.xml` file
The `testng.xml` configuration file is used to store data about the tests to execute. 


# Test execution order
The order of TestNG tests can be affected by:
* The order the tests are found inside the `testng.xml` file
* The `preserve-order` attribute of the `<test>` tag (if `false` the tests will run in unpredictable order)
* The `dependsOnMethods`, `dependsOnGroups`, `priority` attributes of the `@Test` annotation
  * `priority`: does not guarantee that a later test will wait for the previous test to finish
  * `dependsOnMethods` and `dependsOnGroups` both guarantee that a later test that depends on the
  respective method/group will wait for that method/group to finish before it starts executing
* The alphabetical sorting of the test methods names - if no `dependsOnMethods`, `dependsOnGroups`, `priority` are used

# Grouping of tests
A group can consist of a module, a certain test type, or whatever we decide.
One test method can belong to a list of groups. The is achieved with the `groups` attribute of
the `@Test` annotation.

This will help running our tests including/excluding these groups according to our needs:
```xml
<groups>
  <run>
    <include name="regression"/>
    <exclude name="defect.fix"/>
  </run>
</groups>
```

# Assertions
## Hard assertions
TestNG hard assertions are all assertions located under `org.testng.Assert` package.
## Soft assertions
TestNG soft assertions are all assertions located under `org.testng.asserts.SoftAssert` package.
To use them simply instantiate a `SoftAssert` object and remember to use `assertAll()` at the end:
```java

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test
public class TestOne extends AbstractTest {

    SoftAssert softAssert = new SoftAssert();
    
    public void test() {

        softAssert.assertTrue(true, "assertion 1 failed");
        softAssert.assertTrue(false, "assertion 2 failed");
        softAssert.assertTrue(true, "assertion 3 failed");
        softAssert.assertTrue(false, "assertion 4 failed");
        softAssert.assertAll();
    }

}
```
If we execute the above test we'll see the output:
```text
java.lang.AssertionError: The following asserts failed:
assertion 2 failed, assertion 4 failed
```

# Data Driven Testing
## Data providers
We can have a Data provider either inside the test class, or in a different class (in this case must be static).

Example in the same test class
```java
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestThree extends AbstractTest {

    @Test(dataProvider = "UserDataProvider")
    public void testTwo(String email, String password, String name, int age) {
        // assertions...
    }

    @DataProvider (name = "UserDataProvider")
    public Object[][] userDataProvider() {
        return new Object[][] {
            {"mario@email.com", "marioPassword1", "Mario", 33},
            {"luigi@email.com", "luigiPassword1", "Luigi", 30}
        };
    }
}
```

Example in a different class
```java
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
```


```java
import dataproviders.UserDataProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestThree extends AbstractTest {

    @Test(dataProvider = "UserDataProvider", dataProviderClass = UserDataProvider.class)
    public void testTwo(String email, String password, String name, int age) {
        // assertions...
    }
}
```

## Parameters
### The `<parameter>` tag
With the `<parameter>` tag we can pass data as parameters, across the whole suite or a specific test.

Example:
```xml
<suite name="Suite 1" verbose="1" >
  <parameter name="URL" value="https://google.com"/>

  <test name="Test on IE">
    <parameter name="BrowserType" value="Internet Explorer"/>
    <classes>
      <class name="TestThree"/>
    </classes>
  </test>
  <test name="Test on Firefox">
    <parameter name="BrowserType" value="Firefox"/>
    <classes>
      <class name="TestThree"/>
    </classes>
  </test>
</suite>
```
Here we passed the parameter `URL` across the whole suite with the same value. The parameter `BrowserType`
passed in each test with a different value.

>**Note:** If the same parameter is passed on `suite` level and on `test` level, then the parameter
> of the `test` level will be used.

### Access the parameters
To access the parameters declared:
```java
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestThree extends AbstractTest {

    @Test
    @Parameters({"URL", "BrowserType"})
    public void testThree(String url, String browserType) {
        System.out.println(this.getClass().getSimpleName() + " :> " + url);
        System.out.println(this.getClass().getSimpleName() + " :> " + browserType);
    }
}
```

## Other Data sources
We can provide data to our tests with:
* CSV file
* Database
* Properties file
* Microsoft Excel
* Hard-Coded Data

# Additional TestNG Concepts
## Disabling tests
## Execute package at runtime
## Execute TestNG from the CLI
## Optional values
## Listeners of test result
## Logs
## Reporting
### Default reports
### Custom reports
## Multithreading


