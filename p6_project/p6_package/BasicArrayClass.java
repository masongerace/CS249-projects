package p6_package;

public class BasicArrayClass
{
   /*
    * Member data
    */
   private int arrayCapacity;
   
   /*
    * Member data
    */
   private int arraySize;
   
   /*
    * Default constant capacity
    */
   private static final int DEFAULT_CAPACITY = 10;
   
   /*
    * Default failed access constant
    */
   public static final int FAILED_ACCESS = -999999;
   
   /*
    * Constant used for allowing setAtIndex to insert value at index
    */
   public static final int INSERT_AFTER = 1003;
   
   /*
    * Constant used for allowing setAtIndex to insert value at index
    */
   public static final int INSERT_BEFORE = 1002;
   
   /*
    * Member data
    */
   private int[] localArray;
   
   /*
    * Constant used for allowing accessAtIndex to remove an item
    */
   public static final int REMOVE = 1004;
   
   /*
    * Constant used for allowing setAtIndex to replace value at index
    */
   public static final int REPLACE = 1001;
   
   /*
    * Constant used for allowing accessAtIndex to retrieve an item
    */
   public static final int RETRIEVE = 1005;
   
   /*
    * Default constructor, initializes array to default capacity
    */
   protected BasicArrayClass()
   {
      //initialize size
      arraySize = 0;
      //set capacity to default_capacity 
      arrayCapacity = DEFAULT_CAPACITY;
      //initialize localArray to the capacity
      localArray = new int[ arrayCapacity ];
   }
   
   /*
    * Initializing constructor, initializes array to specified capacity
    * 
    * @param capacity - integer maximum capacity specification for the array
    */
   protected BasicArrayClass( int capacity )
   {
      //initialize size
      arraySize = 0;
      //set capacity to capacity 
      arrayCapacity = capacity;
      //initialize localArray to the capacity
      localArray = new int[ arrayCapacity ];
   }
   
   /*
    * Copy constructor, initializes array to size and capacity of copied array, 
    * then copies only the elements up to the given size
    * 
    * @param copied - BasicArrayClass object to be copied
    */
   protected BasicArrayClass( BasicArrayClass copied )
   {
      //initialize arraySize
      arraySize = copied.arraySize;
      //set capacity to coped.capacity 
      arrayCapacity = copied.arrayCapacity;
      //initialize localArray to the capacity
      localArray = new int[ arrayCapacity ];
      //loop through the array
      int index;
      for( index = 0; index < arraySize; index++ )
      {
         localArray[ index ] = copied.localArray[ index ];
      }
   }
   
   /*
    * Utility method used by getAtIndex and removeAtIndex to access and 
    * possibly remove element depending on control code
    * <p>
    * Note: Uses only one loop
    * 
    * @param controlCode - integer value with either RETRIEVE or REMOVE to 
    *                      control operations
    * 
    * @param index - integer index of element to be retrieved or removed
    * 
    * @return integer value at element or FAILED_ACCESS if attempt to 
    *          access data out of bounds
    */
   private int accessAtIndex( int controlCode, int index )
   {
      //check if retrieve flag
      if( controlCode == RETRIEVE )
      {
         //check if in bounds
         if( arraySize < index )
         {
            return FAILED_ACCESS;
         }
         if( index < 0 )
         {
            return FAILED_ACCESS;
         }
         //return value
         return localArray[ index ];
      }
      //check if remove flag
      else if( controlCode == REMOVE )
      {
         //check if in bounds
         if( arraySize < index )
         {
            return FAILED_ACCESS;
         }
         if( index < 0 )
         {
            return FAILED_ACCESS;
         }
         //return value
         int removedVal = localArray[ index ];
         //remove value
         int newIndex;
         for( newIndex = index; newIndex < arraySize; index++)
         {
            localArray[ newIndex ] = localArray [ newIndex + 1 ];
         }
         //return value
         arraySize--;
         return removedVal;
      }
      //else return failed access
      else
      {
         return FAILED_ACCESS;
      }
   }

   /*
    * Description: Checks for need to resize; if this is necessary, 
    * creates new array with double the original capacity, loads data from 
    * original array to new one, then sets localArray to new array
    */
   protected void checkForResize()
   {
      //check if size == capacity
      if( arraySize == arrayCapacity )
      {
         //double capacity
         arrayCapacity *= 2;
         //set resized array 
         int[] resizedArray = new int[ arrayCapacity ];
         //copy localArray data into the resized array
         int index;
         for( index = 0; index < arraySize; index++ )
            {
               resizedArray[ index ] = localArray[ index ];
            }
         //save resized array to local array
         localArray = resizedArray;
      }
   }
   
   /*
    * Clears array of all valid values by setting array size to zero, values 
    * remain in array but are not accessible
    */
   protected void clear()
   {
      arraySize = 0;
   }
   
   /*
    * Accesses item in array at specified index if index within array 
    * size bounds
    * <p>
    * Note: Calls accessAtIndex with RETRIEVE to conduct action
    * 
    * @param accessIndex - integer index of requested element value
    * 
    * @return integer accessed value if successful, FAILED_ACCESS if not
    */
   protected int getAtIndex( int accessIndex )
   {
      return accessAtIndex( RETRIEVE, accessIndex );
   }
   
   /*
    * Description: Gets current capacity of array
    * <p>
    * Note: capacity of array indicates number of values the array can hold
    * 
    * @return integer capacity of array
    */
   protected int getCurrentCapacity()
   {
      return arrayCapacity;
   }
   
   /*
    * Description: Gets current size of array
    * <p>
    * Note: size of array indicates number of valid or viable values in the 
    * array
    * 
    * @return integer size of array
    */
   protected int getCurrentSize()
   {
      return arraySize;
   }
   
   /*
    * Tests for size of array equal to zero, no valid values stored in array
    * 
    * @return Boolean result of test for empty
    */
   protected boolean isEmpty()
   {
      return arraySize == 0;
   }
   
   /*
    * Description: Removes item from array at specified index if index within 
    * array size bounds
    * <p>
    * Note: Each data item from the element immediately above the remove index 
    * to the end of the array is moved down by one element
    * <p>
    * Note: Must call accessAtIndex with REMOVE to conduct action
    * 
    * @param removeIndex - integer index of element value to be removed
    * 
    * @return removed integer value if successful, FAILED_ACCESS if not
    */
   protected int removeAtIndex( int removeIndex )
   {
      return accessAtIndex( REMOVE, removeIndex );
   }
   
   /*
    * Description: sets item in array at specified index
    * <p>
    * CHECK Note: If constant REPLACE is used, new value overwrites value at 
    * given index
    * <p>
    * CHECK Note: If constant INSERT_BEFORE is used, new value is inserted prior 
    * to the value at the given index moving all other elements up by one
    * <p>
    * CHECK Note: If constant INSERT_AFTER is used, new value is inserted after 
    * the value at the given index moving all other elements up by one
    * <p>
    * CHECK Note: If either constant INSERT_BEFORE or INSERT_AFTER is used with 
    * index zero and an empty array, new value is inserted at index zero
    * <p>
    * CHECK Note: Method checks for available array capacity and adjusts it as 
    * needed prior to inserting new item
    * <p>
    * CHECK Note: Method must also check for correct array boundaries depending 
    * upon INSERT/REPLACE state
    * 
    * @param setIndex - integer index of element at which value is to be 
    * inserted
    * 
    * @param newValue - integer value to be placed in array
    * 
    * @param replaceFlag - integer flag to indicate insertion or replacement 
    * in the array
    * 
    * @return Boolean success if inserted, or failure if incorrect index was 
    * used
    */
   protected boolean setAtIndex( int setIndex, int newValue, int replaceFlag )
   {
      //check flags, then check for special case. 
      if(  replaceFlag == INSERT_BEFORE )
      {
         //if the flags are up, check for special case (insert index 0)
         if( setIndex == 0 && arraySize == 0 )
         {
            //if special case/check/set/increment
            checkForResize();
            localArray[ setIndex ] = newValue;
            arraySize++;
            return true;
         }
         //if insert before, check/set/increment
         checkForResize();
         int lastIndex;
         //loop through and open up set index
         for( lastIndex = arraySize - 1; lastIndex >= setIndex; lastIndex-- )
         { 
            localArray[ lastIndex + 1 ] = localArray[ lastIndex ];
         }
         localArray[ setIndex ] = newValue;
         arraySize++;
         return true;
      }
      else if( replaceFlag == INSERT_AFTER )
      {
         //if the flags are up, check for special case (insert index 0)
         if( setIndex == 0 && arraySize == 0 )
         {
            checkForResize();
            localArray[ setIndex ] = newValue;
            arraySize++;
            return true;
         }
         //insert before case
         checkForResize();
         int afterIndex = setIndex + 1;
         int lastIndex;
         //loop to open up a spot in the array
         for( lastIndex = arraySize - 1; lastIndex >= afterIndex; lastIndex-- )
         { 
            localArray[ lastIndex + 1 ] = localArray[ lastIndex ];
         }
         localArray[ afterIndex ] = newValue;
         arraySize++;
         return true;
      }
      //replace case
      else if( replaceFlag == REPLACE )
      {
         checkForResize();
         localArray[ setIndex ] = newValue;
         return true;
      }
      //else case
      else
      {
         return false;
      }
   }
}