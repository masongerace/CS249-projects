package p11_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProbingStateHashClassMain
{
   /**
    * FileReader reference for use in upload
    */
   private static FileReader fileIn;

   /**
    * constant definition of space
    */
   private static final char SPACE = ' ';

   /**
    * constant definition of space
    */
   private static final char COMMA = ',';

   /**
    * constant definition of minus sign (dash)
    */
   private static final char MINUS_SIGN = '-';

   /**
    * constant definition for maximum input string
    */
   private static final int MAX_INPUT_CHARS = 80;

   /**
    * constant definition of end of file marker
    */
   private static final int EOF_MARKER = -1;

   /**
    * constant definition for show input control code
    */
   private static final boolean SHOW_INPUT = false;

   /**
    * Main method driver for testing LinkedHashClass data structure
    * 
    * @param args String argument not used
    */
   public static void main(String[] args)
   {
      ProbingStateHashClass hashTest;
      StateDataClass testVal;
      int primeTableSize = 67;  
      // recommended to be prime number (or at least odd)

      hashTest = uploadData( "stateData.txt", 
            primeTableSize, ProbingStateHashClass.QUADRATIC_PROBING );

      hashTest.showHashTableStatus();

      System.out.println( "\nRemoving Selected States\n" );
      testVal = hashTest.removeItem( getQueryObject( "Tennessee" ) );
      System.out.println( "Removed: " + testVal.toString() + "\n" );

      testVal = hashTest.removeItem( getQueryObject( "Arizona" ) );        
      System.out.println( "Removed: " + testVal.toString() + "\n" );

      testVal = hashTest.removeItem( getQueryObject( "Maryland" ) );
      System.out.println( "Removed: " + testVal.toString() + "\n" );

      testVal = hashTest.removeItem( getQueryObject( "Oregon" ) );
      System.out.println( "Removed: " + testVal.toString() + "\n" );

      testVal = hashTest.removeItem( getQueryObject( "Texas" ) );
      System.out.println( "Removed: " + testVal.toString() + "\n" );

      testVal = hashTest.removeItem( getQueryObject( "Minnesota" ) );
      System.out.println( "Removed: " + testVal.toString() + "\n" );

      hashTest.showHashTableStatus();

   }

   public static StateDataClass getQueryObject( String stateName )
   {
      return new StateDataClass( stateName, "", 0, 0, 0, 0 );      
   }

   /**
    * uploads data from requested file
    * 
    * @param fileName name of file to access
    * 
    * @return Boolean result of data upload
    */
   private static ProbingStateHashClass uploadData( String fileName, 
         int tableSize, int probeType )
   {
      String stateName, stateInit;
      int numInst, lowTemp, highTemp;
      double avgTemp;
      StateDataClass dataItem;
      ProbingStateHashClass newData = new ProbingStateHashClass( tableSize, 
                                                                  probeType );

      try
      {
         // Open FileReader 
         fileIn = new FileReader( fileName );

         // get first line - state name
         stateName = getALine( MAX_INPUT_CHARS, COMMA );

         while( compareStrings( stateName, "EndOfFile" ) != 0 )
         {
            // get state initials
            stateInit = getALine( MAX_INPUT_CHARS, COMMA );

            // get number of institutions
            numInst = getAnInt( MAX_INPUT_CHARS );

            // get average temperature
            avgTemp = getADouble( MAX_INPUT_CHARS );

            // get lowest temperature
            lowTemp = getAnInt( MAX_INPUT_CHARS );

            // get highest temperature
            highTemp = getAnInt( MAX_INPUT_CHARS );

            // add data to StateDataClass, and then to tree
            dataItem = new StateDataClass( stateName, stateInit, 
                  numInst, avgTemp, lowTemp, highTemp );

            newData.addItem( dataItem );

            if( SHOW_INPUT )
            {
               System.out.println( dataItem.toString() );
            }

            stateName = getALine( MAX_INPUT_CHARS, COMMA );                  
         }              
      }

      catch( FileNotFoundException fnfe )
      {
         System.out.println( "DATA ACCESS ERROR: Failure to open input file" );

         return null;
      }

      return newData;
   }

   /**
    * gets a string up to a maximum length or to specified delimiter
    * 
    * @param maxLength maximum length of input line
    * 
    * @param delimiterChar delimiter character to stop input
    * 
    * @return String captured from file
    */
   private static String getALine( int maxLength, char delimiterChar )
   {
      int inCharInt;
      int index = 0;
      String strBuffer = "";

      try
      {
         inCharInt = fileIn.read();

         // consume leading spaces
         while( index < maxLength && inCharInt <= (int)( SPACE )  )
         {
            if( inCharInt == EOF_MARKER )
            {
               return "EndOfFile";
            }

            index++; 

            inCharInt = fileIn.read();
         }

         while( index < maxLength && inCharInt != (int)( delimiterChar ) )
         {   
            if( inCharInt >= (int)( SPACE ) )
            {
               strBuffer += (char)( inCharInt );

               index++;
            }

            inCharInt = fileIn.read();             
         }

         //inCharInt = fileIn.read();
      }

      catch( IOException ioe )
      {
         System.out.println( "INPUT ERROR: Failure to capture character" );

         strBuffer = "";
      }

      return strBuffer;
   }

   /**
    * gets an integer from the input string
    * 
    * @param maxLength maximum length of characters
    * input to capture the integer
    * 
    * @return integer captured from file
    */
   private static int getAnInt( int maxLength )
   {
      int inCharInt;
      int index = 0;
      String strBuffer = "";
      int intValue;
      boolean negativeFlag = false;

      try
      {
         inCharInt = fileIn.read();

         // clear space up to number
         while( index < maxLength && !charInString( (char)inCharInt, 
                  "0123456789+-" ) )
         {
            inCharInt = fileIn.read();

            index++;
         }

         if( inCharInt == MINUS_SIGN )
         {
            negativeFlag = true;

            inCharInt = fileIn.read();
         }

         while( charInString( (char)inCharInt, "0123456789" ) )
         {   
            strBuffer += (char)( inCharInt );

            index++;

            inCharInt = fileIn.read();
         }            
      }

      catch( IOException ioe )
      {
         System.out.println( "INPUT ERROR: Failure to capture character" );

         strBuffer = "";
      }

      intValue = Integer.parseInt( strBuffer );

      if( negativeFlag )
      {
         intValue *= -1;
      }

      return intValue;
   }

   /**
    * gets an integer from the input string
    * 
    * @param maxLength maximum length of characters
    * input to capture the integer
    * 
    * @return integer captured from file
    */
   private static double getADouble( int maxLength )
   {
      int inCharInt;
      int index = 0;
      String strBuffer = "";
      boolean negativeFlag = false;
      double doubleValue;

      try
      {
         inCharInt = fileIn.read();

         // clear space up to number
         while( index < maxLength && !charInString( (char)inCharInt, 
                          ".0123456789+-" ) )
         {
            inCharInt = fileIn.read();

            index++;
         }

         if( inCharInt == MINUS_SIGN )
         {
            negativeFlag = true;

            inCharInt = fileIn.read();
         }

         while( charInString( (char)inCharInt, ".0123456789" ) )
         {   
            strBuffer  += (char)( inCharInt );

            index++;

            inCharInt = fileIn.read();
         }            
      }

      catch( IOException ioe )
      {
         System.out.println( "INPUT ERROR: Failure to capture character" );

         strBuffer = "";
      }

      doubleValue = Double.parseDouble( strBuffer );

      if( negativeFlag )
      {
         doubleValue *= -1;
      }

      return doubleValue;
   }

   /**
    * compares two strings without consideration for case
    * 
    * @param oneString one of the strings to be tested
    * 
    * @param otherString other string to be tested
    * 
    * @return first greater than second: greater than zero;
    * first equal to second: equals zero
    * first less than second: less than zero
    */
   public static int compareStrings( String oneString, String otherString )
   {
      int difference, index = 0;

      while( index < oneString.length() && index < otherString.length() )
      {
         difference = toLower( oneString.charAt( index ) ) 
               - toLower( otherString.charAt( index ) );

         if( difference != 0 )
         {
            return difference;
         }

         index++;
      }

      return oneString.length() - otherString.length();
   }        

   /**
    * tests and reports if a character is found in a given string
    * 
    * @param testChar character to be tested against the string
    * 
    * @param testString string within which the character is tested
    * 
    * @return Boolean result of test
    */
   private static boolean charInString( char testChar, String testString )
   {
      int index;

      for( index = 0; index < testString.length(); index++ )
      {
         if( testChar == testString.charAt( index ) )
         {
            return true;
         }
      }

      return false;
   }

   /**
    * changes upper case letter to a lower case letter
    * 
    * @param inChar letter to be tested and potentially modified
    * 
    * @return lower case letter if upper case letter is input;
    * otherwise character is returned unchanged
    */
   private static char toLower( char inChar )
   {
      if( inChar >= 'A' && inChar <= 'Z' )
      {
         return (char)( inChar - 'A' + 'a' );
      }

      return inChar;
   }

}
