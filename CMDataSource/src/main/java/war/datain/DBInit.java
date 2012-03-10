package war.datain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


public class DBInit extends ServerResource {
	private String db_path = "cm_db_datasource.db";
	private String SQL_CREATE_USER = "" +
			"CREATE TABLE IF NOT EXISTS user (" +
			"	user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"	user_label TEXT NOT NULL" +
			")";
	private String SQL_CREATE_APP = "" +
			"CREATE TABLE IF NOT EXISTS app (" +
			"	app_id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"	app_label TEXT NOT NULL " +
			")";
	private String SQL_CREATE_APPFACETIME = "" +
			"CREATE TABLE IF NOT EXISTS app_facetime (" +
			"	id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"	app_id INTEGER NOT NULL," +
			"	user_id INTEGER NOT NULL," +
			"	facetime INTEGER," +
			"	starttime TEXT," +
			"	FOREIGN KEY (user_id) REFERENCES user (user_id), " +
			"	FOREIGN KEY (app_id) REFERENCES app (app_id)" +
			")";
	private String SQL_CREATE_KEY = "" +
			"CREATE TABLE IF NOT EXISTS key (" +
			"	key_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"	validate_from TEXT NOT NULL," +
			"	validate_to TEXT NOT NULL," +
			"	dest_url TEXT NOT NULL," +
			"	dest_name TEXT NOT NULL" +
			")";
	private String SQL_CREATE_ACCESS = "" +
			"CREATE TABLE IF NOT EXISTS access_record (" +
			"	r_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"	key_id INTEGER NOT NULL," +
			"	access_from TEXT NOT NULL," +
			"	access_to TEXT NOT NULL," +
			"	access_desc TEXT NOT NULL" +//access request description, like sql, etc
			")";
	private String SQL_INSERT_USER = "" +
			"INSERT INTO user VALUES (NULL, '?')";
	
	private String db_init() {
		String out = "process to init database ... <br>";
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+this.db_path);
				Statement statement = connection.createStatement();
				statement.setQueryTimeout(30);
				
				statement.executeUpdate(this.SQL_CREATE_USER);
				statement.executeUpdate(this.SQL_CREATE_APP);
			    statement.executeUpdate(this.SQL_CREATE_APPFACETIME);
			    statement.executeUpdate(this.SQL_CREATE_KEY);
			    statement.executeUpdate(this.SQL_CREATE_ACCESS);
			    
			    //statement.executeUpdate("INSERT INTO user VALUES (null, 'jianhua shao')");
			    
			    ResultSet rs = statement.executeQuery(".TABLE");
			    while(rs.next()) {
			    	// read the result set
			        out += "name = " + rs.getString("user_label");
			        out += "id = " + rs.getInt("user_id");
			        out += "<br>";
			    }
			    rs.close();
			} catch (SQLException e){
				System.err.println(e.getMessage());
			} finally {
				try {
			        if(connection != null)
			          connection.close();
			    } catch(SQLException e) {
			        // connection close failed.
			        System.err.println(e);
			    }
			}
		} catch (Exception e){ //ClassNotFoundException
			System.err.println(e);
		}
		
		return out;
	}
	
	
	@Get 
	public String represent() {
		//String out = "<html><body>";
		//out += this.db_init();
		//out += "</body></html>";
		//return out;
		return this.db_init();
	}

}
