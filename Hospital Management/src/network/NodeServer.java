package network;

import model.Blockchain;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NodeServer {
    private static final int PORT = 5000;
    private static Blockchain blockchain = new Blockchain();

    public static void main(String[] args) {
        System.out.println("🚀 Node Server is running on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            ExecutorService pool = Executors.newCachedThreadPool();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ClientHandler(clientSocket, blockchain));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

