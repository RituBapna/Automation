package oj.tests.sample.converter.chart;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;

import java.net.URL;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import javax.xml.parsers.ParserConfigurationException;

import oj.tests.sample.converter.executor.Executor;
import oj.tests.sample.converter.executor.NashornExecutor;
import oj.tests.sample.converter.executor.RhinoExecutor;
import oj.tests.sample.converter.util.Svg2PngConverterUtil;

import org.apache.batik.transcoder.TranscoderException;

import org.xml.sax.SAXException;

/**
 * Main utility class exposing the getSVG and exportSVGToPNG static methods.
 * 12.10.2015 - ccsenter: changed the static references to the ScriptEngine and will
 *                        re-create the ScriptEngine with each chart execution to avoid the caching
 *                        issue with Nashorn. The way the code was executing before the change always
 *                        created the same chart (whatever was the first option to execute)
 */
public class JetChartImageConverter
{


  private static final List<String> packageImports = new ArrayList<String>();
  
  
  static
  {
    packageImports.add("java.awt");
    packageImports.add("java.awt.font");
    packageImports.add("java.awt.geom");
    packageImports.add("java.awt.image");
    packageImports.add("java.io");
    packageImports.add("java.lang");
    packageImports.add("java.lang.math");
    packageImports.add("java.lang.number");
    packageImports.add("java.util");    
    packageImports.add("Packages.javax.faces.context");
    packageImports.add("Packages.javax.imageio");
    packageImports.add("Packages.javax.xml.parsers");
    packageImports.add("Packages.org.w3c.dom");
  }

  /**
   * Returns the SVG for a rendered chart component.
   * @param width the desired width of the SVG
   * @param height the desired height of the SVG   
   * @param options The json string that specifies the chart configuration and data.
   * @return the rendered chart in SVG format.
   */
  public static String getSVG(int width, int height, String options)
  {
    return (new ConverterHelper()).getSVG(width, height, options);
  }

  /**
   * Given an SVG, converts this into a rasterized PNG format and writes the image to an output stream.
   * @param width The width of the image.
   * @param height The height of the image.
   * @param svg The SVG to convert to PNG.
   * @param out The output stream to write to.
   * @throws IOException
   * @throws TranscoderException
   * @throws ParserConfigurationException
   * @throws SAXException
   */
  public static void exportSVGToPNG(int width, int height, String svg, OutputStream out)
    throws IOException, TranscoderException, ParserConfigurationException, SAXException
  {
    Svg2PngConverterUtil.transformSvg2Png(width, height, svg, out);
  }

  /**
   * Protected helper class that does the work:
   *   1. sets up and initializes the ScriptEngine and an Executor for the given jdk
   *   2. loads and evaluates the requireJs adapter r.js
   *   3. loads and evaluates the sample bootstrapping script jet-dom-sample.js
   *   4. retrieves the SVG
   */
  protected static class ConverterHelper
  {
    protected ScriptEngine scriptEngine;
    protected Executor executor;
    private boolean isNashorn = true;
    private static boolean isInitialized = false;
    
    /**
     * Returns the rendered chart in SVG format
     * @param width the desired with of the SVG
     * @param height the desired height of the SVG
     * @param options the chart's configuration and data in JSON format
     * @return the SVG
     */
    public String getSVG(int width, int height, String options)
    {
      // a Set of javascript files required by the converter
      LinkedHashSet<String> files = new LinkedHashSet<String>();
      String svg = null;
      
      try
      {
        //if (!isInitialized)
        {
          // get the ScriptEngine and instantiate an Executor for the given runtime
          setUp();

          // Import java packages, which are used in services like text measurement
          importJavaPackages();
          evaluate("print(System.getProperty(\"user.dir\"))");

          // create the top level window object
          // this is similar as in the browser. It provides the global namespace
          evaluate("var window = this;");
          isInitialized = true;
        }
        // since JET is using requireJs, we will add r.js (an adapter that makes requireJs 
        // to work with Nashorn, Rhino, Node) to the list of files to be loaded below        
        String jsFile = "oj/tests/sample/scripts/jet-dom/r.js";
        files.add(jsFile);

        // create the object that will reference the chart's configuration and data
        // also create the width and height variables that will size the rendered chart
        evaluate("var chartOptions = " + options);
        evaluate("var chartWidth = " + width);
        evaluate("var chartHeight = " + height);
        
        // add the sample script that bootstraps requireJs, loads the simple JET-DOM implementation
        // and calls the JET chart API
        jsFile = "oj/tests/sample/scripts/jet-dom/main.js";
        files.add(jsFile);
        
        // now read all the files and load using the proper Executor        
        readAllJS(files);
        
        // now get the SVG
        svg = getSVG();
        
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

      return svg;
    }
    
    /**
     * Private call to evaluate the result of the SVG conversion to a String.
     * @return the String representation of an SVG element.
     * @throws Exception
     */
    private String getSVG()
      throws Exception
    {
      Object result = evaluate("svg.toString();");
      if (result != null)
      {
        return result.toString();
      }

      return null;
    }
    /**
     * Method that reads and loads the content of a javascript file using the current ScriptEngine.
     * @param files a Set of file paths
     * @throws Exception
     */
    protected void readAllJS(LinkedHashSet<String> files)
      throws Exception
    {

      for (String jsFile: files)
      {
        Reader jsReader = getReader(jsFile);
        try
        {
          executor.loadJS(scriptEngine, jsReader, jsFile);
        }
        finally
        {
          jsReader.close();
        }
      }
    }

    @SuppressWarnings("oracle.jdeveloper.java.nested-assignment")
    private Reader getReader(String jsfile)
      throws Exception
    {
      Reader reader = null;
      BufferedReader rd = null;
      URL url = JetChartImageConverter.class.getClassLoader().getResource(jsfile);
      try
      {
        rd = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine = null;
        StringBuilder builder = new StringBuilder();
        while ((inputLine = rd.readLine()) != null)
        {
          builder.append(inputLine);
          builder.append("\n");
        }

        String content = builder.toString();      
        reader = new StringReader(content);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      finally
      {
        try
        {
          if (rd != null)
          {
            rd.close();
          }
        }
        catch (IOException ex)
        {          
          ex.printStackTrace();
        }
      }

      return reader;
    }
    
    /**
     *  Retrieves an instance of the ScriptEngine then instantiates an Executor responsible for
     *  evaluating/executing javascript code.
     */
    protected void setUp()
    {
      if (scriptEngine == null)
      {
        ScriptEngineManager manager = new ScriptEngineManager();
        scriptEngine = manager.getEngineByName("nashorn");
        if (scriptEngine == null)
        {
          isNashorn = false;
          scriptEngine = manager.getEngineByName("rhino");
        }
      }
      if (!isNashorn)
      {
        executor = new RhinoExecutor();
      }
      else
      {
        executor = new NashornExecutor();
      }
    }
    
    /**
     * Imports some java packages into the javascript environment. This is used in calculating text measurements.
     * @throws Exception
     */
    protected void importJavaPackages()
      throws Exception
    {
      //Nashorn no longer supports importPackage statements unless the following line is evaluated.
      if (isNashorn)
      {
        evaluate("load(\"nashorn:mozilla_compat.js\");");
      }
      for (String packageImport: packageImports)
      {
        evaluate("importPackage(" + packageImport + ")");
      }
    }
    
    /**
     * Abstraction of javascript code evaluation. The instantiated Executor handles the specifics of the execution.
     * @param javascript String representation of the javascript code to be evaluated/executted.
     * @return an Object representing the result of the evaluation.
     * @throws Exception
     */
    protected Object evaluate(String javascript)
      throws Exception
    {
      return executor.execute(scriptEngine, javascript);
    }
  }
}
