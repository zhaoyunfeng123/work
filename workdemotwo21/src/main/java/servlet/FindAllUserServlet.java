package servlet;

import bean.UserBean;
import net.sf.json.JSONArray;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 查询所有用户
 * */

@WebServlet("/admin/selectAllUser")
public class FindAllUserServlet extends HttpServlet {

    private static final long serialVersionUID = 17485466L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        UserService userService = new UserServiceImpl();
        List<UserBean> list = userService.selectAllUser();
        PrintWriter out = resp.getWriter();
        JSONArray jsonArray = JSONArray.fromObject(list);
        out.print(jsonArray);
        out.flush();
        out.close();
    }
}
