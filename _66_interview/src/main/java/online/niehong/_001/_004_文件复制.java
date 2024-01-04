package online.niehong._001;

import java.io.*;
import java.nio.file.Files;

/**
 * 004文件复制
 *
 * @author NieHong
 * @date 2024/01/03
 */
public class _004_文件复制 {
    public static void main(String[] args) {
        // 要复制的源文件
        File source = new File("C:\\workSpace\\workSpace\\backup\\learn-2023\\software-development\\_66_interview\\src\\main\\java\\online\\niehong\\assets\\MySQL.jpg");
        // 复制到哪个文件去
        File target = new File("./MySQL.jpg");

        int byteRead;
        byte[] buf = new byte[1024];
        try (InputStream sourceInputStream = Files.newInputStream(source.toPath());
             OutputStream targetOutputStream = Files.newOutputStream(target.toPath())) {
            while ((byteRead = sourceInputStream.read(buf)) != -1) {
                targetOutputStream.write(buf, 0, byteRead);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
