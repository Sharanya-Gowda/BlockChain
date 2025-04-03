package network;

import model.Block;
import model.Blockchain;
import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket socket;
    private Blockchain blockchain;

    public ClientHandler(Socket socket, Blockchain blockchain) {
        this.socket = socket;
        this.blockchain = blockchain;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String request;
            while ((request = in.readLine()) != null) {  // ✅ FIX: Handle **multiple** commands
                System.out.println("📩 Received: " + request);

                if (request.startsWith("ADD")) {
                    String[] parts = request.substring(4).split(",", 2);
                    if (parts.length == 2) {
                        String patientId = parts[0].trim();
                        String data = parts[1].trim();

                        blockchain.addBlock(patientId, data);
                        out.println("✅ Block added successfully for Patient ID: " + patientId);
                    } else {
                        out.println("❌ Invalid format! Use: ADD patientId,data");
                    }
                } 
                else if (request.equals("GET_CHAIN")) {
                    StringBuilder chainData = new StringBuilder();
                    for (Block block : blockchain.getChain()) {
                        chainData.append("🩺 Patient ID: ").append(block.getPatientId())
                                 .append(", Data: ").append(block.getData())
                                 .append(", Block Hash: ").append(block.getHash())
                                 .append(", Previous Hash: ").append(block.getPreviousHash())
                                 .append("\n");
                    }
                    out.println(chainData.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

