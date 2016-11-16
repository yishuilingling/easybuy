package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.model.EbProduct;
import sdkd.com.ec.model.EbShoppingCart;
import sdkd.com.ec.model.EbShoppingCartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
@WebServlet(name = "EbShoppingController")
public class EbShoppingController extends HttpServlet {
    EbProductDao productDao = new EbProductDao();
    EbShoppingCart cart = new EbShoppingCart();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id、index和quantity
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String paramsId = request.getParameter("id");
        String paramsIndex = request.getParameter("index");

        int id = 0;
        if (paramsId != null && !"".equals(paramsId)) {
            id = Integer.parseInt(paramsId);
        }
        int index = 0;
        if (paramsIndex != null && !"".equals(paramsIndex)) {
            index = Integer.parseInt(paramsIndex);
        }

        //判断进行哪种操作
        String action = request.getParameter("action");
        if ("mod".equals(action)) {//网页修改数量
            modify(request, response, index);
        } else if ("remove".equals(action)) {//删除某件商品
            cart.removeItem(index);
        } else{//增加
            cart = insert(request, response, id);
        }
        request.getSession().setAttribute("cart", cart);
        response.sendRedirect("/shopping.jsp");
    }

    public EbShoppingCart modify(HttpServletRequest request, HttpServletResponse response, Integer index) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String paramsQuantity = request.getParameter("quantity");
        long quantity = 0;
        if (paramsQuantity != null && !"".equals(paramsQuantity)) {
            quantity = Integer.parseInt(paramsQuantity);
        }
        cart.modifyQuantity(index, quantity);
        return cart;
    }

    public EbShoppingCart insert(HttpServletRequest request, HttpServletResponse response, Integer id) throws ServletException, IOException {
        boolean issame = false;
        EbProduct product = productDao.getProductById(id);
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        //存储至session
        //存session之前判断是否已经存在
        EbShoppingCart cart = (EbShoppingCart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new EbShoppingCart();
            cart.addItem(product, 1);
        } else {
            //如果存在，查找有无相同的商品，如果有则增加数量
            List<EbShoppingCartItem> items = cart.getItems();
            if (items != null && items.size() > 0) {
                for (EbShoppingCartItem item : items) {
                    EbProduct itemPro = item.getProduct();
                    if (itemPro.getEpId() == id) {
                        item.setQuantity(item.getQuantity() + 1);
                        issame = true;
                    }
                }
            }
            //如果不存在，添加商品，默认数量为1
            if (!issame) {
                cart.addItem(product, 1);
            }
        }
        return cart;
    }
}
