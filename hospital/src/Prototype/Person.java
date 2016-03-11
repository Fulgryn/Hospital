package Prototype;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Person 
{
   private int id;
   private String first_name;
   private String last_name;
   private String DOB;
   private String address;
   private String email;
   private String phone_Nb;
 
   //Database variables
   private static String url = "jdbc:mysql://localhost:3306/";
   private static String dbName = "hospital";
   private String driver = "com.mysql.jdbc.Driver";
   private static String userName = "root";
   private static String password = "password";
   private static Statement statement = null;
   private static ResultSet resultSet = null;
   
   public Person(int iDin, String fn, String ln, String dob, String ad,String em, String  pn)
   {
	   id = iDin;
	   first_name = fn;
	   last_name=ln;
	   DOB=dob;
	   address=ad;
	   email=em;
	   phone_Nb=pn;
   }
   public Person()
   {   
   }
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
   public int add(Person Pin)
   {
	   int status = 0;
	   String sqlString= "insert into hospital.Patient(pps, name, dob, email, phone number, allergy, history, refferal, refferal class) "
	   		+ "Values("+Pin.getID()+ ",/"+Pin.getfirst_name() + ",/"+ Pin.getlastName()+ ",/" + Pin.getDOB() + ",/" + Pin.getAddress()+ 
	   		                ",/" + Pin.getemail() + ",/"+ Pin.getphone_nb() + ",/" + ")";
	   status = databaseUpdate(sqlString);
                         
	   return status;
   }
   //Veiw all patients 
   public static ArrayList<Person> viewAllPerson()
   {
	  ArrayList<Person> AllPerson = new ArrayList<Person>();
	  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url+dbName,userName, password);
		  statement = conn.createStatement();
		  resultSet = statement.executeQuery("select * from hospital.Person");
		  
		  while(resultSet.next ())
		  {
			  Person nextPerson = new Person(resultSet.getInt("id"),
			                                 resultSet.getString("first_name"),
			                                 resultSet.getString("last_name"),
			                                 resultSet.getString("DOB"),
			                                 resultSet.getString("address"),
			                                 resultSet.getString("email"),
			                                 resultSet.getString("phone_Nb"));
			  AllPerson.add(nextPerson);
		  }
		  conn.close();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	   return AllPerson; 
   }
   public Person searchAPerson(int searchNo)
   {
	   Person findp = new Person();
	   try{
		   Class.forName("com.mysql.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url+dbName, userName, password);
		   resultSet=statement.executeQuery("select * from hospital.Person where hospital.Person.host_id=" + findp);
		   while( resultSet.next());
		   {
			   findp = new Person(resultSet.getInt("id"),
                                   resultSet.getString("first_name"),
                                   resultSet.getString("last_name"),
                                   resultSet.getString("DOB"),
                                   resultSet.getString("address"),
                                   resultSet.getString("email"),
                                   resultSet.getString("phone_Nb").toString());
		   }
		   conn.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return findp;
   }
   private int databaseUpdate(String sqlUpdate)
   {
	   int status = 0;
	   
	   try{
		   Class.forName("com.mysql.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url+dbName, userName, password);
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
