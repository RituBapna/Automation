package oj.tests.common;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import java.util.List;

public abstract class TestCompBase extends OJetBase {

    protected boolean usesCollapsible = false;


    public TestCompBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp) {
        super(new TestConfigBuilder().setContextRoot(myContextRoot).setAppRoot(myAppRoot).build());
        this.myAppRoot = myAppRoot;
        this.myContextRoot = myContextRoot;
        this.pageTitle = pageTitle;
        this.myPage = myPage;
        this.myComp = myComp;
    }

    public TestCompBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String exposerId) {
        super(new TestConfigBuilder().setContextRoot(myContextRoot).setAppRoot(myAppRoot).build());
        this.myAppRoot = myAppRoot;
        this.myContextRoot = myContextRoot;
        this.pageTitle = pageTitle;
        this.myPage = myPage;
        this.myComp = myComp;
        this.exposerId = exposerId;
    }

    public void waitForComp() {
        // if you need to check for component to be rendered, ie, ready event
        log("Checking for compnent ready and exposed...");
        waitForMilliseconds(2000L);  // for non-tables which don't use ready signals
        waitForText("id=exposed", "exposed");
        waitForText("id=ready", "true");
        log("Component ready and exposed.");
    }

    public String myComp() {
      return this.myComp;
    }

    public void createComp(String ... expected) throws Exception {

        //Start the test and bring up the browser
        startupTest(myPage, pageTitle);

        verifyCreateComp(expected);
    }

    public void verifyCreateComp(String ... expected) throws Exception {

        // If inside collapsible, dialog, or popup, expose table first
        showComponent();

        waitForComp();
        // waitForMilliseconds(2000L);

        assertMsgLog(expected);
    }



    public void destroyComp(String ... expected) throws Exception {

        //Start the test and bring up the browser
        startupTest(myPage, null);

        verifyDestroyComp(expected);
    }

    public void verifyDestroyComp(String ... expected) throws Exception {

        // Clear log
        clickOn("id=btn_clear");

        // If inside collapsible, dialog, or popup, expose table first
        showComponent();

        waitForComp();

        boolean compShown = true;

        clickOn("id=btn_destroy");

        waitForMilliseconds(2000L);

        assertMsgLog(expected);

        try{
                WebElement t1 = getElement(compLocator());
                compShown = t1.isDisplayed();
        }catch(Exception e) {
                compShown = false;
        }

        Assert.assertFalse(compShown);

        assertMsgLog(expected);
    }





    public void clickOn(String locator) {
        waitForElementVisible(locator);

        WebElement comp = getElement(locator);

        if(!comp.isDisplayed()) {
            log("comp not displayed, not clicking on it : " + locator);
            return;
        }
        comp.click();
        waitForMilliseconds(1000L);
    }



    /**
     * Clicks on an element and selects the menu action item from the menu that displays
     *
     * @param elementLocator to identify the DOM element. Can be one of id, xpath, css, name or tagName.
     * @param menuActionLocator to identify the DOM element of the menu action item     *
     */
    public void clickAndSelectMenuOption(String elementLocator,
                    String menuActionLocator) throws WebDriverException {


        WebElement elem = getElement(elementLocator);
        Actions menuClick = new Actions(getWebDriver());
        menuClick.moveToElement(elem);
        menuClick.click(elem);

        waitForMilliseconds(3000L);

        menuClick.perform();

        waitForElementVisible(menuActionLocator);

        //Click on menu option
        WebElement menuOpt = getElement(menuActionLocator);
        menuOpt.click();

    }

    protected String getMsgLog(String ... myStrings) {
      WebElement msgLog = getElement(msgLogLocator());
      String retVal = "";
      retVal = stripString(msgLog.getAttribute("value"));
      for (String s : myStrings)
          retVal = stripMsg(retVal,s);
      return retVal;
    }

    protected void assertMsgLog(String ... expectedStrings) {
        assertTextareaEqual(msgLogLocator(),expectedStrings);
    }

    protected void assertMsgLogDebug(String ... expectedStrings) {
      String myLog = getMsgLog().trim();
      String expected = appendStrings(expectedStrings).trim();
      int myLogLen = myLog.length();
      int expectedLen = expected.length();
      Assert.assertEquals(String.valueOf(myLogLen),String.valueOf(expectedLen),
        "Same length log msgs expected: " + String.valueOf(myLogLen) + ","
        + String.valueOf(expectedLen));
      for(int i = 0; i < myLogLen; i++)
        Assert.assertTrue(myLog.charAt(i) == expected.charAt(i), "Character at " + String.valueOf(i)
            + " mismatch: "
            + myLog.charAt(i) + ", expected " + expected.charAt(i) + "[Found char val : " +
            String.valueOf((int)(myLog.charAt(i))) + ", expected  char val: " +
            String.valueOf((int)(expected.charAt(i))) + "]");
      int differsAt = myLog.compareTo(expected);
      Assert.assertTrue(differsAt == 0, "Expected differs at " + String.valueOf(differsAt) + " of string "
            + myLog + "[" + myLog.substring(differsAt));

    }

    protected void assertTextareaEqual(String locator, String... expectedStrings) {
        waitForElementVisible(locator);
        WebElement msgLog = getElement(locator);
        String actual = stripString(msgLog.getAttribute("value"));
        // Temp hack to ignore debug log statements in log
        String filtered = filterMsg(actual);
/*        String filtered = stripMsg(actual,"Setting val = hidden (was = exposed)");
//      filtered = stripMsg(actual,"Table ready event received : table");
        filtered = stripMsg(filtered,"Setting val = exposed (was = hidden)");
        filtered = stripMsg(filtered, "Status: exposed");
        filtered = stripMsg(filtered, "Status: hidden");
        filtered = stripMsg(filtered,"Setting ready = true (was = false");
        filtered = stripMsg(filtered,"Component created : paging_nav_input");
        filtered = stripMsg(filtered,"ojCombobox before expanded");
//      filtered = stripMsg(filtered,"reset table ready"); */
        String expected = appendStrings(expectedStrings);

        Assert.assertEquals(filtered, expected);

    }

    protected String filterMsg(String actual) {
        String filtered = stripMsg(actual,"Setting val = hidden (was = exposed)");
//      filtered = stripMsg(actual,"Table ready event received : table");
        filtered = stripMsg(filtered,"Setting val = exposed (was = hidden)");
        filtered = stripMsg(filtered, "Status: exposed");
        filtered = stripMsg(filtered, "Status: hidden");
        filtered = stripMsg(filtered,"Setting ready = true (was = false");
        filtered = stripMsg(filtered,"Component created : paging_nav_input");
        filtered = stripMsg(filtered,"ojCombobox before expanded");
        return filtered;
    }

    public WebElement getSubId(String subId) throws Exception {
        return getSubId(myComp,subId);
    }

    public WebElement getSubId(String id, String subId) throws Exception {
        String element = "{\"element\":\"#" + id + "\",\"subId\":\"" + subId + "\"}";
        WebElement webElement = getElement(element);
        try {
            log("#" + id + ":" + subId + ": "+ webElement.getText());
        } catch (StaleElementReferenceException e) {
            log("#" + id + ":" + " [UNABLE TO RETRIEVE]");
        }
        return webElement;
    }


    /**
     * Utility for stripping newlines from textarea values
     **/
    protected String stripString(String msgText) {
        if (msgText != null)
            return msgText.replace("\n", "");
        else
            return msgText;
    }

        /**
     * Utility for stripping informational msgs from log
     **/
    protected String stripMsg(String msgText,String msg) {
        if (msgText != null)
            return msgText.replace(msg, "");
        else
            return msgText;
    }

    /**
     * Utility for appending expected strings for textarea comparisons
     **/
    protected String appendStrings(String... myStrings) {
        String retString = "";
        for (String s : myStrings)
            retString += s;
        return retString;
    }

    public String msgLogLocator() {
        return "id=" + msgLog;
    }

    public String compLocator() {
        return "id=" + myComp();
    }



    protected void log(String log) {
        System.out.println(log);

        getLogger().fine("[" + myContextRoot + "Test] " + log);
    }

    public WebElement parentOf(WebElement myElement) {
        return (WebElement) ((JavascriptExecutor) getWebDriver()).executeScript(
            "return arguments[0].parentNode;", myElement);
    }

    public WebElement labelOf(WebElement myElement) {
      // Find the id for the element
      String idValue = myElement.getAttribute("id");
      // Search for all the label elements
      WebElement body = getElement("id=html_body");
      List<WebElement> labels = body.findElements(By.tagName("label"));
      // Find the label with attribute for == id
      for(WebElement we : labels){
        if(we.getAttribute("for").matches(idValue)) {
          System.out.println("label = " + we.getAttribute("outerHTML"));
          return we;
        }
      }
      Assert.assertTrue(false, "Unable to find label for " + idValue + " in labels = " + labels);
      return null; // not found
    }

    public boolean labelHasSpan(WebElement myLabel) {
      WebElement labelGroup = parentOf(myLabel);
      List<WebElement> spans = labelGroup.findElements(By.tagName("span"));
      return spans.size() > 0;
    }

    public WebElement spanOf(WebElement myLabel) {
      WebElement labelGroup = parentOf(myLabel);
      System.out.println("labelGroup = " + labelGroup.getAttribute("outerHTML"));
      // WebElement span = labelGroup.findElement(By.className("oj-label-required-icon"));
      WebElement span = labelGroup.findElement(By.tagName("span"));
      System.out.println("labelGroup span = " + span.getAttribute("outerHTML"));
      return span;
    }

    public boolean elementHasAttribute(WebElement myElement, String myAttribute) {
              String attrValue = myElement.getAttribute(myAttribute);
              boolean isAttrValuePresent = (attrValue != null);
              Assert.assertTrue(isAttrValuePresent, "Attribute " + myAttribute + " not found on " + myElement.getTagName()
                + " outerHTML = " + myElement.getAttribute("outerHTML"));
              return isAttrValuePresent;
    }

    public boolean elementHasNoAttribute(WebElement myElement, String myAttribute) {
              String attrValue = myElement.getAttribute(myAttribute);
              boolean isAttrValuePresent = (attrValue != null);
              Assert.assertFalse(isAttrValuePresent, "Attribute " + myAttribute + " found on " + myElement.getTagName()
                + " outerHTML = " + myElement.getAttribute("outerHTML"));
              return !isAttrValuePresent;
    }

    public boolean elementHasAttributeValue(WebElement myElement, String myAttribute, String myValue) {
              String attrValue = myElement.getAttribute(myAttribute);
              boolean isAttrValuePresent = (attrValue != null) && (attrValue.equals(myValue));
              Assert.assertTrue(isAttrValuePresent, "Attribute " + myAttribute + ", value " + myValue + " not found on "
                  + myElement.getTagName() + " outerHTML = " + myElement.getAttribute("outerHTML") + ", found value " + attrValue);
              return isAttrValuePresent;
    }

    public boolean elementHasClass(WebElement myElement, String myClass) {
              String classes = myElement.getAttribute("class");
              boolean isCorrectClassPresent = (classes != null ) && (classes.indexOf(myClass) > -1);
              Assert.assertTrue(isCorrectClassPresent, "Class " + myClass + " not found on " + myElement
                + " outerHTML = " + myElement.getAttribute("outerHTML"));
              return isCorrectClassPresent;
    }

    public boolean elementHasNoClass(WebElement myElement, String myClass) {
              String classes = myElement.getAttribute("class");
              boolean isCorrectClassNotPresent = (classes == null ) || (classes.indexOf(myClass) == -1);
              Assert.assertTrue(isCorrectClassNotPresent, "Class " + myClass + " found on " + myElement
                + " outerHTML = " + myElement.getAttribute("outerHTML"));
              return isCorrectClassNotPresent;
    }

    public WebElement getParentContainer(WebElement myElement) {
      // override this as needed for combobox or others
        WebElement parentDiv = parentOf(myElement);
        if(parentDiv.getTagName().equals("span"))
          parentDiv = parentOf(parentDiv);
        return parentDiv;
    }

    public boolean parentHasClass(WebElement myElement, String myClass) {
        WebElement parentDiv = getParentContainer(myElement);
        return elementHasClass(parentDiv,myClass);
    }

    public boolean parentHasNoClass(WebElement myElement, String myClass) {
        WebElement parentDiv = getParentContainer(myElement);
        return elementHasNoClass(parentDiv,myClass);
    }

    public boolean parentHasAttribute(WebElement myElement, String myAttribute) {
        WebElement parentDiv = getParentContainer(myElement);
        return elementHasAttribute(parentDiv,myAttribute);
    }

    public boolean parentHasAttributeValue(WebElement myElement, String myAttribute, String myValue) {
        WebElement parentDiv = getParentContainer(myElement);
          return elementHasAttributeValue(parentDiv, myAttribute,myValue);
    }

    // In truth,it's not the label that gets classes, but a span the precedes
    // the label in the label group.  However, easier to just say labelHas...
    public WebElement getLabelSpan(WebElement myElement) {
        WebElement myLabel = labelOf(myElement);
        if(labelHasSpan(myLabel)) {
          WebElement mySpan = spanOf(myLabel);
          return mySpan;
        }
        return null;
    }


    public boolean labelHasClass(WebElement myElement, String myClass) {
        WebElement mySpan = getLabelSpan(myElement);
        if(mySpan != null) {
            return elementHasClass(mySpan,myClass);
        }
/*        WebElement myLabel = labelOf(myElement);
        if(labelHasSpan(myLabel)) {
          WebElement mySpan = spanOf(myLabel);
          return elementHasClass(mySpan,myClass);
        } */
        Assert.assertTrue(false, "Class " + myClass + " not found on " + myElement
          + " outerHTML = " + myElement.getAttribute("outerHTML"));
        return false;
    }

    public boolean labelHasNoClass(WebElement myElement, String myClass) {
        WebElement mySpan = getLabelSpan(myElement);
        if(mySpan != null) {
            return elementHasNoClass(mySpan,myClass);
        }
/*        WebElement myLabel = labelOf(myElement);
        if(labelHasSpan(myLabel)) {
          WebElement mySpan = spanOf(myLabel);
          return elementHasNoClass(mySpan,myClass);
        } */
        return true;
    }

    public boolean labelHasAttribute(WebElement myElement, String myAttribute) {
        WebElement mySpan = getLabelSpan(myElement);
        if(mySpan != null) {
            return elementHasAttribute(mySpan,myAttribute);
        }

/*        WebElement myLabel = labelOf(myElement);
        if(labelHasSpan(myLabel)) {
          WebElement mySpan = spanOf(myLabel);
          return elementHasAttribute(mySpan,myAttribute);
        } */
        Assert.assertTrue(false, "Attribute " + myAttribute + " not found on " + myElement.getTagName()
          + " outerHTML = " + myElement.getAttribute("outerHTML"));
        return false;
    }

    public boolean labelHasAttributeValue(WebElement myElement, String myAttribute, String myValue) {
        WebElement mySpan = getLabelSpan(myElement);
        if(mySpan != null) {
            return elementHasAttributeValue(mySpan,myAttribute,myValue);
        }
/*        WebElement myLabel = labelOf(myElement);
        if(labelHasSpan(myLabel)) {
          WebElement mySpan = spanOf(myLabel);
          return elementHasAttributeValue(mySpan,myAttribute, myValue);
        } */
        Assert.assertTrue(false, "Attribute " + myAttribute + ", value " + myValue + " not found on " + myElement.getTagName()
          + " outerHTML = " + myElement.getAttribute("outerHTML"));
        return false;
    }

    public boolean isMessageShown() {
      WebElement body = getElement("id=html_body");
      List<WebElement> elements = body.findElements(By.className("oj-messaging-inline-container"));
      if (elements.isEmpty())
        return false;
      else
        return true;
    }

    public boolean isHintShown() {
      WebElement body = getElement("id=html_body");
      List<WebElement> elements = body.findElements(By.className("oj-form-control-hint"));
      if (elements.isEmpty())
        return false;
      else
        return true;
    }

    public boolean isMenuShown() {
      WebElement body = getElement("id=html_body");
      List<WebElement> elements = body.findElements(By.className("oj-menu-layer"));
      if (elements.isEmpty())
        return false;
      else
        return true;
    }

    public boolean messageShown(String summary) {
      // Verify div with class oj-messaging-inline-container now in dom
      Assert.assertTrue(isMessageShown(), "Messages not shown");
      // Verify div with class oj-message-summary "Value is required." contents etc
      // Verify div with class oj-message-detail has child span of "You must enter a value." etc
      WebElement body = getElement("id=html_body");
      WebElement msgSummary = body.findElement(By.className("oj-message-summary"));
      Assert.assertEquals(msgSummary.getText().trim(), summary);
      return true;
    }


    public boolean messageShown(String summary, String detail) {
      // Verify div with class oj-messaging-inline-container now in dom
      Assert.assertTrue(isMessageShown(), "Messages not shown");
      // Verify div with class oj-message-summary "Value is required." contents etc
      // Verify div with class oj-message-detail has child span of "You must enter a value." etc
      WebElement body = getElement("id=html_body");
      WebElement msgSummary = body.findElement(By.className("oj-message-summary"));
      Assert.assertEquals(msgSummary.getText().trim(), summary);
      WebElement msgDetail = body.findElement(By.className("oj-message-detail"));
      Assert.assertEquals(msgDetail.getText().trim(), detail);
      return true;
    }

    public boolean hintShown(String hint) {
      Assert.assertTrue(isHintShown(), "Hint not shown");
      WebElement body = getElement("id=html_body");
      WebElement hintShown = body.findElement(By.className("oj-form-control-hint"));
      Assert.assertEquals(hintShown.getText().trim(), hint);
      return true;
    }

    public boolean hintsShown(String ... hints) {
      int i = 0;
      Assert.assertTrue(isHintShown(), "Hint not shown");
      WebElement body = getElement("id=html_body");
      List<WebElement> hintsShown = body.findElements(By.className("oj-form-control-hint"));
      Assert.assertEquals(hintsShown.size(), hints.length, "Mismatch hints expected");
      for(WebElement hintShown : hintsShown) {
        Assert.assertEquals(hintShown.getText().trim(), hints[i]);
        i++;
      }
      return true;
    }


      public String getOjMethod(String ojCommand, String myMethod) {
          return getJq(myComp(),ojCommand,myMethod);
/*          String evalString = "return $('#" + myComp + "')." + ojCommand + "('" + myMethod + "')";
          String retValue = evalJavascript(evalString);
          log(evalString + " = " + retValue);
          return retValue; */
      }

      public String getOjMethod(String ojCommand, String myMethod,String value) {
          return getJq(myComp(),ojCommand,myMethod, value);
/*          String evalString = "return $('#" + myComp + "')." + ojCommand + "('" + myMethod + "," + value + "')";
          String retValue = evalJavascript(evalString);
          log(evalString + " = " + retValue);
          return retValue; */
      }

    public String getJq(String compId, String method, String ... values) {
        String evalString = "return $('#" + compId + "')." + method + "('";
        if(values.length == 1)
          evalString += values[0];
        else {
          for (String s : values)
              evalString += values + "','";
            }
        evalString += "')";
        String retValue = evalJavascript(evalString);
        waitForMilliseconds(500L);
        log(evalString + " = " + retValue);
        return retValue;
    }

    // For some comps, like date, time, datetime, dialogs invoke picker on rendering
    // so you can use this to prep page by overriding
    public void prepPage() {

    }

    // Default implementation,
    public void showComponent() {
      if(usesCollapsible) {
            log("Exposing component using collapsible");
            String locString = "{\"element\":\"#" + exposerId + "\",\"subId\":\"oj-collapsible-disclosure\"}";
            // var node = $( ".selector" ).ojCollapsible( "getNodeBySubId", {'subId': 'oj-collapsible-disclosure'} );
            WebElement header1 = getElement(locString);
            header1.click();
      }
      else if(exposerId.length() > 0) {
            log("Exposing component");
            clickOn(exposerLocator());
        }
      else
        return;

      waitForComp();
      waitForMilliseconds(1000L);
      prepPage();
    }

    public void setExposerId(String id) {
        this.exposerId = id;
    }

    public String exposerLocator() {
        return "id=" + exposerId;
    }

    //          <span id="exposed" data-bind="text: model.exposed"></span>
    public String exposedLocator() {
        return "id=exposed";
    }


    public void clearLog() {
      WebElement textArea = getElement("id=msg-log");
      textArea.clear();
/*        // Clear log
        String evalString = "return $('#msg-log').val('')";
        // String retValue = evalJavascript(evalString);
        String retValue = "";
        ((JavascriptExecutor) getWebDriver()).executeScript(
                    evalString, retValue);
        log(evalString + " = " + retValue); */
        waitForMilliseconds(1000L);
        // clickOn("id=btn_clear");
    }

    public void clickOnClear() {
        // Clear log
        clearLog();
        //         clickOn("id=btn_clear");
    }

    public void clickOnOk() {
        clickOn("id=btn_ok");
    }


    public void clickOnDisable() {
        clickOn("id=btn_dis");
    }

    public void clickOnMenu() {
        clickOn("id=btn_menu");
    }

    public void clickOnRefresh() {
        clickOn("id=btn_refresh");
    }
    public void clickOnLtr() {
        clickOn("id=btn_ltr");
    }
    public void clickOnLang() {
        clickOn("id=btn_lang");
    }
    public void clickOnCss() {
        clickOn("id=btn_css");
    }
    public void clickOnCssNative() {
        clickOn("id=btn_cssnative");
    }
    public void clickOnNotag() {
        clickOn("id=btn_notag");
    }
    public void clickOnMinCss() {
        clickOn("id=btn_min");
    }
    public void clickOnConverter() {
        clickOn("id=btn_conv");
    }
    public void clickOnValidator() {
        clickOn("id=btn_Valid");
    }
    public void clickOnHelp() {
        clickOn("id=btn_help");
    }
    public void clickOnMessages() {
        clickOn("id=btn_mesg");
    }
    public void clickOnPattern() {
        clickOn("id=btn_pattern");
    }
    public void clickOnReadOnly() {
        clickOn("id=btn_ro");
    }
    public void clickOnRequired() {
        clickOn("id=btn_req");
    }
    public void clickOnTitle() {
        clickOn("id=btn_title");
    }
    public void clickOnTranslations() {
        clickOn("id=btn_Trans");
    }
    public void clickOnValid() {
        clickOn("id=btn_Valid");
    }
    public void clickOnValue() {
        clickOn("id=btn_Value");
    }
    public void clickOnFocus() {
        // Can do directly using :
        // getJq(myComp(), "focus");
        // Not testing the focus button on our app pages :)
        clickOn("id=btn_focus");
    }
    public void clickOnGetMessages() {
        clickOn("id=btn_getMesg");
    }
    public void clickOnGetNode() {
        clickOn("id=btn_getNode");
    }
    public void clickOnIsValid() {
        clickOn("id=btn_isValid");
    }
    public void clickOnReset() {
        clickOn("id=btn_reset");
    }
    public void clickOnShowMessages() {
        clickOn("id=btn_showMesg");
    }
    public void clickOnValidate() {
        clickOn("id=btn_val");
    }
    public void clickOnWidget() {
        clickOn("id=btn_widget");
    }
    public void clickOnClosePopup() {
      clickOn("id=btn_closepopup");
    }
    public void clickOnOpenPopup() {
      clickOn("id=btn_openpopup");
    }

    // Tables and paging
    public void clickOnPageAuto() {
        clickOn("id=autoOptId");
    }
    public void clickOnPageInput() {
        clickOn("id=inputOptId");
    }
    public void clickOnPageRange() {
        clickOn("id=rangeTextOptId");
    }
    public void clickOnPagePages() {
        clickOn("id=pagesOptId");
    }
    public void clickOnPageNav() {
        clickOn("id=navOptId");
    }
    public void clickOnPagingType() {
        clickOn("id=btn_pagingType");
    }
    public void clickOnPagingOrientation() {
        clickOn("id=btn_pagingOrientation");
    }
    public void clickOnPagingMaxPageLinks() {
        clickOn("id=btn_pagingMaxPageLinks");
    }
    public void clickOnPagingGetPage() {
        clickOn("id=btn_getPage");
    }
    public void clickOnPagingSetPage() {
        clickOn("id=btn_setPage");
    }
    public void clickOnPagingModel() {
        clickOn("id=btn_getPagingModel");
    }

    public void clickOnSortable() {
        clickOn("id=btn_sortable");
    }
    public void clickOnSortProperty() {
        clickOn("id=btn_sortproperty");
    }
    public void clickOnAccRowHeaderCol() {
        clickOn("id=btn_accRowHeaderCol");
    }
    public void clickOnSelectionModeRow() {
        clickOn("id=btn_selectionModeRow");
    }
    public void clickOnSelectionModeColumn() {
        clickOn("id=btn_selectionModeColumn");
    }
    public void clickOnVerticalGridVisible() {
        clickOn("id=btn_verticalGridVisible");
    }
    public void clickOnHorizontalGridVisible() {
        clickOn("id=btn_horizontalGridVisible");
    }
    public void clickOnHeaderClassName() {
        clickOn("id=btn_headerClassName");
    }
    public void clickOnHeaderStyle() {
        clickOn("id=btn_headerStyle");
    }
    public void clickOnFooterClassName() {
        clickOn("id=btn_footerClassName");
    }
    public void clickOnFooterStyle() {
        clickOn("id=btn_footerStyle");
    }
    public void clickOnClassName() {
        clickOn("id=btn_className");
    }
    public void clickOnStyle() {
        clickOn("id=btn_style");
    }
    public void clickOnCurrentSelection() {
        clickOn("id=btn_currentSelection");
    }


    public void isValid(WebElement myInput) {
          // Verify that parent div no longer has oj-invalid class
          parentHasNoClass(myInput,"oj-invalid-class");
          // Verify that input id has aria-invalid=false
          elementHasAttributeValue(myInput,"aria-invalid","false");
          // Verify div with class oj-message-summary no longer in dom
          Assert.assertFalse(isMessageShown(),"Message not shown");
        }



    protected String exposerId = "";
    protected String msgLog = "msg-log";
    protected String myAppRoot;
    protected String myContextRoot;

    protected String pageTitle;
    protected String myPage;
    protected String myComp;
    protected String widget;


}
