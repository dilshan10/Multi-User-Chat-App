package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static ArrayList<Client> client = new ArrayList();
    public static DataOutputStream dataOutputStream;
    public DataInputStream dataInputStream;

    Server(){
        String name;
        Socket socket;

        try {
            ServerSocket servSock = new ServerSocket(5000);

            while(true) {
                socket = servSock.accept();
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                name = dataInputStream.readUTF() ;
                Client user = new Client(name, dataOutputStream, dataInputStream);
                System.out.println("Connected : " + name);
                client.add(user);

                String enter_message = "{ \"name\" : \"" + "[ SERVER NOTICE ]" + "\", \"message\" : \"" + name +" Connected" + "\"}";
                //System.out.println(enter_message);
                List<Client> entry = Server.client;
                for (Client cli : entry) {
                    DataOutputStream edos = cli.getDos();
                    edos.writeUTF(enter_message);
                }

                System.out.println("[Current User : " + Server.client.size() + "]");

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
