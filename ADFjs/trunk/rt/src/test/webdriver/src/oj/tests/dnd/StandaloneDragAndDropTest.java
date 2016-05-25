package oj.tests.dnd;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfig;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;

public class StandaloneDragAndDropTest extends OJetBase
{
  public StandaloneDragAndDropTest()
  {
    super(_createTestConfig());
  }

  private static TestConfig _createTestConfig()
  {
    
    return (new TestConfigBuilder()).setContextRoot("dnd").setAppRoot("standalone").
          setHomePage("standalone.html").setHomePageTitle("Standalone Drag and Drop Test").build();
  }
  
  @Test(groups={"dnd"})
  public void testDragAndDrop() throws Exception {
      startupTest();
      waitForElementVisible(_DRAG_SOURCE);
  }
  
  private static final String _DRAG_SOURCE = "dragSource";
  private static final String _DROP_TARGET = "dropTarget";
}
