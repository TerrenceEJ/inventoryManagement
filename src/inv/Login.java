package inv;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {
	
	public Login() {
		
	}
	
	@FXML
	private Button button;
	@FXML
	private Label wrongLogin;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	//binding objects
	
	
	public void userLogin(ActionEvent event) throws IOException {
		checkLogin(); //
	}
	
	private void checkLogin() throws IOException {
		Main m = new Main();
		if(username.getText().toString().equals("admin") && password.getText().toString().equals ("admin")) {
			wrongLogin.setText("Success!");
			
			m.changeScene("afterLogin.fxml");//change scene after successful login
		}
		
		else if(username.getText().isEmpty() && password.getText().isEmpty()) {
			wrongLogin.setText("Please enter your login info.");
		}
		
		else {
			wrongLogin.setText("Wrong username or password!");
		}
	}
}
