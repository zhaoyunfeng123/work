package servlet;

import bean.BookBean;
import bean.ShoppingCartBean;
import bean.UserBean;
import service.BookService;
import service.Impl.BookServiceImpl;
import service.Impl.ShoppingCartServiceImpl;
import service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 添加购物车
 * */
public class AddBookToCartServlet extends HttpServlet {

    private static final long serialVersionUID = 18974601251L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String bookId = req.getParameter("bookId");
        String userName = req.getParameter("userName");
        BookService bookService = new BookServiceImpl();
        BookBean book = bookService.findOneBook(bookId);
        Double price = book.getPrice();
        String bookName = book.getBookName();

        ShoppingCartBean cartBean = new ShoppingCartBean();
        cartBean.setBookId(bookId);
        cartBean.setUserName(userName);
        cartBean.setNumber(1);////////////设置购买数量
        cartBean.setPrice(price);
        cartBean.setBookName(bookName);

        ShoppingCartService cartService = new ShoppingCartServiceImpl();
        boolean result = cartService.addBook(cartBean);
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
