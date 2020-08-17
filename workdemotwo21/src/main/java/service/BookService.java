package service;

import bean.BookBean;

import java.util.List;

public interface BookService {
    /**
     * 添加图书
     */
    boolean addBook(BookBean bookBean);
    /**
     * 查询所有图书
     * */
    List<BookBean> selectBooks();
    /**
     * 删除图书
     * */
    boolean deleteBook(String bookId);
    /**
     * 根据图书id查询图书
     * */
    BookBean findOneBook(String bookId);
}
