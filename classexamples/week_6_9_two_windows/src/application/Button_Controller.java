package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Button_Controller implements Initializable {

	JobSeeker jobSeeker = new JobSeeker();

	@FXML
	private Label lblName;
	@FXML
	private CheckBox backgroundChB;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtSurname;
	@FXML
	private TextArea txtAInfo;

	@FXML
	private void handleButtonAction(ActionEvent event) {
		System.out.println("You clicked me!");
		lblName.setText("Hello World!");
		jobSeeker.setFirstName(txtName.getText());
		jobSeeker.setSurname(txtSurname.getText());

		try {
			Stage stage = new Stage();
			txtAInfo.setText(jobSeeker.getInfo() + " " + backgroundChB.getText());
			String fxmlFileName = "Second_window.fxml";
			FXMLLoader loader = new  FXMLLoader(getClass().getResource(fxmlFileName));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			Second_Controller controller = loader.<Second_Controller>getController();
			controller.populateData(backgroundChB.getText() + " " + String.valueOf(backgroundChB.isSelected()));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
