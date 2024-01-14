package online.niehong._002;

import online.niehong._002.car.Car;
import online.niehong._002.user.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 003懒惰init属性作用
 *
 * @author NieHong
 * @date 2024/01/04
 */
public class _003_lazy_init属性的作用 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:_003_lazy_init.xml");

        // 只有显式的获取bean对象时IOC容器才会去创建该对象
        // Car carLazyInit = (Car) context.getBean("carLazyInit");
    }
}
