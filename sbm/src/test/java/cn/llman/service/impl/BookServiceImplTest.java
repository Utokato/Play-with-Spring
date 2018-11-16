package cn.llman.service.impl;

import cn.llman.beans.dataobject.BookDetail;
import cn.llman.beans.dto.BookDTO;
import cn.llman.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void addBook() {
        BookDTO bookDTO = new BookDTO("拿破仑传111","埃米尔·路德维希", "典藏版", 396, new BigDecimal(26.9), null);
        BookDetail bookDetail = new BookDetail("", "拿破仑", "名人故事", "无", "", new Date(), new Date());
        bookDTO.setBookDetail(bookDetail);
        String result = bookService.addBook(bookDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void delBook() {
        String bookId = "1063315103298813952";
        bookService.delBook(bookId);
    }

    @Test
    public void updateBook() {
        String bookId = "1062971783624192000";
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("拿破仑传111");
        BookDetail bookDetail = new BookDetail();
        bookDetail.setOther("有点东西a");
        bookDTO.setBookDetail(bookDetail);
        bookService.updateBook(bookId, bookDTO);
    }

    @Test
    public void findBookById() {
        String bookId = "1062246651712679936";
        BookDTO book = bookService.findBookById(bookId);
        Assert.assertEquals(bookId, book.getBookDetail().getBookId());
    }

    @Test
    public void findBooksByName() {
        List<BookDTO> booksByName = bookService.findBooksByName("传");
        Assert.assertNotNull(booksByName);
    }

    @Test
    public void findAllBook() {
        List<BookDTO> books = bookService.findAllBook();
        Assert.assertNotNull(books);
    }

}