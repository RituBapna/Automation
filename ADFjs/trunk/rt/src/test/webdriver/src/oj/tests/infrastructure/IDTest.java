package oj.tests.infrastructure;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;

/* This test covers
 * 1. translation toggling between   en and fr using button action
 * 2. Merging of resource translation bundles
 * 3. Translation for labels and innerText
 * 4. translation for validation error messages
 * 5. translation of placeholder value
*/


public class IDTest extends OJetBase {

    public IDTest() {
        super(new TestConfigBuilder().setContextRoot("infrastructure").setAppRoot("idTest").
                    setHomePage("testInfra.html").setHomePageTitle("Oracle JET Infrastructure Test").build());
    }

    @Test(groups = { "infrastructure" })
    public void testInfrastructure() throws Exception {
        startupTest();
        // wait for the ready flag to be set
        waitForLoadFlag();
        
        //wait for the page to be displayed
        waitForElementVisible("{\"element\":\"#spin\",\"subId\":\"oj-inputnumber-up\"}");

    }


}
