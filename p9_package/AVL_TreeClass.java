package p9_package;
/*
 * @author Mason Gerace
 * 
 * Binary Search Tree (BST) class with self-balancing attributes specifically 
 * using the Adelson-Velsky and Landis (AVL) strategy
 */
public class AVL_TreeClass
{
   /*
    * Binary Search Tree node class for managing character data within an AVL 
    * Tree
    */
   private class Node extends Object
   {
      /*
       * Character data for Node class
       */
      private char data;

      /*
       * left child reference for tree
       */
      private Node leftChildRef;

      /*
       * right child reference for tree
       */
      private Node rightChildRef;

      /*
       * Initialization constructor for Node class
       * 
       * @param inData - char quantity
       */
      public Node( char inData )
      {
         data = inData;
         leftChildRef = null;
         rightChildRef = null;
      }

      /*
       * Initialization constructor for data and child references
       * 
       * @param inData - char quantity
       * 
       * @param leftRef - reference for left child
       * 
       * @param rightRef - reference for right child
       */
      public Node( char inData, Node leftRef, Node rightRef )
      {
         data = inData;
         leftChildRef = leftRef;
         rightChildRef = rightRef;
      }

      /*
       * Copy constructor for AVL tree node
       * 
       * @param copied - Node object to be copied
       */
      public Node( Node copied )
      {
         data = copied.data;
         leftChildRef= null;
         rightChildRef = null;
      }
   }

   /*
    * Constant used to represent dash
    */
   private static final char DASH = '-';

   /*
    * Null character returned if data not available
    */
   private final char NULL_CHAR = '\0';

   /*
    * Class global variable used to display tree structure
    */
   private boolean rowStartFlag;

   /*
    * Constant used to represent space
    */
   private static final char SPACE = ' ';

   /*
    * Root of AVL Tree
    */
   private Node treeRoot;

   /*
    * Default class constructor
    */
   public AVL_TreeClass()
   {
      rowStartFlag = false;
      treeRoot = null;
   }

   /*
    * Copy constructor
    * 
    * @param copied - AVL_TreeClass object to be copied
    */
   public AVL_TreeClass( AVL_TreeClass copied )
   {
      rowStartFlag = false;
      treeRoot = copyConstructorHelper( copied.treeRoot );
   }

   /*
    * clears tree
    */
   public void clearTree()
   {
      treeRoot = null;
   }

   /*
    * Recursive copy constructor helper
    * <p>
    * Note: Uses preorder strategy to copy nodes
    * 
    * @param wkgCopiedRef - Node reference at which method starts at each 
    * level of recursion
    * 
    * @return Node reference to link current node information to methods/Nodes 
    * calling this method
    */
   private Node copyConstructorHelper( Node wkgCopiedRef )
   {
      // set a local reference to null
      Node localRef = null;
      // check for copied reference not null
      if( wkgCopiedRef != null )
      {
         // create a new node to copy current node
         localRef = new Node( wkgCopiedRef );
         // call recursion to the left,
         // assigning result to the left child of the new node
         localRef.leftChildRef = 
               copyConstructorHelper( wkgCopiedRef.leftChildRef );
         // call recursion to the right,
         // assigning result to the right child of the new node
         localRef.rightChildRef = 
               copyConstructorHelper( wkgCopiedRef.rightChildRef );
      }
      // return the new local reference, either null or with node
      return localRef;
   }

   /*
    * Local recursive method to display a specified number of a specified 
    * character
    * 
    * @param numChars - number of characters to display
    * 
    * @param outChar - character to display
    */
   private void displayChars( int numChars, char outChar )
   {
      //check if there are chars to display
      if( numChars > 0 )
      {
         //display char and call recursion
         System.out.print( outChar );
         displayChars( numChars - 1, outChar);
      }
   }

   /*
    * provides tree height to user
    * <p>
    * Note: uses getTreeHeight
    * 
    * @return integer height of tree
    */
   public int findTreeHeight()
   {
      return getTreeHeight( treeRoot );
   }

   /*
    * gets balance factor indicating if tree is unbalanced from given root down
    * 
    * @param wkgLocalRef - Node from which balance factor is found
    * 
    * @return integer balance factor
    */
   private int getBalanceFactor( Node wkgLocalRef )
   {
      if( wkgLocalRef != null )
      {
         return getTreeHeight( wkgLocalRef.leftChildRef ) - 
               getTreeHeight( wkgLocalRef.rightChildRef );
      }
      return 0;
   }

   /*
    * Finds maximum of two given numbers
    * 
    * @param one - one of two values to be tested
    * 
    * @param other - other of two values to be tested
    * 
    * @return greater of the two values
    */
   private int getMax( int one, int other )
   {
      int maxVal = one;
      //check if other is greater than max
      if( other > maxVal )
      {
         //set max to other
         maxVal = other;
      }
      //otherwise return max
      return maxVal;
   }

   /*
    * Tree height helper method
    * 
    * @param wkgLocalRef - Node node from which height is found
    * 
    * @return integer height of tree
    */
   private int getTreeHeight( Node wkgLocalRef )
   {
      //check if wkgRef is null
      if( wkgLocalRef != null )
      {
         //save left and right heights as variables 
         int leftHeight = getTreeHeight( wkgLocalRef.leftChildRef );
         int rightHeight = getTreeHeight( wkgLocalRef.rightChildRef );
         //call recursion
         return 1 + getMax( leftHeight, rightHeight );
      }
      //otherwise return -1
      return -1;
   }

   /*
    * In order display of tree
    */
   public void inOrderDisplay()
   {
      inOrderDisplayHelper( treeRoot );
   }

   /*
    * Provides inOrder traversal action
    * 
    * @param wkgLocalRef - Node tree root reference at the current recursion 
    * level
    */
   private void inOrderDisplayHelper( Node wkgLocalRef )
   {
      // check for local root not null
      if( wkgLocalRef != null )
      {
         // call recursion to the left
         inOrderDisplayHelper( wkgLocalRef.leftChildRef );
         // display the current data
         System.out.print( wkgLocalRef.data );
         System.out.print( SPACE );
         // call recursion to the right
         inOrderDisplayHelper( wkgLocalRef.rightChildRef );
      }
   }

   /*
    * Insert method for AVL Tree
    * <p>
    * Note: uses insert helper method which returns the root node reference 
    * to this method
    * 
    * @param inData - char data to be added to AVL Tree
    */
   public void insert( char inData )
   {
      System.out.println("Inserting"+ SPACE + inData + SPACE + "and balancing");
      treeRoot = insertHelper( treeRoot, inData );
   }

   /*
    * Insert helper method for AVL Tree insert action
    * <p>
    * Note: Does not allow duplicate keys
    * 
    * @param wkgLocalRef - Node tree root reference at the current recursion 
    * level
    * 
    * @param inData - char item to be added to AVL Tree
    * 
    * @return Node reference to current AVL Tree root
    */
   private Node insertHelper( Node wkgLocalRef, char inData )
   {
      // check for current node null
      if( wkgLocalRef == null )
      {
         // return a new created node
         return wkgLocalRef = new Node( inData );
      }
      // otherwise, check to see if the new value is less than the 
      // current node data
      else if( inData < wkgLocalRef.data )
      {
         // make the recursive call to the left child
         // and assign it to the current node's left child
         wkgLocalRef.leftChildRef = 
               insertHelper( wkgLocalRef.leftChildRef, inData);
      }

      // otherwise, check to see if the new value is greater than the 
      // current node data
      else if( inData > wkgLocalRef.data )
      {
         // make the recursive call to the right child
         // and assign it to the current node's right child
         wkgLocalRef.rightChildRef = 
               insertHelper( wkgLocalRef.rightChildRef, inData);
      }
      // otherwise assume the value is already in the tree
      else
      {
         // update the current node with the new data
         wkgLocalRef.data = inData;
      }

      // find the balance factor and assign it to a variable
      int balancedTree = getBalanceFactor( wkgLocalRef );
      //identify the 4 special cases that will need to be addressed
      //left left case, BF greater than 1 and inData less than leftChild
      if( balancedTree > 1 && inData < wkgLocalRef.leftChildRef.data )
      {
         //identify and rotate right
         System.out.println("Identified: Left Left Case");
         System.out.println("    - Rotating Right");
         return rotateRight( wkgLocalRef );
      }
      //right right case, BF less than -1 and inData greater than rightChild
      else if( balancedTree < -1 && inData > wkgLocalRef.rightChildRef.data )
      {
         //identify and rotate left
         System.out.println("Identified: Right Right Case");
         System.out.println("    - Rotating Left");
         return rotateLeft( wkgLocalRef );
      }
      //left right case, BF greater than 1 and inData greater then leftChild
      else if( balancedTree > 1 && inData > wkgLocalRef.leftChildRef.data )
      {
         //identify and rotate the left child left
         System.out.println("Identified: Left Right Case");
         System.out.println("    - Rotating Left");
         wkgLocalRef.leftChildRef = rotateLeft( wkgLocalRef.leftChildRef );
         //then rotate wkgRef right
         System.out.println("    - Rotating Right");
         return rotateRight( wkgLocalRef );
      }
      //right left case, BF less than -1 and inData less than rightChild
      else if( balancedTree < -1 && inData < wkgLocalRef.rightChildRef.data )
      {
         //identify and rotate the right child right
         System.out.println("Identified: Right Left Case");
         System.out.println("    - Rotating Right");
         wkgLocalRef.rightChildRef = rotateRight( wkgLocalRef.rightChildRef );
         //then rotate the wkgRef left
         System.out.println("    - Rotating Left");
         return rotateLeft( wkgLocalRef );
      }
      //when it gets this far, return wkgRef
      return wkgLocalRef;
   }

   /*
    * Test for empty tree
    * 
    * @return Boolean result of test
    */
   public boolean isEmpty()
   {
      return treeRoot == null;
   }

   /*
    * Rotates local tree left or CCW
    * 
    * @param wkgLocalRef - reference of current item
    * 
    * @return Node resulting current root
    */
   private Node rotateLeft( Node wkgLocalRef )
   {
      //save wkgRef right child as well as temps left child as a ref
      //these nodes have the potential to be overwritten 
      Node temp = wkgLocalRef.rightChildRef;
      Node tempChild = temp.leftChildRef;
      //set temps left child to wkgLocalRef
      temp.leftChildRef = wkgLocalRef;
      //set wkgRefs right child to temps left child (saved ref)
      wkgLocalRef.rightChildRef= tempChild;
      //return temp
      return temp;
   }

   /*
    * Rotates local tree right or CW
    * 
    * @param wkgLocalRef - reference of current item
    * 
    * @return Node resulting current root
    */
   private Node rotateRight( Node wkgLocalRef )
   {
      //save wkgRef lefts child as well as temps right child as a ref
      //these nodes have the potential to be overwritten 
      Node temp = wkgLocalRef.leftChildRef;
      Node tempChild = temp.rightChildRef;
      //set temps left child to wkgLocalRef
      temp.rightChildRef = wkgLocalRef;
      //set wkgRefs left child to temps right child (saved ref)
      wkgLocalRef.leftChildRef= tempChild;
      //return temp
      return temp;
   }
   
   /*
    * Searches for data in AVL Tree given char with necessary key
    * 
    * @param searchData - char item containing key
    * 
    * @return char reference to found data
    */
   public char search( char searchData )
   {
      return searchHelper( treeRoot, searchData );
   }
   
   /*
    * Helper method for AVL Tree search action
    * 
    * @param wkgLocalRef - Node tree root reference at the current recursion 
    * level
    * 
    * @param searchData - char item containing key
    * 
    * @return char result of search
    */
   private char searchHelper( Node wkgLocalRef, char searchData )
   {
      //check if wkgRef is not null
      if( wkgLocalRef != null )
      {
         //check left side
         if( searchData < wkgLocalRef.data )
         {
            return searchHelper( wkgLocalRef.leftChildRef, searchData );
         }
         //check right side
         else if( searchData > wkgLocalRef.data )
         {
            return searchHelper( wkgLocalRef.rightChildRef, searchData );
         }
         //otherwise, return wkgref
         return wkgLocalRef.data;
      }
      //return null if not in AVL
      return NULL_CHAR;
   }

   /*
    * Local recursive method to calculate exponentiation with integers
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
         //recursively return base times itself
         return toPower( base, exponent - 1 ) * base;
      }
      //return 1 otherwise
      return 1;
   }
   
   /*
    * [NOT ASSIGNED] Displays text-graphical representation of one level/line 
    * of the AVL tree
    * 
    * @param workingNode - node reference at current recursive level
    * 
    * @param nodeHeight - height of tree plus two for current height of nodes, 
    * including lowermost null children
    * 
    * @param displayLevel - level of tree at which the current line of display 
    * is to be presented
    * 
    * @param workingLevel - current level during recursive actions
    */
   private void displayAtTreeLevel(AVL_TreeClass.Node workingNode, 
         int nodeHeight, int displayLevel, int workingLevel )
   {
      char charOut = workingNode.data;
      if( workingLevel == displayLevel )
      {
         displayValue( charOut, nodeHeight, workingLevel );
         return;
      }
      if( workingNode.leftChildRef != null )
      {
         displayAtTreeLevel( workingNode.leftChildRef, nodeHeight,
               displayLevel, workingLevel + 1 );
      }
      else
      {
         displayEmptyNodeSpaces( nodeHeight, displayLevel, workingLevel + 1 );
      }
      if( workingNode.rightChildRef != null )
      {
         displayAtTreeLevel( workingNode.rightChildRef, nodeHeight,
               displayLevel, workingLevel + 1 );
      }
      else
      {
         displayEmptyNodeSpaces( nodeHeight, displayLevel, workingLevel + 1 );
      }   
   }
   
   /*
    * [NOT ASSIGNED] Method that displays null or blank nodes for a tree at 
    * null locations
    * <p>
    * Note: used by displayAtTreeLevel
    * 
    * @param nodeHeight - height of tree plus two for current height of nodes, 
    * including lower most null children
    * 
    * @param displayLevel - level of the tree at which the display will be 
    * applied
    * 
    * @param workingLevel - level of tree just below non-null node at which 
    * method is currently working
    */
   private void displayEmptyNodeSpaces( int nodeHeight, int displayLevel, 
         int workingLevel )
   {
      int nodesToDisplay = toPower( 2, displayLevel - workingLevel ); 
      char charOut = SPACE;
      if( displayLevel == workingLevel )
      {
         charOut = DASH;
      }
      while( nodesToDisplay > 0 )
      {
         displayValue( charOut, nodeHeight, displayLevel );
         nodesToDisplay--;
      }
   }
   /*
    * [NOT ASSIGNED] Displays text-graphical representation of AVL tree
    */
   public void displayTreeStructure()
   {
      int displayLevel, nodeHeight = getTreeHeight( treeRoot ) + 2;
      int workingLevel = 1;
      if( treeRoot != null )
      {
         for( displayLevel = 1; displayLevel <= nodeHeight; displayLevel++ )
         {
            rowStartFlag = true;
            displayAtTreeLevel( treeRoot, nodeHeight, 
                  displayLevel, workingLevel );
            System.out.println();
         }
      }
      else
      {
         System.out.println( "\nEmpty Tree - No Display");
      }
   }

   /*
    * [NOT ASSIGNED] Method used to display a character or color letter along 
    * with calculated leading spaces
    * <p>
    * Note: used in displayAtTreeLevel and displayEmptyNodeSpaces
    * 
    * @param data - data value to display, either letter or color data
    * 
    * @param nodeHeight - height of tree plus two for current height of nodes, 
    * including lower most null children
    * 
    * @param workingLevel - current level during recursive actions
    */
   private void displayValue( char data, int nodeHeight, int workingLevel )
   {
      int leadingSpaces;
      if( rowStartFlag )
      {
         leadingSpaces = toPower( 2, nodeHeight - workingLevel );
         rowStartFlag = false;
      }
      else
      {
         leadingSpaces = toPower( 2, nodeHeight - workingLevel + 1 ) - 1;
      }
      displayChars( leadingSpaces, SPACE );
      System.out.print( data );  
   }
}
