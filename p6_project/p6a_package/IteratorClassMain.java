package p6a_package;

public class IteratorClassMain
   {

   public static void main(String[] args)
      {
       int index, value;
       p6_package.IteratorClass ic = new p6_package.IteratorClass();
       
       for( index = 0; index < 25; index++ )
          {
           value = (int)( index * 10 * Math.random() );
           ic.setToLastElement();
           
           ic.setBeforeCurrent( value );
           ic.runDiagnosticDisplay();
          }

       ic.movePrev(); ic.movePrev(); ic.movePrev();
       ic.movePrev(); ic.movePrev(); ic.movePrev();
       ic.movePrev(); ic.movePrev(); ic.movePrev();

       ic.runDiagnosticDisplay();

       ic.removeAtCurrent();
       ic.runDiagnosticDisplay();
       
       ic.setToFirstElement();
       ic.runDiagnosticDisplay();

       for( index = 0; index < 12; index++ )
          {
           ic.moveNext(); ic.moveNext(); ic.moveNext();
           ic.runDiagnosticDisplay();
          }

       ic.setToLastElement();
       ic.runDiagnosticDisplay();

       for( index = 0; index < 12; index++ )
          {
           ic.movePrev(); ic.movePrev(); ic.movePrev();
           ic.runDiagnosticDisplay();
          }
      
       System.out.println( "\nBackwards iteration starting at last element"
                                            + " and ending at first element:" );
       
       if( ic.setToLastElement() )
          {
           value = ic.getAtCurrent();
           
           System.out.print( "" + value );
           
           do
              {
               ic.movePrev();
               
               value = ic.getAtCurrent();
               
               System.out.print( ", " + value );
              }
           while( !ic.isAtFirstElement() );              
          }
       
       System.out.println( "\n" );
       
      }

   }
