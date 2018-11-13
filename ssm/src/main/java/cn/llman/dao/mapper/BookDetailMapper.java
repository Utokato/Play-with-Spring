package cn.llman.dao.mapper;

import cn.llman.beans.dataobject.BookDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * 书籍详情mapper接口
 * 书籍详情与书籍一一对应
 *
 * @author
 * @date 2018/11/13
 */
@Component
public interface BookDetailMapper {

    /**
     * 保存书籍详情，一般和书籍一起存储
     *
     * @param bookDetail
     * @return
     */
    @Insert("INSERT INTO book_detail(id,characters,introduction,other,book_id) " +
            "VALUES (#{id,jdbcType=VARCHAR},#{characters,jdbcType=VARCHAR},#{introduction,jdbcType=VARCHAR}," +
            "#{other,jdbcType=VARCHAR},#{bookId,jdbcType=VARCHAR})")
    int insertBookDetail(BookDetail bookDetail);

    /**
     * 更新书籍详情数据
     *
     * @param bookId
     * @param bookDetail
     * @return
     */
    @Update("UPDATE book_detail SET characters = #{bookDetail.characters} , introduction = #{bookDetail.introduction},other=#{bookDetail.other},update_time=#{bookDetail.updateTime} WHERE book_id = #{bookId}")
    int updateByCategoryType(@Param("bookId") String bookId,
                             @Param("bookDetail") BookDetail bookDetail);

    /**
     * 根据书籍id查询书籍详情
     *
     * @param bookId
     * @return
     */
    @Select("SELECT id,characters,introduction,other,book_id FROM book_detail WHERE book_id = #{bookId,jdbcType=VARCHAR}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "characters", property = "characters"),
            @Result(column = "introduction", property = "introduction"),
            @Result(column = "other", property = "other"),
            @Result(column = "book_id", property = "bookId")
    })
    BookDetail queryBookDetailByBookId(@Param("bookId") String bookId);


    /**
     * 根据书籍id删除一条书籍详情记录
     *
     * @param bookId
     * @return
     */
    @Delete("DELETE FROM book_detail WHERE book_id = #{bookId}")
    int deleteBookDetailByBookId(@Param("bookId") String bookId);

}
