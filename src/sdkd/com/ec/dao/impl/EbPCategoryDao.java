package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbPCategory;
import sdkd.com.ec.model.EbProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10929 on 2016/7/7.
 */
public class EbPCategoryDao extends BaseDao {
    public List<EbPCategory> getPCategory(){
        List<EbPCategory> categoryList = new ArrayList<EbPCategory>();
        String sql = "select * from easybuy_product_category";
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbPCategory category = new EbPCategory();
                category.setEpcId(rs.getInt("epc_id"));
                category.setEpcName(rs.getString("epc_name"));
                category.setEpcParentId(rs.getInt("epc_parent_id"));
                //添加到集合中
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
    public List<EbProduct> getProductById(Integer id) {
        List<EbProduct> proDetailsList = new ArrayList<EbProduct>();
        String sql = "select * from easybuy_product where epc_child_id = ?";
        try {
            List<String> params = new ArrayList<String>();
            params.add(id + "");
            ResultSet rs = this.executeSearch(sql, params);
            while (rs.next()) {
                EbProduct product = new EbProduct();
                product.setEpId(rs.getInt("ep_id"));
                product.setEpName(rs.getString("ep_name"));
                product.setEpPrice(rs.getDouble("ep_price"));
                product.setEpDescription(rs.getString("ep_description"));
                product.setEpFileName(rs.getString("ep_file_name"));
                product.setEpScan(rs.getInt("ep_scan"));
                product.setEpStock(rs.getString("ep_stock"));

                proDetailsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proDetailsList;
    }

    public int modifyClass(String id,String name,String parentId){
        String sql1 = "select epc_parent_id from easybuy_product_category where epc_id = ?";
        List<String> params1 = new ArrayList<String>();
        params1.add(id+"");
        String sql = null;
        List<String> params = new ArrayList<String>();
        int result = 0;
        try {
            ResultSet rs = this.executeSearch(sql1, params1);
            while (rs.next()) {
                if(rs.getInt("epc_parent_id") == 0){
                    sql = "update easybuy_product_category set epc_name = ? where epc_id = ?";
                    params.add(name);
                    params.add(id+"");
                }else{
                    sql = "update easybuy_product_category set epc_name = ? ,epc_parent_id = ? where epc_id = ?";
                    params.add(name);
                    params.add(parentId+"");
                    params.add(id+"");
                }
                result = this.executeModify(sql,params);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public int deleteClass(String id,String pId){
        int result = 0;
        String sql = null;
        List<String> params = new ArrayList<String>();
        if(pId == null || "0".equals(pId)){//删除父分类
            sql = "delete from easybuy_product_category where epc_parent_id = ? or epc_id = ?";
            params.add(id);
            params.add(id);
            result = this.executeModify(sql,params);

        }else{//删除子类
            sql = "delete from easybuy_product_category where epc_id = ?";
            params.add(id);
            result = this.executeModify(sql,params);
        }
        return result;
    }
    public int insertClass(String pId,String name){
        int result = 0;
        String sql = null;
        List<String> params = new ArrayList<String>();
        if(pId == null || "0".equals(pId)){//添加父类
            sql = "insert into easybuy_product_category (epc_name) values (?)";
            params.add(name);
            result = this.executeModify(sql,params);

        }else{//添加子类
            sql = "insert into easybuy_product_category (epc_name,epc_parent_id) values (?,?)";
            params.add(name);
            params.add(pId);
            result = this.executeModify(sql,params);
        }
        return result;
    }
}
