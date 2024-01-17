package online.niehong._001;

/**
 * 003找出1到1000内质数
 *
 * @author NieHong
 * @date 2024/01/03
 */
public class _003_找出1到1000内的质数 {

    /**
     * 什么是质数？
     * 在大于1的自然数中，只能被1和它自身锁整除的数字，称为质数
     * @param args
     */
    public static void main(String[] args) {
        boolean primeNumberFlag;
        for (int i = 2; i < 1000; i++) {
            primeNumberFlag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    primeNumberFlag = false;
                    break;
                }
            }
            if (primeNumberFlag) {
                System.out.println(i);
            }
        }
    }
}
