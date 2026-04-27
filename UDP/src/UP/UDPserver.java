package UP;

import java.io.*;
import java.net.*;

public class UDPserver {
    public static void main(String[] args) throws IOException {
        int port = 5001;
        // 1. Create a DatagramSocket to receive packets
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("UDP Server is listening on port " + port);

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            while (true) {
                // 2. Create a DatagramPacket to hold incoming data
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // 3. Receive the packet (blocking call)
                socket.receive(receivePacket);

                // 4. Extract data and sender information
                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received: " + sentence + " from " + receivePacket.getAddress());

                // 5. Prepare a response
                String capitalizedSentence = sentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();

                // 6. Get sender's IP and Port
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // 7. Create response packet and send
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        }
    }
}
