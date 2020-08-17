package servlet;

import bean.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户退出
 * */
//@WebServlet("/loginOut")
public class LoginExitServlet extends HttpServlet {
    private static final long serialVersionUID = 1579846238L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        //获取session
        HttpSession session = req.getSession();
        // 获取用户对象
        String userName = (String)session.getAttribute("user");
        //判断用户是否有效
        if (userName != null){
            // 将用户对象逐出session
            session.invalidate();
            out.write("success");
        }else {
            out.write("fail");
        }
        out.flush();
        out.close();
    }
}
