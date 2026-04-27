package UP;

import java.io.*;
import java.net.*;

public class UDPclient {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int port = 5001;

        // 1. Create a DatagramSocket (binds to any available local port)
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress IPAddress = InetAddress.getByName(hostName);
            byte[] sendData;
            byte[] receiveData = new byte[1024];

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter message to send via UDP:");

            String sentence = inFromUser.readLine();
            sendData = sentence.getBytes();

            // 2. Create a DatagramPacket to send to the server's address and port
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

            // 3. Send the packet
            clientSocket.send(sendPacket);
            System.out.println("Sent: " + sentence);

            // 4. Prepare packet to receive response
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // 5. Receive response (blocking call)
            clientSocket.receive(receivePacket);

            // 6. Extract and display response data
            String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("FROM SERVER: " + modifiedSentence);
        }
    }
}
