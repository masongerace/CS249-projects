package p1_package;

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
public class SetClass
{
   /**
    * constant with default array capacity
    */
   public static final int DEFAULT_ARRAY_CAPACITY = 10;

   /**
    * integer array for data
    */
   int[] setArray;

   /**
    * capacity of array
    */
   int arrayCapacity;

   /**
    * number of values in array
    */
   int arraySize;

   /**
    * constants for specifying set data
    */
   public static final int INCREMENTED = 101;
   public static final int ODD = 102;
   public static final int EVEN = 103;
   public static final int PRIME = 104;

   /**
    * Default constructor
    * <p>
    * Initializes set array but sets power set array to null
    */
   public SetClass()
      {
         //set arraySize equal to 0
         arraySize = 0;
         //set arrayCapacity to DEFAULT_ARRAY_CAPACITY
         arrayCapacity = DEFAULT_ARRAY_CAPACITY;
         //initialize setArray to the DEFAULT_ARRAY_CAPACITY
         setArray = new int[ DEFAULT_ARRAY_CAPACITY ];
      }

   /**
    * Initialization constructor
    * <p>
    * Allows specification of set array capacity
    * <p>
    * Initializes set array but sets power set array to null
    *
    * @param initialCapacity integer that specifies array capacity
    */
   public SetClass( int initialCapacity )
      {
         //set arraySize equal to zero
         arraySize = 0;
         //set arrayCapacity to initialCapacity
         arrayCapacity = initialCapacity;
         //initialize setArray to the initialCapacity
         setArray = new int [ initialCapacity ];
      }

   /**
    * Copy constructor
    * <p>
    * Duplicates copied set class
    * <p>
    * Also responsible for correct initialization/update
    * of power set array
    *
    * @param copied SetClass object to be copied
    */
   public SetClass( SetClass copied )
      {
         //create index variable
         int index;
         //set arraySize to copied arraySize
         this.arraySize = copied.arraySize;
         //set arrayCapacity to copied arrayCapacity
         this.arrayCapacity = copied.arrayCapacity;
         //initialize setArray to the copied arrayCapacity
         this.setArray = new int [ copied.arrayCapacity ];
         //for loop to go through the array
         for( index = 0; index < this.arraySize; index++ )
            {
               //set the setArray index to the copiedArray index
               this.setArray[ index ] = copied.setArray[ index ];
            }
      }

   /**
    * Adds integer to set
    * <p>
    * increases capacity using checkForResize if array is full
    * <p>
    * does not allow duplicate values in set
    *
    * @param item integer value to be added to set
    */
   public void addItem( int item )
      {
         //call checkForResize, check if array needs to be resize
         checkForResize();
         //check if array contains the item thats going to be added
         if( !hasElement( item ))
            {
               //if item is not in the array, add it
               this.setArray[ arraySize ] = item;
               //increase arraySize
               this.arraySize += 1;
            }
      }

   /**
    * Local function tests for resize of the set array
    * <p>
    * If array needs to be resized, array capacity is doubled;
    * otherwise, no change
    *
    * @return boolean report that resize was conducted
    */
   private boolean checkForResize()
      {
         //create index variable
         int index;
         //check if array is at the capacity
         if( this.arraySize == this.arrayCapacity )
            {
               //if array is at capacity, then double the array
               this.arrayCapacity *= 2;
               //set a new array to the doubled capacity
               int [] doubledArray = new int[ arrayCapacity ];
               //for loop to loop through the array
               for( index = 0; index < this.arraySize; index++ )
                  {
                     //set the doubledArray index to the value at setArrays index
                     doubledArray[ index ] = this.setArray[ index ];
                  }
               //set the setArray equal to the doubledArray
               this.setArray = doubledArray;
               //return true
               return true;
            }
         //return false
         return false;
      }

   /**
    * Returns the intersection of THIS set and the given other set
    *
    * @param other SetClass data with which intersection is found
    *
    * @return SetClass object with intersection of two sets
    */
   public SetClass findIntersection( SetClass other )
      {
         //create index variable
         int index;
         //create intersectionValue variable
         int intersectionValue;
         //create a setClass object..........
         SetClass intersectionSet = new SetClass();
         //for loop to go through the array
         for( index = 0; index < arraySize; index++ )
            {
               //set the intersectionValue to the setArray index value
               intersectionValue = this.setArray[ index ];
               //check if the other arrays contain the intersectionValue
               if( other.hasElement( intersectionValue ))
                  {
                     //add the intersectionValues to the intersectionSet
                     intersectionSet.addItem( intersectionValue );
                  }
            }
         //return the intersection values
         return intersectionSet;
      }

   /**
    * Returns the union of THIS set and the given other set
    *
    * @param other SetClass data with which union is found
    *
    * @return SetClass object with union of two sets
    */
   public SetClass findUnion( SetClass other )
      {
         //create index variable
         int index;
         //create union Value variable
         int unionValue;
         //create a unionSet using a new SetClass object
         SetClass unionSet = new SetClass(this);
         //for loop to loop through the array
         for( index = 0; index < other.arraySize; index++ )
            {
               //set the unionnValue to the setArray index value
               unionValue = other.setArray[ index ];
               //add the intersectionValues to the intersectionSet
               unionSet.addItem( unionValue ); 
            }
         //return the union values
         return unionSet;
      }

   /**
    * Finds relative complement of THIS set in given other set
    * <p>
    * Returns other set having removed any items intersecting
    * with THIS set
    *
    * @param other SetClass object from which THIS SetClass items
    * will be removed
    *
    * @return SetClass object with data as specified
    */
   public SetClass findRelativeComplementOfThisSetIn( SetClass other )
      {
         //create index variable
         int index;
         
         //for loop to go through the array
         for( index = 0; index < this.arraySize; index++ )
            {
               //check if the other array has the same value at the index
               if( other.hasElement( setArray[ index ] ))
                  {
                     //remove the value at the other index
                     other.removeValue( setArray[ index ] );
                  }
            }
         //return the other array
         return other;
      }
   
   /**
    * Seeks and finds prime starting at given value
    *
    * @param value integer value to start search for prime
    *
    * @return next prime number
    */
   private int getNextPrime( int value )
      {
         if( isEven( value ) )
         {
            value += 1;
         }
         else
         {
            //next value = + 2 if value is odd
            value += 2;
         }
         //return nextPrime
         return value;
      }

   /**
    * Tests to indicate if integer value is one
    * of the set elements
    *
    * @param testElement integer element to be found in set
    *
    * @return boolean result of test
    */
   public boolean hasElement( int testElement )
      {
         //create index variable
         int index;
         //for loop to go through the array
         for( index = 0; index < arraySize; index++ )
            {
               //check if the array index contains the test element
               if( this.setArray[ index ] == testElement )
                  {
                     //if test element is in the array, return true
                     return true;
                  }
            }
         //if test element is not in the array, return false
         return false;
      }

   /**
    * Tests for even, reports
    *
    * @param value integer value to be tested
    *
    * @return boolean result as specified
    */
   private boolean isEven( int value )
      {
         //check if value is even
         return ( value % 2 == 0 );
      }

   /**
    * Tests to indicate if set is subclass of another given set
    *
    * @param other SetClass object to be tested
    * if THIS set is a subset of it
    *
    * @return boolean result of test
    */
   public boolean isSubsetOf( SetClass other )
      {
         //create index variable
         int index;
         //initialize a counter variable
         int count = 0;
         //for loop to go through the array
         for( index = 0; index < other.arraySize; index++ )
            {
               //check if other array has the indexed element
               if( other.hasElement( index ))
                  {
                     //increase count
                     count++;
                  }
            }
         //return count == arraySize
         return (count == other.arraySize);
      }

   /**
    * Removes value at given index;
    * moves all succeeding data down in array
    *
    * @param indexToRemove integer index of element value to remove
    */
   private void removeAtIndex( int indexToRemove )
      {
         //create index variable
         int index;
         //decrease the array size
         this.arraySize --;
         //for loop to go through the array
         for( index = indexToRemove; index < this.arraySize; index++ )
            {
               //set the array value to be removed to the array value after it
               this.setArray[ index ] = this.setArray[ index + 1 ];
            }
      }

   /**
    * Removes value if it is found in set
    *
    * @param valToRemove integer value to be removed
    *
    * @return boolean result of operation success
    */
   public boolean removeValue( int valToRemove )
      {
         //create index variable
         int index;
         //for loop to go through the array
         if( hasElement( valToRemove ))
            {
               //for loop to go through the array
               for( index = 0; index < this.arraySize; index++ )
                  {
                     //check if the indexed value is the index to remove
                     if( this.setArray[ index ] == valToRemove )
                        {
                         //call removeAtIndex to remove the value
                           removeAtIndex( index );
                           //return true
                           return true;
                        }
                  }
            }
         //return false
         return false;
      }

   ////////////////////////////////////////////////////////////////////////////////
   // DO NOT MODIFY CODE BELOW THIS LINE (but you may use the methods)
   ////////////////////////////////////////////////////////////////////////////////
   /**
    * Tests to indicate if given integer value is prime
    *
    * @param testVal integer value given
    *
    * @return boolean result of test
    */
   @SuppressWarnings("unused")
   private boolean isPrime( int testVal )
      {
         int modVal = 2;
         int testDivide = (int)( Math.ceil( Math.sqrt( (double)testVal ) ) );

         while( modVal <= testDivide )
            {
               if( testVal % modVal == 0 )
                  {
                     return false;
                  }

               modVal++;
            }

         return true;
      }

   /**
    * Loads a number of specified integers to set
    * <p>
    * Characteristics may be odd, even, incremented, or prime
    * <p>
    * Parameter four is only used with INCREMENTED
    *
    * @param startValue integer value indicates starting value
    *
    * @param numItems integer number of items to load
    *
    * @param valueCharacteristic integer characteristic code
    * (ODD, EVEN, INCREMENTED, PRIME )
    *
    * @param incrementBy integer value used to specify increment
    * if INCREMENTED characteristic is set
    */
   public void loadItems( int startValue, int numItems,
         int valueCharacteristic, int incrementBy )
      {
         int index, runningValue = startValue;

         if( valueCharacteristic == ODD || valueCharacteristic == EVEN )
            {
               if( valueCharacteristic == ODD && isEven( runningValue )
                     || valueCharacteristic == EVEN && !isEven( runningValue ) )
                  {
                     runningValue++;
                  }

               for( index = 0; index < numItems; index++ )
                  {
                     addItem( runningValue );

                     runningValue += 2;
                  }
            }

         else if( valueCharacteristic == PRIME )
            {
               // decrement by one for the first iteration
               runningValue--;

               for( index = 0; index < numItems; index++ )
                  {
                     runningValue = getNextPrime( runningValue + 1 );

                     addItem( runningValue );
                  }
            }

         else  // assume incremented
            {
               for( index = 0; index < numItems; index++ )
                  {
                     addItem( runningValue );

                     runningValue += incrementBy;
                  }
            }
      }

   /**
    * Provides list of set array elements
    * as comma-delimited string
    */
   @Override
   public String toString()
      {
         int index;
         String outString = "";

         for( index = 0; index < arraySize; index++ )
            {
               if( index > 0 )
                  {
                     outString += ", ";
                  }

               outString += setArray[ index ];
            }

         return outString;
      }
}