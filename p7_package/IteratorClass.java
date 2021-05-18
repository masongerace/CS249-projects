package p7_package;
/*
 * @author Mason Gerace
 */
public class IteratorClass extends Object
{
   /*
    * Current index of iterator
    */
   private int currentIndex;
   
   /*
    * Constant character for display
    */
   private final char LEFT_BRACKET = '[';
   
   /*
    * Basic Linked list engine
    */
   BasicLLClass llData;
   
   /*
    * Constant character for display
    */
   private final char RIGHT_BRACKET = ']';
   
   /*
    * Constant character for display
    */
   private final char SPACE = ' ';
   
   
   /*
    * Default constructor for IteratorClass
    */
   public IteratorClass()
   {
      llData = new BasicLLClass();
      currentIndex = -1;
   }
   
   /*
    * Copy constructor for IteratorClass
    * 
    * @param copied - IteratorClass object to be copied
    */
   public IteratorClass( IteratorClass copied )
   {
      llData = new BasicLLClass( copied.llData );
      currentIndex = copied.currentIndex;
   }
   
   /*
    * Clears array
    */
   public void clear()
   {
      llData.clear();
      currentIndex = -1;
   }
   
   /*
    * Gets value at iterator cursor location
    * 
    * @return integer value returned; FAILED_ACCESS if not found
    */
   public int getAtCurrent()
   {
      return llData.getAtIndex( currentIndex );
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
      //return result for action
      return currentIndex == llData.getCurrentSize() - 1;
   }
   
   /*
    * Reports if list is empty
    * 
    * @return Boolean result of action; true if empty, false otherwise
    */
   public boolean isEmpty()
   {
      return llData.isEmpty();
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
      //if at first element or empty, cant move
      if( isAtFirstElement() || isEmpty() )
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
      //moveNext();
      int temp = llData.removeAtIndex( currentIndex );
      return temp;
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
      return llData.setAtIndex( currentIndex, newValue, BasicLLClass.REPLACE );
   }
   
   /*
    * Shows space-delimited list with cursor location indicated
    */
   public void runDiagnosticDisplay()
   {
      // initialize variables including size
      int LLSize = llData.getCurrentSize();
      int index;
      //loop to end of array of elements
      for ( index = 0; index < LLSize; index++ )
      {
         // check if index is at cursor index
         if (index == currentIndex)
         {
            // print value at cursor location with proceeding space
            System.out.print( "" + LEFT_BRACKET );
            System.out.print( llData.getAtIndex( index ) );
            System.out.print( "" +  RIGHT_BRACKET );
            System.out.print( "" + SPACE );
         }
         // otherwise at other location
         else
         {
            // prints element with leading space
            System.out.print( llData.getAtIndex( index ) );
            System.out.print( "" + SPACE );
         }
      }
      // go to next line of elements
      System.out.println();
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
      if( currentIndex > 0)
      {
      //temp value to return
      boolean temp = llData.setAtIndex( currentIndex, newValue, 
            BasicLLClass.INSERT_AFTER );
      movePrev();
      return temp;
      }
      return false;
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
      if(currentIndex > 0)
      {
      //temp value to return
      boolean temp = llData.setAtIndex( currentIndex, newValue, 
            BasicLLClass.INSERT_BEFORE );
      moveNext();
      return temp;
      }
      return false;
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
      currentIndex = llData.getCurrentSize() - 1;
      return true;
   } 
}
