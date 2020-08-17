package servlet;

import service.Impl.ShoppingCartServiceImpl;
import service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 清空购物车
 * */
public class DeleteAllFromShoppingCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1289746251L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String userName = req.getParameter("userName");

        ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
        boolean result = shoppingCartService.deleteCart(userName);
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
