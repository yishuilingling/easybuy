package sdkd.com.ec.controller;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.dao.impl.EbNewsDao;
import sdkd.com.ec.dao.impl.EbUserDao;
import sdkd.com.ec.model.EbProduct;
import sdkd.com.ec.model.EbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by 10929 on 2016/7/8.
 */
@WebServlet(name = "EbUserController")
public class EbUserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("login".equalsIgnoreCase(action)) {
            login(request, response);
        } else if ("register".equalsIgnoreCase(action)) {
            register(request, response);
        } else if ("showUsers".equalsIgnoreCase(action)) {
            showUsers(request, response);
        } else if ("showUser".equalsIgnoreCase(action)) {
            showUser(request, response);
        } else if ("update".equalsIgnoreCase(action)) {
            updateUser(request, response);
            request.getRequestDispatcher("/user?action=showUsers").forward(request, response);
        } else if ("personUpdate".equals(action)) {
            EbUser user = new EbUser();
            user = updateUser(request, response);
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/personal.jsp").forward(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            deleteUser(request, response);
        } else if ("insert".equalsIgnoreCase(action)) {
            insertUser(request, response);
        } else if ("logout".equalsIgnoreCase(action)) {
            request.getSession().removeAttribute("user");
            request.getRequestDispatcher("pca").forward(request, response);
        }

    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = null;
        String password = null;
        String check = null;
        boolean flag = true;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw = response.getWriter();
        name = request.getParameter("userName");
        password = request.getParameter("passWord");
        check = request.getParameter("veryCode");
        HttpSession session = request.getSession();

        EbUserDao userDao = new EbUserDao();
        List<EbUser> list = userDao.getUsers();
        for (EbUser user : list) {
            if (user.getEuUserName().trim().equals(request.getParameter("userName")) && user.getEuUserPassword().trim().equals(password) && check.equalsIgnoreCase(String.valueOf(session.getAttribute("randCheckCode")))) {
                if (1 == user.getEuStatus()) {
                    session.setAttribute("user", user);
                    request.getRequestDispatcher("pca").forward(request, response);
                } else if (2 == user.getEuStatus()) {
                    session.setAttribute("user", user);
                    request.getRequestDispatcher("/manage/index.jsp").forward(request, response);
                }
                flag = false;
            }
        }
        if (flag != false) {
            pw.print("<script>alert('登录失败,请重新登录');history.back(-1);</script>");
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = null;
        String password = null;
        String sex = null;
        String birthday = null;
        String mobile = null;
        String email = null;
        String identityCode = null;
        String address = null;
        String status = null;
        String check = null;
        boolean flag = true;
        boolean flag1 = true;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw = response.getWriter();
        name = request.getParameter("userName");
        password = request.getParameter("passWord");
        sex = request.getParameter("sex");
        birthday = request.getParameter("birthday");
        mobile = request.getParameter("mobile");
        email = request.getParameter("email");
        identityCode = request.getParameter("identityCode");
        address = request.getParameter("address");
        status = request.getParameter("status");
        check = request.getParameter("veryCode");
        HttpSession session = request.getSession();
        EbUserDao userDao = new EbUserDao();
        List<EbUser> list = userDao.getUsers();
        for (EbUser user : list) {
            if (user.getEuUserName().equals(name)) {
                flag1 = false;
            }
        }
        if (flag1 == true && check.equalsIgnoreCase(String.valueOf(session.getAttribute("randCheckCode")))) {
            String sql = "insert into easybuy_user(eu_user_name,eu_user_password,eu_sex,eu_birthday," +
                    "eu_mobile,eu_email,eu_identity_code,eu_address,eu_status) values('" + name + "','" + password + "'," +
                    "'" + sex + "','" + birthday + "','" + mobile + "','" + email + "','" + identityCode + "','" + address + "','" + status + "')";
            BaseDao baseDao = new BaseDao();
            int res = baseDao.executeModify(sql, null);
            request.getSession().setAttribute("userId",res);
            response.sendRedirect("reg-result.jsp");
            flag = false;
        } else if (flag == true) {
            pw.print("<script>alert('注册失败,请重新输入');history.back(-1);</script>");

        }
    }

    public void showUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        EbUserDao userDao = new EbUserDao();

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

        List<EbUser> userList=userDao.getUserPager(pageIndex,pageSize);
        int count = userDao.getProductCount();

        int totalPage  = count % pageSize == 0 ?(count/pageSize):((count/pageSize)+1);

        request.setAttribute("userList",userList);
        request.setAttribute("pageIndex",pageIndex);
        request.setAttribute("totalPage",totalPage);

        request.getRequestDispatcher("/manage/user.jsp").forward(request, response);
    }

    public void showUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String userId = request.getParameter("userId");
        EbUserDao userDao = new EbUserDao();
        EbUser user = new EbUser();
        user = userDao.getUserById(userId);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/manage/user-modify.jsp").forward(request, response);
    }

    public EbUser updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        int userId = Integer.parseInt(request.getParameter("userId"));
        String name = request.getParameter("name");
        String password = request.getParameter("passWord");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String identityCode = request.getParameter("identityCode");
        int status = Integer.parseInt(request.getParameter("status"));

        EbUser user = new EbUser();
        user.setEuUserId(userId);
        user.setEuUserName(name);
        user.setEuUserPassword(password);
        user.setEuUserSex(sex);
        user.setEuBrithday(birthday);
        user.setEuMobile(mobile);
        user.setEuAddress(address);
        user.setEuStatus(status);
        user.setEuEmail(email);
        user.setEuIdentityCode(identityCode);

        EbUserDao.updata_user(user);

        return user;
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String id = request.getParameter("userId");
        EbUserDao.delete_user(Integer.parseInt(id));

        request.getRequestDispatcher("/user?action=showUsers").forward(request, response);

    }

    public void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

//        int userId= Integer.parseInt(request.getParameter("userId"));
        String name = request.getParameter("name");
        String password = request.getParameter("passWord");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String identityCode = request.getParameter("identityCode");
        int status = Integer.parseInt(request.getParameter("status"));


        EbUser user = new EbUser();
//        user.setEuUserId(userId);
        user.setEuUserName(name);
        user.setEuUserPassword(password);
        user.setEuUserSex(sex);
        user.setEuBrithday(birthday);
        user.setEuMobile(mobile);
        user.setEuAddress(address);
        user.setEuStatus(status);
        user.setEuEmail(email);
        user.setEuIdentityCode(identityCode);

        EbUserDao.insert_user(user);


        request.getRequestDispatcher("/user?action=showUsers").forward(request, response);
    }
}
