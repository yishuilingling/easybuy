package sdkd.com.ec.dao.impl;


import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbNews;
import sdkd.com.ec.model.EbProduct;
import sdkd.com.ec.model.EbUser;
import sun.applet.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10929 on 2016/7/5.
 */
public class EbUserDao extends BaseDao {
    public static void insert_user(EbUser user){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://192.168.130.60:3306/easybuy?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true","root","");

            String sql="insert into easybuy_user(eu_user_name,eu_user_password,eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,eu_address,eu_status) values('"+user.getEuUserName()+"','"+user.getEuUserPassword()+"','"+user.getEuUserSex()+"','"+user.getEuBrithday()+"','"+user.getEuIdentityCode()+"','"+user.getEuEmail()+"','"+user.getEuMobile()+"','"+user.getEuAddress()+"','"+user.getEuStatus()+"')" ;
            System.out.print(sql);
            PreparedStatement ps=connection.prepareStatement(sql);
            int result=ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void updata_user(EbUser user){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://192.168.130.60:3306/easybuy?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true","root","");
           System.out.print(user.getEuIdentityCode());
            String sql="UPDATE easybuy_user set eu_user_name='"+user.getEuUserName()+"', eu_user_password= '"+user.getEuUserPassword()+"',eu_sex='"+user.getEuUserSex()+ "', eu_birthday='"+user.getEuBrithday()+"', eu_identity_code='"+user.getEuIdentityCode()+ "', eu_email='"+user.getEuEmail()+ "', eu_mobile='"+user.getEuMobile()+ "', eu_address='"+user.getEuAddress()+ "', eu_status='"+user.getEuStatus() +"' WHERE eu_user_id='"+user.getEuUserId()+"'" ;
            PreparedStatement ps=connection.prepareStatement(sql);
            int result=ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void delete_user(int id){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://192.168.130.60:3306/easybuy?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true","root","");
            System.out.print("yes");
            String sql="DELETE FROM easybuy_user  WHERE eu_user_id='"+ id+"'";
            PreparedStatement ps=connection.prepareStatement(sql);
            int result=ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void select_easybuy(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://192.168.130.60:3306/easybuy?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true","root","");
            System.out.print("yes");
            String sql="SELECT  * FROM easybuy_user" ;
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            String name=null;
            while (rs.next()){
                name=rs.getString("eu_user_name");
            }
            System.out.print(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<EbUser> getUsers(){
        List<EbUser> usersList = new ArrayList<EbUser>();
        String sql = "select * from easybuy_user";
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbUser user = new EbUser();
                user.setEuUserId(rs.getInt("eu_user_id"));
                user.setEuUserName(rs.getString("eu_user_name"));
                user.setEuUserPassword(rs.getString("eu_user_password"));
                user.setEuUserSex(rs.getString("eu_sex"));
                user.setEuBrithday(rs.getString("eu_birthday"));
                user.setEuIdentityCode(rs.getString("eu_identity_code"));
                user.setEuEmail(rs.getString("eu_email"));
                user.setEuMobile(rs.getString("eu_mobile"));
                user.setEuAddress(rs.getString("eu_address"));
                user.setEuStatus(rs.getInt("eu_status"));
                //添加到集合中
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }
    public EbUser getUserById(String id){
        EbUser user=new EbUser();
        String sql = "select * from easybuy_user where eu_user_id="+id;
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                user.setEuUserId(rs.getInt("eu_user_id"));
                user.setEuUserName(rs.getString("eu_user_name"));
                user.setEuUserPassword(rs.getString("eu_user_password"));
                user.setEuAddress(rs.getString("eu_address"));
                user.setEuBrithday(rs.getString("eu_birthday"));
                user.setEuEmail(rs.getString("eu_email"));
                user.setEuIdentityCode(rs.getString("eu_identity_code"));
                user.setEuUserSex(rs.getString("eu_sex"));
                user.setEuMobile(rs.getString("eu_mobile"));
                user.setEuStatus(rs.getInt("eu_status"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    /**
     * 所有后台用户列表（分页）
     * @return
     */
    public List<EbUser> getUserPager(int pageIndex, int pageSize){
        List<EbUser> userList=new ArrayList<EbUser>();
        String sql = "select * from easybuy_user limit ?,?";
        ResultSet rs = null;
        try {
            Connection con = this.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            int start = (pageIndex * pageSize)-pageSize;
            int x=1;
            ps.setInt(x,start);
            ps.setInt(x+1,pageSize);
            rs = ps.executeQuery();
            try {
                while(rs.next()){
                    EbUser user=new EbUser();
                    user.setEuUserId(rs.getInt("eu_user_id"));
                    user.setEuUserName(rs.getString("eu_user_name"));
                    user.setEuUserPassword(rs.getString("eu_user_password"));
                    user.setEuAddress(rs.getString("eu_address"));
                    user.setEuBrithday(rs.getString("eu_birthday"));
                    user.setEuEmail(rs.getString("eu_email"));
                    user.setEuIdentityCode(rs.getString("eu_identity_code"));
                    user.setEuUserSex(rs.getString("eu_sex"));
                    user.setEuMobile(rs.getString("eu_mobile"));
                    user.setEuStatus(rs.getInt("eu_status"));
                    userList.add(user);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    /**
     * 所有后台用户数量
     * @return
     */
    public int getProductCount(){
        int count = 0;
        String sql = "select count(eu_user_id) from easybuy_user";
        ResultSet rs = this.executeSearch(sql,null);
        try {
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
