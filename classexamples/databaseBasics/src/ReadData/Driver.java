package ReadData;
import java.sql.*;;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String conString="jdbc:mysql://localhost:3306/schoolwork?useSSL=false";
		String user="student";
		String Pwd="student";
		try {
			//connect to database
			Connection myCon= DriverManager.getConnection(conString,user,Pwd);
			//create statement
			Statement myStmt= myCon.createStatement();	
			//Script
			String myScript="Select * from hospitals";
			//execute query
			ResultSet myResult= myStmt.executeQuery(myScript);
			//show result
			while(myResult.next()) {
				System.out.println(myResult.getString("Name"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
