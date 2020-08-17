package servlet;

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
 * 删除图书
 * */
@WebServlet("/deleteBookById")
public class DeleteBookServlet extends HttpServlet {

    private static final long serialVersionUID = 222746251L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String bookId = req.getParameter("bookId");
        BookService bookService = new BookServiceImpl();
        boolean deleteResult = bookService.deleteBook(bookId);
        PrintWriter out = resp.getWriter();
        String result ="fail";
        if (deleteResult){
            result = "success";
        }
        out.write(result);
        out.flush();
        out.close();
    }
}
