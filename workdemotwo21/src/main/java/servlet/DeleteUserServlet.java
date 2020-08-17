package servlet;

import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 删除用户
 * */
//@WebServlet("/admin/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    private static final long serialVersionUID = 222227462781L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String userName = req.getParameter("userName");
        UserService userService = new UserServiceImpl();
        boolean result = userService.deleteUserByUserName(userName);
        PrintWriter out = resp.getWriter();
        if (result){
            out.write("success");
        }else {
            out.write("fail");
        }
        out.flush();
        out.close();
    }
}
