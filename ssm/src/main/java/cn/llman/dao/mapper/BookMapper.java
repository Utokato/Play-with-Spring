package cn.llman.dao.mapper;

import cn.llman.beans.dataobject.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author
 * @date 2018/11/12
 */
@Component
public interface BookMapper {

    /**
     * 保存或修改书籍
     *
     * @param book
     * @return
     */
    int saveBook(Book book);

    /**
     * 根据bookId 修改书籍信息
     *
     * @param id
     * @param book
     * @return
     */
    int updateBookById(@Param("id") String id,
                       @Param("book") Book book);

    /**
     * 根据id查询书籍
     *
     * @param id
     * @return
     */
    Book queryBookById(String id);

    /**
     * 根据name模糊查询书籍
     *
     * @param name
     * @return
     */
    List<Book> queryBookListByName(@Param("name")String name);

    /**
     * 查询书籍列表
     *
     * @return
     */
    List<Book> queryBookList();

    /**
     * 根据id删除书籍
     *
     * @param id
     * @return
     */
    int deleteBookById(String id);


}
