package com.example.handballfrenzy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


//Class for TCP Connection with Singleton and Observer implemented
public class TCPSingleton extends Thread {
    private Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;

    //Observer
    private onMessageListener observer;

    //Static unique instance
    private static TCPSingleton uniqueTCP;

    //Private constructor for singleton implementation
    private TCPSingleton(){

    }
    //Static method for getting the only instance of this class
    public static TCPSingleton getInstance(){
        if(uniqueTCP==null){
            uniqueTCP= new TCPSingleton();
        }
        return uniqueTCP;
    }

    //Method for setting the observer and implementing observer pattern
    public void setObserver(onMessageListener observer) {
        this.observer = observer;
    }

    //Client Initialization
    @Override
    public void run(){
        try {
            //10.0.2.2 Emulator
            //IP 192.168.0.5
            socket = new Socket("10.0.2.2", 5000);

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            while (true) {
                System.out.println("Waiting");
                String line = br.readLine();
                System.out.println("Recieved");
                System.out.println("Msg: " + line);
                observer.messageRecieved(line);
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(){
        new Thread(
                () -> {

                    try {

                        bw.write("msg" + "\n");
                        bw.flush();

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }).start();
    }

}
