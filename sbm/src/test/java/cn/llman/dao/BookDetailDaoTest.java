package cn.llman.dao;

import cn.llman.beans.dataobject.BookDetail;
import cn.llman.common.utils.KeyUtils;
import cn.llman.dao.mapper.BookDetailMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDetailDaoTest {

    @Autowired
    private BookDetailDao bookDetailDao;

    String bookId = "1062636241074212864";

    @Test
    public void addBookDetail() {
        BookDetail bookDetail = new BookDetail();
        bookDetail.setId(KeyUtils.nextId());
        bookDetail.setCharacters("我，你，他");
        bookDetail.setIntroduction("励志");
        bookDetail.setOther("无");
        bookDetail.setBookId(bookId);
        int result = bookDetailDao.addBookDetail(bookDetail);
        Assert.assertEquals(1, result);
    }

    @Test
    public void modifyByBookId() {
        BookDetail bookDetail = bookDetailDao.findBookDetailByBookId(bookId);
        bookDetail.setIntroduction("励志吗，哈哈");
        int result = bookDetailDao.modifyByBookId(bookId, bookDetail);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findBookDetailByBookId() {
        BookDetail bookDetail = bookDetailDao.findBookDetailByBookId(bookId);
        Assert.assertNotNull(bookDetail);
    }

    @Test
    public void deleteBookDetailByBookId() {
        int result = bookDetailDao.deleteBookDetailByBookId(bookId);
        Assert.assertEquals(1, result);
    }

}