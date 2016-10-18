package application;
import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class MainFXApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {

			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/view/ATMfront.fxml"));
			Scene scene = new Scene(root,800,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			// we need to give the controller access to the Main app.
			MainController controller=new MainController();
			controller.setMain(this);;
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);

	}
}
