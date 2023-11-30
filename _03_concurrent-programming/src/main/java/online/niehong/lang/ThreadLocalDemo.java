package online.niehong.lang;

import lombok.Data;

/**
 * 线程本地演示
 *
 * @author NieHong
 * @date 2023/11/30
 */
public class ThreadLocalDemo {

    static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    // 设置ThreadLocal变量值
    static {
        THREAD_LOCAL.set("65535");
    }


    public static void main(String[] args) {
        // 通过创建新线程来测试在其它线程是否能访问到属于当前线程的ThreadLocal变量
        Example example = new Example();
        Thread thread = new Thread(example, "example");
        thread.start();


        // 对于不要需要的ThreadLocal变量，应该手动移除掉
        System.out.println("移除前: " + ThreadLocalDemo.THREAD_LOCAL.get());
        ThreadLocalDemo.THREAD_LOCAL.remove();
        System.out.println("移除后: " + ThreadLocalDemo.THREAD_LOCAL.get());
    }
}


/**
 * 示例
 *
 * @author NieHong
 * @date 2023/12/01
 */
@Data
class Example implements Runnable {
    public Example() {
        System.out.println("线程名称: " + Thread.currentThread().getName() +
                " , ThreadLocal变量值: " + ThreadLocalDemo.THREAD_LOCAL.get());
    }

    @Override
    public void run() {
        System.out.println("线程名称: " + Thread.currentThread().getName() +
                " , ThreadLocal变量值: " + ThreadLocalDemo.THREAD_LOCAL.get());
    }
}
