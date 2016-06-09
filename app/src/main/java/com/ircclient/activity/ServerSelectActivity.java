package com.ircclient.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.ListView;
import com.ircclient.Server;
;
import com.ircclient.ServerList;


import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Created by Elina on 09.06.2016..
 */
public class ServerSelectActivity extends Service {
    Server server = new Server("localhost", "localhost", 1500, "box", "box", "box");

    public void connectToServer(ListView servList_) throws IOException {

        try {
            this.connect(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        /*String channel = "#camelot";
        BufferedWriter writer;
        BufferedReader reader;
        String line;
        Socket socket = null;
        try {
            socket = new Socket("chat.camelot.lv", 6667);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer.write("NICK " + "box" + "\r\n");
        writer.write("USER " + "box" + "\r\n");
        writer.flush( );
        line = null;
        while ((line = reader.readLine( )) != null) {
            if (line.indexOf("004") >= 0) {
                // ielogoshanas izdevas
                break;
            }
            else if (line.indexOf("433") >= 0) {
                System.out.println("Nickname is already in use.");
                return;
            }
        }
        writer.write("JOIN " + channel + "\r\n");
        try {
            writer.flush( );
        } catch (IOException e) {
            e.printStackTrace();
        }*/



    public void connect(Server s) {
        final String hostName = s.getServerName();
        final int SERVERPORT = s.getPort();

        Socket socket = new Socket();

        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        }catch (IOException e) {
            System.err.println("Error" + e);
        }
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (IOException e) {
            System.err.println("Error" + e);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ObjectInputStream sInput;
        ObjectOutputStream sOutput = null;
        String userInput = "";

        try { socket = new Socket(hostName,SERVERPORT); }
        catch (Exception e) {
            System.err.println("Error connecting to server:" + e);
        }

        System.out.println("Connection done: " + socket.getInetAddress() + ":" + socket.getPort());

        try {
            sInput = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
        }catch (IOException e) {
            System.err.println("Exception creating new I/O streams: " + e);
        }

        try {sOutput.writeObject("box");
        }catch (IOException e) {
            System.err.println("Exception doing login " + e);
            //disconnect
            try{if(socket != null) socket.close();} catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

