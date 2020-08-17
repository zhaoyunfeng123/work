package servlet;

import bean.UserBean;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *修改用户信息
 * */
public class UpdateUserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 11976468251L;

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
        user.setFullName(req.getParameter("fullName"));
        user.setTel(req.getParameter("tel"));
        user.setHobby(req.getParameter("hobby"));
        user.setEmail(req.getParameter("email"));
        user.setProblem(req.getParameter("problem"));
        user.setAnswer(req.getParameter("answer"));
        //更新时间
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateRegister = sdf.format(date);
        user.setModifiedTime(dateRegister);
        //打印提交信息
        System.out.println(user.toString());
        UserService userService = new UserServiceImpl();
        boolean result = userService.updateUserByUserName(user);

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
