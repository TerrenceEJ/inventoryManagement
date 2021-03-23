package inv;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Product {

	public static ObservableList<Product> items = FXCollections.observableArrayList();

	private File file = new File("src/data/inventory.txt");

	private String name;
	private double price;
	private int quantity;
	private int number;

	public Product(){
		this.name = "";
		this.price = 0;
		this.quantity = 0;
		this.number = 1;
	}
	public Product(String name, double price, int quantity, int number){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.number = number;
	}

	protected ObservableList<Product> read() throws IOException, ClassNotFoundException{
		Scanner input = new Scanner(new File("src/data/inventory.txt"));
		input.useDelimiter("-");

		Product[] products = new Product[0];
			try{
				while(input.hasNext()){//assign values to each array element
					String name = input.next();
					double price = Double.valueOf(input.next());
					int quantity = input.nextInt();
					int number = input.nextInt();

					Product newProduct = new Product(name, price, quantity, number); //constructor
					products = addProduct(products, newProduct);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		items = FXCollections.observableArrayList(products);
			System.out.println(items);

		return FXCollections.observableArrayList(products);
	}

	private static Product[] addProduct(Product[] products, Product newProduct) {
		Product[] newProducts = new Product[products.length + 1];
		System.arraycopy(products, 0, newProducts, 0, products.length);
		newProducts[newProducts.length - 1] = newProduct;

		return newProducts;
	}

	protected ObservableList<Product> remove(int index) throws IOException, ClassNotFoundException{
		items.addAll(read());
		items.remove(index); //remove the line indicated by the index
		save();
		return FXCollections.observableArrayList(items);
	}

	void save() throws IOException, ClassNotFoundException {
		FileWriter writer = new FileWriter("src/data/inventory.txt");
		List<Product> list = items;
		for(Product product: list) {
			writer.write(product.getName() + "-" + product.getPrice() + "-" + product.getQuantity() + "-" + product.getNumber() + "-"); //rewrite
		}
		writer.close();
	}

	void adjust(String name, Double price, int quantity) throws IOException, ClassNotFoundException {
		setPrice(price);
		setQuantity(quantity); //go through and set price and quantity updates
		save();
	}

	void add(String name, Double price, int quantity, int number) throws IOException, ClassNotFoundException {
		//setName(name);
		//setPrice(price);
		//setQuantity(quantity);
		//setNumber(number); //go through and set details


		items.add(new Product(name, price, quantity, number));
		save();
	}


	public String toString(){
		return name + "-" + price +"-" + quantity +"-" + number +"-";}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Double getPrice(){
		return price;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getNumber(){
		return number;
	}

	public void setNumber(int number){
		this.number = number;
	}
}
