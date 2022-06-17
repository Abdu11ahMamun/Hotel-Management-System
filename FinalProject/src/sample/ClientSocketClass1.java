package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClientSocketClass1 extends Application {
    @Override
    public void start(Stage primaryStage244) throws Exception {
        //  Parent root = FXMLLoader.load(getClass().getResource("socketServer.fxml"));
        Parent root;
       root = FXMLLoader.load(getClass().getResource("customerScene.fxml"));

        //primaryStage244.setTitle("First Client");
        primaryStage244.initStyle(StageStyle.UNDECORATED);
        primaryStage244.setScene(new Scene(root, 700, 575));
        primaryStage244.show();
    }
    public static void main(String[] args) {
        launch(args);
      /*  try {
            Socket socket=new Socket("127.0.0.1",9999);
            //Server make a connection with client socket after getting the socket object

            *//*Sending Data through socket object*//*
            OutputStreamWriter osw=new OutputStreamWriter(socket.getOutputStream());
            *//*Writing msg to send*//*
            BufferedWriter writer=new BufferedWriter(osw);

            writer.write("Hello World \n");
            //We could use close(), but that would close the writer
            writer.flush();

            writer.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }*/




    }


}
