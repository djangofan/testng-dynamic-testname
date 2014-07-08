package qa.tests;

import java.lang.reflect.Method;

import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.test.TestParameters;

public class MyTest implements ITest
{
    // Has to be set to prevent NullPointerException from reporters
    protected String mTestCaseName = "";

    @DataProvider(name="BasicDataProvider")
    public Object[][] getTestData() {
        Object[][] data = new Object[][] {
                { new TestParameters("TestCase1", "Sample test 1")},
                { new TestParameters("TestCase2", "Sample test 2")},
                { new TestParameters("TestCase3", "Sample test 3")},
                { new TestParameters("TestCase4", "Sample test 4")},
                { new TestParameters("TestCase5", "Sample test 5") }
        };
        return data;
    }

    @BeforeMethod( alwaysRun = true )
    public void testData( Method method, Object[] testData ) {
        String testCase = "";
        if (testData != null && testData.length > 0) {
            TestParameters testParams = null;
            //Check if test method has actually received required parameters
            for ( Object testParameter : testData ) {
                if ( testParameter instanceof TestParameters ) {
                    testParams = (TestParameters)testParameter;
                    break;
                }
            }
            if ( testParams != null ) {
                testCase = testParams.getTestName();
            }
        }
        this.mTestCaseName = String.format("%s(%s)", method.getName(), testCase);
        //this.mTestCaseName = testCase;
    }

    @Override
    public String getTestName() {
        return this.mTestCaseName;
    }

    @Test( dataProvider="BasicDataProvider" )
    public void testSample1( TestParameters testParams ){
        //test code here
    }

    @Test(dataProvider="BasicDataProvider")
    public void testSample2( TestParameters testParams ){
        //test code here
    }

    @Test
    public void testSample3(){
        //test code here
    }

}

