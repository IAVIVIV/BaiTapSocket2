package BT2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 12345);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			// Nhập username
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter your username: ");
			String username = scanner.nextLine();
			out.println(username);

			// Nhận tin nhắn từ server
			Thread t = new Thread(() -> {
				String message;
				try {
					while ((message = in.readLine()) != null) {
						System.out.println(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			t.start();

			// Gửi tin nhắn từ client tới server
			while (true) {
				String message = scanner.nextLine();
				out.println(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
