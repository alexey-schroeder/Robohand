package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));

        Parent root = (Parent) fxmlLoader.load();
        primaryStage.setTitle("Robohand");
        primaryStage.setScene(new Scene(root));
        Controller controller = fxmlLoader.getController();
        primaryStage.show();
        controller.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
