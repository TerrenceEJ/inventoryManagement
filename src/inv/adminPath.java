package inv;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class adminPath implements Initializable{
	
	Product product = new Product(); //instance Product class

	@FXML
	private Button adminLogout; //bind logout button to userLogout

	@FXML
	private TextField addText;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button removeButton;

	@FXML
	private TableView<Product> table;

	@FXML
	private TableView<Product> viewStock;

	@FXML
	private TableColumn<Product, String> nameC;

	@FXML
	private TableColumn<Product, Double> priceC;


	@FXML
	private TableColumn<Product, Integer> quantityC;

	@FXML
	private TableColumn<Product, Integer> numberC;

	@FXML
	private TableColumn<Product, String> nameC1;

	@FXML
	private TableColumn<Product, Double> priceC1;


	@FXML
	private TableColumn<Product, Integer> quantityC1;

	@FXML
	private TableColumn<Product, Integer> numberC1;

	public static int index;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			nameC.setCellValueFactory(new PropertyValueFactory<>("name")); //set what values the columns will take
			priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
			quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			numberC.setCellValueFactory(new PropertyValueFactory<>("number"));

			table.setItems(getProducts()); //set the table

			nameC1.setCellValueFactory(new PropertyValueFactory<>("name")); //set what values the columns will take
			priceC1.setCellValueFactory(new PropertyValueFactory<>("price"));
			quantityC1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			numberC1.setCellValueFactory(new PropertyValueFactory<>("number"));

			viewStock.setItems(getProducts());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Product> getProducts() throws IOException, ClassNotFoundException {
		ObservableList<Product> products = FXCollections.observableArrayList();
		products.addAll(product.read());
		return products;
	}
	
	public void adminLogout(ActionEvent event) throws IOException{
		Main m = new Main(); //create main object
		m.changeScene("Login.fxml"); //set scene back to login
	}

	public void removeS() throws IOException, ClassNotFoundException {
		index = table.getSelectionModel().getSelectedIndex(); //remove selected item from
		product.remove(index);
		table.setItems(getProducts()); //reread file and update table
	}

	public void getIndex() throws IOException, ClassNotFoundException {
		index = table.getSelectionModel().getSelectedIndex(); //remove selected item from
	}

	
}
