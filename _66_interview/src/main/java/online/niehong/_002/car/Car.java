package online.niehong._002.car;

import lombok.Data;

@Data
public class Car {
    /**
     * 品牌名称
     */
    private String BrandName;

    /**
     * 价格
     */
    private String price;

    public Car() {
        System.out.println("car no args constructor");
    }
}
