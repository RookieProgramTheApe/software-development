package online.niehong._002;

import online.niehong._002.print.Print;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.nio.channels.Pipe;

/**
 * 004 aop五种通知类型
 *
 * @author NieHong
 * @date 2024/01/04
 */
public class _004_AOP的五种通知类型 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("_004_aop_notification.xml");

        Print print = context.getBean(Print.class);
        print.print("AOP还挺不错的呀");
        //
    }
}
