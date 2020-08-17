package servlet;

import bean.UserBean;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * 登录
 * */
//@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1748546L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        UserBean user = new UserBean();

        user.setUserName(req.getParameter("userName"));
        user.setPassword(req.getParameter("password"));
        user.setUserType(req.getParameter("userType"));

        UserService userService = new UserServiceImpl();
        PrintWriter out = resp.getWriter();
        boolean result = userService.login(user);
        if(result){
            // 将用户对象放入session中
            HttpSession session = req.getSession();
            session.setAttribute("user",user.getUserName());
            out.write("success");
        }else {
            out.write("fail");
        }
        out.flush();
        out.close();
    }
}
