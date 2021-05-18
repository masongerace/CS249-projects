package p2_package;

/**
 * Class wrapper for a Java array of generic data (classes), with 
 * additional management operations
 * <p>
 * Only works with class that extends Comparable, as shown in class declaration
 * <p>
 * Maintains a capacity value for maximum number of items that can be stored, 
 * and a size value for the number of valid or viable data items in the array
 *
 * @author MichaelL
 * @author Mason Gerace
 */
public class GenericDataMgmtClass<GenericData extends Comparable<GenericData>> 
   {
      /**
       * constant with default capacity
       */
      public static final int DEFAULT_CAPACITY = 10;

      /**
       * integer array for data
       */
      private Object[] localArray;

      /**
       * number of values in array
       */
      private int arraySize;

      /**
       * capacity of array
       */
      private int arrayCapacity;

      /**
       * Default constructor
       * <p>
       * Initializes array to default capacity (10)
       */
      public GenericDataMgmtClass()
         {
            //initialize array size
            arraySize = 0;
            //setArray capacity to default capacity (10)
            arrayCapacity = DEFAULT_CAPACITY;
            //initialize localArray to the DEFAULT_CAPACITY
            localArray = new Object[ DEFAULT_CAPACITY ];
         }

      /**
       * Initializing constructor
       * <p>
       * Initializes array to specified capacity
       * 
       * @param capacity maximum capacity specification for the array
       */
      public GenericDataMgmtClass( int capacity )
         {
            //initialize arraySize
            arraySize = 0;
            //set array capacity to capacity
            arrayCapacity = capacity;
            //initialize localArray to the capacity
            localArray = new Object[ arrayCapacity ];
         }

      /**
       * Accesses item in array at specified index if index 
       * within array size bounds
       *
       * @param accessIndex index of requested element value 
       *
       * @return accessed value if successful, null if not
       */
      @SuppressWarnings("unchecked")
      public GenericData accessItemAt( int accessIndex )
         {
            //create GenericData object
            GenericData indexValue = null; 
            //check if the accessIndex is within bounds
            if( accessIndex < arraySize  && accessIndex >= 0)
               {
                  indexValue = ( GenericData ) localArray[ accessIndex ];
                  //return accessed value 
                  return indexValue;
               }
               //if not return null
               return indexValue;
         }

      /**
       * Appends item to end of array, if array is not full, 
       * e.g., no more values can be added
       * 
       * @param newValue value to be appended to array
       * 
       * @return Boolean success if appended, or failure if array was full
       */
      public boolean appendItem( GenericData newValue )
         {
            //check if array if full
            if( !isFull() )
               {
                  //append item if array is not full
                  localArray[ arraySize ] = newValue;
                  //increase arraySize
                  arraySize++;
                  //return true if successful
                  return true;
               }
            //return false if unsuccessful
            return false;
         }

      /**
       * Clears array of all valid values by setting array size to zero, 
       * values remain in array but are not accessible
       */
      public void clear()
         {
            //set arraySize equal to 0
            arraySize = 0;
         }

      /**
       * Gets current capacity of array
       * <p>
       * Capacity of array indicates number of values the array can hold
       * 
       * @return capacity of array
       */
      public int getCurrentCapacity()
         {
            //return the capacity of the array
            return arrayCapacity;
         }

      /**
       * Gets current size of array
       * <p>
       * Size of array indicates number of valid or viable values in the array
       * 
       * @return size of array
       */
      public int getCurrentSize()
         {
            //return size of the array
            return arraySize;
         }

      /**
       * Inserts item to array at specified index if array is not full,
       * e.g., no more values can be added
       * <p>
       * Value is inserted at given index, all data from that 
       * index to the end of the array is shifted up by one
       * 
       * @param insertIndex index of element into which value is to be inserted
       * 
       * @param newValue value to be inserted into array
       * 
       * @return boolean success if inserted, or failure if array was full
       */
      public boolean insertData( int insertIndex, GenericData newValue )
         {
            //check if array is full
            if( !isFull() )
               {
                  int index; 
                  for( index = insertIndex; index < arraySize; index++ )
                  {
                     localArray[ insertIndex + 1 ] = localArray[ index ];
                  }
                  //insert value into index
                  localArray[ insertIndex ] = newValue;
                  //increase arraySize
                  arraySize++;
                  //return true if successful
                  return true;
               }
            //return false otherwise
            return false;
         }

      /**
       * Tests for size of array equal to zero, no valid values stored in array
       * 
       * @return boolean result of test for full
       */
      public boolean isEmpty()
         {
            //return result if not equal to 0
            return arraySize == 0;
         }

      /**
       * Tests for size of array equal to capacity, no more values can be added
       * 
       * @return boolean result of test for full
       */
      public boolean isFull()
         {
            //return test for full
            return arraySize == arrayCapacity;
         }

      /**
       * Removes item from array at specified index if index within 
       * array size bounds
       * <p>
       * Each data item from the element immediately above the remove index 
       * to the end of the array is moved down by one element
       * 
       * @param removeIndex index of element value to be removed
       * 
       * @return removed value if successful, null if not
       */
      @SuppressWarnings( "unchecked" )
      public GenericData removeData( int removeIndex )
         {
            //create index variable
            int index;
            //create GenericData object
            GenericData removedValue = null; 
            //check if removeIndex is in bounds
            if( removeIndex >= 0 && removeIndex < arraySize )
               {
                  //for loop to go through the array
                  for( index = removeIndex; index < arraySize; index++ )
                     {
                        //set removedValue to the localArray[ removeIndex ]
                        removedValue = ( GenericData ) localArray[ removeIndex ];
                        //set every index after the removed index to index + 1
                        localArray[ index ] = localArray[ index + 1 ];
                     }
                  //decrease arraySize
                  arraySize--;
                  //return removedValue
                  return removedValue;                    
               }
            //return null if not in bounds
            return null;
         }

      /**
       * Resets array capacity, copies current size and current size 
       * number of elements
       * <p>
       * Method will not resize capacity below current array capacity, returns 
       * false if this is attempted, true otherwise
       * 
       * @param newCapacity new capacity to be set; must be larger than current
       * capacity
       * 
       * @return boolean condition of resize success or failure
       */
      public boolean resize( int newCapacity )
         {
            //initialize variables
            int index;
            //initialize new array reference 
            Object[] referenceArray = new Object [ newCapacity ];
            //check if new capacity > current capacity
            if( newCapacity > arrayCapacity )
               {
                  //set the member capacity to the new capacity
                  arrayCapacity = newCapacity;
                  //for loop to go through the array
                  for( index = 0; index < arraySize; index++ )
                     {
                        //copy localArray index value to reference array
                        referenceArray[ index ] = localArray[ index ];
                     }
                  //set localArray to referenceArray
                  localArray = referenceArray;
                  //return true
                  return true;  
               }
            //return false
            return false;
         }

      /**
       * Sorts elements using the bubble sort algorithm
       * <p>
       * The data is sorted using the compareTo method of the given data set; 
       * the compareTo method decides the key and the order resulting
       */
      @SuppressWarnings("unchecked")
      public void runBubbleSort()
         {
            //set a boolean called swapped to true
            boolean swapped = true;
            GenericData firstIndex, secondIndex;
            int index;
            //while swapped is true
            while( swapped )
               {
                  //set swapped to false
                  swapped = false;
                  //for loop to go through the array
                  for( index = 0; index < arraySize - 1; index++ )
                     {
                        //set firstIndex to the first index
                        firstIndex = ( GenericData ) localArray[ index ];
                        //set secondIndex to the second index
                        secondIndex = ( GenericData ) localArray[ index + 1 ];
                        //check if firstIndex compared to secondIndex > 0
                        if( firstIndex.compareTo( secondIndex ) > 0 )
                           {
                              //swap first and second index
                              swapElements( index, index + 1 );
                              //set swapped to true
                              swapped = true;
                           }
                     }
               }
         }

      /**
       * Sorts elements using the insertion sort algorithm
       * <p>
       * The data is sorted using the compareTo method of the given data set; 
       * the compareTo method decides the key and the order resulting
       */
      @SuppressWarnings( "unchecked" )
      public void runInsertionSort()
         {
            //set up generic data 
            GenericData firstIndex, secondIndex;
            //set up index counters
            int innerIndex, outerIndex;
            //initialize outer index to 0
            outerIndex = 0;
            //loop through the array 
            while( outerIndex < arraySize - 1 )
               {
                  
                  firstIndex = removeData( outerIndex+ 1 );
                  
                  //for loop to go though the inner array
                  for( innerIndex = 0; innerIndex < outerIndex; innerIndex++ )
                     {
                        //
                        secondIndex = (GenericData) localArray[ innerIndex ];
                        //check if the innerIndex should be after innerIndex + 1
                        if( firstIndex.compareTo( secondIndex ) >= 0 )
                           {
                              insertData( innerIndex, firstIndex ); 
                              //swap elements
                              //swapElements(innerIndex , innerIndex + 1);
                           }
                        else if( firstIndex.compareTo( secondIndex ) >= 0 &&
                                 innerIndex == outerIndex )
                           {
                              insertData( outerIndex, firstIndex ); 
                           }
                     }
                  //increase the outerIndex
                  outerIndex++;
               }
         }

      /**
       * Sorts elements using the selection sort algorithm
       * <p>
       * The data is sorted using the compareTo method of the given data set; 
       * the compareTo method decides the key and the order resulting
       */
      @SuppressWarnings( "unchecked" )
      public void runSelectionSort()
         {
            //set  up generic data
            GenericData firstIndex, secondIndex;
            //set up needed variables
            int outerIndex, innerIndex, lowerIndex;
            //for loop to go through the outer index
            for( outerIndex = 0; outerIndex < arraySize - 1; outerIndex++ )
               {
                  lowerIndex = outerIndex;
                  //for loop to go through the inner index
                  for( innerIndex = outerIndex + 1; innerIndex < arraySize; 
                        innerIndex++  )
                     {
                        //setting up GenericData types
                        firstIndex = ( GenericData ) localArray[ lowerIndex ];
                        secondIndex = 
                              ( GenericData ) localArray[ innerIndex];
                        //comparing the variables
                        if( firstIndex.compareTo( secondIndex ) > 0 )
                           {
                              lowerIndex = innerIndex;
                           }
                     }
                  //swap elements
                  swapElements( outerIndex, lowerIndex );
               }
         }

      /**
       * Uses Shell's sorting algorithm to sort Generic Data in an array 
       * of Objects
       * <p>
       * Shell's sorting algorithm is an optimized insertion algorithm
       */
      @SuppressWarnings( "unchecked" )
      public void runShellSort()
         {
            int gap, gapPassIndex, insertionIndex;
            GenericData tempItem, testItem;
            boolean continueSearch;
            for( gap = arraySize / 2; gap > 0; gap /= 2 )
               {
                  for( gapPassIndex = gap; 
                        gapPassIndex < arraySize; gapPassIndex++ )
                     {
                        tempItem = (GenericData)localArray[ gapPassIndex ];
                        insertionIndex = gapPassIndex;
                        continueSearch = true;
                        
                        while( continueSearch && insertionIndex >= gap )
                           {
                              testItem 
                              = (GenericData)localArray[ insertionIndex - gap ];
                              
                              if( testItem.compareTo( tempItem ) >  0 )
                                 {
                                    localArray[ insertionIndex ] 
                                          = localArray[ insertionIndex - gap ];  

                                    insertionIndex -= gap;
                                 }
                              else
                                 {
                                    continueSearch = false;
                                 }
                           }  // end search loop
                        localArray[ insertionIndex ] = tempItem;
                     }  // end list loop
               }  // end gap size setting loop   
         }

      /**
       * Swaps one element in the local array at a given index with another
       * element in the array at the other given element
       * 
       * @param oneIndex index of one of two elements to be swapped
       * 
       * @param otherIndex index of second of two elements to be swapped
       */
      private void swapElements( int oneIndex, int otherIndex )
         {
            //create a temporary object to store oneIndex value
            Object temporary = localArray[ oneIndex ];
            //set oneIndex to otherIndex
            localArray[ oneIndex ] = localArray[ otherIndex ];
            //set otherIndex to temporary value 
            localArray[ otherIndex ] = temporary;
         }
   }
