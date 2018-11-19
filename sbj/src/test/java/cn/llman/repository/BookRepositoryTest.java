package cn.llman.repository;

import cn.llman.beans.dataobject.Book;
import cn.llman.common.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    /**
     * 增加一本书籍
     */
    @Test
    public void save(){
        Book book = new Book();
        book.setId(KeyUtils.nextId());
        book.setName("无名人传");
        book.setAuthor("罗曼·罗兰");
        book.setVersion("少儿版");
        book.setPages(459);
        book.setPrice(new BigDecimal(23.6));
        Book result = bookRepository.save(book);
        assertNotNull(result);
    }

    /**
     * 修改一本书籍
     * 和增加一本书籍使用的是相同的接口
     * 区别在于id是否已经在数据库中存在
     *      id不存在时，为新增
     *      id已存在时，为修改
     */
    @Test
    public void update(){
        String bookId = "1064430663487168512";
        Book book = new Book();
        book.setId(bookId);
        book.setName("无名人的传记");
        book.setAuthor("小马乐");
        book.setVersion("傻瓜版");
        book.setPages(459);
        book.setPrice(new BigDecimal(23.6));
        Book result = bookRepository.save(book);
        assertNotNull(result);
    }

    /**
     * 根据id查询一本书籍
     * 返回参数为Optional对象，这是一个神奇的对象
     */
    @Test
    public void findBookById(){
        String bookId = "1062244882999873536";
        Optional<Book> book = bookRepository.findById(bookId);
        System.out.println(book.get());
        assertNotNull(book.get());
    }

    /**
     * 根据书名模糊查询书籍
     */
    @Test
    public void findBooksByName(){
        String bookName = "传";
        List<Book> result = bookRepository.findAllByNameContaining(bookName);
        System.out.println(result);
        assertNotNull(result);
    }

    /**
     * 查询所有的书籍
     */
    @Test
    public void findAllBooks(){
        List<Book> books = bookRepository.findAll();
        System.out.println(books);
        assertNotNull(books);
    }

    /**
     * 先判断书籍是否存在
     * 根据id删除一本书籍
     */
    @Test
    public void deleteBookById() {
        String bookId = "1064430663487168512";
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
        } else {
            System.out.println("待删除书籍不存在");
        }
    }
}