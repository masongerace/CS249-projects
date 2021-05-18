package p10_package;

/**
 * Class for managing student data
 * 
 * @author MichaelL
 *
 */
public class StudentClass
{
   /**
    * Name data for class
    */
   public String name;

   /**
    * Student ID data for class
    */
   public int studentID;

   /**
    * Gender data for class
    */
   public char gender;

   /**
    * GPA data for class
    */
   public double gpa;

   /**
    * Initialization constructor for data
    * <p>
    * Note: Class does not require a default constructor
    * 
    * @param inName name of student to be input into object
    * 
    * @param inStudentID ID number of student to be input into object
    * 
    * @param inGender gender of student to be input into object
    * 
    * @param inGPA gpa of student to be input into object
    * 
    */
   public StudentClass( String inName, 
         int inStudentID, char inGender, double inGPA )
   {
      name = inName;

      studentID = inStudentID;

      gender = inGender;

      gpa = inGPA;
   }

   /**
    * Copy constructor
    * <p>
    * Calls other constructor with copied data
    *
    * @param copied StudentClass object to be copied
    */
   public StudentClass( StudentClass copied )
   {
      this( copied.name, copied.studentID, copied.gender, copied.gpa );
   }

   /**
    * Overrides Object compareTo with local
    * <p>
    * Returns number greater than zero if this object greater than other;
    * returns number less than zero if this object less than other;
    * returns zero if this object equal to other
    * 
    * @param other StudentClass object to be compared to this object
    * 
    * @return integer result of GPA comparison
    */
   public int compareTo( StudentClass other )
   {
      return (int)( ( this.gpa - other.gpa ) * 100 );   
   }

   /**
    * Overrides Object toString with local
    */
   @Override
   public String toString()
   {
      return name + '/' + studentID + '/' + gender + '/' + gpa;           
   }      
}
