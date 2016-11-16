package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbOrder;
import sdkd.com.ec.model.EbOrderDetail;
import sdkd.com.ec.model.EbShoppingCart;
import sdkd.com.ec.model.EbShoppingCartItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class EbOrderDetailDao extends BaseDao {
    public int saveOrderDetail(Integer orderId, EbShoppingCart cart) {
        String sql = "insert into easybuy_order_detail (eo_id,ep_id,eod_quantity,eod_cost) values (?,?,?,?)";
        int result = 0;
        for (EbShoppingCartItem item : cart.getItems()) {
            List<String> params = new ArrayList<String>();
            params.add(orderId + "");
            params.add(item.getProduct().getEpId() + "");
            params.add(item.getQuantity() + "");
            params.add(item.getCost() + "");

            result = this.executeModify(sql, params);
        }
        return result;
    }
    public void deleteDetailtById(Integer id) {
        String sql = "delete from easybuy_order_detail where eo_id = ?";
        List<String> params = new ArrayList<String>();
        params.add(id + "");
        this.executeModify(sql, params);
    }
    public List<EbOrderDetail> getOrderDetail(String orderId){
        List<EbOrderDetail> detailList = new ArrayList<EbOrderDetail>();
        String sql = "select * from easybuy_order_detail where eo_id = ?";
        try{
            List<String> params = new ArrayList<String>();
            params.add(orderId);
            ResultSet rs = this.executeSearch(sql,params);
            while(rs.next()){
                EbOrderDetail detail = new EbOrderDetail();
                detail.setEodId(rs.getInt("eod_id"));
                detail.setEoId(rs.getInt("eo_id"));
                detail.setEpId(rs.getInt("ep_id"));
                detail.setEodQuantity(rs.getInt("eod_quantity"));
                detail.setEodCost(rs.getDouble("eod_cost"));
                String sql1 = "select ep_name from easybuy_product where ep_id = ?";
                List<String> params1 = new ArrayList<String>();
                params1.add(detail.getEpId()+"");
                ResultSet rs1 = this.executeSearch(sql1,params1);
                while(rs1.next()){
                    detail.setEpName(rs1.getString("ep_name"));
                }
                detailList.add(detail);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return detailList;
    }
}
