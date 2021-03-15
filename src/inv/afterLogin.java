package inv;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class afterLogin {
	
	@FXML
	private Button logout; //bind logout button to userLogout
	
	public void userLogout(ActionEvent event) throws IOException{
		Main m = new Main(); //create main object
		m.changeScene("login.fxml"); //set scene back to login
	}
}
