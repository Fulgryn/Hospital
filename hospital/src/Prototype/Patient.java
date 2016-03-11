package Prototype;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
public class Patient
{
   //Class attributes variables	
   private int id;
   private String first_name;
   private String last_name;
   private String DOB;
   private String address;
   private String email;
   private String phone_Nb;	
   private String PPSN;
   private String next_of_kin;
   private String allergy;
   private String history;
   private String referral;
   private String referral_class;
   private Ward ward;

   
   //Database variables
   private static String url = "jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false";
   //private static String dbName = "Hospital";
   private String driver = "com.mysql.jdbc.Driver";
   private static String userName = "root";
   private static String password = "password";
   private static Statement statement = null;
   private static ResultSet resultSet = null;

   
   //constructor
   public Patient(/*int iDin,*/ String fn, String ln, String dob, String ad,String em, String  pn,String patientPPS,
		        String nokIn, String allerIN, String histin, String reffIn, String reffCIn)
   {
	   /*id = iDin;*/
	   first_name = fn;
	   last_name=ln;
	   DOB=dob;
	   address=ad;
	   email=em;
	   phone_Nb=pn;
	   PPSN=patientPPS;
	   next_of_kin=nokIn;
	   allergy=allerIN;
	   history=histin;
	   referral=reffIn;
	   referral_class=reffCIn;
	    
	   
   }
   
   public Patient(int iDin, String fn, String ln, String dob, String ad,String em, String  pn,String patientPPS,
	        String nokIn, String allerIN, String histin, String reffIn, String reffCIn)
{
  id = iDin;
  first_name = fn;
  last_name=ln;
  DOB=dob;
  address=ad;
  email=em;
  phone_Nb=pn;
  PPSN=patientPPS;
  next_of_kin=nokIn;
  allergy=allerIN;
  history=histin;
  referral=reffIn;
  referral_class=reffCIn;
   
  
}
   public Patient()
   {
	   
   }
   //Accessor methods
   public int getID()
   {
	   return id;
   }
   public String getfirst_name()
   {
	   return first_name;
   }
   public String getlastName()
   {
	   return last_name;
   }
   public String getDOB()
   {
	   return DOB;
   }
   public String getAddress()
   {
	   return address;
   }
   public String getemail()
   {
	   return email;
   }
   public String getphone_nb()
   {
	   return phone_Nb;
   }
   public String getPPS()
   {
	   return PPSN;
   }
   public String getkin()
   {
	   return next_of_kin;
   }
   public String Getallergy()
   {
	   return allergy;
   }
   public String GetHistory()
   {
	   return history;
   }
   public String GetRefferal()
   {
	   return referral;
   }
   public String GetRefferalClass()
   {
	   return referral_class;
   }
   //database access and update methods
   //ADD//
   public static int add(Patient Pin)
   {
	   int status = 0;
	   String sqlString= "insert into hospital.Patient(first_name, last_name, DOB, address, email, phone_Nb, PPSN, next_of_kin, allergy, history, referral, referral_class) values(\'"+Pin.getfirst_name() + 
			"\', \'"+ Pin.getlastName()+ "\', \'" + Pin.getDOB() + "\', \'" + Pin.getAddress()+ 
            "\', \'" + Pin.getemail() + "\', \'"+ Pin.getphone_nb() + "\', \'" +Pin.getPPS() + "\', \'"+Pin.getkin() + "\', \'"+ Pin.Getallergy()+ "\', \'" + Pin.GetHistory() + "\', \'" + Pin.GetRefferal()+ 
	   		"\', \'" + Pin.GetRefferalClass()+ "\')";
	   status = databaseUpdate(sqlString);
           //System.out.print(sqlString);              
	   return status;
   }
  
   //delete the selected patient from the database
   public static int deletePatient(int pID)
   {
	   int status = 0;
	   String sqlString= "delete from hospital.Patient where id="+pID;
	   status = databaseUpdate(sqlString);
	   return status;
   }
   
   //View all patients 
   public static ArrayList<Patient> viewAllPatients()
   {
	  ArrayList<Patient> AllPatients = new ArrayList<Patient>();
	  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url,userName, password);
		  statement = conn.createStatement();
		  resultSet = statement.executeQuery("select * from hospital.Patient");
		  
		  while(resultSet.next ())
		  {
			  Patient nextPatient = new Patient( resultSet.getInt("id"),
							                     resultSet.getString("first_name"),
							                     resultSet.getString("last_name"),
							                     resultSet.getString("DOB"),
							                     resultSet.getString("address"),
							                     resultSet.getString("email"),
							                     resultSet.getString("phone_Nb"),
	                                             resultSet.getString("PPSN"),
	                                             resultSet.getString("allergy"),
	                                             resultSet.getString("next_of_kin"),
	                                             resultSet.getString("history"),
	                                             resultSet.getString("referral"),
	                                             resultSet.getString("referral_class"));
			  AllPatients.add(nextPatient);
		  }
		  conn.close();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	   return AllPatients; 
   }
   public Patient searchAPatient(int searchNo)
   {
	   Patient foundp = new Patient();
	   try{
		   Class.forName("com.mysql.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, userName, password);
		   resultSet=statement.executeQuery("select * from hospital.Patient where hospital.Person.host_id=" + foundp);
		   while( resultSet.next());
		   {
			   foundp = new Patient( resultSet.getInt("id"),
				                     resultSet.getString("first_name"),
				                     resultSet.getString("last_name"),
				                     resultSet.getString("DOB"),
				                     resultSet.getString("address"),
				                     resultSet.getString("email"),
				                     resultSet.getString("phone_Nb"),
                                     resultSet.getString("PPSN"),
                                     resultSet.getString("allergy"),
                                     resultSet.getString("next_of_kin"),
                                     resultSet.getString(" history"),
                                     resultSet.getString("referral"),
                                     resultSet.getString("referral_class"));
			          
		   }
		   conn.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return foundp;
   }
   private static int databaseUpdate(String sqlUpdate)
   {
	   int status = 0;
	   
	   try{
		   Class.forName("com.mysql.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, userName, password);
		   statement=conn.createStatement();
		   status=statement.executeUpdate(sqlUpdate);
		   conn.close();
	   }
	   catch (Exception e)
	   {
		   e.printStackTrace();
	   }
	   return status;
   }
   
   public String toString(){
	   return this.id+" "+this.first_name+" "+ this.last_name+" "+this.DOB+" "+this.address+" "+this.email+" "+this.phone_Nb;
   }
}
