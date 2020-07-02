import java.sql.*;

/**
 * @description: 原生 jdbc 操作数据库
 * @author: Seldom
 * @time: 2020/7/2 23:47
 */
public class JdbcTest {
    public static void main(String[] args) {
        //数据库连接
        Connection connection = null;
        //预编译的Statement，使用预编译的statement的可以提高性能，使用statement操作数据库
        PreparedStatement preparedStatement = null;
        //结果集
        ResultSet resultSet = null;

        try {
            //加载数据库驱动
            //Class.forName("com.mysql.jdbc.Driver");
            //通过数据驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://zq1024.cn:3306/gdu?characterEncoding=utf-8", "seldom", "123456");
            //定义sql语句，其中？标识占位符
            String sql = "select * from banner where bannerId > ?";
            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            //设置参数，第一个参数为sql中参数的序号，第二个参数为设置的参数值
            preparedStatement.setInt(1, 1);
            //向数据库发出sql查询，查询出数据集
            resultSet = preparedStatement.executeQuery();
            //遍历查询结果
            while (resultSet.next()) {
                System.out.println(resultSet.getString("bannerId") + "->" + resultSet.getString("url") + "->" + resultSet.getString("title"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
