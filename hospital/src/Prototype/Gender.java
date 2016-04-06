package Prototype;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Gender {

	private int id;
	private String gender;


	   
	   //Database variables
	   private static String url = "jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false";
	   //private static String dbName = "Hospital";
	   private String driver = "com.mysql.jdbc.Driver";
	   private static String userName = "root";
	   private static String password = "password";
	   private static Statement statement = null;
	   private static ResultSet resultSet = null;

	   
	   //constructor
	   public Gender(int ID, String gen)
	   {
		   id = ID;
		   gender=gen;	   
	   }
	   
	 
	   public Gender()
	   {
		   
	   }
	   //Accessor methods
	   public int getID()
	   {
		   return id;
	   }
	   public String getGender()
	   {
		   return gender;
	   }
	   
	   public static ArrayList<Gender> viewAllGender()
	   {
		  ArrayList<Gender> AllGender = new ArrayList<Gender>();
		  try{
			  Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection(url,userName, password);
			  statement = conn.createStatement();
			  resultSet = statement.executeQuery("select * from hospital.Gender");
			  
			  while(resultSet.next ())
			  {
				  Gender nextGender = new Gender( resultSet.getInt("id"),
								                  resultSet.getString("gender"));
				  AllGender.add(nextGender);
			  }
			  conn.close();
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		   return AllGender;
	   }
	  
}