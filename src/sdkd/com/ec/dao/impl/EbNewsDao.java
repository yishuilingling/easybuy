package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbComment;
import sdkd.com.ec.model.EbNews;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SDUST-132 on 2016/7/6.
 */
public class EbNewsDao extends BaseDao {
    public List<EbNews> getNews(){
        List<EbNews> newsList = new ArrayList<EbNews>();
        String sql = "select * from easybuy_news order by en_create_time desc limit 0,7";
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbNews news = new EbNews();
                news.setEnId(rs.getInt("en_id"));
                news.setEnTitle(rs.getString("en_title"));
                //添加到集合中
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public List<EbNews> getNewsList(){//按编号顺序显示新闻
        List<EbNews> newsList = new ArrayList<EbNews>();
        String sql = "select * from easybuy_news order by en_id asc";
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbNews news = new EbNews();
                news.setEnId(rs.getInt("en_id"));
                news.setEnTitle(rs.getString("en_title"));
                news.setEnContent(rs.getString("en_content"));
                //添加到集合中
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public EbNews getDetailsById(Integer id){
        String sql = "select * from easybuy_news where en_id = ?";
        EbNews news = new EbNews();
        try {
            List<String> params=new ArrayList<String>();
            params.add(id+"");
            ResultSet rs = this.executeSearch(sql,params);
            while (rs.next()){
                news.setEnId(rs.getInt("en_id"));
                news.setEnTitle(rs.getString("en_title"));
                news.setEnContent(rs.getString("en_content"));
                news.setEnCreateTime(rs.getTime("en_create_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    public int addNews(String title,String content) {
        String sql = "insert into easybuy_news(en_title,en_content,en_create_time) values (?,?,?)";
        EbNews news = new EbNews();
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

    public int updateNews(String title,String content,String id) {
        String sql = "update easybuy_news set en_title=?,en_content=? where en_id=?";
        EbNews news = new EbNews();
        int result = 0;
        List<String> params = new ArrayList<String>();
        params.add(title + "");
        params.add(content + "");
        try{
            int id_news=Integer.parseInt(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        params.add(id);
        result = this.executeModify(sql, params);
        return result;
    }

    public int deleteNews(String id) {
        String sql = "delete from easybuy_news where en_id=?";
        EbNews news = new EbNews();
        int result = 0;
        List<String> params = new ArrayList<String>();
        try{
            int id_news=Integer.parseInt(id);
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
    public List<EbNews> getNewPager(int pageIndex, int pageSize){
        List<EbNews> newList= new ArrayList<EbNews>();
        String sql = "select * from easybuy_news limit ?,?";
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
                    EbNews news=new EbNews();
                    news.setEnId(rs.getInt("en_id"));
                    news.setEnTitle(rs.getString("en_title"));
                    news.setEnContent(rs.getString("en_content"));
                    news.setEnCreateTime(rs.getTime("en_create_time"));
                    newList.add(news);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newList;
    }

    /**
     * 所有后台用户数量
     * @return
     */
    public int getCommentCount(){
        int count = 0;
        String sql = "select count(en_id) from easybuy_news";
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
