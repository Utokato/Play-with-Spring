package cn.llman.service;

import cn.llman.beans.dto.BookDTO;

import java.util.List;

/**
 * @author
 * @date 2018/11/12
 */
public interface BookService {

    /**
     * 增加一本书，同时添加到主表和详情表
     *
     * @param bookDTO
     * @return
     */
    void addBook(BookDTO bookDTO);

    /**
     * 删除书籍，同时删除详情表
     *
     * @param bookId
     * @return
     */
    void delBook(String bookId);

    /**
     * 更新书籍或详情记录
     *
     * @param bookId
     * @param bookDTO
     */
    void updateBook(String bookId, BookDTO bookDTO);

    /**
     * 根据id查找一本书
     *
     * @param bookId
     * @return
     */
    BookDTO findBookById(String bookId);

    /**
     * 根据名字模糊查询书籍
     *
     * @param name
     * @return
     */
    List<BookDTO> findBooksByName(String name);

    /**
     * 查询全部书籍列表
     *
     * @return
     */
    List<BookDTO> findAllBook();

}
