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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable{
	
	@FXML private TableView<Clothes> table;
	@FXML private TableColumn<Clothes, String> ColumnName;
	@FXML private TextField categorytxt;
	@FXML private TextField colortxt;
	@FXML private TextField clothesSizetxt;
	@FXML private TextField materialtxt;
	
	private ObservableList<Clothes> data;
	private DbConnection myCon;
	PreparedStatement AddClothes = null;
	PreparedStatement UpdateClothes= null;
	PreparedStatement DeleteClothes= null;
	
	String AddString ="Insert into Clothes(category, color, clothesSize, material) values(?,?,?,?)";
	String UpdateString="UPDATE Clothes"+
			" SET category=?,color=?,clothesSize=?,material=?" + 
			" WHERE clothesID =?";
	String DeleteString ="Delete from Clothes where clothesID =?";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myCon= new DbConnection();
		buildData();
	}
	
	@FXML
	public void buildData(){
		Connection conn=myCon.Connect();
		data = FXCollections.observableArrayList();
    	String myScript="Select * from Clothes";
    	try {
			ResultSet myResult= conn.createStatement().executeQuery(myScript);
	        while(myResult.next()){
	            data.add(new Clothes(myResult.getInt("clothesID"),myResult.getString("category"),
	            		myResult.getString("color"),myResult.getInt("clothesSize"),
	            		myResult.getString("material")));                  
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	ColumnName.setCellValueFactory(new PropertyValueFactory<>("category"));
    	table.setItems(null);
        table.setItems(data);
	}
	@FXML
	public void selectrow() {
		categorytxt.setText(table.getSelectionModel().getSelectedItem().getCategory());
		colortxt.setText(table.getSelectionModel().getSelectedItem().getColor());
		clothesSizetxt.setText(table.getSelectionModel().getSelectedItem().getClothesSize().toString());
		materialtxt.setText(table.getSelectionModel().getSelectedItem().getMaterial());
		
	}
	//---------------------
	public void AddClothes(){
		data = FXCollections.observableArrayList();
		Connection conn=myCon.Connect();
		try {
			conn.setAutoCommit(false);
			AddClothes = conn.prepareStatement(AddString);
			AddClothes.setString(1,categorytxt.getText());
			AddClothes.setString(2,colortxt.getText());
			AddClothes.setInt(3,Integer.parseInt(clothesSizetxt.getText()));
			AddClothes.setString(4,materialtxt.getText());

			AddClothes.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buildData();
	}
	//---------------------
	public void UpdateClothes(){
		data = FXCollections.observableArrayList();
		Connection conn=myCon.Connect();
		try {
			conn.setAutoCommit(false);
			UpdateClothes = conn.prepareStatement(UpdateString);
			UpdateClothes.setString(1,categorytxt.getText());
			UpdateClothes.setString(2,colortxt.getText());
			UpdateClothes.setInt(3,Integer.parseInt(clothesSizetxt.getText()));
			UpdateClothes.setString(4,materialtxt.getText());
			UpdateClothes.setInt(5,table.getSelectionModel().getSelectedItem().getId());
			
			UpdateClothes.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buildData();
	}
	//---------------------
	public void DeleteClothes(){
		Connection conn=myCon.Connect();
		try {
			conn.setAutoCommit(false);
			DeleteClothes = conn.prepareStatement(DeleteString);
			DeleteClothes.setInt(1,table.getSelectionModel().getSelectedItem().getId());
			
			DeleteClothes.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buildData();
		categorytxt.setText("");
		colortxt.setText("");
		clothesSizetxt.setText("");
		materialtxt.setText("");
	}
}
