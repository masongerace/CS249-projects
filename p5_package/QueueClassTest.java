package p5_package;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QueueClassTest
{
 int numItems = 21;
 int[] testArray = { 89, 97, 58, 74, 34, 76, 31, 83, 70, 61,
                     25, 58, 98, 53, 69, 94, 73, 65, 47, 36, 15 };
 
@Test
public void enqueueTest()
   {
    int index;
    SimpleQueueClass sqc = new SimpleQueueClass();
    
    for( index = 0; index < numItems; index++ )
       {
        sqc.enqueue( testArray[ index ] );
       }
    assertEquals( sqc.peekFront(), 89 );
   }

@Test
public void dequeueTest()
   {
    int index;
    SimpleQueueClass sqc = new SimpleQueueClass();
    
    for( index = 0; index < numItems; index++ )
       {
        sqc.enqueue( testArray[ index ] );
       }
    
    assertEquals( sqc.peekFront(), 89 );
    
    for( index = 0; index < 5; index++ )
       {
        sqc.dequeue();
       }
    
    assertEquals( sqc.peekFront(), 76 );
   }

@Test
public void clearTest()
   {
    int index;
    SimpleQueueClass sqc = new SimpleQueueClass();
    
    for( index = 0; index < numItems; index++ )
       {
        sqc.enqueue( testArray[ index ] );
       }
    
    assertEquals( sqc.peekFront(), 89 ); //89
    
    for( index = 0; index < 5; index++ )
       {
        sqc.dequeue();
       }
    
    assertEquals( sqc.peekFront(), 76 ); //76
    
    sqc.clear();
    
    assertEquals( sqc.peekFront(), -999999 );
   }

@Test
public void reEnqueueTest()
   {
    int index;
    SimpleQueueClass sqc = new SimpleQueueClass();
    
    for( index = 0; index < numItems; index++ )
       {
        sqc.enqueue( testArray[ index ] );
       }
    
    assertEquals( sqc.peekFront(), 89 );
    
    for( index = 0; index < 5; index++ )
       {
        sqc.dequeue();
       }
    
    assertEquals( sqc.peekFront(), 76 );
    
    sqc.clear();
    
    assertEquals( sqc.peekFront(), -999999 );
    
    for( index = 0; index < 15; index++ )
       {
        sqc.enqueue( testArray[ index ] );
       }
    
    for( index = 0; index < 7; index++ )
       {
        sqc.dequeue();
       }
    
    assertEquals( sqc.peekFront(), 83 );
   }

 @Test
 public void copyTest()
   {
    int index, value;
    SimpleQueueClass sqc = new SimpleQueueClass();
   
    System.out.print( "Original queue: " );
    for( index = 0; index < numItems; index++ )
       {
        if( index > 0 )
           {
            System.out.print( ", ");
           }
        
        System.out.print( testArray[ index ] );
        
        sqc.enqueue( testArray[ index ] );
       }
   
    System.out.println();
    
    SimpleQueueClass copiedSqc = new SimpleQueueClass( sqc );

    System.out.print( "Copied queue: " );

    for( index = 0; index < numItems; index++ )
       {
        value = copiedSqc.dequeue();
        
        if( index > 0 )
           {
            System.out.print( ", " );
           }
        
        System.out.print( value );

       }
       
    System.out.println();
   }
}


