package qa.tests;

import java.util.Set;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MyTest
{
	
    @DataProvider( name = "dp" )
    public Object[][] getTestData() {
        Object[][] data = new Object[][] {
                { 1, "TestCase1", "Sample test 1" },
                { 2, "TestCase2", "Sample test 2" },
                { 3, "TestCase3", "Sample test 3" },
                { 4, "TestCase4", "Sample test 4" },
                { 5, "TestCase5", "Sample test 5" }
        };
        return data;
    }

    @Test( dataProvider = "dp" )
    public void testSample1( int num, String name, String desc, ITestContext ctx ) {
    	ctx.setAttribute( "testName" + num, name );
    	ctx.setAttribute( "testDesc" + num, desc );
        assertTrue( true );
    }

    @Test( dataProvider = "dp" )
    public void testSample2( int num, String name, String desc, ITestContext ctx ) {
    	ctx.setAttribute( "testName" + num, name );
    	ctx.setAttribute( "testDesc" + num, desc );
    	assertTrue( true );
    }
    
    @AfterTest
    public void printIt( ITestContext ctx ) {
    	Set<String> attNames = ctx.getAttributeNames();
    	for ( String x : attNames ) {
    	     Reporter.log( "ITESTCONTEXT - KEY: " + x + " VAL: " + ctx.getAttribute( x ), true );
    	}
    }

}

