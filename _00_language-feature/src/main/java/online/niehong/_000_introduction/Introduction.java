package online.niehong._000_introduction;

import java.io.File;
import java.io.FileFilter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static online.niehong._000_introduction.FilteringApples.filterApples;

/**
 * 导言
 *
 * @author NieHong
 * @date 2023/12/11
 */
public class Introduction<T> {

    public static void main(String[] args) {
        List<FilteringApples.Apple> inventory = Arrays.asList(new FilteringApples.Apple(80, "green"),
                new FilteringApples.Apple(155, "green"),
                new FilteringApples.Apple(120, "red"));

        // 过滤苹果
        filterAppleExample(inventory);


    }


    /**
     * 筛选苹果
     * - 总结：
     * 1. 采用方法引用的方式，可以使调用方的代码变得更简洁，加上意思明确的方法名，可提高代码的可读性
     * 2. 直接传入lambda表达式则更适用于代码简短且逻辑复用很低的情况
     *
     * @param inventory 库存
     */
    public static void filterAppleExample(List<FilteringApples.Apple> inventory) {

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<FilteringApples.Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);

        // [Apple{color='green', weight=155}]
        List<FilteringApples.Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<FilteringApples.Apple> greenApples2 = filterApples(inventory, (FilteringApples.Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApples2);

        // [Apple{color='green', weight=155}]
        List<FilteringApples.Apple> heavyApples2 = filterApples(inventory, (FilteringApples.Apple a) -> a.getWeight() > 150);
        System.out.println(heavyApples2);

        // []
        List<FilteringApples.Apple> weirdApples = filterApples(inventory, (FilteringApples.Apple a) -> a.getWeight() < 80 ||
                "brown".equals(a.getColor()));
        System.out.println(weirdApples);
    }


    /**
     * 过滤金钱
     */
    public void filterMoney(List<BigDecimal> moneys) {
        // JDK1.8之前的写法
        for (BigDecimal money : moneys) {
            if (money.compareTo(new BigDecimal("1000")) > 0) {

            }
        }
    }

    /**
     * 获得所有隐藏文件列表(JDK1.8之前的写法)
     *
     * @param filePath 文件路径
     * @return {@link File[]}
     */
    public static File[] hiddenFiles(String filePath) {
        return new File(filePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
    }

    /**
     * JDK1.8的方法引用写法
     *
     * @param filepath
     * @return
     */
    public static File[] hiddenFilesFeature(String filepath) {
        return new File(filepath).listFiles(File::isHidden);
    }
}

class FilteringApples {

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

}
