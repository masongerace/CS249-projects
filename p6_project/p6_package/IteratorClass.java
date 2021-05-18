package p6_package;

public class IteratorClass extends BasicArrayClass
{
   /*
    * Current index of iterator
    */
   private int currentIndex;
   
   /*
    * Constant character for display
    */
   private final char LEFT_BRACKET = 91;
   
   /*
    * Constant character for display
    */
   private final char RIGHT_BRACKET = 93;
   
   /*
    * Constant character for display
    */
   private final char SPACE = 32;
   
   /*
    * Default constructor for IteratorClass
    */
   public IteratorClass()
   {
      super();
      currentIndex = 0;
   }
   
   /*
    * Initialization constructor for IteratorClass
    * 
    * @param initCapacity - integer value at which to set initial array capacity
    */
   public IteratorClass( int initCapacity )
   {
      super( initCapacity );
      currentIndex = 0;
   }
   
   /*
    * Copy constructor for IteratorClass
    * 
    * @param copied - IteratorClass object to be copied
    */
   public IteratorClass( IteratorClass copied )
   {
      super( copied );
      currentIndex = 0;
   }

   /*
    * Clears array
    * 
    * @overrides clear in class BasicArrayClass
    */
   public void clear()
   {
      super.clear();
   }
   
   /*
    * Gets value at iterator cursor location
    * 
    * @return integer value returned; FAILED_ACCESS if not found
    */
   public int getAtCurrent()
   {
      return getAtIndex( currentIndex );
   }
   
   /*
    * Reports if iterator cursor is at first element 
    * <p>
    * Must consider whether list is empty
    * 
    * @return Boolean result of action; true if at beginning, false otherwise
    */
   public boolean isAtFirstElement()
   {
      //check if empty
      if( isEmpty() )
      {
         return false;
      }
      //return result of action
      return currentIndex == 0;
   }
   
   /*
    * Reports if iterator cursor is at last element
    * <p>
    * Must consider whether list is empty
    * 
    * @return Boolean result of action; true if at end, false otherwise
    */
   public boolean isAtLastElement()
   {
      //check if empty
      if( isEmpty() )
      {
         return false;
      }
      //return result for action
      return currentIndex == getCurrentSize() - 1;
   }
   
   /*
    * Reports if list is empty
    * 
    * @return Boolean result of action; true if empty, false otherwise
    */
   public boolean isEmpty()
   {
      return super.isEmpty();
   }
   
   /*
    * If possible, moves iterator cursor one position to the right, or next
    * <p>
    * Must consider whether list is empty
    * 
    * @return Boolean result of action; true if successful, false otherwise
    */
   public boolean moveNext()
   {
      //if empty or last element, cant move
      if( isEmpty() || isAtLastElement() )
      {
         return false;
      }
      //increment and return true
      currentIndex += 1;
      return true;
   }
   
   /*
    * If possible, moves iterator cursor one position to the left, or previous
    * <p>
    * Must consider whether list is empty
    * 
    * @return Boolean result of action; true if successful, false otherwise
    */
   public boolean movePrev()
   {
      //if at first element, cant move
      if( isEmpty() || isAtFirstElement() )
      {
         return false;
      }
      //increment and return true
      currentIndex -= 1;
      return true;
   }
   
   /*
    * Removes and returns a data value from the iterator cursor position
    * <p>
    * Note: cursor must be located at succeeding element unless last item 
    * removed
    * 
    * @return integer value removed from list, or FAILED_ACCESS if not found
    */
   public int removeAtCurrent()
   {
      //if empty
      if( isEmpty() )
      {
         return FAILED_ACCESS;
      }
      //call removeAtIndex,increment, return removed val
      int removedVal = removeAtIndex( currentIndex );
      currentIndex -= 1;
      return removedVal;
   }
   
   /*
    * Replaces value at iterator cursor with new value
    * 
    * @param newValue - integer value to be inserted in list
    * 
    * @return Boolean result of action; true if successful, false otherwise
    */
   public boolean replaceAtCurrent( int newValue )
   {
      return setAtIndex( currentIndex, newValue, REPLACE );
   }
   
   /*
    * Shows space-delimited list with cursor location indicated
    */
   public void runDiagnosticDisplay()
   {
      //needed variables
      int index;
      String display = "";
      //if not empty
      if ( !isEmpty() )
      {
         //loop through the array
         for ( index = 0; index < getCurrentSize(); index++ )
         {
            //if at currentIndex, print brackets around index
            if (index == currentIndex)
            {
               display +=  "" + LEFT_BRACKET + getAtIndex( index ) 
               + "" + RIGHT_BRACKET + "" + SPACE;
            }
            //else print value and space
            else
            {
               display += getAtIndex(index) + "" + SPACE;
            }
         }
      }
      //void, so no return, use print
      System.out.println(display);
   }
   
   /*
    * Inserts new value after value at iterator cursor
    * <p>
    * Note: Current value must remain the same after data set
    * 
    * @param newValue - integer value to be inserted in list
    * 
    * @return Boolean result of action; true if successful, false otherwise
    */
   public boolean setAfterCurrent( int newValue )
   {
      boolean temp = setAtIndex( currentIndex, newValue, INSERT_AFTER );
      movePrev();
      return temp;
   }
   
   /*
    * Inserts new before value at iterator cursor
    * <p>
    * Note: Current value must remain at the same index after data set
    * 
    * @param newValue - integer value to be inserted in list
    * 
    * @return Boolean result of action; true if successful, false otherwise
    */
   public boolean setBeforeCurrent( int newValue )
   {
      boolean temp = setAtIndex( currentIndex, newValue, INSERT_BEFORE );
      moveNext();
      return temp;
   }
   
   /*
    * Sets iterator cursor to beginning of list
    * 
    * @return Boolean result of action; true if successful, false otherwise
    */
   public boolean setToFirstElement()
   {
      //if empty return false
      if( isEmpty() )
      {
         return false;
      }
      //return true and set index
      currentIndex = 0;
      return true;
   }
   
   /*
    * Sets iterator cursor to the end of the list
    * 
    * @return Boolean result of action; true if successful, false otherwise
    */
   public boolean setToLastElement()
   {
      //if empty return false
      if( isEmpty() )
      {
         return false;
      }
      //return true and set index
      currentIndex = getCurrentSize() - 1;
      return true;
   }
}
