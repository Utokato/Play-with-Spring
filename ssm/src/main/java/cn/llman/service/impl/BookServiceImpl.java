package cn.llman.service.impl;

import cn.llman.beans.dataobject.Book;
import cn.llman.beans.dataobject.BookDetail;
import cn.llman.beans.dto.BookDTO;
import cn.llman.dao.BookDao;
import cn.llman.dao.BookDetailDao;
import cn.llman.exception.BookException;
import cn.llman.service.BookService;
import cn.llman.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date 2018/11/12
 */
@Service
@Slf4j
@Transactional(rollbackFor = BookException.class)
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookDetailDao bookDetailDao;

    @Override
    public void addBook(BookDTO bookDTO) {
        try {
            // 生成bookId
            String bookId = KeyUtils.nextId();
            // 存入书籍表
            Book book = new Book();
            BeanUtils.copyProperties(bookDTO, book);
            book.setId(bookId);
            bookDao.saveBook(book);
            // 存入书籍详情表
            BookDetail bookDetail = bookDTO.getBookDetail();
            bookDetail.setBookId(bookId);
            bookDetail.setId(KeyUtils.nextId());
            bookDetailDao.insertBookDetail(bookDetail);
        } catch (Exception e) {
            log.error("[添加操作] 操作异常: ", e);
            throw new BookException("添加操作异常!");
        }
    }

    @Override
    public void delBook(String bookId) {
        if (bookId == null || StringUtils.isEmpty(bookId)) {
            log.error("[删除操作] 操作异常: 待删除书籍不存在.");
            return;
        }
        try {
            bookDao.deleteBookById(bookId);
            bookDetailDao.deleteBookDetailByBookId(bookId);
        } catch (Exception e) {
            log.error("[删除操作] 操作异常: ", e);
            throw new BookException("删除操作异常!");
        }
    }

    /**
     * 这个方法有问题
     *
     * @param bookDTO
     */
    @Override
    public void updateBook(String bookId, BookDTO bookDTO) {
        if (bookId == null || StringUtils.isEmpty(bookId)) {
            log.error("[更新操作] 操作异常: 待更新书籍不存在.");
            throw new BookException("更新操作异常,书籍不存在");
        }
        try {
            // 更新书籍主表
            Book book = new Book();
            BeanUtils.copyProperties(bookDTO, book);
            book.setId(bookId);
            book.setUpdateTime(new Date());
            bookDao.updateBookById(bookId, book);
            // 更新书籍详情表
            BookDetail bookDetail = bookDetailDao.queryBookDetailByBookId(bookId);
            convertNewPropertiesToBookDetail(bookDTO.getBookDetail(), bookDetail);
            bookDetail.setUpdateTime(new Date());
            bookDetailDao.updateByCategoryType(bookId, bookDetail);

        } catch (Exception e) {
            log.error("[更新操作] 操作异常: ", e);
            throw new BookException("更新操作异常!");
        }
    }

    /**
     * 辅助函数，将新的属性拷贝到原有的对象中，如果有的话.
     *
     * @param newBookDetail
     * @param bookDetail
     */
    private void convertNewPropertiesToBookDetail(BookDetail newBookDetail, BookDetail bookDetail) {
        if (newBookDetail.getCharacters() != null && !StringUtils.isEmpty(newBookDetail.getCharacters())) {
            bookDetail.setCharacters(newBookDetail.getCharacters());
        }
        if (newBookDetail.getIntroduction() != null && !StringUtils.isEmpty(newBookDetail.getIntroduction())) {
            bookDetail.setIntroduction(newBookDetail.getIntroduction());
        }
        if (newBookDetail.getOther() != null && !StringUtils.isEmpty(newBookDetail.getOther())) {
            bookDetail.setOther(newBookDetail.getOther());
        }
    }

    @Override
    public BookDTO findBookById(String bookId) {
        if (bookId == null || StringUtils.isEmpty(bookId)) {
            log.error("[查询操作] 操作异常: 待查询书籍不存在.");
            return null;
        }
        try {
            BookDTO bookDTO = new BookDTO();
            Book book = bookDao.queryBookById(bookId);
            BookDetail bookDetail = bookDetailDao.queryBookDetailByBookId(bookId);
            BeanUtils.copyProperties(book, bookDTO);
            bookDTO.setBookDetail(bookDetail);
            return bookDTO;
        } catch (Exception e) {
            log.error("[查询操作] 操作异常: ", e);
            return null;
        }
    }

    @Override
    public List<BookDTO> findBooksByName(String name) {
        if (name == null || StringUtils.isEmpty(name)) {
            log.error("[查询操作] 操作异常: 待查询书籍不存在.");
            return null;
        }
        List<BookDTO> bookDTOList = new ArrayList<BookDTO>();
        try {
            List<Book> books = bookDao.queryBookListByName(name);
            convertBooksToBookDTOs(books, bookDTOList);
            return bookDTOList;
        } catch (Exception e) {
            log.error("[查询操作] 操作异常: ", e);
            return null;
        }
    }

    @Override
    public List<BookDTO> findAllBook() {
        List<Book> books = bookDao.queryBookList();
        List<BookDTO> bookDTOList = new ArrayList<BookDTO>();
        convertBooksToBookDTOs(books, bookDTOList);
        return bookDTOList;
    }

    private void convertBooksToBookDTOs(List<Book> books, List<BookDTO> bookDTOList) {
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO();
            BookDetail bookDetail = bookDetailDao.queryBookDetailByBookId(book.getId());
            BeanUtils.copyProperties(book, bookDTO);
            bookDTO.setBookDetail(bookDetail);
            bookDTOList.add(bookDTO);
        }
    }

}
