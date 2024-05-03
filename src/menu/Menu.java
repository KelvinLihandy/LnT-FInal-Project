package menu;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Menu {
	private String menuCode;
	private String menuName;
	private int menuPrice;
	private int menuStock;
	
	public Menu(String menuCode, String menuName, int menuPrice, int menuStock) {
		super();
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuStock = menuStock;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public int getMenuStock() {
		return menuStock;
	}

	public void setMenuStock(int menuStock) {
		this.menuStock = menuStock;
	}
	
	public StringProperty codeProperty() {
		return new SimpleStringProperty(menuCode);
	}
	
	public StringProperty nameProperty() {
		return new SimpleStringProperty(menuName);
	}
	
	public StringProperty priceProperty() {
		return new SimpleStringProperty(Integer.toString(menuPrice));
	}
	
	public StringProperty stockProperty() {
		return new SimpleStringProperty(Integer.toString(menuStock));
	}
}
