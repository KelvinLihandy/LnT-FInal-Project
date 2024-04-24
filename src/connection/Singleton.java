package connection;

public class Singleton {
	private static Database database = null;
	
	public static Database create() {
		if(database == null) {
			database = new Database();
		}
		return database;
	}
}
