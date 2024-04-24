package pages;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import system.Control;

public class UpdateMenu {
	protected Stage stage;
	protected Scene scene;
	protected BorderPane borderPane;
	protected GridPane gridPane;
	protected Label choiceLabel;
	protected Label nameLabel;
	protected ListView<String> codeList;
	protected Label newPriceLabel;
	protected Label newStockLabel;
	protected TextField newPriceField;
	protected TextField newStockField;
	protected Button menuButton;
	protected Button homeButton;
	protected Button updateButton;
	
	public UpdateMenu(Stage stage) {
		this.stage = stage;
		stage.setTitle("Update Menu");
		init();
		placement();
		event();
		scene = new Scene(borderPane, 600, 600);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	public void init() {
		stage = new Stage();
		borderPane = new BorderPane();
		gridPane = new GridPane();
		nameLabel = new Label("Choose a menu");
		choiceLabel = new Label("Choose a menu");
		newPriceLabel = new Label("New price");
		newStockLabel = new Label("New stock");
		codeList = new ListView<>();
		codeList.setItems(FXCollections.observableArrayList(Control.retrieveCode()));
		newPriceField = new TextField();
		newStockField = new TextField();
		newPriceField.setPromptText("Insert new price");
		newStockField.setPromptText("Insert new stock");
		homeButton = new Button("Return to Home");
		updateButton = new Button("Update Menu");
	}
	
	public void placement() {
		gridPane.add(codeList, 0, 0);
		gridPane.add(nameLabel, 0, 1);
		gridPane.add(choiceLabel, 1, 1);
		gridPane.add(newPriceLabel, 0, 2);
		gridPane.add(newPriceField, 1, 2);
		gridPane.add(newStockLabel, 0, 3);
		gridPane.add(newStockField, 1, 3);
		gridPane.add(homeButton, 0, 4);
		gridPane.add(updateButton, 1, 4);
		gridPane.setVgap(10);
		GridPane.setHalignment(codeList, HPos.CENTER);
		GridPane.setHalignment(updateButton, HPos.RIGHT);
		codeList.setPrefHeight(300);
		nameLabel.setStyle(
				"-fx-font-weight: bold;"
				+ "-fx-font-size: 20px");
		choiceLabel.setStyle(
				"-fx-font-size: 20px;");
		newPriceLabel.setStyle(
				"-fx-font-weight: bold;"
				+ "-fx-font-size: 20px");
		newStockLabel.setStyle(
				"-fx-font-weight: bold;"
				+ "-fx-font-size: 20px");
		newPriceField.setStyle(
				"-fx-font-size: 20px;");
		newStockField.setStyle(
				"-fx-font-size: 20px;");
		homeButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: green;"
				+ "-fx-cursor: hand;");
		updateButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: green;"
				+ "-fx-cursor: hand;");
		GridPane.setMargin(homeButton, new Insets(30, 0, 0, 0));
		GridPane.setMargin(updateButton, new Insets(30, 0, 0, 0));
		borderPane.setCenter(gridPane);
		gridPane.setAlignment(Pos.CENTER);
	}
	
	public void event() {
		codeList.setOnMouseClicked(event -> {
			choiceLabel.setText(Control.retrieveNameByCode(codeList.getSelectionModel().getSelectedItem()));
		});
		homeButton.setOnAction(event -> {
			codeList.setDisable(true);
			choiceLabel.setText("");
			newPriceField.setDisable(true);
			newStockField.setDisable(true);
			updateButton.setDisable(true);
			homeButton.setDisable(true);
			HomePage homePage = new HomePage(stage);
			stage.setScene(homePage.getScene());
			stage.show();
		});
		updateButton.setOnAction(event -> {
			String updatedMenu = Control.retrieveNameByCode(codeList.getSelectionModel().getSelectedItem());
			int price = 0;
			int stock = 0;
			try {
				price = Integer.parseInt(newPriceField.getText());
			}catch(NumberFormatException e) {
				newPriceField.clear();
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Invalid Input");
				error.setHeaderText(null);
				error.setContentText("Menu price must be in number format");
				error.showAndWait();
				return;
			}
			try {
				stock = Integer.parseInt(newStockField.getText());
			}catch(NumberFormatException e) {
				newStockField.clear();
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Invalid Input");
				error.setHeaderText(null);
				error.setContentText("Menu stock must be in number format");
				error.showAndWait();
				return;
			}
			boolean success = false;
			try {
				success = Control.update(codeList.getSelectionModel().getSelectedItem(), price, stock);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(success) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Update succesful!");
				alert.setHeaderText(null);
				alert.setContentText("Menu " + updatedMenu + " is updated");
				alert.showAndWait();
			}
		});
	}
}
