package online.niehong._002;

import online.niehong._002.car.Car;
import online.niehong._002.user.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 002范围作用与取值
 *
 * @author NieHong
 * @date 2024/01/04
 */
public class _002_Scope的作用与取值 {

    /**
     * 默认情况下，spring bean实例是采用单例模式来创建和管理的，
     * 这意味着对于每个Spring IoC容器中定义的bean，无论请求多少次，都将返回同一个共享实例。
     *
     * 可通过修改scope属性值来修改对象的作用域
     *  singleton: 单例
     *  prototype:
     *     每次请求都会创建新实例：每当客户端（例如控制器、服务类等）通过Spring容器请求一个原型（prototype）作用域的bean时，Spring容器都会生成一个新的bean实例。
     *     状态独立：由于每次请求都会返回不同的bean实例，因此对于有状态的bean来说，每个实例可以拥有独立的状态，互不影响。
     *     资源消耗：相比于单例（Singleton）bean，原型bean可能会消耗更多的系统资源，因为每次创建和销毁对象都需要额外的开销。
     *     无缓存行为：Spring容器不会缓存原型bean的实例，而单例bean则会被缓存并在整个应用上下文中共享。
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:_002_scope.xml");

        User user = context.getBean(User.class);
        User user1 = context.getBean(User.class);
        System.out.println("user对象是否相等：" + (user == user1));

        Car car = context.getBean(Car.class);
        Car car1 = context.getBean(Car.class);
        System.out.println("car对象是否相等：" + (car == car1));
    }
}
