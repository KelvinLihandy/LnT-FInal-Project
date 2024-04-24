package system;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import connection.Database;
import connection.Singleton;
import menu.Menu;

public class Control {
	protected static Database database = Singleton.create();
	static String query;
	static PreparedStatement statement;

	public static Boolean codeDuplicate(String code) {
		query = "SELECT * FROM Menus WHERE Menu_Code = ?";
		try {
			statement = database.connection.prepareStatement(query);
			statement.setString(1, code);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Boolean insert(Menu menu){
		query = "INSERT INTO Menus (Menu_Code, Menu_Name, Menu_Price, Menu_Stock) VALUES (?, ?, ?, ?)";
		try {
			statement = database.connection.prepareStatement(query);
			statement.setString(1, menu.getMenuCode());
			statement.setString(2, menu.getMenuName());
			statement.setString(3, Integer.toString(menu.getMenuPrice()));
			statement.setInt(4, menu.getMenuStock());
			return statement.executeUpdate() > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Boolean checkMenu() {
		query = "SELECT * FROM Menus";
		try {
			statement = database.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<Menu> retrieveMenu(){
		query = "SELECT * FROM Menus";
		String code;
		String name;
		String price;
		int stock;
		List<Menu> retrievedMenu = new ArrayList<Menu>();
		try {
			statement = database.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				code = result.getString("Menu_Code");
				name = result.getString("Menu_Name");
				price = result.getString("Menu_Price");
				stock = result.getInt("Menu_Stock");
				Menu retrieved = new Menu(code, name, Integer.parseInt(price), stock);
				retrievedMenu.add(retrieved);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return retrievedMenu;
	}
	
	public static List<String> retrieveName(){
		query = "SELECT Menu_Name FROM Menus";
		String name;
		List<String> retrievedName = new ArrayList<String>();
		try {
			statement = database.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				name = result.getString("Menu_Name");
				retrievedName.add(name);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return retrievedName;
	}
	
	public static List<String> retrieveCode(){
		query = "SELECT Menu_Code FROM Menus";
		String code;
		List<String> retrievedCode = new ArrayList<String>();
		try {
			statement = database.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				code = result.getString("Menu_Code");
				retrievedCode.add(code);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return retrievedCode;
	}
	
	public static String retrieveNameByCode(String menuCode) {
		query = "SELECT Menu_Name FROM MENUS WHERE Menu_Code = ?";
		String retrievedName = null;
		try {
			statement = database.connection.prepareStatement(query);
			statement.setString(1, menuCode);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				retrievedName = result.getString("Menu_Name");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return retrievedName;
	}
	
	public static Boolean update(String code, int price, int stock) {
		query = "UPDATE Menus SET Menu_Price = ?, Menu_Stock = ? WHERE Menu_Code = ?";
		try {
			statement = database.connection.prepareStatement(query);
			statement.setInt(1, price);
			statement.setInt(2, stock);
			statement.setString(3, code);
			return statement.executeUpdate() > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Boolean delete(String deletedName) {
		query = "DELETE FROM Menus WHERE Menu_Name = ?";
		try {
			statement = database.connection.prepareStatement(query);
			statement.setString(1, deletedName);
			return statement.executeUpdate() > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}