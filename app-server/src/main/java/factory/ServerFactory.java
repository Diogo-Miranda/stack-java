package factory;

import core.TcpCore;
import core.UdpCore;

public class ServerFactory {
    public static void setup() {
        TcpCore tcpCore = new TcpCore();
        UdpCore udpCore = new UdpCore();

        tcpCore.setup();
        udpCore.setup();
    }
}
