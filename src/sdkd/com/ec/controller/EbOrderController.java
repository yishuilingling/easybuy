package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbOrderDao;
import sdkd.com.ec.dao.impl.EbOrderDetailDao;
import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
@WebServlet(name = "EbOrderController")
public class EbOrderController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            list(request, response);
        } else if ("更新".equals(action)) {
            modify(request, response);
        } else if("delete".equals(action)){
            delete(request,response);
        }else if ("查询".equals(action)) {
            select(request, response);
        }else if ("detail".equals(action)) {//根据id查看订单详情
            detail(request, response);
        }else if ("person".equals(action)) {//根据查用户id查看个人订单详情
            person(request, response);
        }else {
            save(request, response);
        }
    }
    /*
       获取订单列表
        */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        EbOrderDao orderDao = new EbOrderDao();
        List<EbOrder> orderList = new ArrayList<EbOrder>();
        orderList = orderDao.getOrder();

        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/manage/order.jsp").forward(request, response);
    }
    /*
      获取查询订单列表
       */
    public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        EbOrderDao orderDao = new EbOrderDao();
        List<EbOrder> orderList = new ArrayList<EbOrder>();
        String orderId = request.getParameter("orderId");
        String userName = request.getParameter("userName");

        orderList = orderDao.getOrderBySel(orderId,userName);
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/manage/order.jsp").forward(request, response);
    }
    /*
      获取订单详情
       */
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String orderId = request.getParameter("orderId");
        EbOrderDao orderDao = new EbOrderDao();
        List<EbOrder> orderList = new ArrayList<EbOrder>();
        orderList = orderDao.getOrderBySel(orderId,null);

        EbOrderDetailDao detailDao = new EbOrderDetailDao();
        List<EbOrderDetail> detailList = new ArrayList<EbOrderDetail>();
        detailList = detailDao.getOrderDetail(orderId);

        request.setAttribute("order", orderList.get(0));
        request.setAttribute("detailList", detailList);
        request.getRequestDispatcher("/manage/order-view.jsp").forward(request, response);
    }
    /*
    获取个人订单列表
     */
    public void person(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        EbOrderDao orderDao = new EbOrderDao();
        List<EbPOrder> pOrderList = new ArrayList<EbPOrder>();
        String userId = request.getParameter("userId");
        pOrderList = orderDao.getOrderByUserId(userId);
        request.setAttribute("pOrderList",pOrderList);
        request.getRequestDispatcher("/personalOrder.jsp").forward(request, response);
    }

    /*
    修改订单
     */
    public void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        EbOrder order = new EbOrder();//获取修改数据
        order.setEoId(Integer.parseInt(request.getParameter("orderId")));
        order.setEoUserName(request.getParameter("name"));
        order.setEoUserAddress(request.getParameter("address"));
        order.setEoCost(Double.valueOf(request.getParameter("cost")).doubleValue());
        order.setEoStatus(Integer.parseInt(request.getParameter("status")));

        EbOrderDao orderDao = new EbOrderDao();
        int res = orderDao.modifyOrder(order);
        if (res > 0) {
            list(request, response);//修改成功
        } else {
                PrintWriter pw=response.getWriter();
                pw.print("<script>alert('修改失败,请重新输入');history.back(-1);</script>");
        }
    }
    /*
   删除订单
    */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
       //获取orderId
        String paramsId = request.getParameter("id");
        int id = Integer.parseInt(paramsId);
        //删除order表
        EbOrderDao orderDao = new EbOrderDao();
        orderDao.deleteOrderById(id);
        //增加库存
        EbProductDao productDao = new EbProductDao();
        productDao.increaseStock(id);
        //删除orderDetail表
        EbOrderDetailDao detailDao = new EbOrderDetailDao();
        detailDao.deleteDetailtById(id);

        list(request, response);//删除成功
    }
    public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        EbShoppingCart cart = (EbShoppingCart) request.getSession().getAttribute("cart");//获取各种值
        double cost = cart.getTotalCost();
        List<EbShoppingCartItem> items = cart.getItems();
        EbUser user = (EbUser) request.getSession().getAttribute("user");

        EbOrderDao orderDao = new EbOrderDao();//保存到订单
        int res1 = orderDao.saveOrder(user, cost);
        int orderId = orderDao.getOrderId(user, cost);

        EbOrderDetailDao detailDao = new EbOrderDetailDao();//保存到订单详情
        int res2 = detailDao.saveOrderDetail(orderId, cart);

        EbProductDao productDao = new EbProductDao();//更新库存
        int res3 = productDao.saveOrderStock(cart);

        for (int i = 0; i < items.size(); i++) {
            cart.removeItem(i);
        }
        if (res1 > 0 && res2 > 0 && res3 > 0) {
            request.getRequestDispatcher("/shopping-result.jsp").forward(request, response);
        } else {
                PrintWriter pw=response.getWriter();
                pw.print("<script>alert('操作失败,请重新输入');history.back(-1);</script>");
        }
    }
}
