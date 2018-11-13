package cn.llman.service.impl;

import cn.llman.beans.dataobject.BookDetail;
import cn.llman.beans.dto.BookDTO;
import cn.llman.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-*.xml"})
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void addBook() {
        BookDTO bookDTO = new BookDTO("拿破仑传", "埃米尔·路德维希", "典藏版", 396, new BigDecimal(26.9), null);
        BookDetail bookDetail = new BookDetail("", "拿破仑", "名人故事", "无", "", new Date(), new Date());
        bookDTO.setBookDetail(bookDetail);
        bookService.addBook(bookDTO);
    }

    @Test
    public void delBook() {
        String bookId = "1062246927941132288";
        bookService.delBook(bookId);
    }

    @Test
    public void updateBook() {
        String bookId = "1062246927941132288";
        BookDTO bookDTO = bookService.findBookById(bookId);
        bookDTO.setName("许三观卖血记");
        BookDetail bookDetail = bookDTO.getBookDetail();
        bookDetail.setCharacters("许三观");
        bookDetail.setIntroduction("生活故事");
        bookDTO.setBookDetail(bookDetail);
        bookService.updateBook(bookId,bookDTO);
    }

    @Test
    public void findBookById() {
        String bookId = "1062246927941132288";
        BookDTO bookDTO = bookService.findBookById(bookId);
        Assert.assertNotNull(bookDTO);
    }

    @Test
    public void findBooksByName() {
        List<BookDTO> bookDTOList = bookService.findBooksByName("传");
        Assert.assertNotNull(bookDTOList);
    }

    @Test
    public void findAllBook() {
        List<BookDTO> bookDTOList = bookService.findAllBook();
        Assert.assertNotNull(bookDTOList);
    }
}