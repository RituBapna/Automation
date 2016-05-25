package oj.tests.sample.converter.executor;

import java.io.Reader;

import javax.script.ScriptEngine;

/**
 *  Implementation of the Executor insteface used with the Nashorn ScriptEngine.
 */
public class NashornExecutor implements Executor {
   

    @Override
    public Object execute(ScriptEngine engine, String options) throws Exception {                
       return engine.eval(options);
    }
    @Override
    public void loadJS(ScriptEngine engine, Reader jsReader, String jsFile) throws Exception {           
        engine.put(ScriptEngine.FILENAME, jsFile);
        engine.eval(jsReader);
    }
}
