package client.controller;

import client.Message;
import client.data;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.Socket;

public class ChatRoomController {
    public static Thread thread;
    public TextArea chatLog;
    public TextField txtMesssage;
    public Label lblUserName;
    public ImageView imageSend;
    public Pane emojePane;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public void initialize() {
        lblUserName.setText(data.name);
        emojePane.setVisible(false);
    }

    public ChatRoomController() {
        try {

            socket = new Socket("localhost", 5000);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());

            dataOutputStream.writeUTF(data.name);
            thread = new Thread(() -> {
                try {

                    JSONParser parser = new JSONParser();

                    while (true) {
                        String newMsgJson = dataInputStream.readUTF();

                        //System.out.println("RE : " + newMsgJson);
                        Message newMsg = new Message();

                        Object object = parser.parse(newMsgJson);
                        JSONObject msg = (JSONObject) object;

                        newMsg.setName((String) msg.get("name"));
                        newMsg.setMessage((String) msg.get("message"));

                        chatLog.appendText(newMsg.getName() + " : " + newMsg.getMessage() + "\n");


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            thread.start();

        } catch (IOException E) {
            E.printStackTrace();
        }

    }

    public void onClickSend() {
        try {
            String msg = txtMesssage.getText();


            JSONObject js = new JSONObject();
            js.put("name", data.name);
            js.put("message", msg);

            String json = js.toJSONString();


            System.out.println(json);

            dataOutputStream.writeUTF(json);
            txtMesssage.setText("");
            txtMesssage.requestFocus();

        } catch (IOException E) {
            E.printStackTrace();
        }

    }

    public void key_entered(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onClickSend();
        }
    }

    public void MinimizeOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void ExitOnAction(ActionEvent event) {
        System.exit(0);
    }

    public void SendImageOnAction(MouseEvent Event) {
    }


    public void ClickOpenEmojePanel(MouseEvent mouseEvent) {
        emojePane.setVisible(true);
    }
}
