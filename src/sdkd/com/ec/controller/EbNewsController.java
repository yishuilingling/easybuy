package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbAnnounceDao;
import sdkd.com.ec.dao.impl.EbNewsDao;
import sdkd.com.ec.dao.impl.EbUserDao;
import sdkd.com.ec.model.EbAnnounce;
import sdkd.com.ec.model.EbComment;
import sdkd.com.ec.model.EbNews;
import sdkd.com.ec.model.EbUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by 10929 on 2016/7/6.
 */
public class EbNewsController extends javax.servlet.http.HttpServlet {
    EbNewsDao newsDao = new EbNewsDao();
    EbAnnounceDao announceDao = new EbAnnounceDao();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        List<EbNews> newslist = newsDao.getNews();//获取新闻列表
        request.setAttribute("newList",newslist);
        List<EbAnnounce> annolist = announceDao.getAnnounce();//获取公告列表
        request.setAttribute("announceList",annolist);
        String action =  request.getParameter("action");//判断显示新闻列表还是新闻详细信息
        if("list".equals(action)){
            list(request,response);
        }else if("details".equals(action)){
            details(request,response);
        } else if("add".equals(action)){
            add_news(request,response);
        }else if("malist".equals(action)){
            list_news(request,response);
        }
        else if("update".equals(action)){
            update_news(request,response);
        }else if("delete".equals(action)){
            delete_news(request,response);
        }else{
            list(request,response);
        }
    }

    //显示新闻列表函数
    public void list(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{
        //跳转
        request.getRequestDispatcher("/announces").forward(request,response);
    }
    //显示新闻详细内容
    public void details(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{
        String ids = request.getParameter("id");//取得相应新闻id
        Integer id = Integer.parseInt(ids);
        EbNews news = newsDao.getDetailsById(id);//根据相应新闻id个到相应新闻
        String userid=request.getParameter("user");//取得登录用户id
        if(userid!=null&&!"".equalsIgnoreCase(userid)){//如果用户登录了执行以下操作
            EbUser user=new EbUser();
            user=new EbUserDao().getUserById(userid);//根据用户id取出相应用户
            request.setAttribute("user",user);
        }
        request.setAttribute("news",news);
        //跳转
        request.getRequestDispatcher("/news-view.jsp").forward(request,response);
    }

    public void add_news(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String title = request.getParameter("title");//取得相应新闻标题
        String content = request.getParameter("content");//取得相应新闻内容
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw=response.getWriter();
        int result= newsDao.addNews(title,content);//判断是否添加成功
        if(result>0)
            request.getRequestDispatcher("news?action=malist").forward(request,response);
        else
            pw.print("<script>alert('添加失败,请重新输入');history.back(-1);</script>");
    }

    public void update_news(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String id = request.getParameter("id");//取得相应新闻编号
        String title = request.getParameter("title");//取得相应新闻标题
        String content = request.getParameter("content");//取得相应新闻内容
        PrintWriter pw=response.getWriter();
        int result= newsDao.updateNews(title,content,id);//判断是否添加成功
        if(result>0)
            request.getRequestDispatcher("news?action=malist").forward(request,response);
        else
            pw.print("<script>alert('修改失败,请重新输入');history.back(-1);</script>");
    }

    public void delete_news(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");//取得相应新闻
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw=response.getWriter();
        int result= newsDao.deleteNews(id);
        if(result>0)
            request.getRequestDispatcher("news?action=malist").forward(request,response);
        else
            pw.print("<script>alert('删除失败,请重试');history.back(-1);</script>");
    }

    public void list_news(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");


        String pageIndexParam=request.getParameter("pageIndex");
        String pageSizeParam=request.getParameter("pageSize");
        int pageIndex=1;
        int pageSize=4;
        if(pageIndexParam!=null&&!"".equalsIgnoreCase(pageIndexParam)){
            pageIndex= Integer.parseInt(pageIndexParam);
        }
        if(pageSizeParam!=null&&!"".equalsIgnoreCase(pageSizeParam)){
            pageSize= Integer.parseInt(pageSizeParam);
        }

        List<EbNews> newList=newsDao.getNewPager(pageIndex,pageSize);
        int count = newsDao.getCommentCount();

        int totalPage  = count % pageSize == 0 ?(count/pageSize):((count/pageSize)+1);

        request.setAttribute("newList",newList);
        request.setAttribute("pageIndex",pageIndex);
        request.setAttribute("totalPage",totalPage);

        request.getRequestDispatcher("manage/news.jsp").forward(request,response);
    }

}
