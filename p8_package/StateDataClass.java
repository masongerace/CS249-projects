package p8_package;

/**
 * Description: class manages data for a state, its initials, 
 * its number of institutions, its average temperature,
 * its lowest temperature, and its highest temperature
 *  
 *  @author Michael Leverington
 * 
 */
public class StateDataClass extends Object
{
   // member data
   public String state;
   private String stateInitials;
   private int numInstitutions;
   private double avgTemp;
   private double lowestTemp;
   private double highestTemp;

   public StateDataClass leftChildRef;
   public StateDataClass rightChildRef;

   /** 
    * Default constructor, initializes all state data to default values
    * 
    */
   public StateDataClass()
   {
      state = "---";
      stateInitials = "**";
      numInstitutions = 0;;
      avgTemp = 0.0;
      lowestTemp = 0.0;
      highestTemp = 0.0;

      leftChildRef = null;
      rightChildRef = null;
   }

   /** Copy constructor, sets all data to copied object
    * 
    * @param copiedSC StateDataClass object to be copied
    */
   public StateDataClass( StateDataClass copiedSC )
   {
      state = copiedSC.state;
      stateInitials = copiedSC.stateInitials;
      numInstitutions = copiedSC.numInstitutions;
      avgTemp = copiedSC.avgTemp;
      lowestTemp = copiedSC.lowestTemp;
      highestTemp = copiedSC.highestTemp;

      leftChildRef = null;
      rightChildRef = null;
   }

   /** Initialization constructor, individually sets data values
    * <p>
    * Note: uses setData
    * 
    * @param stateIn String name of state
    * 
    * @param initialsIn String state initials
    * 
    * @param numInstIn integer number of institutions
    * for the state
    * 
    * @param avgTempIn double average temperature for the state
    * 
    * @param lowTempIn double lowest temperature for the state
    * 
    * @param highTempIn double highest temperature for the state
    * 
    */
   public StateDataClass( String stateIn, String initialsIn, 
         int numInstIn, double avgTempIn, 
         double lowTempIn, double highTempIn )
   {
      setData( stateIn, initialsIn, numInstIn, 
            avgTempIn, lowTempIn, highTempIn );

      leftChildRef = null;
      rightChildRef = null;
   }

   /**
    * Sets data in a given node with all data
    * 
    * @param stateIn String name of state
    * 
    * @param initialsIn String state initials
    * 
    * @param numInstIn integer number of institutions
    * for the state
    * 
    * @param avgTempIn double average temperature for the state
    * 
    * @param lowTempIn double lowest temperature for the state
    * 
    * @param highTempIn double highest temperature for the state
    * 
    */
   public void setData( String stateIn, String initialsIn, 
         int numInstIn, double avgTempIn, 
         double lowTempIn, double highTempIn )
   {
      state = stateIn;
      stateInitials = initialsIn;
      numInstitutions = numInstIn;
      avgTemp = avgTempIn;
      lowestTemp = lowTempIn;
      highestTemp = highTempIn;
   }

   /**
    * Sets data in a given node with all data
    * <p>
    * Note: Class uses overloaded setData with all data
    * 
    * @param newData StateDataClass reference
    * 
    */
   public void setData( StateDataClass newData )
   {
      setData( newData.state, newData.stateInitials,
            newData.numInstitutions, newData.avgTemp,
            newData.lowestTemp, newData.highestTemp );
   }

   /** Overrides Object.toString, provides raw data from object
    * <p>
    * Note: sortKey and sortDirKey not displayed
    * 
    */
   @Override
   public String toString()
   {
      return state + '/' + stateInitials + '/' + numInstitutions 
            + '/' + avgTemp + '/' + lowestTemp + '/' + highestTemp;
   }


}