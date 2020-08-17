package servlet;

import bean.BookBean;
import net.sf.json.JSONArray;
import service.BookService;
import service.Impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
 * 查询所有图书
 * */
@WebServlet("/admin/selectAllBook")
public class SelectAllBookServlet extends HttpServlet {

    private static final long serialVersionUID = 9746251L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        BookService bookService = new BookServiceImpl();
        List<BookBean> list = bookService.selectBooks();
        System.out.println("++++++++++++img++："+list.toString());
        PrintWriter out = resp.getWriter();
        JSONArray jsonArray = JSONArray.fromObject(list);
        out.print(jsonArray);
        out.flush();
        out.close();
    }
}
