package inv;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class adminPath implements Initializable{
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private Button adminLogout; //bind logout button to userLogout
	
	@FXML
	private ListView<String> inventory;
	
	@FXML
	private TextField addText;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button removeButton;
	
	private ObservableList<String> items = FXCollections.observableArrayList(); //initialize list to prep for reading inventory from text file
	
	public void adminLogout(ActionEvent event) throws IOException{
		Main m = new Main(); //create main object
		m.changeScene("Login.fxml"); //set scene back to login
	}
	

	public void add() {
		items.add(addText.getText());
		addText.setText("");
		inventory.setItems(items);
	}
	
	public void remove() {
		int x = inventory.getSelectionModel().getSelectedIndex(); //remove selected item from 
		items.remove(x);
		inventory.setItems(items);
	}

	
}
