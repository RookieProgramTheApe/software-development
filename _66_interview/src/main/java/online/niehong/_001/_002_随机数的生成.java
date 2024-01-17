package online.niehong._001;

import java.util.Random;

/**
 * 002随机数生成
 *
 * @author NieHong
 * @date 2024/01/03
 */
public class _002_随机数的生成 {
    private static final int MIN = 30;
    // 由于nextInt只包含指定值之间的数，所以这里需要写成101
    private static final int MAX = 101;
    public static void main(String[] args) {
        System.out.println(RandomInt1());
        System.out.println(RandomInt2());
    }

    private static int RandomInt1() {
        return new Random().nextInt(MAX - MIN) + MIN;
    }

    private static int RandomInt2() {
        return (int) (Math.random() * (MAX - MIN)) + MIN;
    }
}
