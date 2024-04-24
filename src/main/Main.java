package main;

import connection.Database;
import connection.Singleton;
import javafx.application.Application;
import javafx.stage.Stage;
import pages.HomePage;

public class Main extends Application{
	Database database = Singleton.create();
	public static void main(String[] args) {
		new Main();
		launch(args);
	}
	
	public Main() {
		database.createTable();
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		HomePage homePage = new HomePage(arg0);
		arg0.setScene(homePage.getScene());
		arg0.show();
	}
}