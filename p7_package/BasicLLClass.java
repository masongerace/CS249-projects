package p7_package;
/*
 * @author Mason Gerace
 */
public class BasicLLClass extends Object
{
   /*
    * @Author Mason Gerace
    */
   private class BasicNode extends Object
   {
      /*
       * 
       */
      BasicNode nextRef;
      int value;
      
      public BasicNode( int newVal )
      {
         value = newVal;
         nextRef = null;
      }

      public BasicNode( BasicNode copied )
      {
         value = copied.value;
         nextRef = null;
      }
   }
   /*
    * Default failed access constant
    */
   public static final int FAILED_ACCESS = -999999;

   /*
    * Member - head reference
    */
   BasicNode headRef;
   
   /*
    * Constant used for allowing setAtIndex to insert value at index
    */
   public static final int INSERT_AFTER = 1003;
   
   /*
    * Constant used for allowing setAtIndex to insert value at index
    */
   public static final int INSERT_BEFORE = 1002;
   
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
   public BasicLLClass()
   {
      headRef = null;
   }
   
   /*
    * Copy constructor, initializes array to size and capacity of copied array, 
    * <p>
    * then copies only the elements up to the given size
    * 
    * @param copied copied - BasicArrayClass object to be copied
    */
   public BasicLLClass( BasicLLClass copied )
   {
      //check if copied headRef is null
      if( copied.headRef == null )
      {
         headRef = null;
      }
      //set up needed variables
      headRef = new BasicNode( copied.headRef );
      BasicNode workingRef = headRef;
      BasicNode copiedWorkingRef = copied.headRef.nextRef;
      //loop while copiedWkgRef isnt null
      while ( copiedWorkingRef != null )
      {
         //copy values to local LL
         workingRef.nextRef = new BasicNode( copiedWorkingRef );
         workingRef = workingRef.nextRef;
         copiedWorkingRef = copiedWorkingRef.nextRef;
      }
   }
   
   /*
    * Utility method used by getAtIndex and removeAtIndex to access and 
    * possibly remove element depending on control code
    * <p>
    * Note: Uses only one loop
    * 
    * @param controlCode - integer value with either RETRIEVE or REMOVE 
    * to control operations
    * 
    * @param index - integer index of element to be retrieved or removed
    * 
    * @return integer value at element or FAILED_ACCESS if attempt to access 
    * data out of bounds
    */
   private int accessAtIndex( int controlCode, int index )
   {
      //needed variables 
      BasicNode workingRef = getRefAtIndex( headRef, index );
      BasicNode prevRef = getRefAtIndex( headRef, index - 1 );
      int removedVal = workingRef.value;
      //check bounds and isEmpty
      if( index < 0 || getCurrentSize() <= index || isEmpty() )
      {
         return FAILED_ACCESS;
      }
      //check if retrieve flag
      else if( controlCode == RETRIEVE )
      {
         return workingRef.value;
      }
      //check if remove flag
      else if( controlCode == REMOVE )
      {
         //special case (1 element)
         if( index == 0 || getCurrentSize() == 1 )
         {
            headRef = workingRef.nextRef;
            return removedVal;
         }
         prevRef.nextRef = workingRef.nextRef;
         return removedVal;
      }
      //else case: return failed access
      else
      {
         return FAILED_ACCESS;
      }
   }
   
   /*
    * Clears array of all valid values by setting array size to zero, 
    * <p>
    * values remain in array but are not accessible
    */
   public void clear()
   {
      headRef = null;
   }
   
   /*
    * Accesses item in array at specified index if index within array size 
    * bounds
    * <p>
    * Note: Calls accessAtIndex with RETRIEVE to conduct action
    * 
    * @param accessIndex - integer index of requested element value
    * 
    * @return integer accessed value if successful, FAILED_ACCESS if not
    */
   public int getAtIndex( int accessIndex )
   {
      return accessAtIndex( RETRIEVE, accessIndex );
   }
   
   /*
    * Description: Gets current size of array
    * <p>
    * Note: size of array indicates number of valid or viable values in the 
    * array
    * 
    * @return integer size of array
    */
   public int getCurrentSize()
   {
      return getCurrentSizeHelper( headRef );
   }
   
   /*
    * Description: Gets current size of array
    * <p>
    * Note: size of array indicates number of valid or viable values in the 
    * array
    * 
    * @param - BasicNode reference starts with head reference and is used for 
    * recursion
    * 
    * @return integer size of array
    */
   private int getCurrentSizeHelper( BasicNode wkgRef )
   {
      if( wkgRef == null )
      {
         return 0;
      }
      //recursively call method (have it act as counter)
      return 1 + getCurrentSizeHelper( wkgRef.nextRef );
   }
   
   /*
    * Gets reference for pseudo index using recursive process
    * 
    * @param wkgRef - BasicNode reference
    * 
    * @param index - integer index for pseudo element requested
    * 
    * @return BasicNode reference to element at pseudo index
    */
   private BasicNode getRefAtIndex( BasicNode wkgRef, int index )
   {
      //check for neg index
      if( index < 0 )
      {
         return null;
      }
      //check for ref != null and index > 0
      else if( index > 0 && wkgRef != null )
      {
         //recursively call method
         return getRefAtIndex( wkgRef.nextRef, index - 1 );
      }
      //return wrk ref
      return wkgRef;
   }
   
   /*
    * Tests for size of array equal to zero, no valid values stored in array
    * 
    * @return Boolean result of test for empty
    */
   public boolean isEmpty()
   {
      return headRef == null;
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
   public int removeAtIndex( int removeIndex )
   {
      return accessAtIndex( REMOVE, removeIndex );
   }
   
   /*
    * Description: sets item in array at specified index
    * <p>
    * Note: If constant REPLACE is used, new value overwrites value at 
    * given index
    * <p>
    * Note: If constant INSERT_BEFORE is used, new value is inserted prior to 
    * the value at the given index moving all other elements up by one
    * <p>
    * Note: If constant INSERT_AFTER is used, new value is inserted after the 
    * value at the given index moving all other elements up by one
    * <p>
    * Note: If either constant INSERT_BEFORE or INSERT_AFTER is used with index 
    * zero and an empty array, new value is inserted at index zero
    * <p>
    * Note: Method checks for available array capacity and adjusts it as needed 
    * prior to inserting new item
    * <p>
    * Note: Method must also check for correct array boundaries depending upon 
    * INSERT/REPLACE state
    * 
    * @param setIndex - integer index of element at which value is to be 
    * inserted
    * 
    * @param newValue - integer value to be placed in array
    * 
    * @param replaceFlag - integer flag to indicate insertion or replacement 
    * in the array
    * 
    * @return Boolean success if inserted, or failure if incorrect index 
    * was used
    */
   public boolean setAtIndex( int setIndex, int newValue, int replaceFlag )
   {
      //create needed variables
      BasicNode workingRef = getRefAtIndex( headRef, setIndex );
      BasicNode prevRef = getRefAtIndex( headRef, setIndex - 1 );
      BasicNode insertRef;
      //check bounds 
      if( setIndex < 0 || getCurrentSize() < setIndex )
      {
         return false;
      }
      //check for special case (empty) 
      else if( ( isEmpty() && setIndex == 0 ) )
      {
         workingRef = new BasicNode( newValue );
         headRef = workingRef;
         return true;
      }
      //check if insert after flag
      else if( replaceFlag == INSERT_AFTER )
      {
         insertRef = new BasicNode( newValue );
         insertRef.nextRef = workingRef.nextRef;
         prevRef.nextRef = insertRef;
         return true;
      }
      //check if insert before flag
      else if( replaceFlag == INSERT_BEFORE )
      {
         //special case (inserting into index 0/head ref)
         if( setIndex == 0 )
         {
            insertRef = new BasicNode( newValue );
            insertRef.nextRef = workingRef;
            headRef = insertRef;
            return true;
         }
         //otherwise insert before index
         insertRef = new BasicNode( newValue );
         insertRef.nextRef = prevRef.nextRef;
         prevRef.nextRef = insertRef;
         return true;
      }
      //replace case
      else if( replaceFlag == REPLACE )
      {
         //workingRef = getRefAtIndex( workingRef, setIndex );
         workingRef.value = newValue;
         return true;
      }
      //else false
      else
      {  
         return false;
      }
   }
}

