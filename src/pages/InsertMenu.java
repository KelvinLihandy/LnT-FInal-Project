package pages;

import java.util.Random;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import menu.Menu;
import system.Control;

public class InsertMenu {
	protected Stage stage;
	protected Scene scene;
	protected BorderPane borderPane;
	protected GridPane gridPane;
	protected Button insertButton;
	protected Button homeButton;
	protected Label menuCodeLabel;
	protected Label generatedCodeLabel;
	protected Label menuNameLabel;
	protected Label menuPriceLabel;
	protected Label menuStockLabel;
	protected String menuCodeString;
	protected TextField menuNameField;
	protected TextField menuPriceField;
	protected TextField menuStockField;
	
	public InsertMenu(Stage stage) {
		this.stage = stage;
		stage.setTitle("Insert Menu");
		init();
		placement();
		event();
		this.scene = new Scene(borderPane, 600, 600);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	private void init() {
		stage = new Stage();
		borderPane = new BorderPane();
		gridPane = new GridPane();
		insertButton = new Button("Insert Menu");
		homeButton = new Button("Return to Home");
		menuCodeLabel = new Label("Menu code");
		generatedCodeLabel = new Label(idGen());
		menuNameLabel = new Label("Menu name");
		menuPriceLabel = new Label("Menu price");
		menuStockLabel = new Label("Menu stock");
		menuNameField = new TextField();
		menuPriceField = new TextField();
		menuStockField = new TextField();
		menuNameField.setPrefWidth(375);
		menuPriceField.setPrefWidth(375);
		menuStockField.setPrefWidth(375);
		menuNameField.setPromptText("Insert name");
		menuPriceField.setPromptText("Insert price");
		menuStockField.setPromptText("Insert stock");
	}
	
	private String idGen() {
		StringBuilder generated = new StringBuilder("PD-");
		Random number = new Random();
		generated.append(number.nextInt(10));
		generated.append(number.nextInt(10));
		generated.append(number.nextInt(10));
		if(Control.codeDuplicate(generated.toString())) {
			generatedCodeLabel.setText(null);
			menuNameField.clear();
			menuPriceField.clear();
			menuStockField.clear();
			menuNameField.setEditable(false);
			menuPriceField.setEditable(false);
			menuStockField.setEditable(false);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Duplicate found");
			alert.setHeaderText(null);
			alert.setContentText("The automated menu code had existed before."
								+ "You can now update menu with that code");
			alert.showAndWait();
			UpdateMenu updateMenu = new UpdateMenu(stage);
			stage.setScene(updateMenu.getScene());
			stage.show();
		};
		return generated.toString();
	}
	
	private void placement() {
		gridPane.add(menuCodeLabel, 0, 0);
		gridPane.add(generatedCodeLabel, 1, 0);
		gridPane.add(menuNameLabel, 0, 1);
		gridPane.add(menuNameField, 1, 1);
		gridPane.add(menuPriceLabel, 0, 2);
		gridPane.add(menuPriceField, 1, 2);
		gridPane.add(menuStockLabel, 0, 3);
		gridPane.add(menuStockField, 1, 3);
		gridPane.add(homeButton, 0, 4);
		gridPane.add(insertButton, 1, 4);
		GridPane.setHalignment(homeButton, HPos.LEFT);
		GridPane.setHalignment(insertButton, HPos.RIGHT);
		gridPane.setVgap(10);
		gridPane.setHgap(20);
		menuCodeLabel.setStyle(
				"-fx-font-weight: bold;"
				+ "-fx-font-size: 20px");
		generatedCodeLabel.setStyle(
				"-fx-font-size: 20px;");
		menuNameLabel.setStyle(
				"-fx-font-weight: bold;"
				+ "-fx-font-size: 20px");
		menuPriceLabel.setStyle(
				"-fx-font-weight: bold;"
				+ "-fx-font-size: 20px");
		menuStockLabel.setStyle(
				"-fx-font-weight: bold;"
				+ "-fx-font-size: 20px");
		menuNameField.setStyle(
				"-fx-font-size: 20px;");
		menuPriceField.setStyle(
				"-fx-font-size: 20px;");
		menuStockField.setStyle(
				"-fx-font-size: 20px;");
		homeButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: green;"
				+ "-fx-cursor: hand;");
		insertButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: green;"
				+ "-fx-cursor: hand;");
		GridPane.setMargin(homeButton, new Insets(30, 0, 0, 0));
		GridPane.setMargin(insertButton, new Insets(30, 0, 0, 0));
		borderPane.setCenter(gridPane);
		gridPane.setAlignment(Pos.CENTER);
	}
	
	private void event() {
		homeButton.setOnAction(event -> {
			generatedCodeLabel.setText(null);
			menuNameField.clear();
			menuPriceField.clear();
			menuStockField.clear();
			menuNameField.setPromptText(null);
			menuPriceField.setPromptText(null);
			menuStockField.setPromptText(null);
			menuNameField.setDisable(true);
			menuPriceField.setDisable(true);
			menuStockField.setDisable(true);
			homeButton.setDisable(true);
			insertButton.setDisable(true);
			HomePage homePage = new HomePage(stage);
			stage.setScene(homePage.getScene());
			stage.show();
		});
		insertButton.setOnAction(event -> {
			String name = menuNameField.getText();
			int price = 0;
			int stock = 0;
			if(name.isEmpty()) {
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Invalid Input");
				error.setHeaderText(null);
				error.setContentText("Menu name must not be empty");
				error.showAndWait();
				return;
			}
			try {
				price = Integer.parseInt(menuPriceField.getText());
			}catch(NumberFormatException e) {
				menuPriceField.clear();
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Invalid Input");
				error.setHeaderText(null);
				error.setContentText("Menu price must be in number format");
				error.showAndWait();
				return;
			}
			try {
				stock = Integer.parseInt(menuStockField.getText());
			}catch(NumberFormatException e) {
				menuStockField.clear();
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Invalid Input");
				error.setHeaderText(null);
				error.setContentText("Menu stock must be in number format");
				error.showAndWait();
				return;
			}
			Menu menu = new Menu(generatedCodeLabel.getText(), name, price, stock);
			boolean success = false;
			try {
				success = Control.insert(menu);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(success) {
				generatedCodeLabel.setText(idGen());
				menuNameField.clear();
				menuPriceField.clear();
				menuStockField.clear();
				Alert inserted = new Alert(AlertType.INFORMATION);
				inserted.setTitle("Insert successful!");
				inserted.setHeaderText(null);
				inserted.setContentText("Menu has succcesfully been inserted");
				inserted.showAndWait();
			}
		});
	}
}
