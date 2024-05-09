package BT1;

import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 12345);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String number;
			while ((number = in.readLine()) != null) {
				System.out.println("Received: " + number);
			}

			in.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
