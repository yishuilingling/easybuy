package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbCommentDao;
import sdkd.com.ec.model.EbAnnounce;
import sdkd.com.ec.model.EbComment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by gkl on 2016/7/12.
 */
@WebServlet(name = "EbCommentController")
public class EbCommentController extends HttpServlet {
    EbCommentDao commentDao = new EbCommentDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action =  request.getParameter("action");//用于判断是显示列表还是详细内容
        if("list".equals(action)){
            list(request,response);
            request.getRequestDispatcher("guestbook.jsp").forward(request, response);
        }else if("add".equals(action)){
            add_comment(request,response);
        } else if("malist".equals(action)){
            maList(request,response);

        }else if("update".equals(action)){
           update_comment(request,response);
        }else{
            delete_comment(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        List commentList = commentDao.getComment();
        request.setAttribute("commentList", commentList);
    }

    public void maList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        List<EbComment> commentList=commentDao.getCommentPager(pageIndex,pageSize);
        int count = commentDao.getCommentCount();

        int totalPage  = count % pageSize == 0 ?(count/pageSize):((count/pageSize)+1);

        request.setAttribute("commentList",commentList);
        request.setAttribute("pageIndex",pageIndex);
        request.setAttribute("totalPage",totalPage);

        request.getRequestDispatcher("manage/guestbook.jsp").forward(request, response);
    }

    public void add_comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String guestName = request.getParameter("guestName");//取得留言昵称
        String guestContent = request.getParameter("guestContent");//取得留言内容
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw=response.getWriter();
        int result= commentDao.addComment(guestName,guestContent);//判断是否添加成功
        if(result>0)
            request.getRequestDispatcher("/comment?action=list").forward(request,response);
        else
            pw.print("<script>alert('添加失败,请重新输入');history.back(-1);</script>");
    }

    public void delete_comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");//取得相应新闻标题
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw=response.getWriter();
        int result= commentDao.deleteComment(id);
        if(result>0)
            request.getRequestDispatcher("/comment?action=malist").forward(request,response);
        else
            pw.print("<script>alert('删除失败,请重试');history.back(-1);</script>");
    }

    public void update_comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String id = request.getParameter("id");//取得相应留言编号
        String  name= request.getParameter("name");//取得相应留言昵称
        String reply = request.getParameter("replyContent");//取得相应回复内容
        PrintWriter pw=response.getWriter();
        int result= commentDao.updateComment(name,reply,id);//判断是否修改成功
        if(result>0)
            request.getRequestDispatcher("/comment?action=malist").forward(request,response);
        else
            pw.print("<script>alert('修改失败,请重新输入');history.back(-1);</script>");
    }
}
