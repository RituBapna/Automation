package oj.tests.translation;

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


public class TranslationTest extends OJetBase {
    private static final String BLUE = "testblue";
    private static final String GREEN = "testgreen";
    private static final String RED = "testred";
    private static final String BUTTON = "toggle";
    private static final String INPUT_LABEL = "inputLabel";
    private static final String INPUT_FIELD = "inputText";

    public TranslationTest() {
        super(new TestConfigBuilder().setContextRoot("translation").setAppRoot("translationTest").
                    setHomePage("testTranslation.html").setHomePageTitle("Oracle JET Translation Test").build());
    }

    @Test(groups = { "translation" })
    public void testTextTranslation() throws Exception {
        startupTest();
        //wait for the page to be displayed
        waitForElementVisible(RED);

        //get the text value in default "en" locale
        waitForText(RED, "customred");
        verifyValue("Incorrect label;", "customblue", getText(BLUE));
        verifyValue("Incorrect label;", "green", getText(GREEN));

        //toggle to fr
        click(BUTTON);
        waitForText(RED, "customrouge-fr");
        verifyValue("Incorrect label;", "bleu-fr", getText(BLUE));
        verifyValue("Incorrect label;", "green", getText(GREEN));

        //toggle back to en
        click(BUTTON);
        waitForText(RED, "customred");
        verifyValue("Incorrect label;", "customblue", getText(BLUE));
        verifyValue("Incorrect label;", "green", getText(GREEN));
        skipBrowserRestart();
    }

    @Test(groups = { "translation" })
    public void testLabelTranslation() throws Exception {
        startupTest();

        //wait for the table to be displayed
        waitForElementVisible(RED);

        //get the text value in default "en" locale
        waitForText(INPUT_LABEL, "Required Input Text Field");
        verifyValue("Incorrect button label", "Label in English: Switch to French", getValue(BUTTON));

        //toggle to fr
        click(BUTTON);
        waitForText(INPUT_LABEL, "FRENCH: Required Input Text Field");
        verifyValue("Incorrect button label", "Label in French-fr: Switch to English", getValue(BUTTON));

        //toggle back to en
        click(BUTTON);
        waitForText(INPUT_LABEL, "Required Input Text Field");
        verifyValue("Incorrect button label", "Label in English: Switch to French", getValue(BUTTON));
        skipBrowserRestart();
    }

    @Test(groups = { "translation" })
    public void testPlaceholderTranslation() throws Exception {
        startupTest();

        //wait for the table to be displayed
        waitForElementVisible(RED);

        //get the text value in default "en" locale
        waitForAttribute(INPUT_FIELD, "placeholder", "Place Holder Value");

        //toggle to fr
        click(BUTTON);
        waitForAttribute(INPUT_FIELD, "placeholder", "FRENCH: Place Holder Value");

        //toggle back to en
        click(BUTTON);
        waitForAttribute(INPUT_FIELD, "placeholder", "Place Holder Value");
        skipBrowserRestart();
    }
/*
    @Test(groups = { "translation" })
    public void testTitleTranslation() throws Exception {
        startupTest();

        //wait for the table to be displayed
        waitForElementVisible(RED);

        //get the text value in default "en" locale
        verifyValue("Incorrect tooltip title value;", "CUSTOM: Title value", getTooltip(INPUT_FIELD));

        //toggle to fr
        click(BUTTON);
        verifyValue("Incorrect tooltip title value;", "FRENCH: CUSTOM: Title value",
                    getTooltip(INPUT_FIELD));

        //toggle back to en
        click(BUTTON);
        verifyValue("Incorrect tooltip title value;", "CUSTOM: Title value", getTooltip(INPUT_FIELD));
        skipBrowserRestart();
    }
*/
}
