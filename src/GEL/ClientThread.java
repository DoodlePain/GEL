package GEL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Adrian on 10.12.2015.
 */
public class ClientThread implements Runnable{
    BufferedReader br;
    PrintWriter pw;
    Socket s;
    String squad,name,cmd;

    public ClientThread(Socket conn){
        s = conn;
        new Thread(this).start();
    }

    public void run(){
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw = new PrintWriter(s.getOutputStream());
            System.err.println("Connection from : " + s);
            System.err.println("Connection established - initialized I/O methods");
            System.err.println("Waiting for client to select squad.");

            while(true){
                squad = br.readLine();
                if(squad.equalsIgnoreCase("ladri")){
                    pw.println("ok");
                    pw.flush();
                    break;
                }else if(squad.equalsIgnoreCase("guardie")){
                    pw.println("ok");
                    pw.flush();
                    break;
                }
                pw.println("error");
                pw.flush();
            }

            System.err.println("Client selected squad : " + squad);
            System.err.println("Waiting for client username: ");
            do{
                name = br.readLine();
            }while(name.length() == 0 || name == null || name.length() < 2);


        }catch(IOException e){
            System.err.println(e);
        }
    }
}