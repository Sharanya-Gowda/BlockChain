package network;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class NodeClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.print("\nEnter command (ADD patientId,data or GET_CHAIN or EXIT): ");
                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("EXIT")) {
                    break;
                }

                out.println(command); // Send command to server
                
                // ✅ FIX: Ensure we read the **full response** from the server
                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println("Server: " + response);
                    if (!in.ready()) break;  // Stop when the response is complete
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

