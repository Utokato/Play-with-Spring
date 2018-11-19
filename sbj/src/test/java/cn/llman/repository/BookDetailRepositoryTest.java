package cn.llman.repository;

import cn.llman.beans.dataobject.BookDetail;
import cn.llman.common.utils.KeyUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDetailRepositoryTest {

    @Autowired
    private BookDetailRepository bookDetailRepository;

    String bookId = "1064430663487168512";

    /**
     * 新增一条书籍详情记录
     */
    @Test
    public void save() {
        BookDetail bookDetail = new BookDetail();
        bookDetail.setId(KeyUtils.nextId());
        bookDetail.setCharacters("我，你，他");
        bookDetail.setIntroduction("励志");
        bookDetail.setOther("无");
        bookDetail.setBookId(bookId);
        BookDetail result = bookDetailRepository.save(bookDetail);
        System.out.println(result);
        assertNotNull(result);
    }

    /**
     * 根据bookId 修改书籍详情
     */
    @Test
    public void updateByBookId() {
        BookDetail toUpdate = bookDetailRepository.findByBookId(bookId);
        toUpdate.setOther("从无到有啊!");
        BookDetail result = bookDetailRepository.save(toUpdate);
        System.out.println(result);
        assertNotNull(result);
    }

    /**
     * 根据bookId 查询书籍详情
     */
    @Test
    public void find() {
        BookDetail result = bookDetailRepository.findByBookId(bookId);
        System.out.println(result);
        assertNotNull(result);
    }

    /**
     * 根据bookId 删除书籍详情
     */
    @Test
    public void delete() {
        if (bookDetailRepository.existsByBookId(bookId)) {
            System.out.println("已经查询到待删除的书籍");
            bookDetailRepository.deleteByBookId(bookId);
        } else {
            System.out.println("待删除书籍详情不存在!");
        }
    }
}