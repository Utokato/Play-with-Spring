package cn.llman.dao;

import cn.llman.dao.mapper.BookMapper;
import cn.llman.beans.dataobject.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 仅仅对mapper接口进行封装
 * 抽象出这一层，可以更加灵活地配置
 *
 * @author
 * @date 2018/11/12
 */
@Repository
public class BookDao {

    @Autowired
    private BookMapper bookMapper;

    public int saveBook(Book book){
        return bookMapper.saveBook(book);
    }

    public int updateBookById(String id,Book book){
        return bookMapper.updateBookById(id,book);
    }

    public Book queryBookById(String id){
        return  bookMapper.queryBookById(id);
    }

    public List<Book> queryBookListByName(String name){
        return bookMapper.queryBookListByName(name);
    }

    public List<Book> queryBookList(){
        return bookMapper.queryBookList();
    }

    public int deleteBookById(String id){
        return bookMapper.deleteBookById(id);
    }


}
