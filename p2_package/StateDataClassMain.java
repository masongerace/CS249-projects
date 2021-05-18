package p2_package;

/** Provides workbench for testing StateDataClass and GenericArrayClass
 * 
 * @author MichaelL
 *
 */
public class StateDataClassMain
{
   private final static char COMMA = ',';

   /** main method for driving multiple tests on Generic Array Class
    * and StateDataClass
    * 
    * @param args command-line string input arguments
    */
   public static void main(String[] args)
      {
         GenericDataMgmtClass<StateDataClass> gSC;
         StateDataClass stateData;
         String[] testData = new String[ 10 ];
         String tempStr;
         int arrayCapacity = 60;
         int index;
         String fileName = "stateData.txt";

         // title
         System.out.println( "\nGenericArrayClass Data Testing Program\n");

         // set up test data
         testData[ 0 ] = "State Name: Rhode Island (RI), Population: 1057315";
         testData[ 1 ] = "State Name: Ohio (OH), Population: 11689442";
         testData[ 2 ] = "State Name: New Jersey (NJ), Population: 9032873";
         testData[ 3 ] = "State Name: California (CA), Population: 39557045";
         testData[ 4 ] = "State Name: Delaware (DE), Population: 967171";
         testData[ 5 ] = "State Name: New York (NY), Population: 19542209";
         testData[ 6 ] = "State Name: Oklahoma (OK), Population: 3943079";
         testData[ 7 ] = "State Name: Missouri (MO), Population: 6126452";
         testData[ 8 ] = "State Name: Vermont (VT), Population: 626299";
         testData[ 9 ] = "State Name: Kentucky (KY), Population: 4468402";

         // access data from file
         // test initialization constructor, appendItem, resize 
         System.out.println( "Data Retrieval from file - Begin");         
         gSC = getData( fileName, arrayCapacity );

         if( gSC != null )
            {
               System.out.println( "Data Retrieval from file - Success");          

               index = 1;

               // remove all but 10 items
               while( gSC.getCurrentSize() > 10 )  
                  {
                     stateData = gSC.removeData( 0 );

                     // Test for data accessed and removed
                     System.out.format( "%2d: %s removed\n", 
                           index, stateData.toString() ); 

                     index++;
                  }

               try
                  {
                     // Test accessItemAt
                     System.out.println( "\nTesting accessItem:");

                     System.out.println( "\tState data: " 
                           + gSC.accessItemAt( 2 ).toString() + " tested.");
                     tempStr = gSC.accessItemAt( 2 ).toString(); 
                     assert tempStr.equals( testData[ 2 ] ) == true;

                     System.out.println( "\tState data: " 
                           + gSC.accessItemAt( 4 ).toString() + " tested.");
                     tempStr = gSC.accessItemAt( 4 ).toString(); 
                     assert tempStr.equals( testData[ 4 ] ) == true;

                     System.out.println( "\tState data: " 
                           + gSC.accessItemAt( 6 ).toString() + " tested.");
                     tempStr = gSC.accessItemAt( 6 ).toString(); 
                     assert tempStr.equals( testData[ 6 ] ) == true;

                     System.out.println( "\tState data: " 
                           + gSC.accessItemAt( 8 ).toString() + " tested.");
                     tempStr = gSC.accessItemAt( 8 ).toString(); 
                     assert tempStr.equals( testData[ 8 ] ) == true;
                  }

               catch( AssertionError ae )
                  {
                     System.out.print( "\t#################### " );
                     System.out.print( "ACCESS ITEM ERROR: " );
                     System.out.println( ae + " ####################" );                
                  }

            }

         else
            {
               System.out.println( 
                     "\nERROR: First Data access failure - Program aborted." );            
            }

         // reload data from file
         System.out.println( "\nData Retrieval from file - Begin");         
         gSC = getData( fileName, arrayCapacity );

         if( gSC != null )
            {
               System.out.println( "Data Retrieval from file - Success");          

               gSC.runBubbleSort();  // default sort, by name, forward

               System.out.println( "\nAfter Bubble Sort: ");

               index = 0;

               while( index < gSC.getCurrentSize() )
                  {
                     System.out.format( "%2d: %s\n", index + 1, 
                           gSC.accessItemAt( index ) );

                     index++;
                  }                          

               // reload data from file
               System.out.println( "\nData Retrieval from file - Begin");         
               gSC = getData( fileName, arrayCapacity );

               if( gSC != null )
                  {
                     System.out.println( "Data Retrieval from file - Success");          

                     gSC.runInsertionSort();

                     System.out.println( "\nAfter Insertion Sort (GPA, Backward): ");

                     index = 0;

                     while( index < gSC.getCurrentSize() )
                        {
                           System.out.format( "%2d: %s\n", index + 1, 
                                 gSC.accessItemAt( index ) );

                           index++;
                        }
                  }

               // reload data from file
               System.out.println( "\nData Retrieval from file - Begin");         
               gSC = getData( fileName, arrayCapacity );

               if( gSC != null )
                  {
                     System.out.println( "Data Retrieval from file - Success");          

                     gSC.runSelectionSort();

                     System.out.println( "\nAfter Selection Sort: ");

                     index = 0;

                     while( index < gSC.getCurrentSize() )
                        {
                           System.out.format( "%2d: %s\n", index + 1, 
                                 gSC.accessItemAt( index ) );

                           index++;
                        }
                  }

               // reload data from file
               System.out.println( "\nData Retrieval from file - Begin");         
               gSC = getData( fileName, arrayCapacity );

               if( gSC != null )
                  {
                     System.out.println( "Data Retrieval from file - Success");          

                     gSC.runShellSort();

                     System.out.println( "\nAfter Shell Sort: ");

                     index = 0;

                     while( index < gSC.getCurrentSize() )
                        {
                           System.out.format( "%2d: %s\n", index + 1, 
                                 gSC.accessItemAt( index ) );

                           index++;
                        }
                  }

            }

         else
            {
               System.out.println( 
                     "\nERROR: Second Data Access Failure - Program Aborted");
            }

         System.out.println( "\n --- End of Program--- " );         
      }

   /**
    * Local method uploads data character by character,
    * parses characters, and loads into StateDataClass
    * type data
    * <p>
    * Exception: If there is a file failure such as file not found,
    * method will return null
    * <p>
    * Exception: If the capacity parameter is set to low for the data,
    * method will also return null
    * 
    * @param fileName name of file in local directory required for upload
    * 
    * @param setCapacity directs method to set capacity
    * of generic array
    * 
    * @return returns generic array holding StateDataClass data
    */
   public static GenericDataMgmtClass<StateDataClass> 
   getData( String fileName, int setCapacity )
      {
         StateDataClass stateDataClassObj;
         GenericDataMgmtClass<StateDataClass> arrayClassObj 
         = new GenericDataMgmtClass<StateDataClass>( setCapacity );
         File_Input_Class fileInObj = new File_Input_Class();

         String stateName, stateAbbrev;
         long statePop;

         // open file, test for success
         if( fileInObj.openInputFile( fileName ) )
            {
               // prime loop
               stateName = fileInObj.getString( COMMA );

               stateAbbrev = fileInObj.getString( COMMA );

               statePop = fileInObj.getLong();

               // loop to end of file
               while( !fileInObj.checkForEndOfFile() )
                  {
                     stateDataClassObj
                     = new StateDataClass( stateName, stateAbbrev, statePop );

                     arrayClassObj.appendItem( stateDataClassObj );
                     stateName = fileInObj.getString( COMMA );

                     stateAbbrev = fileInObj.getString( COMMA );

                     statePop = fileInObj.getLong();
                  }
            }

         else
            {
               return null;             
            }

         return arrayClassObj;
      }

}
