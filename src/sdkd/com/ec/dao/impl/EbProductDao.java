package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbOrder;
import sdkd.com.ec.model.EbProduct;
import sdkd.com.ec.model.EbShoppingCart;
import sdkd.com.ec.model.EbShoppingCartItem;
import sun.plugin.PluginURLJarFileCallBack;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10929 on 2016/7/7.
 */
public class EbProductDao extends BaseDao {
//    private String sql;
//    private List<String> params;
//    public EbProductDao(String sql,List<String> params){
//        this.sql=sql;
//        this.params=params;
//    }
//    public List<EbProduct> getProduct(){
//        List<EbProduct> productList = new ArrayList<EbProduct>();
//        try {
//            ResultSet rs = this.executeSearch(sql,params);
//            while (rs.next()){
//                EbProduct product = new EbProduct();
//                product.setEpId(rs.getInt("ep_id"));
//                product.setEpName(rs.getString("ep_name"));
//                product.setEpPrice(rs.getDouble("ep_price"));
//                product.setEpDescription(rs.getString("ep_description"));
//                product.setEpScan(rs.getInt("ep_scan"));
//                product.setEpStock(rs.getString("ep_stock"));
////                System.out.print(product.getEpcId()+product.getEpName()+product.getEpPrice());
//                productList.add(product);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return productList;
//    }

    public List<EbProduct> getNewProduct() {
        List<EbProduct> newProductList = new ArrayList<EbProduct>();
        String sql = "select * from easybuy_product where ep_discount=1 limit 0,8";
        try {
            ResultSet rs = this.executeSearch(sql, null);
            while (rs.next()) {
                EbProduct product = new EbProduct();
                product.setEpId(rs.getInt("ep_id"));
                product.setEpName(rs.getString("ep_name"));
                product.setEpPrice(rs.getDouble("ep_price"));
                product.setEpDescription(rs.getString("ep_description"));
                product.setEpFileName(rs.getString("ep_file_name"));
                product.setEpScan(rs.getInt("ep_scan"));
                product.setEpStock(rs.getString("ep_stock"));
                newProductList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newProductList;
    }

    public List<EbProduct> getHotProduct() {
        List<EbProduct> hotProductList = new ArrayList<EbProduct>();
        String sql = "select * from easybuy_product order by ep_scan desc limit 0,12";
        try {
            ResultSet rs = this.executeSearch(sql, null);
            while (rs.next()) {
                EbProduct product = new EbProduct();
                product.setEpId(rs.getInt("ep_id"));
                product.setEpName(rs.getString("ep_name"));
                product.setEpPrice(rs.getDouble("ep_price"));
                product.setEpDescription(rs.getString("ep_description"));
                product.setEpFileName(rs.getString("ep_file_name"));
                product.setEpScan(rs.getInt("ep_scan"));
                product.setEpStock(rs.getString("ep_stock"));
                hotProductList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotProductList;
    }

    public EbProduct getProductById(Integer id) {
        EbProduct product = new EbProduct();
        String sql = "select * from easybuy_product where ep_id = ?";
        try {
            List<String> params = new ArrayList<String>();
            params.add(id + "");
            ResultSet rs = this.executeSearch(sql, params);
            while (rs.next()) {
                product.setEpId(rs.getInt("ep_id"));
                product.setEpName(rs.getString("ep_name"));
                product.setEpDescription(rs.getString("ep_description"));
                product.setEpPrice(rs.getDouble("ep_price"));
                product.setEpStock(rs.getString("ep_stock"));
                product.setEpcId(rs.getInt("epc_id"));
                product.setEpcChildId(rs.getInt("epc_child_id"));
                product.setEpFileName(rs.getString("ep_file_name"));
                product.setEpScan(rs.getInt("ep_scan"));
                product.setEp_discount(rs.getInt("ep_discount"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * 所有商品列表（分页）
     * @return
     */
    public List<EbProduct> getProductPager(int pageIndex,int pageSize){
        //pageIndex 1 2 3 4 5 6
        List<EbProduct> productList = new ArrayList<EbProduct>();
        String sql = "select * from easybuy_product limit ?,?";
        ResultSet rs = null;
        try {
            Connection con = this.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            int start = (pageIndex * pageSize)-pageSize;
            int x=1;
            ps.setInt(x,start);
            ps.setInt(x+1,pageSize);
            rs = ps.executeQuery();
            try {
                while(rs.next()){
                    EbProduct product = new EbProduct();
                    product.setEpcId(rs.getInt("epc_id"));
                    product.setEpcChildId(rs.getInt("epc_child_id"));
                    product.setEpDescription(rs.getString("ep_description"));
                    product.setEp_discount(rs.getInt("ep_discount"));
                    product.setEpFileName(rs.getString("ep_file_name"));
                    product.setEpId(rs.getInt("ep_id"));
                    product.setEpName(rs.getString("ep_name"));
                    product.setEpPrice(rs.getDouble("ep_price"));
                    product.setEpStock(rs.getString("ep_stock"));
                    product.setEpScan(rs.getInt("ep_scan"));
                    productList.add(product);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    /**
     * 所有商品数量
     * @return
     */
    public int getProductCount(){
        int count = 0;
        String sql = "select count(ep_id) from easybuy_product";
        ResultSet rs = this.executeSearch(sql,null);
        try {
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int saveOrderStock(EbShoppingCart cart) {

        String sql = "update easybuy_product set ep_stock = ? where ep_id = ?";
        String sql1 = "select ep_stock from easybuy_product where ep_id = ?";//获取原库存
        int result = 0;
        for (EbShoppingCartItem item : cart.getItems()) {
            int oldStock = 0;
            try {
                List<String> params1 = new ArrayList<String>();
                params1.add(item.getProduct().getEpId() + "");
                ResultSet rs = this.executeSearch(sql1, params1);
                while (rs.next()) {
                    result = 1;
                    oldStock = rs.getInt("ep_stock");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            long currentStock = oldStock - item.getQuantity();//新库存

            List<String> params = new ArrayList<String>();
            params.add(currentStock + "");
            params.add(item.getProduct().getEpId() + "");
            this.executeModify(sql, params);
        }
        return result;
    }
    public  void increaseStock(Integer id){
        String sql1="select ep_id,eod_quantity from easybuy_order_detail where eo_id = ?";//获取quantity
        String sql2 = "select ep_stock from easybuy_product where ep_id = ?";//获取原库存
        String sql3 = "update easybuy_product set ep_stock = ? where ep_id = ?";//增加库存

        List<String> params1 = new ArrayList<String>();
        params1.add(id + "");
        ResultSet rs = this.executeSearch(sql1,params1);

        try{
            while(rs.next()){
                List<String> params2 = new ArrayList<String>();
                params2.add(rs.getInt("ep_id") + "");
                ResultSet rs2 = this.executeSearch(sql2,params2);
                while(rs2.next()){
                    int oldStock = rs2.getInt("ep_stock");
                    List<String> params3 = new ArrayList<String>();
                    int currentStock = oldStock + rs.getInt("eod_quantity");

                    params3.add(currentStock + "");
                    params3.add(rs.getInt("ep_id") + "");
                    this.executeModify(sql3, params3);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EbProduct> getProduct(){
        List<EbProduct> productList = new ArrayList<EbProduct>();
        String sql = "select * from easybuy_product";
        try{
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbProduct product = new EbProduct();
                product.setEpId(rs.getInt("ep_id"));
                product.setEpName(rs.getString("ep_name"));
                product.setEpPrice(rs.getDouble("ep_price"));
                product.setEpDescription(rs.getString("ep_description"));
                product.setEpFileName(rs.getString("ep_file_name"));
                product.setEpScan(rs.getInt("ep_scan"));
                product.setEpStock(rs.getString("ep_stock"));
                product.setEp_discount(rs.getInt("ep_discount"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }


    public static void updateProudct(EbProduct product){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.130.60:3306/easybuy?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true","root","");
            System.out.print("yes");
            String sql="UPDATE easybuy_product set ep_name='"+product.getEpName()+"', ep_description= '"+product.getEpDescription()+"',ep_price='"+product.getEpPrice()+ "', ep_stock='"+product.getEpStock()+ "', epc_id='"+product.getEpcId()+ "', epc_child_id='"+product.getEpcChildId()+ "', ep_file_name='"+product.getEpFileName()+ "', ep_discount='"+product.getEp_discount()+"', ep_scan='"+product.getEpScan() +"' WHERE ep_id='"+product.getEpId()+"'" ;
            System.out.print(sql);
            PreparedStatement ps=connection.prepareStatement(sql);
            int result=ps.executeUpdate();
            System.out.print("yes");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteProduct(int id){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://192.168.130.60:3306/easybuy?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true","root","");
            System.out.print("yes");
            String sql="DELETE FROM easybuy_product  WHERE ep_id= '"+ id+"'";
            PreparedStatement ps=connection.prepareStatement(sql);
            int result=ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertProduct(EbProduct product){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://192.168.130.60:3306/easybuy?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true","root","");
            System.out.print("yes");
            String sql="insert into easybuy_product(ep_name,ep_description,ep_price,ep_stock,epc_id,epc_child_id,ep_file_name,ep_discount,ep_scan) values('"+product.getEpName()+"','"+product.getEpDescription()+"','"+product.getEpPrice()+"','"+product.getEpStock()+"','"+product.getEpcId()+"','"+product.getEpcChildId()+"','"+product.getEpFileName()+"','"+product.getEp_discount()+"','"+product.getEpScan()+"')" ;
            System.out.print(sql);
            PreparedStatement ps=connection.prepareStatement(sql);
            int result=ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
