package online.niehong.bio;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * TCP-Client
 *
 * @author NieHong
 * @date 2023/12/03
 */
// 忽略IDEA里无限循环的警告
@SuppressWarnings("InfiniteLoopStatement")
public class Client {
    @SneakyThrows
    public static void main(String[] args) {
        while (true) {
            @Cleanup Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream os = socket.getOutputStream();
            os.write(new Scanner(System.in).nextLine().getBytes());

            byte[] bytes = new byte[1024];
            InputStream is = socket.getInputStream();
            is.read(bytes);

            System.out.println(new String(bytes).trim());
        }
    }
}
