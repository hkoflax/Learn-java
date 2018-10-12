package application;
import java.sql.*;;

public class DbConnection {

	public Connection Connect() {
		String conString="jdbc:mysql://localhost:3306/schoolwork?useSSL=false";
		String user="student";
		String Pwd="student";
		try {
	    	Connection myCon= DriverManager.getConnection(conString,user,Pwd);
	    	return myCon;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
