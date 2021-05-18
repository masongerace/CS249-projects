package p10_package;
/*
 * @author Mason Gerace
 * 
 * Array-based StudentClass max heap class used as a priority queue for 
 * StudentClass data; prioritized by GPA using StudentClass compareTo
 */
public class StudentHeapClass
{

   /*
    * Management data for array
    */
   private int arrayCapacity;

   /*
    * Management data for array
    */
   private int arraySize;

   /*
    * Initial array capacity
    */
   public final int DEFAULT_ARRAY_CAPACITY = 10;

   /*
    * Display flag can be set to observe bubble up and trickle down operations
    */
   private boolean displayFlag;

   /*
    * Array for heap
    */
   private StudentClass[] heapArray;

   /*
    * Enumerated left child flag
    */
   public final int LEFT = 2002;

   /*
    * Enumerated parent flag
    */
   public final int PARENT = 1001;

   /*
    * Enumerated right child flag
    */
   public final int RIGHT = 3003;

   /*
    * Default constructor sets up array management conditions and default 
    * display flag setting
    */
   public StudentHeapClass()
   {
      //initialize size
      arraySize = 0;
      //set display flag
      displayFlag = false;
      //set capacity to default_capacity 
      arrayCapacity = DEFAULT_ARRAY_CAPACITY;
      //initialize heapArray to the capacity
      heapArray = new StudentClass[ arrayCapacity ];
   }

   /*
    * Copy constructor copies array and array management conditions and default
    * display flag setting
    *  
    * @param copied - StudentHeapClass object to be copied
    */
   public StudentHeapClass( StudentHeapClass copied )
   {
      //initialize size
      arraySize = copied.arraySize;
      //set display flag
      displayFlag = copied.displayFlag;
      //set capacity to default_capacity 
      arrayCapacity = copied.arrayCapacity;
      heapArray = new StudentClass[ arraySize ];
      //copy the copied array to heapArray
      int index;
      for( index = 0; index < arraySize; index++ )
      {
         heapArray[ index ]= new StudentClass( copied.heapArray[ index ] );
      }
   }

   /*
    * Accepts StudentData item and adds it to heap
    * <p>
    * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data 
    * addition
    * <p>
    * Note: must check for resize before attempting to add an item
    * 
    * @param newItem - StudentClass data item to be added
    */
   public void addItem( StudentClass newItem )
   {
      //check if display flag is true
      if( displayFlag )
      {
         //display process
         System.out.println();
         System.out.print( "Adding new process: " );
         System.out.println( newItem.toString() );
      }
      //check for resize
      checkForResize();
      //add item to last index ( arraySize )
      heapArray[ arraySize ] = newItem;
      //call bubble up
      arraySize += 1;
      bubbleUpArrayHeap( arraySize - 1 );
      //increase arraySize
      //arraySize += 1;
   }

   /*
    * Recursive operation to reset data in the correct order for the max heap 
    * after new data addition
    * 
    * @param currentIndex - index of current item being assessed, and moved up 
    * as needed
    */
   private void bubbleUpArrayHeap( int currentIndex )
   {
      // check for current index greater than 0
      // (top of tree, beginning of list)
      if( currentIndex > 0 )
      {
         // calculate parent's index
         int parentIndex = (( currentIndex - 1) / 2 );
         // check if current child's value is less than parent value
         if( heapArray[ parentIndex ].compareTo( heapArray[ currentIndex ] ) 
               < 0 )
         {
            //check if display flag is true
            if( displayFlag )
            {
               //print process
               System.out.println( "   - Bubble up: " );
               System.out.print( "     - Swapping parent: " );  
               System.out.print( heapArray[ parentIndex ].toString() );
               System.out.print( " with child: " );
               System.out.println( heapArray[ currentIndex ].toString() );
            }
            // swap the parent's and child's values
            StudentClass temp = heapArray[ currentIndex ];
            heapArray[ currentIndex ] = heapArray[ parentIndex ];
            heapArray[ parentIndex ] = temp;
            // call the method recursively with the parent's index
            bubbleUpArrayHeap( parentIndex );
         }
      }
   }

   /*
    * Automatic resize operation used prior to any new data addition in the heap
    * <p>
    * Tests for full heap array, and resizes to twice the current capacity as 
    * required
    */
   private void checkForResize()
   {
      //check if size == capacity
      if( arraySize == arrayCapacity )
      {
         //double capacity
         arrayCapacity *= 2;
         //set resized array 
         StudentClass[] resizedArray = new StudentClass[ arrayCapacity ];
         //copy heapArray data into the resized array
         int index;
         for( index = 0; index < arraySize; index++ )
         {
            resizedArray[ index ] = heapArray[ index ];
         }
         //save resized array to heap array
         heapArray = resizedArray;
      }
   }

   /*
    * Tests for empty heap
    * 
    * @return boolean result of test
    */
   public boolean isEmpty()
   {
      return arraySize == 0;
   }

   /*
    * Removes StudentClass data item from top of max heap, thus being the 
    * student with the highest GPA
    * <p>
    * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data 
    * removal
    * 
    * @return StudentClass item removed
    */
   public StudentClass removeItem()
   {
      if( arraySize <= 0 )
      {
         return null;
      }
      //check if display flag is true
      if( displayFlag )
      {
         //print process
         System.out.println();
         System.out.print( "Removing process: " );
         System.out.println( heapArray[ 0 ].toString() );
      }
      //save the SC object to be removed ( index 0 )
      StudentClass removedItem = heapArray[ 0 ];
      //replace index - with last index
      heapArray[ 0 ] = heapArray[ arraySize - 1 ];
      //set last index to null
      heapArray[ arraySize - 1 ] = null;
      //decrement arraySize
      arraySize -= 1;
      //call trickle down
      trickleDownArrayHeap( 0 );
      //arraySize -= 1;
      //return removedItem
      return removedItem;
   }

   /*
    * Utility method to set the display flag for displaying internal operations 
    * of the heap bubble and trickle operations
    * 
    * @param setState - flag used to set the state to display, or not
    */
   public void setDisplayFlag( boolean setState )
   {
      displayFlag = setState;
   }

   /*
    * Dumps array to screen as is, no filtering or management
    */
   public void showArray()
   {
      //loop through heapArray to display data
      int index;
      for( index = 0; index < arraySize; index++ )
      {
         //display data at index
         System.out.print( heapArray[ index ].toString() );
         System.out.print( " " );
      }
      //print new line
      System.out.println();
   }

   /*
    * Recursive operation to reset data in the correct order for the max heap 
    * after data removal
    * 
    * @param currentIndex - index of current item being assessed, and moved 
    * down as required
    */
   private void trickleDownArrayHeap( int currentIndex )
   {
      // find the children's indices from the current one
      int leftChildIndex = (( currentIndex * 2 ) + 1 );
      int rightChildIndex = (( currentIndex * 2 ) + 2 );
      StudentClass temp = heapArray[ currentIndex ];
      // check for both child indices less than the array size
      if(  leftChildIndex < arraySize && rightChildIndex < arraySize )
      {
         // check for either of the children to be smaller than the parent
         if( heapArray[ leftChildIndex ].compareTo( heapArray[ rightChildIndex ]
               ) > 0 )
         {
            // left child is smaller, compare left to parent
            if( heapArray[ currentIndex ].compareTo( heapArray[ leftChildIndex ] 
                  ) < 0 )
            {
               //check if display flag is true
               if( displayFlag )
               {
                  //print process
                  System.out.println( "   - Trickle down: " );
                  System.out.print( "     - Swapping parent: " );  
                  System.out.print( heapArray[ currentIndex ].toString() );
                  System.out.print( " with left child: " );
                  System.out.println( heapArray[ leftChildIndex ].toString() );
               }
               // swap the parent with the smallest of the two children
               heapArray[ currentIndex ] = heapArray[ leftChildIndex ];
               heapArray[ leftChildIndex ] = temp;
               // call the method recursively with that child's index
               trickleDownArrayHeap( leftChildIndex );
            }
         }
         // otherwise right is smaller
         else
         {
            //compare right child to parent
            if( heapArray[ currentIndex ].compareTo( 
                  heapArray[ rightChildIndex ] ) < 0 )
            {
               //check if display flag is true
               if( displayFlag )
               {
                  //print process
                  System.out.println( "   - Trickle down: " );
                  System.out.print( "     - Swapping parent: " );  
                  System.out.print( heapArray[ currentIndex ].toString() );
                  System.out.print( " with right child: " );
                  System.out.println( heapArray[ rightChildIndex ].toString() );
               }
               // swap the parent with the smallest of the two children
               heapArray[ currentIndex ] = heapArray[ rightChildIndex ];
               heapArray[ rightChildIndex ] = temp;
               // call the method recursively with that child's index
               trickleDownArrayHeap( rightChildIndex );
            }
         }
      }
      // check for only the left child index less than the array size
      if( leftChildIndex < arraySize )
      {
         // check for the left child to be smaller than the parent
         if( heapArray[ currentIndex ].compareTo( heapArray[ leftChildIndex ] ) 
               < 0 )
         {
            //check if display flag is true
            if( displayFlag )
            {
               //print process
               System.out.println( "   - Trickle down: " );
               System.out.print( "     - Swapping parent: " );  
               System.out.print( heapArray[ currentIndex ].toString() );
               System.out.print( " with left child: " );
               System.out.println( heapArray[ leftChildIndex ].toString() );
            }
            // swap the left child's value with the parent's value
            heapArray[ currentIndex ] = heapArray[ leftChildIndex ];
            heapArray[ leftChildIndex ] = temp;
            // call the method recursively with the left child's index
            trickleDownArrayHeap( leftChildIndex );
         }
      }
   }
}
