package cn.llman.dao;

import cn.llman.beans.dataobject.Book;
import cn.llman.dao.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author
 * @date 2018/11/14
 */
@Repository
public class BookDao {

    @Autowired
    private BookMapper bookMapper;

    public int addBook(Book book) {
        return bookMapper.insertBook(book);
    }

    public int modifyBookById(String id, Book book) {
        return bookMapper.updateBookById(id, book);
    }

    public Book findBookById(String id) {
        return bookMapper.selectBookById(id);
    }

    public List<Book> findBookListByName(String name) {
        return bookMapper.selectBookListByName(name);
    }

    public List<Book> findAllBook() {
        return bookMapper.selectAllBook();
    }

    public int deleteBookById(String id) {
        return bookMapper.deleteBookById(id);
    }

}
