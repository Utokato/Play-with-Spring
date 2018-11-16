package cn.llman.dao;

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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void addBook() {
        Book book = new Book();
        book.setId(KeyUtils.nextId());
        book.setName("名人传");
        book.setAuthor("罗曼·罗兰");
        book.setVersion("少儿版");
        book.setPages(459);
        book.setPrice(new BigDecimal(23.6));
        int result = bookDao.addBook(book);
        Assert.assertEquals(1,result);
    }

    @Test
    public void modifyBookById() {
        String bookId = "1062636241074212864";
        Book book = bookDao.findBookById(bookId);
        book.setName("无名人传");
        int result = bookDao.modifyBookById(bookId, book);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findBookById() {
        String bookId = "1062636241074212864";
        Book book = bookDao.findBookById(bookId);
        Assert.assertNotNull(book);
    }

    @Test
    public void findBookListByName() {
        List<Book> bookListByName = bookDao.findBookListByName("传");
        Assert.assertNotNull(bookListByName);
    }

    @Test
    public void findAllBook() {
        List<Book> result = bookDao.findAllBook();
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteBookById() {
        String bookId = "1062636241074212864";
        int result = bookDao.deleteBookById(bookId);
        Assert.assertEquals(1,result);
    }
}