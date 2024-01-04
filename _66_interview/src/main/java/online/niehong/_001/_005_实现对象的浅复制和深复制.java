package online.niehong._001;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 005实现对象浅复制和深复制
 *
 * @author NieHong
 * @date 2024/01/03
 */
public class _005_实现对象的浅复制和深复制 {
    /**
     * 浅复制：只对对象及变量值进行复制，引用对象地址不变
     *  - 浅复制的对象，对象里的引用类型是指向同一个内存地址
     *
     * 深复制：不仅对象及变量值进行复制，引用对象也进行复制
     *
     */
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Car benz = new Car("Benz");

        List<String> list = new ArrayList<>();
        list.add("AMD");
        list.add("Smart");
        benz.setList(list);

        Car clone = (Car) benz.clone();
        System.out.println("-----浅复制-----");
        System.out.println("浅复制的对象：" + clone);

        list.add("Maybach(迈巴赫)");
        benz.setList(list);
        System.out.println("修改后的原对象：" + benz);
        System.out.println("浅复制的对象：" + clone);
        System.out.println("原对象List字段的hashcode：" + benz.getList().hashCode());
        System.out.println("浅复制的对象List字段的hashcode：" + clone.getList().hashCode());


        System.out.println("\n\n\n-----深复制-----");
        list.add("AMD");
        list.add("Smart");
        benz.setList(list);

        Car deepCopyObj = benz.deepCopy();
        System.out.println("深复制的对象：" + deepCopyObj);

        list.add("EQ");
        benz.setList(list);
        System.out.println("修改后的原对象：" + benz);
        System.out.println("深复制的对象：" + deepCopyObj);
        System.out.println("原对象List字段的hashcode：" + benz.getList().hashCode());
        System.out.println("深复制的对象List字段的hashcode：" + deepCopyObj.getList().hashCode());
    }

    static class Car implements Cloneable, Serializable {
        private String Brand;
        private List list;

        public Car(String brand) {
            Brand = brand;
        }

        public String getBrand() {
            return Brand;
        }

        public List getList() {
            return list;
        }

        public void setList(List list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "Brand='" + Brand + '\'' +
                    ", list=" + list +
                    '}';
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        /**
         * 深层拷贝
         *
         * @return {@link Car}
         */
        public Car deepCopy() throws IOException, ClassNotFoundException {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            return ((Car) ois.readObject());
        }
    }
}
