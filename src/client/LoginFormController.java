package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class LoginFormController {
    public TextField name;

    public void onClick(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked");
        data.name = name.getText();

        Parent root = FXMLLoader.load(LoginFormController.class.getResource("../client/Chat-room.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        Stage stage= new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle(data.name);
        stage.setOnCloseRequest(e-> {
            ChatRoomController.thread.stop();
            System.exit(0);
        });
        stage.setResizable(false);

        stage.show();
    }


    public void ExitOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void MinimizeOnAction(ActionEvent event) {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}
