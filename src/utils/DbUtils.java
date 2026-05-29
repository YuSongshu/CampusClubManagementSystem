package utils;


import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

//数据库工具类 方法 sql发送
public class DbUtils {

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    private static String url;
    private static String username;
    private static String password;
    private static String driver;

    //静态代码块
    static {
        InputStream stream  = DbUtils.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(stream);
            driver = properties.getProperty("db.driver");
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");

            Class.forName(driver);

            connection = DriverManager.getConnection(url,username,password);
            System.out.println("ok了");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 预处理查询方法 防止sql注入
     * @param sql ? 参数占位
     * @param param 数据
     * @return
     */
    public ArrayList<HashMap<String,Object>>  preparedQuery(String sql,Object... param){
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();

        //声明
        PreparedStatement preparedStatement = null;
        try {

            //1.赋值
            preparedStatement = connection.prepareStatement(sql);

            //2.?赋值
            if (param != null && param.length > 0){
                for (int i = 0; i < param.length;i++){
                    preparedStatement.setObject(i + 1,param[i]);
                }
            }

            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()){  //每一条数据
                HashMap<String,Object> map = new HashMap<>();

                for (int i = 1; i <= metaData.getColumnCount();i++){ //有几列数据
                    String key = metaData.getColumnName(i);
                    Object value = resultSet.getObject(key);
                    map.put(key,value);
                }
                list.add(map);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //释放预处理语句 对象 资源
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return  list;
    }
    /**
     * 通用增删改方法（insert / update / delete）
     */
    public int executeUpdate(String sql, Object... param) {
        int rows = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                pstmt.setObject(i + 1, param[i]);
            }
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rows;
    }

}

