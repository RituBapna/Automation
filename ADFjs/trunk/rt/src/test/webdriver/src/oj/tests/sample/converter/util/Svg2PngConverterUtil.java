package oj.tests.sample.converter.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * SVG to PNG converter utility class. It uses Batik for this process
 */
public class Svg2PngConverterUtil
{
  static boolean DEBUG = false;
  static final Logger LOGGER = Logger.getLogger("Svg2PngConverterUtil");
  static HashMap<String, Integer> NAMED_FONT_SIZES;
  static
  {    
    NAMED_FONT_SIZES = new HashMap<String, Integer>();
    NAMED_FONT_SIZES.put("xx_small", 8);
    NAMED_FONT_SIZES.put("x_small", 9);
    NAMED_FONT_SIZES.put("small", 10);
    NAMED_FONT_SIZES.put("medium", 12);
    NAMED_FONT_SIZES.put("large", 14);
    NAMED_FONT_SIZES.put("x-large", 16);
    NAMED_FONT_SIZES.put("xx-large", 18);
    NAMED_FONT_SIZES.put("smaller", 10);
    NAMED_FONT_SIZES.put("larger", 14);
  }


  public static void transformSvg2Png(int width, int height, String strSVG, OutputStream out)
    throws TranscoderException, ParserConfigurationException, SAXException, IOException
  {
    if (strSVG != null && !strSVG.isEmpty() && out != null)
    {
      // batik does not support CSS3 type properties like fill=rgba(249,249,249,85) (opacity is the fourth parameter
      // so this needs to be replaced with fill=rgb(249,249,249) fill-opacity=85
      strSVG =
        strSVG.replaceAll("(stroke|fill)=\"rgba\\(([ 0-9]+,[ 0-9]+,[ 0-9]+),([ 0-9\\.]+)\\)\"",
                          "$1=\"rgb($2)\" $1-opacity=\"$3\"");
      // adjustments are needed for text element's dominant-baseline
      // get the Document from the strSVG
      Document document =
        DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(strSVG.getBytes("utf-8"))));

      Element root = document.getDocumentElement();
      // do some adjustments needed for Batik conversion
      processElement(root);
      // now convert the Document back to String
      DOMImplementationLS domImpl = (DOMImplementationLS)document.getImplementation();
      LSSerializer lsSerializer = domImpl.createLSSerializer();
      String updatedSVG = lsSerializer.writeToString(document);
         
      Reader reader = null;
      try
      {
        PNGTranscoder transcoder = new PNGTranscoder();
        transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));
        transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, new Float(width));
        transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, new Float(height));
        reader = new StringReader(updatedSVG);        
        transcoder.transcode(new TranscoderInput(reader), new TranscoderOutput(out));        
      }

      finally
      {
        try
        {
          out.flush();
          out.close();
          reader.close();          
        }
        catch (IOException ioe)
        {
          ioe.printStackTrace();
        }
      }
    }
  }

  /**
   * Walks the document and looks for Elements that do have the dominant-baseline attribute set.
   * If found the attribute will be removed and the 'baseline-shift' attribute calculated and set.
   * 'aria' and 'vector-effect' attributes will also be removed from the element being processed. 
   * @param element The current element being processed.
   */
  private static void processElement(Element element)
  {
    if (DEBUG)
    {
      LOGGER.info("Processing element:" + element.getNodeName());
    }
    processAttributes(element);
    NodeList nodes = element.getChildNodes();
    if (nodes != null && nodes.getLength() > 0)
    {
      for (int i = 0; i < nodes.getLength(); i++)
      {
        Node node = nodes.item(i);
        if (node != null && node.getNodeType() == Node.ELEMENT_NODE)
        {
          processElement((Element) node);
        }
      }
    }
  }
  
  /**
   * Makes some heuristic shifts of the text elements dominant baseline. Without this adjustment
   * the rendered text in the PNG image looks slightly off.
   * @param element the text Element being processed. 
   */
  private static void processAttributes(Element element)
  {
    if (element != null)
    {
      if (element.hasAttributeNS(null, "dominant-baseline"))
      {
        String dbl = element.getAttributeNS(null, "dominant-baseline");
        if (dbl != null && !dbl.isEmpty())
        {
          if (DEBUG)
          {
            LOGGER.info("   dominant-baseline attribute is: " + dbl);
          }
          // look for fontSize up in the chain
          String fontSize = null;
          Element currentElement = element;
          while ((fontSize == null || fontSize.isEmpty()) && !"html".equals(currentElement.getNodeName()))
          {
            fontSize = currentElement.getAttributeNS(null, "font-size");
            currentElement = (Element) currentElement.getParentNode();
          }

          if (fontSize != null && !fontSize.isEmpty())
          {
            if (DEBUG)
            {
              LOGGER.info("   found font-size: " + fontSize);
            }
            float relativeShift;
            if (dbl.equals("text-before-edge"))
            {
              relativeShift = -1.0f;
            }
            else if (dbl.equals("middle"))
            {
              relativeShift = -0.25f;
            }
            else if (dbl.equals("text-after-edge"))
            {
              relativeShift = -0.15f;
            }
            else
            {
              relativeShift = 1.0f;
            }

            float shiftedValue = 1.0f;
            try
            {
              float flfs = Float.parseFloat(fontSize);
              shiftedValue = flfs * relativeShift;
            }
            catch (NumberFormatException ex)
            {
              try
              {
                Integer parsedFontSize = parseFontSize(fontSize);
                shiftedValue = parsedFontSize.floatValue() * relativeShift;
              }
              catch (IllegalArgumentException iae)
              {
                iae.printStackTrace();
              }
            }

            String newValue = String.valueOf(shiftedValue);
            if (DEBUG)
            {
              LOGGER.info("   setting baseline-shift to: " + newValue);
            }
            element.setAttributeNS(null, "baseline-shift", newValue);
          }
          element.removeAttributeNS(null, "dominant-baseline");;
        }
      }
      if (element.hasAttributeNS(null, "aria"))      
      {
        element.removeAttributeNS(null, "aria");
      }
      if (element.hasAttributeNS(null, "vector-effect"))      
      {
        element.removeAttributeNS(null, "vector-effect");
      }
    }
    
  }

  private static Integer parseFontSize(String fontVal)
    throws IllegalArgumentException
  {
    if ((fontVal == null) || (fontVal.length() == 0))
      return null;

    fontVal = fontVal.trim().toLowerCase();

    if (NAMED_FONT_SIZES.containsKey(fontVal))
    {
      return NAMED_FONT_SIZES.get(fontVal);
    }

    if (fontVal.charAt(fontVal.length() - 1) == '%')
    {
      double percent = 0;

      try
      {
        percent = Double.parseDouble(fontVal.substring(0, fontVal.length() - 1));
      }
      catch (NumberFormatException e)
      {
        throw new IllegalArgumentException("Invalid percentage: " + fontVal);
      }

      // We just assume the percentage is relative to our base font size - 12pt.
      return (int) ((percent / 100.0) * 12);
    }

    if (fontVal.endsWith("in") || fontVal.endsWith("cm") || fontVal.endsWith("mm") || fontVal.endsWith("pt") ||
        fontVal.endsWith("pc") || fontVal.endsWith("em") || fontVal.endsWith("ex") || fontVal.endsWith("px"))
    {
      // Parse out the size
      double size = 0;

      try
      {
        size = Double.parseDouble(fontVal.substring(0, fontVal.length() - 2));
      }
      catch (NumberFormatException e)
      {
        throw new IllegalArgumentException("Invalid length: " + fontVal);
      }

      // Convert the size to "points".  It is a rough approximation since we don't
      // know the screen resolution. The assumption is that 1pt = 1px
      int points = 0;

      if (fontVal.endsWith("in"))
      {
        points = (int) (72 * size);
      }
      else if (fontVal.endsWith("cm"))
      {
        points = (int) ((72 * size) / 2.54);
      }
      else if (fontVal.endsWith("mm"))
      {
        points = (int) ((72 * size) / 25.4);
      }
      else if (fontVal.endsWith("pt"))
      {
        points = (int) size;
      }
      else if (fontVal.endsWith("pc"))
      {
        points = (int) (12 * size);
      }
      else if (fontVal.endsWith("em"))
      {
        // We don't try to figure out the right em size here. Use dynamicResize="DYNAMIC_SIZE"
        // to let the client determine the size and re-fetch.
        points = (int) (12 * size);
      }
      else if (fontVal.endsWith("ex"))
      {
        // We don't try to figure out the right ex size here. Use dynamicResize="DYNAMIC_SIZE"
        // to let the client determine the size and re-fetch.
        points = (int) (6 * size);

      }
      else if (fontVal.endsWith("px"))
      {
        points = (int) size;
      }
      else
      {
        throw new IllegalArgumentException("Invalid length: " + fontVal);
      }

      return points;
    }

    throw new IllegalArgumentException("Invalid font size: " + fontVal);
  }
}
