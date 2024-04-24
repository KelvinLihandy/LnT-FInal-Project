package pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import menu.Menu;
import system.Control;

public class ViewMenu {
	protected Stage stage;
	protected Scene scene;
	protected BorderPane borderPane;
	protected GridPane gridPane;
	protected Button insertPageButton;
	protected Button homeButton;
	protected TableView<Menu> menuTable;
	protected ObservableList<Menu> menuTableList;
	protected TableColumn<Menu, String> codeColumn;
	protected TableColumn<Menu, String> nameColumn;
	protected TableColumn<Menu, String> priceColumn;
	protected TableColumn<Menu, String> stockColumn;
	
	public ViewMenu(Stage stage) {
		this.stage = stage;
		stage.setTitle("View Menu");
		init();
		placement();
		event();
		scene = new Scene(borderPane, 600, 600);
	}

	public Scene getScene() {
		return this.scene;
	}
	
	@SuppressWarnings("unchecked")
	public void init() {
		stage = new Stage();
		borderPane = new BorderPane();
		gridPane = new GridPane();
		insertPageButton = new Button("Insert a New Menu");
		homeButton = new Button("Return to Home");
		menuTable = new TableView<>();
		menuTableList = FXCollections.observableArrayList(Control.retrieveMenu());
		codeColumn = new TableColumn<>("Menu Code");
		nameColumn = new TableColumn<>("Menu Name");
		priceColumn = new TableColumn<>("Menu Price");
		stockColumn = new TableColumn<>("Menu Stock");
		codeColumn.setCellValueFactory(cell -> cell.getValue().codeProperty());
		nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
		priceColumn.setCellValueFactory(cell -> cell.getValue().priceProperty());
		stockColumn.setCellValueFactory(cell -> cell.getValue().stockProperty());
		menuTable.setItems(menuTableList);
		menuTable.getColumns().addAll(codeColumn, nameColumn, priceColumn, stockColumn);
	}
	
	public void placement() {
		gridPane.add(menuTable, 0, 0);
		gridPane.add(insertPageButton, 0, 1);
		gridPane.add(homeButton, 0, 1);
		GridPane.setHalignment(insertPageButton, HPos.RIGHT);
		GridPane.setHalignment(homeButton, HPos.LEFT);
		menuTable.setPrefSize(400, 400);
		gridPane.setVgap(10);
		insertPageButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: green;"
				+ "-fx-cursor: hand;");
		homeButton.setStyle(
				"-fx-font-size: 20px;"
				+ "-fx-base: green;"
				+ "-fx-cursor: hand;");
		GridPane.setMargin(homeButton, new Insets(30, 0, 0, 0));
		GridPane.setMargin(insertPageButton, new Insets(30, 0, 0, 0));
		borderPane.setCenter(gridPane);
		gridPane.setAlignment(Pos.CENTER);
	}
	
	public void event() {
		homeButton.setOnAction(event -> {
			menuTable.setVisible(false);
			insertPageButton.setDisable(true);
			homeButton.setDisable(true);
			HomePage homePage = new HomePage(stage);
			stage.setScene(homePage.getScene());
			stage.show();
		});
		insertPageButton.setOnAction(event -> {
			menuTable.setVisible(false);
			insertPageButton.setDisable(true);
			homeButton.setDisable(true);
			InsertMenu insertMenu = new InsertMenu(stage);
			stage.setScene(insertMenu.getScene());
			stage.show();
		});
	}
}
