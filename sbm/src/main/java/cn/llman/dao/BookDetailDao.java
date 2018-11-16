package cn.llman.dao;

import cn.llman.beans.dataobject.BookDetail;
import cn.llman.dao.mapper.BookDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author
 * @date 2018/11/14
 */
@Repository
public class BookDetailDao {

    @Autowired
    private BookDetailMapper bookDetailMapper;

    public int addBookDetail(BookDetail bookDetail) {
        return bookDetailMapper.insertBookDetail(bookDetail);
    }

    public int modifyByBookId(String bookId, BookDetail bookDetail) {
        return bookDetailMapper.updateByBookId(bookId, bookDetail);
    }

    public BookDetail findBookDetailByBookId(String bookId) {
        return bookDetailMapper.selectBookDetailByBookId(bookId);
    }

    public int deleteBookDetailByBookId(String bookId) {
        return bookDetailMapper.deleteBookDetailByBookId(bookId);
    }

}
