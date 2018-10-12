package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class myController implements Initializable {

	@FXML
	private Button copyButton;
	@FXML
	private TextArea textArea1;
	@FXML
	private TextArea textArea2;
	@FXML
	private void handleButtonAction(ActionEvent event) {
	System.out.println("You clicked me!");
	textArea2.setText(textArea1.getSelectedText());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
