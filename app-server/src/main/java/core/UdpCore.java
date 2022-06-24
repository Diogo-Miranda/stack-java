package core;

import client.Client;
import client.UdpClient;
import server.Server;
import server.UdpServer;

public class UdpCore {
    private Server udpServer;
    private Client udpClient;

    public UdpCore() {
        this.udpServer = new UdpServer();
        this.udpClient = new UdpClient();
    }

    public void setup() {
        Thread udpServerThread = new Thread(() -> {
            try {
                System.out.println("[Thread-" + Thread.activeCount() + "] Initializing UDP Server..");
                udpServer.run();
                System.out.println("[Thread-" + Thread.activeCount() + "] Finishing UDP Server..");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Thread udpClientThread = new Thread(() -> {
            try {
                System.out.println("[Thread-" + Thread.activeCount() + "] Initializing UDP Client..");
                udpClient.call();
                System.out.println("[Thread-" + Thread.activeCount() + "] Finishing UDP Client..");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        udpServerThread.start();
        udpClientThread.start();
    }
}
