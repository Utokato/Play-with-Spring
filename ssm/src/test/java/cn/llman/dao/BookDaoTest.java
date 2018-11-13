package cn.llman.dao;

import cn.llman.beans.dataobject.Book;
import cn.llman.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author
 * @date 2018/11/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-*.xml"})
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void saveBook() {
        Book book = new Book();
        book.setId(KeyUtils.nextId());
        book.setName("名人传");
        book.setAuthor("罗曼·罗兰");
        book.setVersion("少儿版");
        book.setPages(459);
        book.setPrice(new BigDecimal(23.6));
        int result = bookDao.saveBook(book);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateBookById() {
        String bookId = "1062171015916769280";
        Book book = new Book();
        book.setId(bookId);
        book.setVersion("典藏版");
        book.setPages(459);
        book.setPrice(new BigDecimal(63.2));
        int result = bookDao.updateBookById(bookId, book);
        Assert.assertEquals(1, result);
    }

    @Test
    public void queryBookById() {
        String id = "1061950566238728192";
        Book book = bookDao.queryBookById(id);
        Assert.assertEquals(id, book.getId());
    }

    @Test
    public void queryBookListByName() {
        String name = "传";
        List<Book> books = bookDao.queryBookListByName(name);
        System.out.println(books);
        Assert.assertNotNull(books);
    }

    @Test
    public void queryBookList() {
        List<Book> bookList = bookDao.queryBookList();
        Assert.assertNotNull(bookList);
    }

    @Test
    public void deleteBookById() {
        String delId = "1062173523917545472";
        int result = bookDao.deleteBookById(delId);
        Assert.assertEquals(1,result);
    }

}