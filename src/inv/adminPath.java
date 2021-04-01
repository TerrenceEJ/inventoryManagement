package inv;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import static inv.Product.items;

public class adminPath implements Initializable{
	
	Product product = new Product(); //instance Product class
	@FXML
	private Label errorP;
	@FXML
	private Label errorQ;
	@FXML
	private Label errorPNum;
	@FXML
	private Label errorPN;
	@FXML
	private Label errorGen;
	@FXML
	private Button adminLogout; //bind logout button to userLogout
	@FXML
	private TextField addPN; //product number textfield
	@FXML
	private TextField addPrice; //price textfield
	@FXML
	private TextField addQ; //quantity textfield
	@FXML
	private TextField addPNum; //product number textfield
	@FXML
	private Button addB;
	@FXML
	private Button update;
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

			table.setItems(product.getProducts()); //set the table

			nameC1.setCellValueFactory(new PropertyValueFactory<>("name")); //set what values the columns will take
			priceC1.setCellValueFactory(new PropertyValueFactory<>("price"));
			quantityC1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			numberC1.setCellValueFactory(new PropertyValueFactory<>("number"));

			viewStock.setItems(product.getProducts());

			//add the event listener to the table
			table.addEventFilter(MouseEvent.MOUSE_CLICKED, clickEvent);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void adminLogout(ActionEvent event) throws IOException{
		Main m = new Main(); //create main object
		m.changeScene("Login.fxml"); //set scene back to login
	}

	public void removeS() throws IOException, ClassNotFoundException {
		index = table.getSelectionModel().getSelectedIndex(); //remove selected item from
		product.remove(index);
		table.setItems(product.getProducts()); //reread file and update table
	}
	
	public String name(){
		String name = addPN.getText();

		if(name.equals("")){
			errorPN.setText("You forgot to include a name for your product"); //make sure a part number was assigned
		}
		else {
			errorPN.setText("");
		}

		return name;
	}

	public Double price(){
		String text = addPrice.getText();
		double price;

		if(text.equals("")){
			errorP.setText("You forgot to include a price"); //make sure a part number was assigned
			price = 0.0;
		}
		else {
			price = Double.parseDouble(text); //convert text to double
			errorP.setText("");
		}

		return price;
	}

	public int quantity(){
		String text = addQ.getText();
		int quantity;

		if(text.equals("")){
			errorQ.setText("You forgot to include how many products you have currently"); //make sure a part number was assigned
			quantity = 0;
		}
		else {
			quantity = Integer.parseInt(text);
			errorQ.setText("");
		}

		return quantity;
	}

	public long PNum(){
		String text = addPNum.getText();
		Long num = Long.valueOf(0);

		if(text.equals("")){
			errorPNum.setText("You forgot to scan a number"); //make sure a part number was assigned
		}
		else {
			try {
				num = Long.parseLong(text);
			} catch (NumberFormatException e){ }
			errorPNum.setText("");
		}

		return num;
	}

	public void addButton() throws IOException, ClassNotFoundException {
		String name = name();
		Double price = price();
		int quantity = quantity();
		Long number = PNum();
		boolean alreadyRan = false;

		List<Product> list = product.getProducts();

		if(!list.stream().anyMatch(o-> o.getName().equals(name)) && !list.stream().anyMatch(o-> o.getNumber() == number)){//run for loop if list doesnt contain name or number
		for(Product products : list) {
			if (products.getName().equals(name) || products.getNumber() == number) {
				errorGen.setText("Error, you are already trying to create an item that exists!");
			}
			if (price == 0.0 || quantity == 0) {
				errorGen.setText("Error, did not input a number!");
			}
			if (!alreadyRan && !products.getName().equals(name) && price != 0 && quantity != 0.0 && products.getNumber() != number) {
				alreadyRan = true;
				product.add(name, price, quantity, number);

				Set<Product> dupe = new HashSet<Product>(list);
				if(dupe.stream().anyMatch(o-> o.getName().equals(name))) {
					product.remove(product.getProducts().size()-1); //make sure no duplicates are made. if made, remove it from list
				}
				table.setItems(product.getProducts()); //set tableview again
			}
			else {
				//do nothing/placeholder
			}
		}
		}
	}

	public void update() throws IOException, ClassNotFoundException {
		String name = name();
		Double price = price();
		int quantity = quantity();

		Iterator<Product> iter = product.getProducts().iterator();
		while(iter.hasNext()) {
			Product products = iter.next();
			if (products.getName().equals(name)) {
				products.adjust(name, price, quantity); //adjust the item, can take all changes or 1
			}
		}
		table.setItems(product.getProducts()); //update table view
	}

	public void stockChange() throws IOException, ClassNotFoundException {
		viewStock.setItems(product.getProducts()); //get current items for tab change
	}
	//Handles clicks within the inventory table
	EventHandler<MouseEvent> clickEvent = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent mouseEvent) {
			//double click to clear
			if(mouseEvent.getClickCount() == 2){
				table.getSelectionModel().clearSelection();
				addPN.clear();
				addQ.clear();
				addPNum.clear();
				addPrice.clear();
			}
			//single click selects object and sets the text for easy adjustment
			else if(mouseEvent.getClickCount() == 1) {
				if(!table.getSelectionModel().isEmpty()){
					addPN.setText(table.getSelectionModel().getSelectedItem().getName());
					addQ.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getQuantity()));
					addPNum.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getNumber()));
					addPrice.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getPrice()));
				}
			}
		}
	};
}
