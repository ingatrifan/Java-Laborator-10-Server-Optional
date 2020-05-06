package org.lab10;

import GameLogic.Player;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {
    Socket socket;
    ClientThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Client accepted, waiting commands...");
        Player player = new Player(Thread.currentThread().getName());
        CommandParser commandParser = new CommandParser();
        while (true){
            try {
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String command = reader.readLine();
                String response = commandParser.parseCommand(command,player);
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(response);
                if (command.equals("stop"))break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
