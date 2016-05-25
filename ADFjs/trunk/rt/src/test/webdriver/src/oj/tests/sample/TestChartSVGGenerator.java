package oj.tests.sample;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import oj.tests.sample.converter.chart.ChartConfiguration;
import oj.tests.sample.converter.chart.JetChartImageConverter;
import oj.tests.sample.converter.util.Svg2PngConverterUtil;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.json.JSONObject;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;


public class TestChartSVGGenerator extends OJetBase {
    private final static String WIDTH = "width";
    private final static String HEIGHT = "height";
    private final static String OUTPUT_FILE = "outputFile";
    private final static String CHART_OPTIONS = "options";
    private static final long THRESHOLD_SIZE = 6000;
    private ChartConfiguration m_config;

    public TestChartSVGGenerator() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }
    /*
     *  Overriding to prevent the browser popup (this test does not need the browser anyway)
     */
    @Override
    protected void onSetUp() {
    }

    /** 
     * Test server side svg generation with JET api.
     * @throws Exception
     */
    @Test
    public void testServerSideChartGenerator() throws Exception {        
        String cd = new File("").getAbsolutePath();
        String path = TestChartSVGGenerator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (cd.endsWith(File.separator + "ant") || cd.endsWith(File.separator + "grunt")) {
            // In grunt or ant, go up three levels--a bit ugly
            path += ".." + File.separator;
        }
        // the location of the json config files, one for each chart type
        File configFilesDir = new File(path + "/oj/tests/sample/jet/sample/converter/chart/config");
        if (configFilesDir.isDirectory()) {

            File[] configFiles = configFilesDir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    if (name != null && name.endsWith(".json")) {
                        return true;
                    }
                    return false;
                }
            });

            if (configFiles != null && configFiles.length > 0) {
                for (File _file : configFiles) {
                    processConfigFile(_file.getPath());
                }
            }
        }
        //MainConverterRunner.main(new String[]{"-infile", "oj/tests/sample/jet/sample/converter/chart/config/areaChartConfig.json"} );        
    }

    private void processConfigFile(String configFile) throws Exception {        
        String json = readFile(configFile);
        JSONObject obj = new JSONObject(json);
        // width the of the SVG
        String width = obj.getString(WIDTH);
        // height the of the SVG
        String height = obj.getString(HEIGHT);
        // path of the final SVG file
        String outFile = obj.getString(OUTPUT_FILE);
        // the chart configuration and data
        String options = obj.getJSONObject(CHART_OPTIONS).toString();

        m_config = new ChartConfiguration();
        if (width != null) {
            m_config.setWidth(Integer.parseInt(width));
        }
        if (height != null) {
            m_config.setHeight(Integer.parseInt(height));
        }
        if (options != null) {
            m_config.setOptions(options);
        }
        if (outFile != null) {
            m_config.setOutputFile(outFile);
        }

        // get the SVG
        String svg = JetChartImageConverter.getSVG(m_config.getWidth(), m_config.getHeight(), m_config.getOptions());        
        // write the result to the file system, second argument is false so that we won't generate PNG
        Assert.assertNotNull(svg, "Expecting a non null SVG!");
        Assert.assertTrue(writeToFile(svg, false), "Expecting an SVG file to be written out!");

    }
    /**
     * reads the content of the configuration file.
     * @param fileName
     * @return
     * @throws IOException
     */
    protected String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    /**
     * Writes out a properly formatted SVG and the generated PNG files.
     * @param strSVG the SVG in a String format
     * @param pngConversion true if PNG generation is desired, false otherwise. 
     */
    protected boolean writeToFile(String strSVG, boolean pngConversion) {
        boolean successful = false;
        try {
            // in the following block we try to format the SVG into a readable layout (indentation)
            Document document =
                DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(strSVG.getBytes("utf-8"))));

            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList =
                (NodeList) xPath.evaluate("//text()[normalize-space()='']", document, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                node.getParentNode().removeChild(node);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);

            transformer.transform(new DOMSource(document), streamResult);

            // get the output file name
            String file = m_config.getOutputFile();
            File rawSVGFile = new File(file);
            // write the nicely formatted SVG to a file
            BufferedWriter writer = new BufferedWriter(new FileWriter(rawSVGFile));
            try {
                writer.write(stringWriter.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Creating SVG file " + rawSVGFile.getAbsolutePath() + " was successful.");
                writer.close();
            }
            // assert that the svg has a certain size (low threshold value of 6 kbyte)
            long size = rawSVGFile.length();
            Assert.assertTrue(size > THRESHOLD_SIZE, "Expecting an SVG file with a size greater than 6 kbytes!");
            successful = true;

            // courtesy conversion call to transform the SVG to PNG format
            // use the same name for the PNG, just change the extension
            if (pngConversion) {
                if (file.endsWith("svg")) {
                    file = file.substring(0, file.indexOf(".svg"));
                }
                file = file + ".png";
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // make the conversion
                Svg2PngConverterUtil.transformSvg2Png(m_config.getWidth(), m_config.getHeight(), strSVG, baos);
                byte[] img = baos.toByteArray();
                // write the PNG file out to the same directory as the SVG
                if (img != null) {
                    File imgFile = new File(file);
                    FileOutputStream stream = new FileOutputStream(imgFile);
                    stream.write(img);
                    System.out.println("Creating PNG file " + imgFile.getAbsolutePath() + " was successful.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return successful;
    }
}

