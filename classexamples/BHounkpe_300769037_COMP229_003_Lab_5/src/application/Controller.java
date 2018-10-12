package application;

import java.net.URL;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable{

	@FXML private TableView<Hospital> table;
	@FXML private TableColumn<Hospital, String> ColumnName;
	@FXML private TextField nametxt;
	@FXML private TextField adresstxt;
	@FXML private TextField faxtxt;
	@FXML private TextField phonetxt;
	@FXML private TextField emailtxt;
	@FXML private TextField ratingtxt;
	@FXML private TextField findtxt;
	@FXML private Button find;
	
	private ObservableList<Hospital> data;
	private DbConnection myCon;
	PreparedStatement Addhospital = null;
	PreparedStatement findName = null;
	PreparedStatement UpdateHospital= null;
	PreparedStatement DeleteHospital= null;
	
	String FindString ="Select * from hospitals where Name like ?";
	String AddString ="Insert into hospitals(Name,Address,FaxNumber,PhoneNumber,Email,Rating) values(?,?,?,?,?,?)";
	String UpdateString="UPDATE hospitals"+
			" SET Name=?,Address=?,FaxNumber=?,PhoneNumber=?,Email=?,Rating=?" + 
			" WHERE HospitalID=?";
	String DeleteString ="Delete from hospitals WHERE HospitalID=?";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myCon= new DbConnection();
		buildData();
	}
	
	@FXML
	public void buildData(){
		Connection conn=myCon.Connect();
		data = FXCollections.observableArrayList();
    	String myScript="Select * from hospitals";
    	try {
			ResultSet myResult= conn.createStatement().executeQuery(myScript);
	        while(myResult.next()){
	            data.add(new Hospital(myResult.getInt("HospitalID"),myResult.getString("Name"),
	            		myResult.getString("Address"),myResult.getString("FaxNumber"),
	            		myResult.getString("PhoneNumber"),myResult.getString("Email"), 
	            		myResult.getInt("Rating")));                  
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	ColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	table.setItems(null);
        table.setItems(data);
	}
	@FXML
	public void selectrow() {
		nametxt.setText(table.getSelectionModel().getSelectedItem().getName());
		emailtxt.setText(table.getSelectionModel().getSelectedItem().getEmail());
		adresstxt.setText(table.getSelectionModel().getSelectedItem().getAddress());
		faxtxt.setText(table.getSelectionModel().getSelectedItem().getFaxNumber());
		phonetxt.setText(table.getSelectionModel().getSelectedItem().getPhoneNumber());
		ratingtxt.setText(table.getSelectionModel().getSelectedItem().getRating().toString());
		
	}
	//-------------------------------------
	@FXML
	public void find(){
		data = FXCollections.observableArrayList();
		Connection conn=myCon.Connect();
		try {
			conn.setAutoCommit(false);
			findName = conn.prepareStatement(FindString);
			findName.setString(1, "%"+findtxt.getText()+"%");
			ResultSet myResult=findName.executeQuery();
	        while(myResult.next()){
	            data.add(new Hospital(myResult.getInt("HospitalID"),myResult.getString("Name"),
	            		myResult.getString("Address"),myResult.getString("FaxNumber"),
	            		myResult.getString("PhoneNumber"),myResult.getString("Email"), 
	            		myResult.getInt("Rating")));  
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	table.setItems(null);
        table.setItems(data);
	}
	//---------------------
	public void AddHospital(){
		data = FXCollections.observableArrayList();
		Connection conn=myCon.Connect();
		try {
			conn.setAutoCommit(false);
			Addhospital = conn.prepareStatement(AddString);
			Addhospital.setString(1,nametxt.getText());
			Addhospital.setString(2,adresstxt.getText());
			Addhospital.setString(3,faxtxt.getText());
			Addhospital.setString(4,phonetxt.getText());
			Addhospital.setString(5,emailtxt.getText());
			Addhospital.setInt(6,Integer.parseInt(ratingtxt.getText()));
			Addhospital.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buildData();
	}
	//---------------------
	public void UpdateHospital(){
		data = FXCollections.observableArrayList();
		Connection conn=myCon.Connect();
		try {
			conn.setAutoCommit(false);
			UpdateHospital = conn.prepareStatement(UpdateString);
			UpdateHospital.setString(1,nametxt.getText());
			UpdateHospital.setString(2,adresstxt.getText());
			UpdateHospital.setString(3,faxtxt.getText());
			UpdateHospital.setString(4,phonetxt.getText());
			UpdateHospital.setString(5,emailtxt.getText());
			UpdateHospital.setInt(6,Integer.parseInt(ratingtxt.getText()));
			UpdateHospital.setInt(7,table.getSelectionModel().getSelectedItem().getId());
			UpdateHospital.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buildData();
	}
	//---------------------
	public void DeleteHospital(){
		Connection conn=myCon.Connect();
		try {
			conn.setAutoCommit(false);
			DeleteHospital = conn.prepareStatement(DeleteString);
			DeleteHospital.setInt(1,table.getSelectionModel().getSelectedItem().getId());
			DeleteHospital.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buildData();
		nametxt.setText("");
		emailtxt.setText("");
		adresstxt.setText("");
		faxtxt.setText("");
		phonetxt.setText("");
		ratingtxt.setText("");
	}
}
