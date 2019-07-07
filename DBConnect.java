import java.sql.*;
public class DBConnect{
	public static Connection kConnect(){
		Connection con=null;
		try{
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres","postgres","password");
		}catch(Exception e){
			System.out.println("Connection not Established :"+e);
		}
		return con;
	}
}
