package pages;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import system.Control;

public class HomePage {
	protected Stage stage;
	protected Scene scene;
	protected BorderPane borderPane;
	protected GridPane gridPane;
	protected Button insertMenuButton;
	protected Button viewMenuButton;
	protected Button updateMenuButton;
	protected Button deleteMenuButton;
	protected Button exitButton;
	protected Label greeting;
	
	public HomePage(Stage stage) {
		this.stage = stage;
		stage.setTitle("Home Page");
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
		insertMenuButton = new Button("Insert Menu");
		viewMenuButton = new Button("View Menu");
		updateMenuButton = new Button("Update Menu");
		deleteMenuButton = new Button("Delete Menu");
		exitButton = new Button("Exit");
		greeting = new Label("Welcome to PT Pudding Menu Database");
	}
	
	private void placement() {
		gridPane.add(greeting, 0, 0);
		gridPane.add(insertMenuButton, 0, 1);
		gridPane.add(viewMenuButton, 0, 2);
		gridPane.add(updateMenuButton, 0, 3);
		gridPane.add(deleteMenuButton, 0, 4);
		gridPane.add(exitButton, 0, 5);
		GridPane.setHalignment(insertMenuButton, HPos.CENTER);
		GridPane.setHalignment(viewMenuButton, HPos.CENTER);
		GridPane.setHalignment(updateMenuButton, HPos.CENTER);
		GridPane.setHalignment(deleteMenuButton, HPos.CENTER);
		GridPane.setHalignment(exitButton, HPos.CENTER);
		gridPane.setVgap(20);
		greeting.setStyle(
				"-fx-font-size: 25px;"
				+ "-fx-font-weight: bold;");
		insertMenuButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: green;"
				+ "-fx-cursor: hand;");
		viewMenuButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: green;"
				+ "-fx-cursor: hand;");
		updateMenuButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: green;"
				+ "-fx-cursor: hand;");
		deleteMenuButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: red;"
				+ "-fx-cursor: hand;");
		exitButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: red;"
				+ "-fx-cursor: hand;");
		GridPane.setMargin(exitButton, new Insets(20, 0, 0, 0));
		BorderPane.setMargin(gridPane, new Insets(30));
		borderPane.setCenter(gridPane);
		gridPane.setAlignment(Pos.TOP_CENTER);
	}
	
	private void emptyAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("Menu is empty");
		alert.showAndWait();
		InsertMenu insertMenu = new InsertMenu(stage);
		stage.setScene(insertMenu.getScene());
		stage.show();
	}
	
	private void event() {
		insertMenuButton.setOnAction(event -> {
			InsertMenu insertMenu = new InsertMenu(stage);
			stage.setScene(insertMenu.getScene());
			stage.show();
		});
		viewMenuButton.setOnAction(event -> {
			if(Control.checkMenu() == false) {
				emptyAlert();
			}
			else {
				ViewMenu viewMenu = new ViewMenu(stage);
				stage.setScene(viewMenu.getScene());
				stage.show();
			}
		});
		updateMenuButton.setOnAction(event -> {
			if(Control.checkMenu() == false) {
				emptyAlert();
			}
			else {
				UpdateMenu updateMenu = new UpdateMenu(stage);
				stage.setScene(updateMenu.getScene());
				stage.show();
			}
		});
		deleteMenuButton.setOnAction(event -> {
			if(Control.checkMenu() == false) {
				emptyAlert();
			}
			else {
				DeleteMenu deleteMenu = new DeleteMenu(stage);
				stage.setScene(deleteMenu.getScene());
				stage.show();
			}
		});
		exitButton.setOnAction(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Exit confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Do you want to exit?");
			if(alert.showAndWait().get() == ButtonType.OK) {
				Platform.exit();				
			}
		});
	}
}
