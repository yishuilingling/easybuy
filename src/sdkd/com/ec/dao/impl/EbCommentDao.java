package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbAnnounce;
import sdkd.com.ec.model.EbComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gkl on 2016/7/12.
 */
public class EbCommentDao extends BaseDao {
    public List<EbComment> getComment(){
        List<EbComment> commentList = new ArrayList<EbComment>();
        String sql = "select * from easybuy_comment";
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbComment comment = new EbComment();
                comment.setEcId(rs.getInt("ec_id"));
                comment.setEcContent(rs.getString("ec_content")) ;
                comment.setEcReply(rs.getString("ec_reply"));
                comment.setEcCreateTime(rs.getString("ec_create_time"));
                comment.setEcNickName(rs.getString("ec_nick_name"));
                //添加到集合中
                commentList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }

    public int addComment(String guestName,String guestContent) {
        String sql = "insert into easybuy_comment(ec_content,ec_create_time,ec_nick_name) values (?,?,?)";
        EbComment comment = new EbComment();
        int result = 0;
        List<String> params = new ArrayList<String>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");//设置日期格式
        String date = df.format(new Date());
        params.add(guestContent + "");
        params.add(date + "");
        params.add(guestName + "");
        result = this.executeModify(sql, params);
        return result;
    }

    public int updateComment(String name,String reply,String id) {
        String sql = "update easybuy_comment set ec_reply=?,ec_reply_time=? ,ec_nick_name=? where ec_id=?";
        EbComment comment = new EbComment();
        int result = 0;
        List<String> params = new ArrayList<String>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");//设置日期格式
        String date = df.format(new Date());
        params.add(reply + "");
        params.add(date + "");
        params.add(name + "");
        try{
            int id_comment=Integer.parseInt(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        params.add(id);
        result = this.executeModify(sql, params);
        System.out.println(sql+reply+name+date+id);
        return result;
    }

    public int deleteComment(String id) {
        String sql = "delete from easybuy_comment where ec_id=?";
        EbComment comment = new EbComment();
        int result = 0;
        List<String> params = new ArrayList<String>();
        try{
            int id_comment=Integer.parseInt(id);
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
    public List<EbComment> getCommentPager(int pageIndex, int pageSize){
        List<EbComment> commentList= new ArrayList<EbComment>();
        String sql = "select * from easybuy_comment limit ?,?";
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
                    EbComment comment = new EbComment();
                    comment.setEcId(rs.getInt("ec_id"));
                    comment.setEcContent(rs.getString("ec_content")) ;
                    comment.setEcReply(rs.getString("ec_reply"));
                    comment.setEcCreateTime(rs.getString("ec_create_time"));
                    comment.setEcNickName(rs.getString("ec_nick_name"));
                    comment.setEcNickName(rs.getString("ec_nick_name"));
                    commentList.add(comment);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentList;
    }

    /**
     * 所有后台用户数量
     * @return
     */
    public int getCommentCount(){
        int count = 0;
        String sql = "select count(ec_id) from easybuy_comment";
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
