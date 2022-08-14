package client.controller;

import client.data;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class LoginFormController {
    public TextField name;
    public AnchorPane root;

    public void onClick(ActionEvent actionEvent) throws IOException {
        clickOnLogin();
    }
    private void clickOnLogin() throws IOException {
        data.name = name.getText();

        Parent root = FXMLLoader.load(LoginFormController.class.getResource("../views/Chat-room.fxml"));

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

    public void key_entered(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            clickOnLogin();
        }
    }
}
