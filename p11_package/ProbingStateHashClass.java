package p11_package;
/*
 * @author Mason Gerace
 * 
 * Simple hash class that uses linear and quadratic probing
 */
public class ProbingStateHashClass
{
   /*
    * Table size default
    */
   private final int DEFAULT_TABLE_SIZE = 11;

   /*
    * Constant for returning item not found with search
    */
   public final int ITEM_NOT_FOUND = -1;

   /*
    * Constant for setting linear probing
    */
   public static final int LINEAR_PROBING = 101;

   /*
    * Flag for setting linear or quadratic probing
    */
   private int probeFlag;

   /*
    * Constant for setting quadratic probing
    */
   public static final int QUADRATIC_PROBING = 102;

   /*
    * Array for hash table
    */
   private StateDataClass[] tableArray;

   /*
    * Size of the array table
    */
   private int tableSize;

   /*
    * Default constructor
    * <p>
    * Initializes to default table size with probe flag set to linear probing
    */
   public ProbingStateHashClass()
   {
      //initialize table size 
      tableSize = DEFAULT_TABLE_SIZE;
      //set probeFlag
      probeFlag = LINEAR_PROBING;
      //initialize tableArray 
      tableArray = new StateDataClass[ tableSize ];
   }

   /*
    * Initialization constructor
    * <p>
    * Initializes to default table size with probe flag set to probe flag 
    * parameter
    * 
    * @param inProbeFlag - sets linear or quadratic probing
    */
   public ProbingStateHashClass( int inProbeFlag )
   {
      //initialize table size
      tableSize = DEFAULT_TABLE_SIZE;
      //set probeFlag
      probeFlag = inProbeFlag;
      //initialize tableArray to table size
      tableArray = new StateDataClass[ tableSize ];
   }

   /*
    * Initialization constructor
    * 
    * @param inTableSize - sets table size (capacity) but does not allow table 
    * size to be less than default capacity
    * 
    * @param inProbeFlag - sets linear or quadratic probing
    */
   public ProbingStateHashClass( int inTableSize, int inProbeFlag )
   {
      //check that inTableSize is greater than Default table size
      if( inTableSize < DEFAULT_TABLE_SIZE )
      {
         inTableSize = DEFAULT_TABLE_SIZE;
      }
      //initialize table size
      tableSize = inTableSize;
      //set probeFlag
      probeFlag = inProbeFlag;
      //initialize tableArray
      tableArray = new StateDataClass[ tableSize ];
   }

   /*
    * Copy constructor
    * 
    * copied - ProbingHashClass object to be copied
    */
   public ProbingStateHashClass( ProbingStateHashClass copied )
   {
      //set table size
      tableSize = copied.tableSize;
      //set probeFlag
      probeFlag = copied.probeFlag;
      //initialize tableArray
      tableArray = new StateDataClass[ tableSize ];
      //copy table array
      int index;
      for( index = 0; index < tableSize; index++ )
      {
         tableArray[ index ] = new StateDataClass( copied.tableArray[ index ] );
      }
   }

   /*
    * Adds StateDataClass data item to hash table
    * <p>
    * Note: Uses hash index value from generateHash
    * <p>
    * Note: Shows probed index with data at the point of insertion
    * <p>
    * Note: Probe attempts are limited to the current size (capacity) of the 
    * table
    * 
    * @param newItem - StateDataClass object
    * 
    * @return Boolean success of operation
    */
   public boolean addItem( StateDataClass newItem )
   {
      //needed variables
      int hashIndex = generateHash( newItem );
      int index = hashIndex;
      int probeIndex = 1;
      int exponentValue = 2;
      //print statement
      System.out.print("Indices probed: " + index );
      //loop while index < tableSize
      while( index < tableSize )
      {
         //check if index is null
         if( tableArray[ index ] == null )
         {
            //insert item
            tableArray[ index ] = newItem;
            System.out.println( "\n" + tableArray[ index ].toString() + ", " 
                  + hashIndex + " -> " + index );
            return true;
         }
         //check if probeflag = linear
         if( probeFlag == LINEAR_PROBING )
         {
            //generate new index
            index += 1;
            System.out.print( ", " + index );
         }
         //check if probeflag = linear
         if( probeFlag == QUADRATIC_PROBING )
         {
            //generate new index
            index = hashIndex + toPower( probeIndex, exponentValue );
            index = index % tableSize ;
            System.out.print( ", " + index );
         }
         //increase probeIndex 
         probeIndex += 1;
      }
      //otherwise return false
      return false;
   }

   /*
    * public void clearHashTable()
    */
   public void clearHashTable()
   {
      //set each element in tableArray to null
      int index;
      for( index = 0; index < tableSize; index++ )
      {
         tableArray[ index ] = null;
      }
   }

   /*
    * Returns item found
    * 
    * @param searchItem - StateDataClass value to be found; uses findItemIndex
    * 
    * @return StateDataClass item found, or null if not found
    */
   public StateDataClass findItem( StateDataClass searchItem )
   {
      //find index of search item
      int itemIndex = findItemIndex( searchItem );
      //check if item was found
      if( itemIndex != ITEM_NOT_FOUND )
      {
         //return item at index
         return tableArray[ itemIndex ];
      }
      //otherwise return null if not found
      return null;
   }

   /*
    * Searches for item index in hash table
    * <p>
    * Note: Uses linear or quadratic probing as configured
    * <p>
    * Note: probing attempts limited to table size (capacity)
    * <p>
    * Note: Probed indices are reported to screen
    * 
    * @param searchItem - StateDataClass object to be found
    * 
    * @return integer index location of search item
    */ 
   private int findItemIndex( StateDataClass searchItem )
   {
      //needed variables
      int hashIndex = generateHash( searchItem );
      int index = hashIndex;
      int probeIndex = 1;
      int exponentValue = 2;
      //print statement
      System.out.print("Indices probed: " + index );
      //loop while index < tableSize
      while( index < tableSize )
      {
         //check if state at index = searchItem
         if( tableArray[ index ].compareTo( searchItem ) == 0 )
         {
            //return index
            System.out.println();
            return index;
         }
         //check if probeflag = linear
         if( probeFlag == LINEAR_PROBING )
         {
            //generate new index
            index += 1;
            System.out.print( ", " + index );
         }
         //check if probeflag = linear
         if( probeFlag == QUADRATIC_PROBING )
         {
            //generate new index
            index = hashIndex + toPower( probeIndex, exponentValue );
            index = index % tableSize ;
            System.out.print( ", " + index );
         }
         //increase probeIndex
         probeIndex += 1;
      }
      //otherwise item not found
      return ITEM_NOT_FOUND;
   }

   /*
    * Method converts StateDataClass hash value to index for use in hash table
    * <p>
    * Sums the Unicode/ASCII values of all letters in the state name; 
    * then finds index
    * <p>
    * Dependencies: .charAt, .length
    * 
    * @param item - StateDataClass object to be converted to hash value
    * 
    * @return integer hash value
    */
   public int generateHash( StateDataClass item )
   {
      //needed variables
      int index;
      String stateName = item.state;
      int generatedHash = 0;
      //loop through each character and hash it 
      for( index = 0; index < stateName.length(); index++ )
      {
         //add each characters hash to a sum
         generatedHash += ( stateName.charAt( index ));
      }
      //return generatedHash
      return (generatedHash % tableSize);
   }

   /*
    * Removes item from hash table
    * 
    * @param toBeRemoved - StateDataClass object used for requesting data uses 
    * findItemIndex
    * 
    * @return StateDataClass object removed, or null if not found
    */
   public StateDataClass removeItem( StateDataClass toBeRemoved )
   {
      //find index of toBeRemoved
      int removedItemIndex = findItemIndex( toBeRemoved );
      //check if item was found
      if( removedItemIndex != ITEM_NOT_FOUND )
      {
         //save item to be removed
         StateDataClass removedItem = tableArray[ removedItemIndex ];
         //set index removed to null
         tableArray[ removedItemIndex ] = null;
         //return removed item
         return removedItem;
      }
      //otherwise return null if not found
      return null;
   }

   /*
    * traverses through all array bins, finds min and max number of contiguous 
    * elements, and number of empty nodes; also shows table loading
    * <p>
    * NOTE: Generates string of used and unused bins in addition to displaying 
    * results on screen
    * 
    * @return String result of hash table analysis
    */
   public String showHashTableStatus()
   {
      //needed variables 
      int maxBin = 0, totalMaxBin = 0, minBin = 0, totalMinBin = tableSize, 
            emptyBin = 0, index;
      String hashStatus = "", hashOutput = "", arrayDump = "";
      for( index = 0; index < tableSize; index++ )
      {
         //index is not null
         if( tableArray[ index ] != null )
         {
            //add D to status, index to dump
            arrayDump += tableArray[ index ].toString() + "\n";
            hashStatus += 'D';
            maxBin += 1;
            minBin += 1;
         }
         //check if index is null
         if( tableArray[ index ] == null )
         {
            //add N to status, null to dump
            arrayDump += "null\n";
            hashStatus += "N";
            //increment empty bin count
            emptyBin += 1;

            //check if current max bin is larger than total max bin
            if( maxBin >= totalMaxBin )
            {
               totalMaxBin = maxBin;
            }
            //check if current min bin is less than total min bin
            if( minBin < totalMinBin && minBin > 0 )
            {
               totalMinBin = minBin;
            }
            //reset max and min bin count
            maxBin = 0;
            minBin = 0;
         }
      }
      //display
      System.out.print( "\nHash Table Status: " + hashStatus + "\n\n" );
      System.out.print( "     Minimum contiguous bins: " + totalMinBin + "\n" );
      System.out.print( "     Maximum contiguous bins: " + totalMaxBin + "\n" );
      System.out.print( "        Number of empty bins: " + emptyBin + "\n\n" );
      System.out.print( "Array Dump:\n" + arrayDump + "" );
      //hashOutput += "\nHash Table Status: " + hashStatus + "\n\n" ;
      //hashOutput += "     Minimum contiguous bins: " + totalMinBin + "\n";
      //hashOutput += "     Maximum contiguous bins: " + totalMaxBin + "\n";
      //hashOutput += "        Number of empty bins: " + emptyBin + "\n\n";
      //hashOutput += "Array Dump:\n" + arrayDump + "";
      System.out.print( hashOutput );
      return hashStatus;
   }

   /*
    * Local recursive method to calculate exponentiation with positive integers
    * 
    * @param base - base of exponentiation
    * 
    * @param exponent - exponent of exponentiation
    * 
    * @return result of exponentiation calculation
    */
   private int toPower( int base, int exponent )
   {
      //check if exponent is greater than 0
      if( exponent > 0 )
      {
         //return recursive call * base
         return base * toPower( base, exponent - 1);
      }
      //otherwise return 1
      return 1;
   }
}
