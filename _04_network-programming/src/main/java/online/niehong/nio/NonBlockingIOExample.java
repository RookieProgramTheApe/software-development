package online.niehong.nio;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO-示例
 *
 * @author NieHong
 * @date 2023/12/03
 */
public class NonBlockingIOExample {

    /**
     * 文件写入
     * - 当前项目文件下新建“nio.txt”文件，并写入内容“NIO，HELLO”
     */
    @Test
    @SneakyThrows
    public void fileWrite() {
        @Cleanup FileOutputStream fs = new FileOutputStream("nio.txt");
        FileChannel channel = fs.getChannel();

        // 分配一个新的字节缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 向缓冲区添加数据
        buffer.put("NIO，HELLO".getBytes());
        // 翻转缓冲区
        buffer.flip();

        // 往通道写入数据
        int write = channel.write(buffer);
        System.out.println(write != 0 ? "字符串写入成功" : "字符串写入失败");
    }

    /**
     * 文件读取
     */
    @Test
    @SneakyThrows
    public void fileRead() {
        @Cleanup FileInputStream is = new FileInputStream("nio.txt");

        FileChannel channel = is.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(is.available());

        channel.read(buffer);
        System.out.println(new String(buffer.array()));
    }

    /**
     * 文件复制
     */
    @Test
    @SneakyThrows
    public void fileCopy() {
        // 被拷贝文件的输入流(从哪里拷贝)
        @Cleanup FileInputStream srcFis = new FileInputStream("nio.txt");
        // 目标文件的输出流(拷贝到哪里去)
        @Cleanup FileOutputStream targetFos = new FileOutputStream("nio-copy.txt");

        // 分别拿到对应的Channel
        FileChannel srcFisChannel = srcFis.getChannel();
        FileChannel targetFosChannel = targetFos.getChannel();

        // 执行文件拷贝
        targetFosChannel.transferFrom(srcFisChannel, 0, srcFisChannel.size());
    }
}
