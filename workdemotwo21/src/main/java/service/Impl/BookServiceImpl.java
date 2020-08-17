package service.Impl;

import bean.BookBean;
import dao.BookDao;
import service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    /**
     * 添加图书
     */
    @Override
    public boolean addBook(BookBean bookBean) {
        BookDao bookDao = new BookDao();
        return bookDao.saveBook(bookBean);
    }
    /**
     * 查询所有图书
     * */
    @Override
    public List<BookBean> selectBooks() {
        BookDao bookDao = new BookDao();
        return bookDao.findAllBooks();
    }
    /**
     * 删除图书
     * */
    @Override
    public boolean deleteBook(String bookId) {
        BookDao bookDao = new BookDao();
        return bookDao.remove(bookId);
    }
    /**
     * 根据图书id查询图书
     * */
    @Override
    public BookBean findOneBook(String bookId) {
        BookDao bookDao = new BookDao();
        return bookDao.findBookById(bookId);
    }
}
