package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbAnnounceDao;
import sdkd.com.ec.dao.impl.EbNewsDao;
import sdkd.com.ec.dao.impl.EbUserDao;
import sdkd.com.ec.model.EbAnnounce;
import sdkd.com.ec.model.EbNews;
import sdkd.com.ec.model.EbProduct;
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
public class EbAnnounceController extends javax.servlet.http.HttpServlet {
    EbAnnounceDao announceDao = new EbAnnounceDao();
    EbNewsDao newsDao = new EbNewsDao();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        List<EbAnnounce> annolist = announceDao.getAnnounce();//获取公告列表
        request.setAttribute("announceList",annolist);
        List<EbNews> newslist = newsDao.getNews();//获取新闻列表
        request.setAttribute("newList",newslist);
        String action =  request.getParameter("action");//用于判断是显示列表还是详细内容
        if("list".equals(action)){
            list(request,response);
        }else if("details".equals(action)){
            details(request,response);
        } else if("add".equals(action)){
            add_announce(request,response);
        }else if("manlist".equals(action)){
            list_announce(request,response);
        }
        else if("update".equals(action)){
            update_announce(request,response);
        }else if("delete".equals(action)){
            delete_announce(request,response);
        }else{
            list(request,response);
        }
    }

    //显示公告列表
    public void list(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{
        //跳转
        request.getRequestDispatcher("/product").forward(request,response);
    }

    //显示公告详细信息
    public void details(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String ids = request.getParameter("id");
        Integer id = Integer.parseInt(ids);
        EbAnnounce announce = announceDao.getDetailsById(id);
        String userid=request.getParameter("user");
        if(userid!=null&&!"".equalsIgnoreCase(userid)){
            EbUser user=new EbUser();
            user=new EbUserDao().getUserById(userid);
            request.setAttribute("user",user);
        }
        request.setAttribute("announce",announce);
        //跳转
        request.getRequestDispatcher("/announces-view.jsp").forward(request,response);
    }

    public void add_announce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String title = request.getParameter("title");//取得相应公告标题
        String content = request.getParameter("content");//取得相应公告内容
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw=response.getWriter();
        int result= announceDao.addAnnounce(title,content);//判断是否添加成功
        if(result>0)
            request.getRequestDispatcher("announces?action=manlist").forward(request,response);
        else
            pw.print("<script>alert('添加失败,请重新输入');history.back(-1);</script>");
    }

    public void update_announce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String id = request.getParameter("id");//取得相应公告编号
        String title = request.getParameter("title");//取得相应公告标题
        String content = request.getParameter("content");//取得相应公告内容
        PrintWriter pw=response.getWriter();
        int result= announceDao.updateAnnounce(title,content,id);//判断是否添加成功
        if(result>0)
            request.getRequestDispatcher("announces?action=manlist").forward(request,response);
        else
            pw.print("<script>alert('修改失败,请重新输入');history.back(-1);</script>");
    }

    public void delete_announce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");//取得相应公告编号
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw=response.getWriter();
        int result= announceDao.deleteAnnounce(id);
        if(result>0)
            request.getRequestDispatcher("announces?action=manlist").forward(request,response);
        else
            pw.print("<script>alert('删除失败,请重试');history.back(-1);</script>");
    }

    public void list_announce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

        List<EbAnnounce> announceList=announceDao.getAnnouncePager(pageIndex,pageSize);
        int count = announceDao.getAnnounceCount();

        int totalPage  = count % pageSize == 0 ?(count/pageSize):((count/pageSize)+1);

        request.setAttribute("announceList",announceList);
        request.setAttribute("pageIndex",pageIndex);
        request.setAttribute("totalPage",totalPage);


        request.getRequestDispatcher("manage/announce.jsp").forward(request,response);
    }

}
