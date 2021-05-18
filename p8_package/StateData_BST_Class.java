package p8_package;
/*
 * @author Mason Gerace
 * 
 * Binary Search Tree (BST) class for managing StateDataClass data
 * 
 * (BST nodes do not go in the tree the same way every time, they go in to the 
 * nodes depending on the way they went in)
 */
public class StateData_BST_Class
{
   /*
    * Display counter
    */
   private int displayCounter;

   /*
    * Traverse code -in order
    */
   public static final int IN_TRAVERSE = 102;

   /*
    * Traverse code -post order
    */
   public static final int POST_TRAVERSE = 103;

   /*
    * Traverse code -pre order
    */
   public static final int PRE_TRAVERSE = 101;

   /*
    * Root of tree
    */
   private StateDataClass rootNode;

   /*
    * Default class constructor, initializes BST
    */
   public StateData_BST_Class()
   {
      rootNode = null;
   }

   /*
    * Copy constructor
    * 
    * @param copied - StateData_BST_Class reference to copied object 
    */
   public StateData_BST_Class( StateData_BST_Class copied )
   { 
      rootNode = copyConstructorHelper( copied.rootNode );
   }

   /*
    * Clears tree
    */
   public void clearTree()
   {
      rootNode = null;
   }

   /*
    * Compares strings
    * <p>
    * If first string parameter is greater than the second, method returns 
    * positive value; if first string parameter is less than second, return 
    * negative value; if first string parameter and second string parameter 
    * are equal, returns zero
    * 
    * @param one - first of two strings to compare
    * 
    * @param other - second of two string to compare
    * 
    * @return integer value as specified
    */
   public int compareStrings( String one, String other )
   {
      int difference, index = 0;
      while( index < one.length() && index < other.length() )
      {
         difference = ( one.charAt( index ) ) - ( other.charAt( index ) );
         if( difference != 0 )
         {
            return difference;
         }
         index++;
      }
      return one.length() - other.length();
   }

   /*
    * Copy constructor helper
    * <p>
    * Recursively copies nodes using pre order traversal
    * 
    * @param wkgRef - current working StudentDataClass reference in recursion
    * 
    * @return StateDataClass reference to link to StateDataClass node above
    */
   private StateDataClass copyConstructorHelper( StateDataClass wkgRef )
   {
      // set a local reference to null
      StateDataClass localRef = null;
      // check for copied reference not null
      if( wkgRef != null )
      {
         // create a new node to copy current node
         localRef = new StateDataClass( wkgRef );
         // call recursion to the left,
         // assigning result to the left child of the new node
         localRef.leftChildRef = copyConstructorHelper( wkgRef.leftChildRef );
         // call recursion to the right,
         // assigning result to the right child of the new node
         localRef.rightChildRef = 
               copyConstructorHelper( wkgRef.rightChildRef );
      }
      // return the new local reference, either null or with node
      return localRef;
   }

   /*
    * Provides inOrder traversal action using recursion
    * 
    * @param wkgRef - StateDataClass tree root reference at the current 
    * recursion level
    */
   private void displayInOrder( StateDataClass wkgRef )
   {
      // check for local root not null
      if( wkgRef != null )
      {
         // call recursion to the left
         displayInOrder( wkgRef.leftChildRef );
         // display the current data
         System.out.format( "%2d" + ":" + " " + wkgRef, displayCounter );
         System.out.println();
         displayCounter += 1;
         // call recursion to the right
         displayInOrder( wkgRef.rightChildRef );
      }
   }

   /*
    * Provides postOrder traversal action using recursion
    * 
    * @param wkgRef - StateDataClass tree root reference at the current 
    * recursion level
    */
   private void displayPostOrder( StateDataClass wkgRef )
   {
      // check for local root not null
      if( wkgRef != null )
      {
         // call recursion to the left
         displayPostOrder( wkgRef.leftChildRef );
         // call recursion to the right
         displayPostOrder( wkgRef.rightChildRef );
         // display the current data
         System.out.format( "%2d" + ":" + " " + wkgRef, displayCounter );
         System.out.println();
         displayCounter += 1;
      }
   }

   /*
    * Provides preOrder traversal action using recursion
    * 
    * @param wkgRef - StateDataClass tree root reference at the current 
    * recursion level
    */
   private void displayPreOrder( StateDataClass wkgRef )
   {
      // check for local root not null
      if( wkgRef != null )
      {
         // display the current data
         System.out.format( "%2d" + ":" + " " + wkgRef, displayCounter );
         System.out.println();
         displayCounter += 1;
         // call recursion to the left
         displayPreOrder( wkgRef.leftChildRef );
         // call recursion to the right
         displayPreOrder( wkgRef.rightChildRef );
      }
   }

   /*
    * Provides user with three ways to display BST data
    * 
    * @param traverseCode - integer code for selecting BST traversal method, 
    * accepts PRE_TRAVERSE, IN_TRAVERSE, POST_TRAVERSE
    */
   public void displayTree( int traverseCode )
   {
      displayCounter = 1;
      //in order traverse
      if( traverseCode == IN_TRAVERSE )
      {
         displayInOrder( rootNode );
      }
      //post order traverse
      else if( traverseCode == POST_TRAVERSE )
      {
         displayPostOrder( rootNode );
      }
      //pre order traverse
      else if( traverseCode == PRE_TRAVERSE )
      {
         displayPreOrder( rootNode );
      }
   }

   /*
    * Insert method for BST
    * <p>
    * Note: calls the insert helper to implement all the data insertions
    * 
    * @param inData - StateDataClas data to be added to BST
    */
   public void insert( StateDataClass inData )
   {
      rootNode = insertHelper( rootNode, inData );
   }

   /*
    * Recursive insert helper method for BST insert action
    * <p>
    * Adds new StateDataClass node to left or right of the current tree; 
    * if node is already in tree, node is updated with the incoming data
    * 
    * @param wkgRef - current working reference at the current recursion level
    * 
    * @param inData - StateDataClass item to be added to BST
    * 
    * @return StateDataClass reference to current node insertion operation
    */
   private StateDataClass insertHelper( StateDataClass wkgRef, 
         StateDataClass inData )
   {
      //check if the current node is not null
      if( wkgRef != null )
      {
         //check if new data is less than the current node data
         if( compareStrings( inData.state, wkgRef.state ) < 0 )
         {
            //recurse to the left
            wkgRef.leftChildRef = insertHelper( wkgRef.leftChildRef, inData);
         }
         //otherwise assume the data is to the right
         else if( compareStrings( inData.state, wkgRef.state ) > 0 )
         {
            //recurse to the right
            wkgRef.rightChildRef = insertHelper( wkgRef.rightChildRef, inData);
         }
      }
      //otherwise list is empty, create a new node to insert
      else
      {
         wkgRef = new StateDataClass( inData );
      }
      //return the local reference to the calling method
      return wkgRef;
   }

   /*
    * Test for empty tree
    * 
    * @return Boolean result of test
    */
   public boolean isEmpty()
   {
      return rootNode == null;
   }

   /*
    * Searches tree from given working reference to minimum value found below 
    * it in farthest left node, stores data value found, unlinks the found 
    * node, links the parent node's left node to the child's right node, 
    * and returns the child/found node to the calling method
    * 
    * @param parentRef - reference to current StateDataClass node
    * 
    * @param childRef - reference to child StateDataClass node to be tested 
    * and/or removed
    * 
    * @return StateDataClass reference containing removed StateDataClass node
    */
   private StateDataClass removeFromMin( StateDataClass parentRef,
         StateDataClass childRef )
   {
      //check if there are more left side values (another min)
      if( childRef.leftChildRef != null )
      {
         //recurse to the left
         return removeFromMin( childRef, childRef.leftChildRef );
      }
      //otherwise, unlink left side
      parentRef.leftChildRef = childRef.rightChildRef;
      return childRef;
   }

   /*
    * Removes data StateDataClass node from tree using given key
    * <p>
    * Note: uses remove helper method
    * <p>
    * Note: uses search initially to get value, if it is in tree; if value 
    * found, remove helper method is called, otherwise returns null
    * 
    * @param outData - StateDataClass node that includes the necessary key
    * 
    * @return StateDataClass result of remove action
    */
   public StateDataClass removeItem( StateDataClass outData )
   {
      //call search, get outData
      StateDataClass temp = search( outData );
      //check if search result is not null
      if( temp != null )
      {
         //assign the result ref to a new node, using the result object
         temp = new StateDataClass( temp );
         //call remove helper, assign to root node
         rootNode = removeItemHelper( rootNode, outData );
      }
      //return result
      return temp;
   }

   /*
    * Removes data StateDataClass node from tree using given key
    * <p>
    * Note: uses remove helper method
    * <p>
    * Note: uses search initially to get value, if it is in tree; if value 
    * found, remove helper method is called, otherwise returns null
    * 
    * @param wkgRef - StateDataClass node reference at the current recursion 
    * level
    * 
    * @param outData - StateDataClass item that includes the necessary key
    * 
    * @return StateDataClass reference result of remove helper action
    */
   private StateDataClass removeItemHelper( StateDataClass wkgRef,
         StateDataClass outData )
   {
      //1. searching for the node
      //check for the current node reference to be null, if needed
      if( wkgRef != null )
      {
         //check if outData less than current node, if so recurse left
         if( compareStrings( outData.state, wkgRef.state ) < 0 )
         {
            wkgRef.leftChildRef = removeItemHelper( wkgRef.leftChildRef, 
                  outData );
         }
         //check if outData greater than current node, if so recurse right
         else if( compareStrings( outData.state, wkgRef.state ) > 0 )
         {
            wkgRef.rightChildRef = removeItemHelper( wkgRef.rightChildRef, 
                  outData );
         }

         //2.eliminating easy child conditions (1 child)
         //check if current nodes left child is null, if so return the 
         //right sides ref
         else if( wkgRef.leftChildRef == null )
         {
            return wkgRef.rightChildRef;
         }
         //check if current nodes right child is null, if so return the 
         //left sides ref
         else if( wkgRef.rightChildRef == null )
         {
            return wkgRef.leftChildRef;
         }

         //3. removing node with two kids
         //if the right child does not have a left child
         else if( wkgRef.rightChildRef.leftChildRef == null )
         {
            //copy the right childs data into the current node and
            //link around
            wkgRef.setData( wkgRef.rightChildRef );
            wkgRef.rightChildRef = wkgRef.rightChildRef.rightChildRef;
         }

         //otherwise, assume left child has a right child
         else
         {
            //find the minimum node (recursively down the tree)
            //assign min node to a temp
            StateDataClass temp = removeFromMin( wkgRef, wkgRef.leftChildRef );
            //assign the temp data to the current node
            wkgRef.setData( temp );
         }
      }
      //return wkgRef
      return wkgRef;
   }

   /*
    * Searches for data in BST given StateDataClass reference with necessary key
    * 
    * @param searchData - StateDataClass reference containing key
    * 
    * @return StateDataClass reference to found data, or null if not found
    */
   public StateDataClass search( StateDataClass searchData )
   {
      return searchHelper( rootNode, searchData );
   }

   /*
    * Helper method for recursive BST search action
    * 
    * @param wkgRef - StateDataClass tree node reference at the current 
    * recursion level
    * 
    * @param searchData - StateDataClass reference containing key
    * 
    * @return StateDataClass item found, or null if not found
    */
   private StateDataClass searchHelper( StateDataClass wkgRef,
         StateDataClass searchData )
   {
      //check if wkgRef is not null
      if( wkgRef != null )
      {
         //check left side
         if( compareStrings( searchData.state, wkgRef.state ) < 0 )
         {
            return searchHelper( wkgRef.leftChildRef, searchData );
         }
         //check right side
         else if( compareStrings( searchData.state, wkgRef.state ) > 0 )
         {
            return searchHelper( wkgRef.rightChildRef, searchData );
         }
         //otherwise, we found it and wkgRef == searchData
         return wkgRef;
      }
      //return null if not in BST
      return null;
   }
}
