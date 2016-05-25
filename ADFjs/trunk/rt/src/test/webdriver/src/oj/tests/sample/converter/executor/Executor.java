package oj.tests.sample.converter.executor;


import java.io.Reader;

import javax.script.ScriptEngine;

public interface Executor {
  /**
   * Executes javascript using the given ScriptEngine.
   * @param engine an instance of ScriptEngine (Nashorn or Rhino)
   * @param options javascript to be executed
   * @return
   * @throws Exception
   */
  public  Object execute(ScriptEngine engine, String options) throws Exception;
  /**
   * Loads external javascript files
   * @param engine an instance of the ScriptEngine (Nashorn or Rhino)
   * @param reader a Reader instance
   * @param fileName the name of the external javascript file
   * @throws Exception
   */
  public  void loadJS(ScriptEngine engine, Reader reader, String fileName) throws Exception;
    
}
