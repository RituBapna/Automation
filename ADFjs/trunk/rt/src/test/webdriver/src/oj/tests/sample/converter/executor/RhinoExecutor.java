package oj.tests.sample.converter.executor;

import java.io.Reader;

import javax.script.ScriptEngine;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.tools.shell.Global;

/**
 *  Implementation of the Executor insteface used with the Rhino ScriptEngine
 */
public class RhinoExecutor implements Executor{
    private Context cx;
    private Scriptable scope;
    
    public RhinoExecutor() {
        super();
        initEnv();
    }
    /**
     *  Need to setup properly the environment
     */
    private void initEnv()
    {      
        cx = Context.enter();
        cx.setLanguageVersion(Context.VERSION_1_8);
        cx.setOptimizationLevel(-1);
        Global global = new Global(cx);        
        Scriptable argsObj = cx.newArray(global, new Object[] {});
        // needed for the proper evaluation of r.js
        global.defineProperty("arguments", argsObj, ScriptableObject.DONTENUM);        
        scope = cx.initStandardObjects(global);         
    }
    @Override
    public void loadJS(ScriptEngine engine, Reader jsReader, String jsFile) throws Exception 
    {        
        cx.evaluateReader(scope, jsReader, jsFile, 0, null);                
    }

    @Override
    public Object execute(ScriptEngine engine, String options) throws Exception {
        Object _o =  cx.evaluateString(scope, options, "executing", 1, null);
        return _o;        
    }
}
