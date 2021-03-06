package server;

import server.Server;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.net.*;

public class UdpServer implements Server {
    private static int portaServidor = 6789;

    @Override
    public void run() throws Exception {
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, true);
        TargetDataLine microphone;
        SourceDataLine speakers;
        try {
            microphone = AudioSystem.getTargetDataLine(format);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int numBytesRead;
            int CHUNK_SIZE = 1024;
            byte[] data = new byte[microphone.getBufferSize() / 5];
            microphone.start();


            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
            speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            speakers.open(format);
            speakers.start();


            // Configure the ip and port
            String hostname = "localhost";
            int port = 6067;

            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();
            byte[] buffer = new byte[1024];
            for(;;) {
                numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
                //  bytesRead += numBytesRead;
                // write the mic data to a stream for use later
                out.write(data, 0, numBytesRead);
                // write mic data to stream for immediate playback
                speakers.write(data, 0, numBytesRead);
                DatagramPacket request = new DatagramPacket(data,numBytesRead, address, port);
                socket.send(request);

            }

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
