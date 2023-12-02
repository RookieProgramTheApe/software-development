package online.niehong.bio;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * tcpserver
 * - 说明：先启动Server类，不然客户端连接会被拒绝
 *
 * @author NieHong
 * @date 2023/12/03
 */
public class Server {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(((char) 97));
        @Cleanup ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器已启动，监听端口：9999");
        while (true) {
            @Cleanup Socket accept = serverSocket.accept();
            byte[] bytes = new byte[1024];
            accept.getInputStream().read(bytes);
            System.out.println(new String(bytes).trim());

            OutputStream os = accept.getOutputStream();
            os.write("你是谁".getBytes());
        }
    }
}
