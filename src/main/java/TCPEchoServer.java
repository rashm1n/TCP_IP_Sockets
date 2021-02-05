import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServer {
    private static final int BUFSIZE = 32;

    public static void main(String[] args) {
        int servPort = 8000;
        try {
            ServerSocket serverSocket = new ServerSocket(servPort);
            int recvMsgSize;
            byte[] receiveBuf = new byte[BUFSIZE];
            while (true) {
                Socket clientSock = serverSocket.accept();
                System.out.println("client"+clientSock.getRemoteSocketAddress().toString());

                InputStream in = clientSock.getInputStream();
                OutputStream out = clientSock.getOutputStream();

                while ((recvMsgSize = in.read(receiveBuf)) != -1) {
                    out.write(receiveBuf, 0, recvMsgSize);
                    }

                clientSock.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
