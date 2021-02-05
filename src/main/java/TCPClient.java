import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

/*
 * Communicates with an echo server
 *
 * */
public class TCPClient {
    public static void main(String[] args) {
        try {
            String networkAddr = "127.0.0.1";
            int port = 8000;

            String data = "rasd";
            byte[] dataStream = data.getBytes(StandardCharsets.UTF_8);

            Socket socket = new Socket(networkAddr, port);
            InputStream in = socket.getInputStream();
            OutputStream ou = socket.getOutputStream();

            ou.write(dataStream);

            // Receive the same string back from the server
            int totalBytesRcvd = 0; // Total bytes received so far
            int bytesRcvd; // Bytes received in last read
            while (totalBytesRcvd < dataStream.length) {
                if ((bytesRcvd = in.read(dataStream, totalBytesRcvd,
                        dataStream.length - totalBytesRcvd)) == -1)
                    throw new SocketException("Connection closed prematurely");
                totalBytesRcvd += bytesRcvd;
            } // data array is full

            System.out.println("Received: " + data);

            socket.close(); // Close the socket and its streams

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
