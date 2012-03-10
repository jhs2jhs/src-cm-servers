package com.cm.CMSource.datain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;


import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class DataServerIn extends ServerResource {
	private String db_path = "cm_db_datasource.db";
	private String SQL_CREATE_USER = "" +
			"CREATE TABLE IF NOT EXISITS user (" +
			"	user_id INTEGEER PRIMARY KEY," +
			"	user_label TEXT NOT NULL" +
			")";
	private String SQL_CREATE_APPFACETIME = "" +
			"CREATE TABLE IF NOT EXISITS app_facetime (" +
			"	app_id INTEGEER PRIMARY KEY," +
			"	user_id INTEGER NOT NULL," +
			"	facetime INTEGER," +
			"	starttime text" +
			"	FOREIGN KEY (user_id) REFERENCES user(user_id)" +
			")";
	private String SQL_INSERT_USER = "" +
			"INSERT INTO user VALUES (?)";
	
	@Get
	public String represent() throws ClassNotFoundException{
		String out = "";
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+this.db_path);
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			statement.executeUpdate(this.SQL_CREATE_USER);
		    statement.executeUpdate(this.SQL_CREATE_USER);
		    
		    PreparedStatement pstmt = connection.prepareStatement(this.SQL_INSERT_USER);
		    pstmt.setString(0, "Jianhua Shao");
		    pstmt.executeUpdate();
		    
		    ResultSet rs = statement.executeQuery("select * from user");
		      while(rs.next())
		      {
		        // read the result set
		        System.out.println("name = " + rs.getString("user_label"));
		        System.out.println("id = " + rs.getInt("user_id"));
		        out += "name = " + rs.getString("user_label");
		        out += "id = " + rs.getInt("user_id");
		      }
		} catch (SQLException e){
			System.err.println(e.getMessage());
		} finally {
			try
		      {
		        if(connection != null)
		          connection.close();
		      }
		      catch(SQLException e)
		      {
		        // connection close failed.
		        System.err.println(e);
		      }
		}
		
		return out;
	}

}
