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

/**
 * 添加图书
 */
//@WebServlet("/admin/addBook")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 189746251L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        BookBean book = new BookBean();
        book.setBookId(req.getParameter("bookId"));
        book.setBookName(req.getParameter("bookName"));
        book.setAuthor(req.getParameter("author"));
        book.setPublisher(req.getParameter("publisher"));
        book.setPrice(Double.parseDouble(req.getParameter("price")));
        book.setStock(Integer.parseInt(req.getParameter("stock")));
        book.setInfo(req.getParameter("info"));
        System.out.println("添加图书信息：" + book.toString());
        //获取图片
//        book.setImg(req.getParameter("img"));
        BookService bookService = new BookServiceImpl();
        PrintWriter out = resp.getWriter();
        String result = "fail";
        boolean s = bookService.addBook(book);
        System.out.println("--------BookServlet---------"+s);
        if (s) {
            result = "success";
            out.write(result);
            out.flush();
            out.close();
        }
    }
}
