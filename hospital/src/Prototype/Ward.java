package Prototype;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
public class Ward extends Patient
{
	//class attributes
  private int id;
  private String ward_name;
  private String room_type;
  private int bed_number;
  private int bed_free;
  
  //Database variables
  private static String url = "jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false";
  //private static String dbName = "Hospital";
  private String driver = "com.mysql.jdbc.Driver";
  private static String userName = "root";
  private static String password = "password";
  private static Statement statement = null;
  private static ResultSet resultSet = null;
  
  public Ward(int idin, String Wardn, String roomT, int bedNum, int bedFree)
  {
	  super();
	  id = idin;
	  ward_name = Wardn;
	  room_type = roomT;
	  bed_number = bedNum;
	  bed_free = bedFree;
	 
  }
  public Ward()
  {
	  
  }
  public int getid()
  {
	  return id;
  }
  public String getward_name()
  {
	  return ward_name;
  }
  public String getroom_type()
  {
	  return room_type;
  }
  public int getbed_number()
  {
	  return id;
  }
  public int getbed_free()
  {
	  return id;
  }
  public Ward search(int searchNum)
  {
	  Ward ward = new Ward();
	   try{
		   Class.forName("com.mysql.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, userName, password);
		   resultSet=statement.executeQuery("select * from hospital.Patient where hospital.Person.host_id=" + ward);
		   while( resultSet.next());
		   {
			   ward = new Ward(resultSet.getInt("id"),
			                   resultSet.getString("ward_name"),
			                   resultSet.getString("room_type"),
			                   resultSet.getInt("bed_number"),
			                   resultSet.getInt("bed_free"));
		   }
		   conn.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return ward;
  }
  public static ArrayList<Ward> viewAllWards()
  {
	  ArrayList<Ward> AllWards = new ArrayList<Ward>();
	  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url,userName, password);
		  statement = conn.createStatement();
		  resultSet = statement.executeQuery("select * from hospital.Ward");
		  
		  while(resultSet.next ())
		  {
			  Ward nextWard = new Ward(resultSet.getInt("id"),
							           resultSet.getString("ward_name"),
							           resultSet.getString("room_type"),
							           resultSet.getInt("bed_number"),
							           resultSet.getInt("bed_free"));
			  AllWards.add(nextWard);
		  }
	  conn.close();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	   return AllWards; 
	}
  public static int assignWard(int wardNum, int searchno)
  {
	   int status = 0;
	   String sqlString= "update patient set ward_id= "
	   		+wardNum+" where id="+searchno;
	   status = databaseUpdate(sqlString);
                         
	   return status;  
  }
  
  public static int removeFromWard(int searchno)
  {
	   int status = 0;
	   String sqlString= "update patient set ward_id=null where id="+searchno;
	   status = databaseUpdate(sqlString);
                         
	   return status;  
  }
  /*public int delete(int host_id)  
  {
      int status=0;  
      String sqlString= "delete from hospital.Patient where snumber="+host_id;     
      status = databaseUpdate(sqlString);                                        
      return status;
   }*/

  private static int databaseUpdate(String sqlUpdate)
  {
     int status=0;
  
     try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,userName,password);
        statement=conn.createStatement();
        status=statement.executeUpdate(sqlUpdate);
        conn.close(); 
     }       
      
     catch (Exception e) {
        e.printStackTrace();
     }   
     return status;
  }
  
  public String toString(){
	   return this.id+" "+this.ward_name;
  }
}
