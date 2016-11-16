package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbPCategoryDao;
import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.dao.impl.EbUserDao;
import sdkd.com.ec.model.EbPCategory;
import sdkd.com.ec.model.EbProduct;
import sdkd.com.ec.model.EbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by 10929 on 2016/7/7.
 */
@WebServlet(name = "EbPCategoryController")
public class EbPCategoryController extends HttpServlet {
    EbPCategoryDao pCategory = new EbPCategoryDao();
    EbProductDao productDao=new EbProductDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        String action = request.getParameter("action");
        if ("list".equals(action)) {
            list(request, response);
            //跳转到后台分类管理productClass.jsp
            request.getRequestDispatcher("/manage/productClass.jsp").forward(request, response);
        } else if ("details".equals(action)) {//前台某子分类商品
            detailsList(request, response);
        } else if ("更新".equals(action)) {
            update(request, response);
        } else if ("delete".equals(action)) {
            delete(request, response);
        } else if ("增加".equals(action)) {
            insert(request, response);
        }else {
            list(request, response);
            //跳转到新闻列表,即首页
            request.getRequestDispatcher("/news").forward(request, response);
        }
    }
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EbPCategory> categoryList = pCategory.getPCategory();//获取分类列表
        request.getSession().setAttribute("categoryList", categoryList);

    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String ids = request.getParameter("id");
        String pIds = request.getParameter("pId");
        int res = pCategory.deleteClass(ids,pIds);
        if(res>0){
            list(request,response);
            request.getRequestDispatcher("/manage/productClass.jsp").forward(request, response);
        }else{
            PrintWriter pw=response.getWriter();
            pw.print("<script>alert('删除失败,请重新输入');history.back(-1);</script>");
        }
    }
    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String pIds = request.getParameter("parentId");
        String name = request.getParameter("className");
        int res = pCategory.insertClass(pIds,name);
        if(res>0){
            list(request,response);
            request.getRequestDispatcher("/manage/productClass.jsp").forward(request, response);
        }else{
            PrintWriter pw=response.getWriter();
            pw.print("<script>alert('添加失败,请重新输入');history.back(-1);</script>");
        }
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String pId = request.getParameter("parentId");
        String id = request.getParameter("classId");
        String name = request.getParameter("className");

        int res = pCategory.modifyClass(id, name, pId);
        if (res > 0) {
            list(request,response);
            request.getRequestDispatcher("/manage/productClass.jsp").forward(request, response);
        }
        else{
            PrintWriter pw=response.getWriter();
            pw.print("<script>alert('修改失败,请重新输入');history.back(-1);</script>");
        }
    }

    public void detailsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String ids = request.getParameter("id");
        String name = request.getParameter("name");//二级分类名
        String parentName = request.getParameter("parentName");//一级分类名

        Integer id = Integer.parseInt(ids);

        List<EbProduct> proDetailsList = pCategory.getProductById(id);//二级分类下的商品列表

        String userid = request.getParameter("user");
        if (userid != null && !"".equalsIgnoreCase(userid)) {
            EbUser user = new EbUser();
            user = new EbUserDao().getUserById(userid);
            request.setAttribute("user", user);
        }

        request.setAttribute("name", name);
        request.setAttribute("parentName", parentName);

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


        //List<EbProduct> productList=productDao.getProductPager(pageIndex,pageSize);
        int count = productDao.getProductCount();

        int totalPage  = count % pageSize == 0 ?(count/pageSize):((count/pageSize)+1);

        request.setAttribute("proDetailsList",proDetailsList);
        request.setAttribute("pageIndex",pageIndex);
        request.setAttribute("totalPage",totalPage);  //总记录数
        System.out.print(totalPage);
        //跳转页面
        request.getRequestDispatcher("/product-list.jsp").forward(request,response);

    }
}
