package sdkd.com.ec.controller;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.dao.impl.EbPCategoryDao;
import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.dao.impl.EbUserDao;
import sdkd.com.ec.model.EbNews;
import sdkd.com.ec.model.EbPCategory;
import sdkd.com.ec.model.EbProduct;
import sdkd.com.ec.model.EbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 10929 on 2016/7/6.
 */
@WebServlet(name = "EbProductController")
public class EbProductController extends HttpServlet {
    EbProductDao productDao = new EbProductDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //待修改
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        EbPCategoryDao pCategory = new EbPCategoryDao();
        List<EbPCategory> categoryList = pCategory.getPCategory();//获取分类列表
        String userId = request.getParameter("user");
        request.setAttribute("categoryList", categoryList);
        //---------
        String action = request.getParameter("action");//判断是显示商品列表还是显示商品信息
        if ("details".equalsIgnoreCase(action)) {
            details(request, response);
        } else {
            list(request, response);
        }
    }

    //显示商品列表
    public void list(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //显示特价商品
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        List<EbProduct> newProductList = productDao.getNewProduct();
        request.setAttribute("newProductList", newProductList);

        //显示热卖商品
        List<EbProduct> HotProductList = productDao.getHotProduct();
        request.setAttribute("hotProductList", HotProductList);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    //显示商品详细信息
    public void details(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String ids = request.getParameter("id");
        Integer id = Integer.parseInt(ids);
        EbProduct product = productDao.getProductById(id);

        int scan=product.getEpScan()+1;
        BaseDao baseDao= new BaseDao();
        String sql="UPDATE easybuy_product set ep_scan='"+scan+"' where ep_id='"+id+"'";
        baseDao.executeModify(sql,null);

        //最近浏览
        List<EbProduct> recentList = (List<EbProduct>) request.getSession().getAttribute("recent");
        if (recentList == null) {
            recentList = new ArrayList<EbProduct>();
        } else {
            Iterator<EbProduct> it = recentList.iterator();
            while(it.hasNext()){
                EbProduct pro = it.next();
                if(pro.getEpId() == id){
                    it.remove();
                }
            }
        }
        if (recentList.size() > 4) {
            recentList.remove(0);
        }
        recentList.add(product);
        request.getSession().setAttribute("recent", recentList);

        request.setAttribute("Product", product);
        //跳转
        request.getRequestDispatcher("/product-view.jsp").forward(request, response);

    }
}
