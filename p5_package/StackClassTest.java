package p5_package;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StackClassTest
{
 int numItems = 21;
 int[] testArray = { 89, 97, 58, 74, 34, 76, 31, 83, 70, 61,
                     25, 58, 98, 53, 69, 94, 73, 65, 47, 36, 15 };

 @Test
 public void pushTest()
    {
     int index;
     SimpleStackClass stc = new SimpleStackClass();
   
     
        
     for( index = 0; index < numItems; index++ )
        {
         stc.push( testArray[ index ] );
        }
    
     assertEquals( stc.peekTop(), 15 );
     System.out.print( "Original queue: " );
     for( index = 0; index < numItems; index++ )
        {
         if( index > 0 )
            {
             System.out.print( ", ");
            }
         System.out.print( testArray[ index ] );
        }
    }
 

 @Test
 public void popTest()
    {
     int index;
     SimpleStackClass sqc = new SimpleStackClass();
   
     for( index = 0; index < numItems; index++ )
        {
         sqc.push( testArray[ index ] );
        }
   
     assertEquals( sqc.peekTop(), 15 );
   
     for( index = 0; index < 5; index++ )
        {
         sqc.pop();
        }
   
     assertEquals( sqc.peekTop(), 94 );
    }

@Test
public void clearTest()
  {
   int index;
   SimpleStackClass sqc = new SimpleStackClass();
   
   for( index = 0; index < numItems; index++ )
      {
       sqc.push( testArray[ index ] );
      }
   
   assertEquals( sqc.peekTop(), 15 );
   
   for( index = 0; index < 5; index++ )
      {
       sqc.pop();
      }
   
   assertEquals( sqc.peekTop(), 94 );
   
   sqc.clear();
   
   assertEquals( sqc.peekTop(), -999999 );
  }

@Test
public void reLoadTest()
  {
   int index;
   SimpleStackClass sqc = new SimpleStackClass();
   
   for( index = 0; index < numItems; index++ )
      {
       sqc.push( testArray[ index ] );
      }
   
   assertEquals( sqc.peekTop(), 15 );
   
   for( index = 0; index < 5; index++ )
      {
       sqc.pop();
      }
   
   assertEquals( sqc.peekTop(), 94 );
   
   sqc.clear();
   
   assertEquals( sqc.peekTop(), -999999 );
   
   for( index = 0; index < 15; index++ )
      {
       sqc.push( testArray[ index ] );
      }
   
   assertEquals( sqc.peekTop(), 69 );

   for( index = 0; index < 7; index++ )
      {
       sqc.pop();
      }
   
   assertEquals( sqc.peekTop(), 83 );
  }

}
