package sample;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class Client implements Runnable{
    //With this class the server will handle multiple client at a time
    private BufferedWriter writer;
    private BufferedReader reader;
    /*With the help of these two the server can read and write from server*/
    ArrayList<Client>clients;
    /*In this arraylist all client whose are connected with server will stored*/

    String clientName;
    Client(Socket socket,ArrayList<Client>clients){
        try {
            OutputStreamWriter o=new OutputStreamWriter(socket.getOutputStream());
             writer=new BufferedWriter(o);

            InputStreamReader isr=new InputStreamReader(socket.getInputStream());

            /*Now by using bufferReader we can read client's data*/
            reader=new BufferedReader(isr);



            clientName=reader.readLine();
            this.clients=clients;
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        /*This function wait for client's data to received, after it get any data it will send it to all other clients */
        try {
            String clientData =  reader.readLine() + "\n";
            clientData = clientName + " writes: " + clientData;
            while (clientData != null){
                for(Client client: clients){
                    synchronized (client.writer) {
                        client.writer.write(clientData);
                        client.writer.flush();
                    }
                }
                clientData = reader.readLine() + "\n";
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
