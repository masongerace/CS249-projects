package p3_package;

/**
 * Driver class for testing LogN_SortDriverClass tools
 * 
 * @author MichaelL
 *
 */
public class LogN_StudentMgmtClass_Main
{
   /**
    * constant definition of space
    */
   private static final char SEMI_COLON = ';';

   /**
    * Main method driver for testing sorting operations
    * 
    * @param args String array arguments not used
    */
   public static void main(String[] args)
      {
         int numItems = 50;
         StudentClass[] msStrArray, qsStrArray; 

         // Upload, sort, display using merge sort
         msStrArray = uploadData( "inData.txt", numItems );

         System.out.println( "List before Merge Sort:");
         displayStudents( msStrArray, numItems );

         LogN_StudentMgmtClass.runMergeSort( msStrArray, numItems );

         System.out.println( "\nList after Merge Sort:");
         displayStudents( msStrArray, numItems );

         // Upload, sort, display using merge sort
         qsStrArray = uploadData( "inData.txt", numItems );

         System.out.println( "\nList before Quick Sort:");
         displayStudents( qsStrArray, numItems );

         LogN_StudentMgmtClass.runQuickSort( qsStrArray, numItems );

         System.out.println( "\nList after Quick Sort:");
         displayStudents( qsStrArray, numItems );

      }

   /**
    * Displays strings from array
    * 
    * @param nameList String array to be displayed
    * 
    * @param numNames integer number of names to be displayed
    */
   public static void displayStudents( StudentClass[] nameList, int numNames )
      {
         int index;

         for( index = 0; index < numNames; index++ )
            {
               System.out.format( "%2d: ", index + 1 );
               System.out.println( nameList[ index ].toString() );
            }

         System.out.println();
      }

   /**
    * Uploads String data from a formatted text data file
    * <p>
    * Note: uses compareStrings method from LogN_SortDriverClass
    * 
    * @param fileName String file name of file
    * 
    * @param arrCapacity integer capacity specification 
    * for returned String array; limits number of strings to be uploaded
    * 
    * @return String data array with data loaded
    */
   public static StudentClass[] uploadData( String fileName, int arrCapacity )
      {
         File_Input_Class fileInRef = new File_Input_Class();
         StudentClass[] studentArray = null;
         String nameString;
         int idVal;
         char genderChar;
         // dummyChar data only used to capture semicolon in file stream
         @SuppressWarnings( "unused") char dummyChar;
         double gpaVal;

         int index = 0;


         // Open file
         if( fileInRef.openInputFile( "inData.txt" ) )
            {    
               studentArray = new StudentClass[ arrCapacity ];

               // get name, ignores semicolon
               nameString = fileInRef.getString( SEMI_COLON );

               while( index < arrCapacity && !fileInRef.checkForEndOfFile() )
                  {
                     // gets integer, ignores semicolon
                     idVal = fileInRef.getInt();

                     // gets char, extra call to ignore semicolon
                     genderChar = fileInRef.getChar();
                     dummyChar = fileInRef.getChar();

                     // get GPA, ignores semicolon
                     gpaVal = fileInRef.getDouble();

                     studentArray[ index ] = new StudentClass( nameString, 
                           idVal, genderChar, gpaVal );

                     // get name, ignores semicolon
                     nameString = fileInRef.getString( SEMI_COLON );

                     index++;
                  }

               fileInRef.closeInputFile();
            }

         return studentArray;       
      }

}
