//written by Terrence Eldred-Jones
package inv;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class userPath implements Initializable {

		Product product = new Product();

		@FXML
		private Button userLogout; //bind logout button to userLogout
		@FXML
		private TableView<Product> checkStock;
		@FXML
		private TableColumn<Product, String> nameC;
		@FXML
		private TableColumn<Product, Double> priceC;
		@FXML
		private TableColumn<Product, Integer> quantityC;
		@FXML
		private TableColumn<Product, Integer> numberC;


		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				nameC.setCellValueFactory(new PropertyValueFactory<>("name")); //set what values the columns will take
				priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
				quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
				numberC.setCellValueFactory(new PropertyValueFactory<>("number"));

				checkStock.setItems(product.getProducts());

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		public void updateStock() throws IOException, ClassNotFoundException {
			checkStock.setItems(product.getProducts());
		}

			public void userLogout(ActionEvent event) throws IOException{
				Main m = new Main(); //create main object
				m.changeScene("Login.fxml"); //set scene back to login
		}
}
