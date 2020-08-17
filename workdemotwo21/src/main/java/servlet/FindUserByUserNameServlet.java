package servlet;

import bean.UserBean;
import net.sf.json.JSONObject;
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
 * 根据用户名（前端的用户id）查找用户
 * */
@WebServlet("/admin/selectUserByUserName")
public class FindUserByUserNameServlet extends HttpServlet {

    private static final long serialVersionUID = 221746251L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String name = req.getParameter("userName");
        UserService userService = new UserServiceImpl();
        UserBean userBean = userService.findUser(name);
        System.out.println("返回用户："+userBean.toString());
        PrintWriter out = resp.getWriter();
        //
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user",userBean);
        if (userBean != null){
            out.print(jsonObject.toString());
        }else {
            out.print("fail");
        }
        out.flush();
        out.close();
    }
}
