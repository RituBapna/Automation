package oj.tests.responsive;

public final class CookbookResponsiveTestUtils
{

  public static final int WIDTH_XL = 1400;
  public static final int WIDTH_LG = 1200;
  public static final int WIDTH_MD = 900;
  public static final int WIDTH_SM = 300;

  // dimensions takes a height, there's no particular reason this value was chosen
  public static final int TEST_HEIGHT = 900;

  // default number of grid columns
  // this is a double because it's used in division and needs to be able to generate fractions
  public static final double GRID_COLS = 12;


  // PRIVATE //

  /**
   This class is for constants and utilities, cannot invoke
  */
  private CookbookResponsiveTestUtils(){
    //this prevents even the native class from 
    //calling this ctor as well :
    throw new AssertionError();
  }

  
}
