package cn.llman.repository;

import cn.llman.beans.dataobject.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author
 * @date 2018/11/19
 */
public interface BookRepository extends JpaRepository<Book,String> {


    /**
     * 根据书名模糊查询书籍列表
     * 值得注意的是：jpa的语法
     * @param name
     * @return 书籍列表
     */
    List<Book> findAllByNameContaining(String name);
}
