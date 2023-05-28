package bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author junius
 * @date 2023/04/09 12:07
 * @project codeHub
 **/
public class RedisClient1 {
    public static void main(String[] args) throws IOException {
        System.out.println("client1 linking");
        Socket socket = new Socket("127.0.0.1",88);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("111111".getBytes());
    }
}
