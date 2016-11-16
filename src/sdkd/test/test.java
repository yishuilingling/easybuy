package sdkd.test;

import sdkd.com.ec.dao.BaseDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdust on 2016/7/6.
 */
public class test {

    public static void main(String[] args){
        String sql = "insert into easybuy_user(eu_user_name) values(?)";
        List<String> params=new ArrayList<String>();
        params.add("yin");
        new BaseDao().executeModify(sql,params);
    }
}
