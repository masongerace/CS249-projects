package p3_package;

public class LogN_StudentMgmtClass extends Object
   {
      /*
       * Default constructor
       * <p>
       * takes no action for this static tool class
       */
      public LogN_StudentMgmtClass()
      {
         
      }
      
      /*
       * Compares two strings character by character set to lower case to see 
       * which is alphabetically greater than the other;
       * <p>
       * if all tested letters of a name are equal, then compares string lengths
       * 
       * @param strOne first String value to be compared
       * 
       * @param strTwo second String value to be compared
       * 
       * @return integer difference as specified
       */
      public static int compareStrings( String strOne, String strTwo )
      {
         //needed variables
         int difference, index = 0;
         char thisStringChar, otherStringChar;
         //traverse the string
         while( index < strOne.length() && index < strTwo.length() )
            {
               //save chars to be compared
               thisStringChar = strOne.charAt( index );
               otherStringChar = strTwo.charAt( index );
               //compare chars
               difference = toLowerCase( thisStringChar ) 
                     - toLowerCase( otherStringChar );
               //check if difference = 0
               if( difference != 0 )
                  {
                     //return difference
                     return difference;
                  }
               //increment index
               index++;
            }
         //return integer difference between strings
         return strOne.length() - strTwo.length();
      }
      
      /*
       * Merges StudentClass objects brought in between a low and high index 
       * segment (inclusive) of an array
       * <p>
       * Notes: uses locally sized single array for temporary storage
       * 
       * @param localArray StudentClass array holding unsorted values
       * 
       * @param lowIndex lowest index of array segment to be managed
       * 
       * @param middleIndex middle index of array segment to be managed
       * 
       * @param highIndex high index of array segment to be managed
       */
      private static void runMerge(StudentClass[] localArray,
            int lowIndex,
            int middleIndex,
            int highIndex)
      {
         //needed variables
         int arraySize = highIndex - lowIndex + 1;
         StudentClass[] mergeArray = new StudentClass[ arraySize ];
         int index = 0;
         int newIndex;
         
         int leftLeftIndex = lowIndex;
         int leftRightIndex = middleIndex;
         
         int rightLeftIndex = middleIndex + 1;
         int rightRightIndex = highIndex;
         
         //while loop to traverse the array
         while( leftLeftIndex <= leftRightIndex 
               && rightLeftIndex <= rightRightIndex )
            {
               //check if LLI compared to RLI <= 0
               if( compareStrings( localArray[ leftLeftIndex ].name, 
                     localArray[ rightLeftIndex ].name ) <= 0 )
                  {
                     //add the first string to the mergeArray
                     mergeArray[ index ] = localArray[ leftLeftIndex ];
                     index++;
                     leftLeftIndex++;
                  }
               //if not, RLI is first
               else
                  {
                   //add the first string to the mergeArray
                     mergeArray[ index ] = localArray[ rightLeftIndex ];
                     index++;
                     rightLeftIndex++;
                  }
               
            }
         //clean up the left side
         while( leftLeftIndex <= leftRightIndex )
            {
               //add the first string to the mergeArray
               mergeArray[ index ] = localArray[ leftLeftIndex ];
               index++;
               leftLeftIndex++;
            }
         //clean up the right side
         while( rightLeftIndex <= rightRightIndex )
            {
               //add the first string to the mergeArray
               mergeArray[ index ] = localArray[ rightLeftIndex ];
               index++;
               rightLeftIndex++;
            }
         //create new for loop to add the mergeArray to the localArray
         for( newIndex = lowIndex; newIndex <= highIndex; newIndex++ )
            {
               localArray[ newIndex ] = mergeArray[ newIndex - lowIndex ];
            }
      }
      
      /*
       * StudentClass data sorted using merge sort algorithm and the names as 
       * the keys
       * <p>
       * Note: Calls runMergeSortHelper with lower and upper indices of array 
       * to be sorted
       * 
       * @param localArray String array holding unsorted values
       * 
       * @param size integer value holding number of values in array 
       */
      public static void runMergeSort(StudentClass[] localArray,
            int size)
      {
         runMergeSortHelper( localArray, 0, size - 1 );
      }
      
      /*
       * Merge sort helper, recursively breaks given array segment down to 
       * smaller segments between lowIndex and highIndex (inclusive), then 
       * sorts data using merge sort method
       * 
       * @param localArrayStudentClass array holding unsorted values
       * 
       * @param lowIndex lowest index of array segment to be managed; this 
       * varies as the segments are broken down recursively
       * 
       * @param highIndex highest index of array segment to be managed; this 
       * varies as the segments are broken down recursively
       */
      private static void runMergeSortHelper(StudentClass[] localArray,
            int lowIndex,
            int highIndex)
      {
         //will divide the array by 2 until we get into single elements 
         int middleIndex;
         //check if the array needs to be split (check low < high)
         if( lowIndex < highIndex )
            {
               //if yes, create mid
               middleIndex =  ( lowIndex + highIndex ) / 2;
               //mergesort(low, high)
               //mergesort(mid+1, high)
               //merge(low,mid,high)
               runMergeSortHelper( localArray, lowIndex, middleIndex );
               runMergeSortHelper( localArray, middleIndex + 1, highIndex);
               runMerge( localArray, lowIndex, middleIndex, highIndex );
               
            }
      }
      
      /*
       * Partitions array using the first value as the partition; when this 
       * method is complete the partition value is in the correct location in 
       * the array
       * 
       * @param localArray StudentClass array holding unsorted values
       * 
       * @param lowIndex low index of array segment to be partitioned
       * 
       * @param highIndex high index of array segment to be partitioned
       * 
       * @return integer index of partition pivot
       */
      private static int runPartition(StudentClass[] localArray,
            int lowIndex,
            int highIndex)
      {
         //needed variables
         StudentClass pivot = localArray[ highIndex ];
         int index = ( lowIndex  - 1);
         int workingIndex;
         int pivotIndex;
         //loop to traverse the array
         for( workingIndex = lowIndex; workingIndex < highIndex; 
               workingIndex++ )
            {
               //check the strings against each other
               if( compareStrings( localArray[ workingIndex ].name, 
                     pivot.name ) < 0 )
                  {
                     //decrease index
                     index++;
                     //swap values
                     swapValues( localArray, index, workingIndex );
                  }
            }
         //swap values
         swapValues( localArray, index + 1, highIndex );
         //set pivotIndex to index
         pivotIndex = index + 1;
         //return pivotIndex
         return pivotIndex;    
      }
      
      /*
       * Data sorted using quick sort algorithm and the names as the keys
       * <p>
       * Note: Call runQuickSortHelper with lower and upper indices of array 
       * to be sorted
       * 
       * @param localArray StudentClass array holding unsorted values
       * 
       * @param size integer value holding the number of values in the array
       */
      public static void runQuickSort(StudentClass[] localArray, int size)
      {
         runQuickSortHelper( localArray, 0, size - 1 );
      }
      
      /*
       * Helper method run with parameters that support recursive access
       * 
       * @param localArray StudentClass array holding unsorted values
       * 
       * @param lowIndex low index of the segment of the array to be processed
       * 
       * @param highIndex high index of the segment of the array to be processed
       */
      private static void runQuickSortHelper(StudentClass[] localArray,
            int lowIndex,
            int highIndex)
      {
         int pivotIndex;
         //check if lowIndex < highIndex
         if( lowIndex < highIndex )
            {
               pivotIndex = runPartition( localArray, lowIndex, highIndex );
               runQuickSortHelper( localArray, lowIndex, pivotIndex - 1 );
               runQuickSortHelper( localArray, pivotIndex + 1, highIndex );
            }
      }
      
      /*
       * Swaps values within given array
       * 
       * @param localArray array of Strings used for swapping
       * 
       * @param indexOne integer index for one of the two items to be swapped
       * 
       * @param indexOther integer index for the other of the two items to be 
       *        swapped
       */
      private static void swapValues( StudentClass[] localArray,
            int indexOne,
            int indexOther )
      {
         //create a temporary object to store the first index
         StudentClass temporary = localArray[ indexOne ];
         //set oneIndex to otherIndex
         localArray[ indexOne ] = localArray[ indexOther ];
         //set otherIndex to temporary value 
         localArray[ indexOther ] = temporary;
      }
      
      /*
       * If character is upper case letter (i.e., 'A' - 'Z'), changes letter to 
       * lower case (i.e., 'a' - 'z'); otherwise, returns character unchanged
       * 
       * @param testChar character value to be tested and possibly modified
       * 
       * @return character value modified as specified
       */
      private static char toLowerCase( char testChar )
      {
         //check if testChar is a letter
         if( testChar >= 'A' && testChar <= 'Z' )
            {
               //set testChar to be lower cased
               testChar = (char)( testChar - 'A' + 'a' );
            }
         //return testChar
         return testChar;
      }
   }
