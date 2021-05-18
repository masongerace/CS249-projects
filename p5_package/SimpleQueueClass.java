package p5_package;

/*
 * @author Mason Gerace
 */

public class SimpleQueueClass extends Object
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
    * Stores queue head index
    */
   private int headIndex;
   
   /*
    * Integer array stores queue data
    */
   private int[] queueData;

   /*
    * Stores current size of stack class
    */
   private int size;

   /*
    * Stores queue tail index
    */
   private int tailIndex;

   /*
    * Default constructor
    */
   public SimpleQueueClass()
   {
      //initialize size
      size = 0;
      //set capacity to default_capacity 
      capacity = DEFAULT_CAPACITY;
      //initialize localArray to the Dcapacity
      queueData = new int[ capacity ];
      //set head and tail index
      headIndex = 0;
      tailIndex = -1;
   }
   
   /*
    * Initialization constructor
    * 
    * @param capacitySetting - initial capacity of queueData class
    */
   public SimpleQueueClass(int capacitySetting)
   {
      //initialize size
      size = 0;
      //set capacity to capacitySetting
      capacity = capacitySetting;
      //initialize queueData to the capacitySetting (capacity)
      queueData = new int[ capacity ];
      //set head and tail index
      headIndex = 0;
      tailIndex = -1;
   }
   
   /*
    * Copy constructor
    * <p>
    * Note: queue is copied so that head index is at zero and tail index is at 
    * size - 1; i.e., this resets the array to start at zero
    * 
    * @param copied - SimpleQueueClass object to be copied
    */
   public SimpleQueueClass(SimpleQueueClass copied)
   {
      //set size, capacity, queueData to copied data
      size = copied.size;
      capacity = copied.capacity;
      queueData = new int[ capacity ];
      //save the copied array
      int index;
      for( index = 0; index < copied.size; index++ )
      {
            queueData[ index ] = copied.queueData[ index ];
      }
      //set head and tail index
      headIndex = 0;
      tailIndex = size - 1;
   }
   
   /*
    * Checks for resize, then enqueues value
    * <p>
    * Note: Updates tail index, then appends value to array at tail index
    * 
    * @param newValue - Value to be enqueued
    */
   public void enqueue(int newValue)
   {
      //check for resize
      checkForReSize();
      //update tail index
      updateTailIndex();
      //enqueue value
      queueData[ tailIndex ] = newValue;
      //increment size
      size = size + 1;
   }
   
   /*
    * Removes and returns value from front of queue
    * <p>
    * Note: Acquires data from head of queue, then updates head index
    * 
    * @return Value if successful, FAILED_ACCESS if not
    */
   public int dequeue()
   {
      //check if empty, return failed access
      if( isEmpty() )
      {
         return FAILED_ACCESS;
      }
      //
      int headData = queueData[ headIndex ];
      //update head index
      updateHeadIndex();
      //decrement size
      size--;
      //return head index value
      //return queueData[ headIndex ];
      return headData;
   }

   /*
    * Provides peek at front of queue
    * 
    * @return Value if successful, FAILED_ACCESS if not
    */
   public int peekFront()
   {
      //check if empty, return failed access
      if( isEmpty() )
         {
            return FAILED_ACCESS;
         }
      //return head index value
      return queueData[ headIndex ];
   }
   
   /*
    * Reports queue empty state
    * <p>
    * Note: Does not use if/else
    * 
    * @return Boolean evidence of empty list
    */
   public boolean isEmpty()
   {
      return size == 0;
   }
   
   /*
    * Checks for resize and resizes to twice the current capacity if needed
    * <p>
    * Note: Returns true if resize is necessary and is conducted; returns 
    * false if no action is taken
    * <p>
    * Update: This method must transfer the data into the array such that the 
    * resized array starts with a head index of zero and a tail index of 
    * size - 1
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
         //set resized array 
         int[] resizedArray = new int[ capacity ];
         //copy queue data into the resized array
         int index;
         for( index = 0; index < size; index++ )
         {
            resizedArray[ index ] = queueData[ index ];
         }
         //save resized array to queue data
         queueData = resizedArray;
         //return true
         return true;
      }
      //return false
      return false;
   }

   /*
    * Clears the queue by setting the size to zero, the tail index to -1 and 
    * the head index to zero
    */
   public void clear()
   {
      size = 0;
      tailIndex = -1;
      headIndex = 0;
   }
   
   /*
    * Updates queue head index to wrap around array as needed
    * <p>
    * Note: Does not use if/else
    */
   private void updateHeadIndex()
   {
      //will increase index + 1 up to last index, then restarts at 0
      headIndex = ( headIndex + 1 ) % ( capacity );
   }
   
   /*
    * Updates queue tail index to wrap around array as needed
    * <p>
    * Note: Does not use if/else
    */
   private void updateTailIndex()
   {
      //will increase index + 1 up to last index, then restarts at 0
      tailIndex = ( tailIndex + 1 ) % ( capacity );
   }

}
