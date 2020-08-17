package servlet;

import bean.ShoppingCartBean;
import net.sf.json.JSONArray;
import service.Impl.ShoppingCartServiceImpl;
import service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
 * 查询购物车
 * */
public class SelectAllCartServlet extends HttpServlet {

    private static final long serialVersionUID = 97468251L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String name = req.getParameter("userName");
        ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
        List<ShoppingCartBean> list = shoppingCartService.selectAllCart(name);

        System.out.println(list.toString());
        PrintWriter out = resp.getWriter();
        JSONArray jsonArray = JSONArray.fromObject(list);
        out.print(jsonArray);
        out.flush();
        out.close();
    }
}
