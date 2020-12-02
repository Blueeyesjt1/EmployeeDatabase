import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Programmer Name: Jaden Williams.
 * Description: Main class that initiates program.
 * Date: 9/18/2020 - 10/31/2020.
 */

public class Main extends Application {

  /**
   * Basic main method function.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Loads on start to create windows and UI of program.
   */
  @Override   //Basic start method
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    Scene scene = new Scene(root, 400, 400);
    primaryStage.setTitle("Product Viewer");
    primaryStage.setScene(scene);
    primaryStage.show();

  }
}