package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbOrder;
import sdkd.com.ec.model.EbOrderDetail;
import sdkd.com.ec.model.EbPOrder;
import sdkd.com.ec.model.EbUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SDUST-132 on 2016/7/9.
 */
public class EbOrderDao extends BaseDao {
    public int saveOrder(EbUser user, double cost) {
        String sql = "insert into easybuy_order (eo_user_id,eo_user_name,eo_user_address,eo_create_time,eo_cost,eo_status,eo_type) values (?,?,?,?,?,1,2)";
        List<String> params = new ArrayList<String>();
        params.add(user.getEuUserId() + "");
        params.add(user.getEuUserName() + "");
        params.add(user.getEuAddress() + "");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//设置日期格式
        String date = df.format(new Date());
        params.add(date + "");
        params.add(cost + "");
        int result = 0;
        result = this.executeModify(sql, params);

        return result;
    }

    public int getOrderId(EbUser user, double cost) {
        int orderId = 0;

        String sql = "select eo_id from easybuy_order where eo_user_id = ? and eo_create_time = ? and eo_cost = ?";
        try {
            List<String> params = new ArrayList<String>();
            params.add(user.getEuUserId() + "");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//设置日期格式
            String date = df.format(new Date());
            params.add(date + "");
            params.add(cost + "");
            ResultSet rs = this.executeSearch(sql, params);
            while (rs.next()) {
                orderId = rs.getInt("eo_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderId;
    }

    /*
    后台订单管理列表
     */
    public List<EbOrder> getOrder() {
        List<EbOrder> orderList = new ArrayList<EbOrder>();
        String sql = "select * from easybuy_order";
        try {
            ResultSet rs = this.executeSearch(sql, null);
            while (rs.next()) {
                EbOrder order = new EbOrder();
                order.setEoId(rs.getInt("eo_id"));
                order.setEoUserId(rs.getInt("eo_user_id"));
                order.setEoUserName(rs.getString("eo_user_name"));
                order.setEoUserAddress(rs.getString("eo_user_address"));
                order.setEoCreateTime(rs.getDate("eo_create_time"));
                order.setEoCost(rs.getDouble("eo_cost"));
                order.setEoStatus(rs.getInt("eo_status"));
                order.setEoType(rs.getInt("eo_type"));
                //添加到集合中
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public int modifyOrder(EbOrder order) {
        String sql = "update easybuy_order set eo_user_name = ? , eo_user_address = ? , eo_cost = ? , eo_status = ? where eo_id = ?";
        int result = 0;

        List<String> params = new ArrayList<String>();
        params.add(order.getEoUserName());
        params.add(order.getEoUserAddress());
        params.add(order.getEoCost() + "");
        params.add(order.getEoStatus() + "");
        params.add(order.getEoId() + "");
        result = this.executeModify(sql, params);
        return result;
    }

    public void deleteOrderById(Integer id) {
        String sql = "delete from easybuy_order where eo_id = ?";
        List<String> params = new ArrayList<String>();
        params.add(id + "");
        this.executeModify(sql, params);
    }

    public List<EbOrder> getOrderBySel(String orderId, String userName) {
        List<EbOrder> orderList = new ArrayList<EbOrder>();
        String sql = null;
        List<String> params = new ArrayList<String>();
        if (orderId == null || "".equals(orderId)) {
            sql = "select * from easybuy_order where eo_user_name = ?";
            params.add(userName);
        } else if (userName == null || "".equals(userName)) {
            sql = "select * from easybuy_order where eo_id = ?";
            params.add(orderId);
        } else {
            sql = "select * from easybuy_order where eo_id = ? and eo_user_name = ?";
            params.add(orderId);
            params.add(userName);
        }
        try {
            ResultSet rs = this.executeSearch(sql, params);
            while (rs.next()) {
                EbOrder order = new EbOrder();
                order.setEoId(rs.getInt("eo_id"));
                order.setEoUserId(rs.getInt("eo_user_id"));
                order.setEoUserName(rs.getString("eo_user_name"));
                order.setEoUserAddress(rs.getString("eo_user_address"));
                order.setEoCreateTime(rs.getDate("eo_create_time"));
                order.setEoCost(rs.getDouble("eo_cost"));
                order.setEoStatus(rs.getInt("eo_status"));
                order.setEoType(rs.getInt("eo_type"));
                //添加到集合中
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<EbPOrder> getOrderByUserId(String userId) {
        List<EbPOrder> pOrderList = new ArrayList<EbPOrder>();
        List<String> params = new ArrayList<String>();
        String sql = "select * from easybuy_order where eo_user_id = ?";
        params.add(userId);
        try {
            ResultSet rs = this.executeSearch(sql, params);
            while (rs.next()) {
                EbPOrder pOrder = new EbPOrder();
                pOrder.setEoId(rs.getInt("eo_id"));
                pOrder.setEoCreateTime(rs.getDate("eo_create_time"));
                pOrder.setEoCost(rs.getDouble("eo_cost"));
                pOrder.setEoStatus(rs.getInt("eo_status"));

                EbOrderDetailDao detailDao = new EbOrderDetailDao();
                List<EbOrderDetail> detailList = new ArrayList<EbOrderDetail>();
                detailList = detailDao.getOrderDetail(pOrder.getEoId().toString());

                pOrder.setProList(detailList);

                //添加到集合中
                pOrderList.add(pOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pOrderList;
    }

}
