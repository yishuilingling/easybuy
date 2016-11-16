package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbAnnounce;
import sdkd.com.ec.model.EbUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SDUST-132 on 2016/7/6.
 */
public class EbAnnounceDao extends BaseDao {
    public List<EbAnnounce> getAnnounce(){
        List<EbAnnounce> announceList = new ArrayList<EbAnnounce>();
        String sql = "select * from easybuy_announce order by ea_create_time desc limit 0,7";
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbAnnounce announce = new EbAnnounce();
                announce.setEaId(rs.getInt("ea_id"));
                announce.setEaTitle(rs.getString("ea_title"));
                //添加到集合中
                announceList.add(announce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return announceList;
    }
    public List<EbAnnounce> getAnnounceList(){//按编号顺序显示公告
        List<EbAnnounce> announceList = new ArrayList<EbAnnounce>();
        String sql = "select * from easybuy_announce order by ea_id asc";
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbAnnounce announce = new EbAnnounce();
                announce.setEaId(rs.getInt("ea_id"));
                announce.setEaTitle(rs.getString("ea_title"));
                announce.setEaContent(rs.getString("ea_content"));
                //添加到集合中
                announceList.add(announce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return announceList;
    }


    public EbAnnounce getDetailsById(Integer id){
        String sql = "select * from easybuy_announce where ea_id = ?";
        EbAnnounce announce = new EbAnnounce();
        try {
            List<String> params=new ArrayList<String>();
            params.add(id+"");
            ResultSet rs = this.executeSearch(sql,params);
            while (rs.next()){
                announce.setEaId(rs.getInt("ea_id"));
                announce.setEaTitle(rs.getString("ea_title"));
                announce.setEaContent(rs.getString("ea_content"));
                announce.setEaCreateTime(rs.getTime("ea_create_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return announce;
    }

    public int addAnnounce(String title,String content) {
        String sql = "insert into easybuy_announce(ea_title,ea_content,ea_create_time) values (?,?,?)";
        EbAnnounce Announce = new EbAnnounce();
        int result = 0;
        List<String> params = new ArrayList<String>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");//设置日期格式
        String date = df.format(new Date());
        params.add(title + "");
        params.add(content + "");
        params.add(date + "");
        result = this.executeModify(sql, params);
        return result;
    }

    public int updateAnnounce(String title,String content,String id) {
        String sql = "update easybuy_announce set ea_title=?,ea_content=? where ea_id=?";
        EbAnnounce Announce = new EbAnnounce();
        int result = 0;
        List<String> params = new ArrayList<String>();
        params.add(title + "");
        params.add(content + "");
        try{
            int id_announce=Integer.parseInt(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        params.add(id);
        result = this.executeModify(sql, params);
        return result;
    }

    public int deleteAnnounce(String id) {
        String sql = "delete from easybuy_announce where ea_id=?";
        EbAnnounce Announce = new EbAnnounce();
        int result = 0;
        List<String> params = new ArrayList<String>();
        try{
            int id_announce=Integer.parseInt(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        params.add(id);
        result = this.executeModify(sql, params);
        return result;
    }


    /**
     * 所有后台用户列表（分页）
     * @return
     */
    public List<EbAnnounce> getAnnouncePager(int pageIndex, int pageSize){
        List<EbAnnounce> announceList=new ArrayList<EbAnnounce>();
        String sql = "select * from easybuy_announce limit ?,?";
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
                    EbAnnounce announce=new EbAnnounce();
                    announce.setEaId(rs.getInt("ea_id"));
                    announce.setEaTitle(rs.getString("ea_title"));
                    announce.setEaContent(rs.getString("ea_content"));
                    announce.setEaCreateTime(rs.getTime("ea_create_time"));
                    announceList.add(announce);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return announceList;
    }

    /**
     * 所有后台用户数量
     * @return
     */
    public int getAnnounceCount(){
        int count = 0;
        String sql = "select count(ea_id) from easybuy_announce";
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
