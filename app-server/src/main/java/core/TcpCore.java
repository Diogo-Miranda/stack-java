package core;

import client.Client;
import client.TcpClient;
import server.Server;
import server.TcpServer;

public class TcpCore {
    private Server tcpServer;
    private Client tcpClient;

    public TcpCore() {
        this.tcpServer = new TcpServer();
        this.tcpClient = new TcpClient();
    }

    public void setup() {
        Thread tcpClientThread = new Thread(() -> {
            try {
                System.out.println("[Thread-" + Thread.activeCount() + "] Initializing TCP Client...");
                tcpClient.call();
                System.out.println("[Thread-" + Thread.activeCount() + "] Finishing TCP Client...");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Thread tcpServerThread = new Thread(() -> {
            try {
                System.out.println("[Thread-] + " + Thread.activeCount() + "] Initializing TCP Server...");
                tcpServer.run();
                System.out.println("[Thread-" + Thread.activeCount() + "] Finishing TCP Client...");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        tcpServerThread.start();
        tcpClientThread.start();
    }
}
