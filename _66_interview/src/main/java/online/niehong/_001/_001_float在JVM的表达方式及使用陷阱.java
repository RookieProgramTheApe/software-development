package online.niehong._001;

/**
 * 001 float在jvm表达方式及使用陷阱
 *
 * @author NieHong
 * @date 2024/01/03
 */
public class _001_float在JVM的表达方式及使用陷阱 {
    /**
     * float数据类型在内存中的存储形式为科学计数法，只保留到了小数点后7位
     * @param args
     */
    public static void main(String[] args) {
        float d1 = 314159261f;
        float d2 = d1 + 1;
        System.out.println(d1 == d2);
        System.out.println(d1);
        System.out.println(d2);
    }
}
