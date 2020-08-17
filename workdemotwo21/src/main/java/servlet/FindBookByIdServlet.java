package servlet;

import bean.BookBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.BookService;
import service.Impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * 根据图书id查询图书
 * */
@WebServlet("/selectBookById")
public class FindBookByIdServlet extends HttpServlet {

    private static final long serialVersionUID = 211746251L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String id = req.getParameter("bookId");
        BookService bookService = new BookServiceImpl();
        BookBean book = bookService.findOneBook(id);

        PrintWriter out = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("book",book);
        if(book != null){
            out.print(jsonObject.toString());
        }else {
            out.write("fail");
        }
        out.flush();
        out.close();
    }
}
