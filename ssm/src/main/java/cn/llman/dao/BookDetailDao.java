package cn.llman.dao;

import cn.llman.beans.dataobject.BookDetail;
import cn.llman.dao.mapper.BookDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @date 2018/11/13
 */
@Repository
public class BookDetailDao {

    @Autowired
    private BookDetailMapper bookDetailMapper;

    public int insertBookDetail(BookDetail bookDetail) {
        return bookDetailMapper.insertBookDetail(bookDetail);
    }

    public int updateByCategoryType(String bookId, BookDetail bookDetail) {
        return bookDetailMapper.updateByCategoryType(bookId, bookDetail);
    }

    public BookDetail queryBookDetailByBookId(String bookId) {
        return bookDetailMapper.queryBookDetailByBookId(bookId);
    }

    public int deleteBookDetailByBookId(String bookId) {
        return bookDetailMapper.deleteBookDetailByBookId(bookId);
    }

}
