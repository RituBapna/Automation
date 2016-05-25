package oj.tests.sample.converter.chart;


/**
 * Property bag for the main parameters of the converter program their with accessor methods.
 */
public class ChartConfiguration
{  
  // a default options and data
  private String m_options = "{type: \"bar\"," +
                                  "series: [{name : \"Series 1\", items : [74, 42, 70, 46]},{name : \"Series 2\", items : [50, 58, 46, 54]},{name : \"Series 3\", items : [34, 22, 30, 32]},{name : \"Series 4\", items : [18, 6, 14, 22]}],"+
                                  "groups: [\"Group A\", \"Group B\", \"Group C\", \"Group D\"]};";
  private String m_outputFile = null;
  // default SVG width and height
  private int m_width = 800;
  private int m_height = 600;  
    
  /**
   * Sets the width of the SVG.
   * @param m_width
   */
  public void setWidth(int m_width)
  {
    this.m_width = m_width;
  }
  /**
   * Get the width of the SVG
   * @return
   */
  public int getWidth()
  {
    return m_width;
  }
  /**
   * Set the height of the SVG
   * @param m_height
   */
  public void setHeight(int m_height)
  {
    this.m_height = m_height;
  }
  /**
   * Get the height of the SVG
   * @return
   */
  public int getHeight()
  {
    return m_height;
  }

  /**
   * Set the options (JSON format) that contains the chart configuration and data.
   * @param m_options
   */
  public void setOptions(String m_options)
  {
    this.m_options = m_options;
  }
  /**
   * Return the chart options.
   * @return
   */
  public String getOptions()
  {
    return m_options;
  }
  /**
   * Sets the path of the file that will contain the generated SVG.
   * @param m_outputFile
   */
  public void setOutputFile(String m_outputFile)
  {
    this.m_outputFile = m_outputFile;
  }
  /**
   * Returns the SVG file path.
   * @return
   */
  public String getOutputFile()
  {
    return m_outputFile;
  }
    
}
