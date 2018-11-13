package cn.llman.dao;

import cn.llman.beans.dataobject.BookDetail;
import cn.llman.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-*.xml"})
public class BookDetailDaoTest {

    @Autowired
    private BookDetailDao bookDetailDao;

    String bookId = "1062171015916769280";

    @Test
    public void insertBookDetail() {
        BookDetail bookDetail = new BookDetail();
        bookDetail.setId(KeyUtils.nextId());
        bookDetail.setCharacters("贝多芬,米开朗基罗,托尔斯泰");
        bookDetail.setIntroduction("励志");
        bookDetail.setOther("无");
        bookDetail.setBookId(bookId);
        int result = bookDetailDao.insertBookDetail(bookDetail);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByCategoryType() {
        BookDetail oldBookDetail = bookDetailDao.queryBookDetailByBookId(bookId);
        BookDetail bookDetail = new BookDetail();
        BeanUtils.copyProperties(oldBookDetail, bookDetail);
        bookDetail.setOther("少儿必读");
        int result = bookDetailDao.updateByCategoryType(bookId, bookDetail);
        Assert.assertEquals(1, result);
    }

    @Test
    public void queryBookDetailByBookId() {
        BookDetail bookDetail = bookDetailDao.queryBookDetailByBookId(bookId);
        Assert.assertNotNull(bookDetail);
    }

    @Test
    public void deleteBookDetailByBookId() {
    }
}