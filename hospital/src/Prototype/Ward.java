package Prototype;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
public class Ward
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
	  return bed_number;
  }
  public int getbed_free()
  {
	  return bed_free;
  }
  
  public Ward search(int searchNum)
  {
	  Ward ward = new Ward();
	   try{
		   Class.forName("com.mysql.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, userName, password);
		   resultSet=statement.executeQuery("select * from hospital.Ward where hospital.Ward.id=" + searchNum);
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
  
  /*public Ward getWardByID(int wardID){
	  Ward ward = new Ward();
	  ArrayList<Ward> AllWards = new ArrayList<Ward>();
	  for(Ward w : AllWards) { 
		   if(w.id = wardID)
			   
		       //found it!
		   }
	  return ward;
  }*/
  
  public static Ward getWardByID(int wardID)
  {
	  Ward ward = new Ward();
	  ArrayList<Ward> AllWards = new ArrayList<Ward>();
	  for(Ward w : AllWards) { 
		  	if(w.id == wardID)
		  		ward = w;
		   }
	  return ward;
  }
  public static int assignWard(Ward ward, int searchno)
  {
	   int status = 0;
	   ward.bed_free--;
	   String sqlString= "update patient set ward_id= "
	   		+ward.id+", bed_free="+ward.bed_free+" where id="+searchno;
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
	   return "ID: "+this.id+" ward name: "+this.ward_name+" Total beds: "+this.bed_number+" Free beds: "+this.getbed_free();
  }
public void setbed_free(int bed_free) {
	this.bed_free = bed_free;
}
}
