package bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author junius
 * @date 2023/04/09 12:07
 * @project codeHub
 **/
public class RedisServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(88);
        while (true){
            System.out.println("link waiting");
            Socket socket = serverSocket.accept();
            System.out.println("link success");

            InputStream inputStream = socket.getInputStream();
            String msg = "";
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len=inputStream.read(buffer))!= -1 ){
                System.out.println(new String(buffer,0,len));
            }
            inputStream.close();
            socket.close();
        }
    }
}
