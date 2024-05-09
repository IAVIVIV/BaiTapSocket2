package BT1;

import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			System.out.println("Server is running...");
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected: " + clientSocket);

				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

				// Gửi các số từ 1 đến 1000 cho client
				for (int i = 1; i <= 1000; i++) {
					out.println(i);
					Thread.sleep(1000); // Đợi 1 giây trước khi gửi số tiếp theo
				}

				out.close();
				clientSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
