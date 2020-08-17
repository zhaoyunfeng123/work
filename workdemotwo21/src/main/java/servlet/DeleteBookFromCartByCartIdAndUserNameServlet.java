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
 * 根据cartID和用户名删除购物车里的商品
 * */
public class DeleteBookFromCartByCartIdAndUserNameServlet extends HttpServlet {

    private static final long serialVersionUID = 21289746251L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String userName = req.getParameter("userName");
        int cartId = Integer.parseInt(req.getParameter("cartId"));

        System.out.println(userName+cartId);

        ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
        boolean result = shoppingCartService.deleteBookFromCartByCartIdAndUserName(userName,cartId);
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
