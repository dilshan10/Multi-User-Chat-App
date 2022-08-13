package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Scanner;

public class ClientInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../client/login-Form.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("multi-chat-app");
        primaryStage.setScene(scene);

        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.show();
    }
}
