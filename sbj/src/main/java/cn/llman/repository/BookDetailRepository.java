package cn.llman.repository;

import cn.llman.beans.dataobject.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * @author
 * @date 2018/11/19
 */
public interface BookDetailRepository extends JpaRepository<BookDetail,String> {

    BookDetail findByBookId(String bookId);

    @Transactional
    void deleteByBookId(String bookId);

    boolean existsByBookId(String bookId);
}
