package p4_package;

/**
 * Driver class for recursive backtracking search solution
 * 
 * @author MichaelL
 *
 */
public class MatrixAnalysisClassMain
{
   /**
    * Driver main method
    * 
    * @param args String command line arguments, not used
    */
   public static void main(String[] args)
   {
      boolean showOperations = true;

      MatrixAnalysisClass mac = new MatrixAnalysisClass( showOperations );

      mac.uploadData( "DataSet_0" );

      mac.dumpArray();

      // data sets 1-5 work with 1139, 1000, & 711
      mac.findSum( 896 );
   }
}
