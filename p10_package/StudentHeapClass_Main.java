package p10_package;

public class StudentHeapClass_Main
{
   public static char SEMI_COLON = ';';

   public static void main(String[] args)
   {
      StudentHeapClass studentHeapData;
      StudentClass removedItem;
      boolean showAddItem = true;
      //boolean showAddItem = false;

      System.out.println( "Adding Items to heap");

      studentHeapData = uploadData( "inData1.txt", showAddItem );

      System.out.println( "\nRemoving Items from heap");

      while( !studentHeapData.isEmpty() )
      {
         removedItem = studentHeapData.removeItem();

         System.out.println( "Removed: " + removedItem.toString() );
      }
   }

   /**
    * Uploads StudentClass data from a formatted text data file
    * <p>
    * Note: uses compareStrings method from StudentClass
    * 
    * @param fileName String file name of file
    * 
    * @param arrCapacity integer capacity specification 
    * for returned String array; limits number of strings to be uploaded
    * 
    * @return String data array with data loaded
    */
   public static StudentHeapClass uploadData( String fileName, boolean showAdd )
   {
      File_Input_Class fileInRef = new File_Input_Class();
      StudentHeapClass inputHeap = null;
      StudentClass studentData = null;
      String nameString;
      int idVal;
      char genderChar;
      // dummyChar data only used to capture semicolon in file stream
      @SuppressWarnings( "unused") char dummyChar;
      double gpaVal;

      // Open file
      if( fileInRef.openInputFile( fileName ) )
      {    
         inputHeap = new StudentHeapClass();

         inputHeap.setDisplayFlag( showAdd );

         // get name, ignores semicolon
         nameString = fileInRef.getString( SEMI_COLON );

         while( !fileInRef.checkForEndOfFile() )
         {
            // gets integer, ignores semicolon
            idVal = fileInRef.getInt();

            // gets char, extra call to ignore semicolon
            genderChar = fileInRef.getChar();
            dummyChar = fileInRef.getChar();

            // get GPA, ignores semicolon
            gpaVal = fileInRef.getDouble();

            studentData = new StudentClass( nameString, 
                  idVal, genderChar, gpaVal );

            // add to heap
            inputHeap.addItem( studentData );

            // get name, ignores semicolon
            nameString = fileInRef.getString( SEMI_COLON );
         }

         fileInRef.closeInputFile();
      }

      return inputHeap;       
   }
}