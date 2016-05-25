package oj.tests.datamodel;

import oracle.ojet.automation.test.OJetBase;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;

public class CommonUtils {
    /**
     * For the apps in this package, IE immediately shows alert: "This page is accessing information that is not under
     * its control." which will cause the next command right after opening the page to fail. This includes verifyTitle
     * and so we cannot use startupTest() here but have to split the start of the browser and title verification.
     */
    public static void startupTest(OJetBase base, boolean isInternetExplorer) {
        if (isInternetExplorer) {
            // handle alert only shown on IE
            while (true) {
                Alert alert = base.getWebDriver().switchTo().alert();
                alert.accept();
                try {
                    // if there is still an alert present, it will throw UnhandledAlertException
                    base.getTitle();
                    // no exception thrown --> test is good to continue
                    break;
                } catch (UnhandledAlertException uae) {
                    // sleep at little before trying to accept alert again
                    base.sleep(250L);
                }
            }
        }
    }
}
