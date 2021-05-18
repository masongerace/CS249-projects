package p1_package;

/**
 * Driver class to test SetClass operations
 * 
 * @author MichaelL
 *
 */
public class SetClassMain
   {
    /**
     * Main function of driver/test class
     * 
     * @param args String arguments not used
     */
    public static void main(String[] args)
       {
        	SetClass sc_1 = new SetClass();
        	SetClass sc_2 = new SetClass();
        	SetClass sc_3 = new SetClass();
        	SetClass sc_Union = new SetClass();
        	SetClass sc_Intersection = new SetClass();
        	SetClass sc_relComplement = new SetClass();
       
        	sc_1.loadItems( 5, 10, SetClass.PRIME, 2 );
        	sc_2.loadItems( 5, 15, SetClass.ODD, 0 );
        	sc_3.loadItems( 10, 3, SetClass.INCREMENTED, 10 );
        	sc_Union = sc_1.findUnion( sc_2 );
        	sc_Intersection = sc_1.findIntersection( sc_2 );
        	sc_relComplement = sc_1.findRelativeComplementOfThisSetIn( sc_2 );
       
        	System.out.println( "Set 1: " + sc_1.toString() );

        	System.out.println( "Set 2: " + sc_2.toString()  );
       
        	System.out.println( "Set 3: " + sc_3.toString()  );
       
        	System.out.println( "Union Sets 1/2: " + sc_Union.toString()  );     
       
        	System.out.println( "Intersection Sets 1/2: " 
                                + sc_Intersection.toString()  );       

        	System.out.println( "Relative complement Sets 1/2: " 
        						+ sc_relComplement.toString() );
       }

   }
