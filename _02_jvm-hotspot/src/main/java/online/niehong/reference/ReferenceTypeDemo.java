package online.niehong.reference;



import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 引用类型演示
 *
 * @author NieHong
 * @date 2023/11/27
 */
public class ReferenceTypeDemo {
    public static void main(String[] args) {
        Object object = new Object();
        SoftReference<String> integerSoftReference = new SoftReference<>(new String("我是软引用对象,快运行看看我还在不在了"));
        WeakReference<String> integerWeakReference = new WeakReference<>(new String("我是弱引用对象,快运行看看我还在不在了"));
        PhantomReference<String> integerPhantomReference = new PhantomReference<>(new String("我是虚引用对象,快运行看看我还在不在了"), new ReferenceQueue<>());

        System.gc();
        System.out.println("强引用：" + object);
        System.out.println("软引用：" + integerSoftReference.get());
        System.out.println("弱引用：" + integerWeakReference.get());
        System.out.println("虚引用：" + integerPhantomReference.get());
    }
}
