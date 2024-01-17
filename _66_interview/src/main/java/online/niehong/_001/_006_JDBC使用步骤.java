package online.niehong._001;

import java.sql.*;

/**
 * 006 jdbc使用步骤
 *
 * @author NieHong
 * @date 2024/01/04
 */
public class _006_JDBC使用步骤 {

    /**
     * 驱动名称
     */
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    /**
     * 连接地址
     */
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/java_001";

    /**
     * 数据库用户名
     */
    public static final String USERNAME = "root";

    /**
     * 数据库密码
     */
    public static final String PASSWORD = "root";



    public static void main(String[] args) throws ClassNotFoundException {

        // 1. 加载驱动
        Class.forName(DRIVER_NAME);

        try (
             // 2. 建立连接
             Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             // 3. 创建命令
             Statement statement = connection.createStatement()) {

            // 4. 处理结果
            ResultSet resultSet = statement.executeQuery("select * from user");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
