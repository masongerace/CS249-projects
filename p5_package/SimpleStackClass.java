package p5_package;

/*
 * @author Mason Gerace
 */

public class SimpleStackClass extends Object
{
   /*
    * Stores current capacity of stack class
    */
   private int capacity;
   
   /*
    * Provides constant for default capacity
    */
   private final int DEFAULT_CAPACITY = 10;
   
   /*
    * Provides constant -999999 for access failure messaging
    */
   public static final int FAILED_ACCESS = -999999;
   
   /*
    * Stores current size of stack class
    */
   private int size;
   
   /*
    * private int[] stackData
    */
   private int[] stackData;
   
   /*
    * Stores stack top index
    */
   private int stackTopIndex;
   
   /*
    * Default constructor
    */
   public SimpleStackClass()
   {
      //initialize size
      size = 0;
      //set capacity to default capacity
      capacity = DEFAULT_CAPACITY;
      //initialize stackData
      stackData = new int[ capacity ];
      //set stack index to -1
      stackTopIndex = -1;
   }
   
   /*
    * Initialization constructor
    * 
    * @param capacitySetting - initial capacity of stackData class
    */
   public SimpleStackClass(int capacitySetting)
   {
      //initialize size
      size = 0;
      //set capacity to capacity setting;
      capacity = capacitySetting;
      //initialize stackData
      stackData = new int[ capacity ];
      //set stack index to -1
      stackTopIndex = -1;
   }
   
   /*
    * Copy constructor
    * 
    * @param copied - SimpleStackClass object to be copied
    */
   public SimpleStackClass(SimpleStackClass copied)
   {
      //set capacity to copied capacity
      capacity = copied.capacity;
      //set size to copied size
      size = copied.size;
      //initialize stackData
      stackData = new int[ capacity ];
      //save the copied data
      int index = 0;
      for( index = 0; index < size; index++ )
      {
         stackData[ index ] = copied.stackData[ index ];
      }
      //set stackTopIndex
      stackTopIndex = size - 1;
   }
   
   /*
    * Reports stack empty status
    * <p>
    * Note: Does not use if/else
    * 
    * @return Boolean evidence of empty list
    */
   public boolean isEmpty()
   {
      //if stackTopIndex is -1 its empty
      return size == 0;
   }
   
   /*
    * Checks for resize, then pushes value onto stack
    * <p>
    * Note: end of array is always the top of the stack; index is incremented 
    * and then value is appended to array
    * 
    * @param newValue - Value to be pushed onto stack
    */
   public void push(int newValue)
   {
      //check for resize
      checkForReSize();
      //increment stack index
      stackTopIndex++;
      //set stackData top index to newValue
      stackData[ stackTopIndex ] = newValue;
      //increment the size
      size++;
   }
   
   /*
    * Removes and returns data from top of stack
    * 
    * @return value if successful, FAILED_ACCESS if not
    */
   public int pop()
   {
      //check if empty, return failed access
      if( isEmpty() )
      {
         return FAILED_ACCESS;
      }
      //decrement stack index (remove value)
      stackTopIndex--;
      //decrement size
      size--;
      //return top stack value
      return stackData[ stackTopIndex + 1];
   }
   
   /*
    * Removes and returns data from top of stack
    * 
    * @return value if successful, FAILED_ACCESS if not
    */
   public int peekTop()
   {
      //if empty, return failed access
      if( isEmpty() )
      {
         return FAILED_ACCESS;
      }
      //return stackTopIndex value
      return stackData[ stackTopIndex ];
   }
   
   /*
    * Checks for resize and resizes to twice the current capacity if needed
    * <p>
    * Note: Returns true if resize is necessary and is conducted; returns 
    * false if no action is taken
    * 
    * @return success of operation
    */
   private boolean checkForReSize()
   {
      //check for resize
      if( size == capacity )
      {
         //double capacity
         capacity *= 2;
         //resize array
         int[] resizedArray = new int[ capacity ];
         //copy stack data into the resized array
         int index;
         for( index = 0; index < size; index++ )
         {
            resizedArray[ index ] = stackData[ index ];
         }
         //save resized array to stackData
         stackData = resizedArray;
         //return true
         return true;
      }
      //return false
      return false;
   }
   
   /*
    * Clears stack by setting size to zero and top index to negative one
    */
   public void clear()
   {
      size = 0;
      stackTopIndex = -1;
   }
}
