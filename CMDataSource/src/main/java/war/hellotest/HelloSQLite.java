package war.hellotest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class HelloSQLite extends ServerResource {
	@Get
	public String represent() throws ClassNotFoundException{
		String out = "";
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			statement.executeUpdate("drop table if exists person");
		      statement.executeUpdate("create table person (id integer, name string)");
		      statement.executeUpdate("insert into person values(1, 'leo')");
		      statement.executeUpdate("insert into person values(2, 'yui')");
		      ResultSet rs = statement.executeQuery("select * from person");
		      while(rs.next())
		      {
		        // read the result set
		        System.out.println("name = " + rs.getString("name"));
		        System.out.println("id = " + rs.getInt("id"));
		        out += "name = " + rs.getString("name");
		        out += "id = " + rs.getInt("id");
		      } //create table if not exists persons (id integer, name string)
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
