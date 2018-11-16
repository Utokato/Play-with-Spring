package cn.llman.dao.mapper;

import org.apache.ibatis.annotations.*;

import cn.llman.beans.dataobject.Book;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static org.apache.ibatis.type.JdbcType.DECIMAL;
import static org.apache.ibatis.type.JdbcType.INTEGER;
import static org.apache.ibatis.type.JdbcType.VARCHAR;

/**
 * @author
 * @date 2018/11/14
 */
@Mapper
@Component
public interface BookMapper {

    /**
     * 保存或修改书籍
     *
     * @param book
     * @return
     */
    @Insert("INSERT INTO book (id,name,author,version,pages,price) " +
            "VALUES (#{id,jdbcType=VARCHAR},#{name,jdbcType=VARCHAR},#{author,jdbcType=VARCHAR}," +
            "#{version,jdbcType=VARCHAR},#{pages,jdbcType=INTEGER},#{price,jdbcType=DECIMAL})")
    int insertBook(Book book);

    /**
     * 根据bookId 修改书籍信息
     *
     * @param id
     * @param book
     * @return
     */
    @Update("UPDATE book SET name = #{book.name, jdbcType=VARCHAR}, author = #{book.author, jdbcType=VARCHAR}," +
            " version = #{book.version, jdbcType=VARCHAR}, pages = #{book.pages, jdbcType=INTEGER}," +
            " price = #{book.price, jdbcType=DECIMAL}, update_time = #{book.updateTime, jdbcType=TIMESTAMP}" +
            " WHERE id = #{id, jdbcType=VARCHAR}")
    int updateBookById(@Param("id") String id,
                       @Param("book") Book book);

    /**
     * 根据id查询书籍
     *
     * @param id
     * @return
     */
    @Select("SELECT id, name, author, version, pages, price FROM book WHERE id = #{id, jdbcType=VARCHAR}")
    @Results({
            @Result(column = "id", property = "id", javaType = String.class, jdbcType = VARCHAR),
            @Result(column = "name", property = "name", javaType = String.class, jdbcType = VARCHAR),
            @Result(column = "author", property = "author", javaType = String.class, jdbcType = VARCHAR),
            @Result(column = "version", property = "version", javaType = String.class, jdbcType = VARCHAR),
            @Result(column = "pages", property = "pages", javaType = Integer.class, jdbcType = INTEGER),
            @Result(column = "price", property = "price", javaType = BigDecimal.class, jdbcType = DECIMAL)
    })
    Book selectBookById(@Param("id") String id);

    /**
     * 根据name模糊查询书籍
     *
     * @param name
     * @return
     */
    @Select("SELECT * FROM book WHERE name LIKE '%${name}%' ")
    List<Book> selectBookListByName(@Param("name") String name);

    /**
     * 查询书籍列表
     *
     * @return
     */
    @Select("SELECT id, name, author, version, pages, price FROM book")
    List<Book> selectAllBook();

    /**
     * 根据id删除书籍
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM book WHERE id = #{id, jdbcType=VARCHAR}")
    int deleteBookById(@Param("id") String id);

}
