package BT2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    static List<Socket> clients = new ArrayList<>();
    
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);
                System.out.println("Client connected: " + clientSocket);
                
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;
    
    public ClientHandler(Socket socket) {
        try {
            clientSocket = socket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            // Nhập username
            out.println("Enter your username:");
            username = in.readLine();
            out.println("Welcome to the chat, " + username + "!");
            
            broadcast(username + " has joined the chat.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                broadcast(username + ": " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void broadcast(String message) {
        for (Socket client : Server.clients) {
            try {
                PrintWriter clientOut = new PrintWriter(client.getOutputStream(), true);
                clientOut.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
