package sdkd.com.ec.dao;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.log.NullLogger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by sdust on 2016/7/6.
 */
public class BaseDao {
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
//    获取数据库连接
    public Connection getConnection(){
        try {
            //1.加载驱动
            Class.forName(getPro("drive"));
            //2.建立连接
            connection=DriverManager.getConnection(getPro("url"),getPro("username"),"");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * 释放资源
     */
    public void close(){
        try {
            if(resultSet!=null)resultSet.close();
            if(preparedStatement!=null)preparedStatement.close();
            if(connection!=null)connection.close();
        }catch (SQLException E){
        }
    }
    /**
     * 获取配置信息
     * @return
     */
    public String getPro(String key){
        Properties properties=new Properties();
        try {
            InputStream is=this.getClass().getResourceAsStream("/jdbc.properties");
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value=properties.get(key).toString();
        return  value;
    }
    public int executeModify(String sql, List<String> params){
        int result=0;
        try {
            connection=this.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql);
            if(params!=null&&params.size()>0){
                for(int i=0;i<params.size();i++){
                    ps.setString((i+1),params.get(i));
                }
            }
            result=ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public  java.sql.ResultSet executeSearch(String sql, List<String> params){
        java.sql.ResultSet rs=null;
        try {
            connection=this.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql);

            if(params!=null&&params.size()>0){
                for(int i=0;i<params.size();i++){
                    ps.setString((i+1),params.get(i));
                }
            }
            rs= ps.executeQuery();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
