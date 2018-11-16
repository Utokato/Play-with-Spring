package cn.llman.dao.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import cn.llman.beans.dataobject.BookDetail;

import static org.apache.ibatis.type.JdbcType.VARCHAR;

/**
 * 书籍详情mapper接口
 * 书籍详情与书籍一一对应
 *
 * @author
 * @date 2018/11/14
 */
@Mapper
@Component
public interface BookDetailMapper {

    /**
     * 保存书籍详情，一般和书籍一起存储
     *
     * @param bookDetail
     * @return
     */
    @Insert("INSERT INTO book_detail(id, characters, introduction, other, book_id) " +
            "VALUES (#{id, jdbcType=VARCHAR},#{characters, jdbcType=VARCHAR},#{introduction, jdbcType=VARCHAR}," +
            "#{other, jdbcType=VARCHAR},#{bookId, jdbcType=VARCHAR})")
    int insertBookDetail(BookDetail bookDetail);

    /**
     * 更新书籍详情数据
     *
     * @param bookId
     * @param bookDetail
     * @return
     */
    @Update("UPDATE book_detail SET characters = #{bookDetail.characters, jdbcType=VARCHAR}, " +
            "introduction = #{bookDetail.introduction, jdbcType=VARCHAR},other=#{bookDetail.other, jdbcType=VARCHAR}," +
            "update_time=#{bookDetail.updateTime, jdbcType = TIMESTAMP} WHERE book_id = #{bookId, jdbcType=VARCHAR}")
    int updateByBookId(@Param("bookId") String bookId,
                          @Param("bookDetail") BookDetail bookDetail);

    /**
     * 根据书籍id查询书籍详情
     *
     * @param bookId
     * @return
     */
    @Select("SELECT id,characters,introduction,other,book_id FROM book_detail WHERE book_id = #{bookId, jdbcType=VARCHAR}")
    @Results({
            @Result(column = "id", property = "id", javaType = String.class, jdbcType = VARCHAR),
            @Result(column = "characters", property = "characters", javaType = String.class, jdbcType = VARCHAR),
            @Result(column = "introduction", property = "introduction", javaType = String.class, jdbcType = VARCHAR),
            @Result(column = "other", property = "other", javaType = String.class, jdbcType = VARCHAR),
            @Result(column = "book_id", property = "bookId", javaType = String.class, jdbcType = VARCHAR)
    })
    BookDetail selectBookDetailByBookId(@Param("bookId") String bookId);


    /**
     * 根据书籍id删除一条书籍详情记录
     *
     * @param bookId
     * @return
     */
    @Delete("DELETE FROM book_detail WHERE book_id = #{bookId, jdbcType=VARCHAR}")
    int deleteBookDetailByBookId(@Param("bookId") String bookId);

}
