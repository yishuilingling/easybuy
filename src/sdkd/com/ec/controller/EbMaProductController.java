package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.model.EbProduct;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10929 on 2016/7/12.
 */
@WebServlet(name = "EbMaProductController")
@MultipartConfig(location = "C:\\Users\\10929\\Desktop\\实训最终\\wy\\web\\images")
public class EbMaProductController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String action=request.getParameter("action");

        if("showProducts".equalsIgnoreCase(action)) {
            showProducts(request, response);
        }else if("showProduct".equalsIgnoreCase(action)){
            showProduct(request,response);
        }else if("updata".equalsIgnoreCase(action)){
            updataProduct(request,response);
        }else if("delete".equalsIgnoreCase(action)){
            deleteProduct(request,response);
        }else if("insert".equalsIgnoreCase(action)){
            insertProduct(request,response);
        }
    }

    public void showProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        EbProductDao productDao=new EbProductDao();


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

        List<EbProduct> productList=productDao.getProductPager(pageIndex,pageSize);
        int count = productDao.getProductCount();

        int totalPage  = count % pageSize == 0 ?(count/pageSize):((count/pageSize)+1);

        request.setAttribute("productList",productList);
        request.setAttribute("pageIndex",pageIndex);
        request.setAttribute("totalPage",totalPage);


        request.getRequestDispatcher("/manage/product.jsp").forward(request,response);
    }

    public void showProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String productId=request.getParameter("productId");
       EbProductDao productDao=new EbProductDao();
        EbProduct product=new EbProduct();
        product=productDao.getProductById(Integer.valueOf(productId));
        request.setAttribute("product",product);
        request.getRequestDispatcher("/manage/product-modify.jsp").forward(request,response);
    }

    public void updataProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        String productId=request.getParameter("productId");
        String productName=request.getParameter("productName");
        String epcId=request.getParameter("epcId");
        String epcChildId=request.getParameter("epcChildId");
        String epDescription=request.getParameter("epDescription");
        String photo=request.getParameter("photo");
        String productPrice=request.getParameter("productPrice");
        String productStock=request.getParameter("productStock");
        String discount=request.getParameter("discount");
        String scan=request.getParameter("productScan");


        EbProduct product=new EbProduct();
        product.setEpId(Integer.parseInt(productId));
        product.setEpName(productName);
        product.setEpDescription(epDescription);
        product.setEpcId(Integer.parseInt(epcId));
        product.setEpcChildId(Integer.parseInt(epcChildId));
        product.setEpPrice(Double.parseDouble(productPrice));
        product.setEpStock(productStock);
        product.setEp_discount(Integer.parseInt(discount));
        product.setEpScan(Integer.parseInt(scan));
        product.setEpFileName(photo);



        EbProductDao.updateProudct(product);

        request.getRequestDispatcher("/manageProduct?action=showProducts").forward(request,response);

    }

    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String id=request.getParameter("productId");
        System.out.print(id);
        EbProductDao.deleteProduct(Integer.parseInt(id));

        request.getRequestDispatcher("/manageProduct?action=showProducts").forward(request,response);
    }

    public void insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");




        String productName=request.getParameter("productName");
        String epcId=request.getParameter("epcId");
        String epcChildId=request.getParameter("epcChildId");
        String epDescription=request.getParameter("epDescription");
        String photo=request.getParameter("photo");
        String productPrice=request.getParameter("productPrice");
        String productStock=request.getParameter("productStock");
        String discount=request.getParameter("discount");
        String scan=request.getParameter("productScan");



        EbProduct product=new EbProduct();
        product.setEpName(productName);
        product.setEpDescription(epDescription);
        product.setEpcId(Integer.parseInt(epcId));
        product.setEpcChildId(Integer.parseInt(epcChildId));
        product.setEpPrice(Double.parseDouble(productPrice));
        product.setEpStock(productStock);
        product.setEp_discount(Integer.parseInt(discount));
        product.setEpScan(Integer.parseInt(scan));
        product.setEpFileName(photo);


        EbProductDao.insertProduct(product);

        request.getRequestDispatcher("/manageProduct?action=showProducts").forward(request,response);
    }

}



