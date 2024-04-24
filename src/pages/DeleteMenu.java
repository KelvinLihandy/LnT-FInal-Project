package pages;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import system.Control;

public class DeleteMenu {
	protected Stage stage;
	protected Scene scene;
	protected BorderPane borderPane;
	protected GridPane gridPane;
	protected Label deleteLabel;
	protected Label choiceLabel;
	protected Button homeButton;
	protected Button deleteButton;
	protected ListView<String> codeList;
	
	public DeleteMenu(Stage stage) {
		this.stage = stage;
		stage.setTitle("Delete Menu");
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
		deleteLabel = new Label("Deleted menu");
		choiceLabel = new Label("Choose a menu");
		homeButton = new Button("Return to Home");
		deleteButton = new Button("Delete Menu");
		codeList = new ListView<>();
		codeList.setItems(FXCollections.observableArrayList(Control.retrieveCode()));
	}
	
	public void placement() {
		gridPane.add(codeList, 0, 0);
		gridPane.add(deleteLabel, 0, 1);
		gridPane.add(choiceLabel, 0, 1);
		gridPane.add(homeButton, 0, 2);
		gridPane.add(deleteButton, 0, 2);
		gridPane.setVgap(10);
		GridPane.setHalignment(choiceLabel, HPos.RIGHT);
		GridPane.setHalignment(homeButton, HPos.RIGHT);
		GridPane.setHalignment(deleteButton, HPos.LEFT);
		codeList.setPrefWidth(400);
		codeList.setPrefHeight(300);
		deleteLabel.setStyle(
				"-fx-font-weight: bold;"
				+ "-fx-font-size: 20px");
		choiceLabel.setStyle(
				"-fx-font-size: 20px");
		homeButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: green;"
				+ "-fx-cursor: hand;");
		deleteButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: red;"
				+ "-fx-cursor: hand;");
		GridPane.setMargin(homeButton, new Insets(30, 0, 0, 0));
		GridPane.setMargin(deleteButton, new Insets(30, 0, 0, 0));
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
			deleteButton.setDisable(true);
			homeButton.setDisable(true);
			HomePage homePage = new HomePage(stage);
			stage.setScene(homePage.getScene());
			stage.show();
		});
		deleteButton.setOnAction(event -> {
			String deletedMenu = Control.retrieveNameByCode(codeList.getSelectionModel().getSelectedItem());
			if(deletedMenu == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Deletion failed");
				alert.setHeaderText(null);
				alert.setContentText("No data is selected");
				alert.showAndWait();
			}
			else {
				Alert confirm = new Alert(AlertType.CONFIRMATION);
				confirm.setTitle("Confirm deletion");
				confirm.setHeaderText(null);
				confirm.setContentText("Are you sure to delete " + deletedMenu + "?");
				if(confirm.showAndWait().get() == ButtonType.OK) {
					Boolean success = false;
					try {
						success = Control.delete(deletedMenu);
					}catch(Exception e) {
						e.printStackTrace();
					}
					if(success) {
						codeList.setItems(FXCollections.observableArrayList(Control.retrieveCode()));
						choiceLabel.setText("");						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Deletion succesful!");
						alert.setHeaderText(null);
						alert.setContentText("Menu " + deletedMenu + " is removed");
						alert.showAndWait();
					}
				}
				
			}
		});
	}
}
