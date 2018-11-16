package cn.llman.service.impl;

import cn.llman.beans.dataobject.Book;
import cn.llman.beans.dataobject.BookDetail;
import cn.llman.beans.dto.BookDTO;
import cn.llman.common.utils.BeanUtils;
import cn.llman.common.utils.CheckUtils;
import cn.llman.common.utils.KeyUtils;
import cn.llman.dao.BookDao;
import cn.llman.dao.BookDetailDao;
import cn.llman.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2018/11/14
 */
@Transactional()
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookDetailDao bookDetailDao;

    @Override
    public String addBook(BookDTO bookDTO) {

        // 校验 必填 字段
        CheckUtils.notAnyEmpty(bookDTO);

        log.info("[添加书籍] 书籍信息为: {}", bookDTO + " .");

        String bookId = KeyUtils.nextId();
        Book book = new Book();
        BeanUtils.copyAttribute(bookDTO, book);
        book.setId(bookId);
        bookDao.addBook(book);

        BookDetail bookDetail = bookDTO.getBookDetail();
        bookDetail.setBookId(bookId);
        bookDetail.setId(KeyUtils.nextId());
        bookDetailDao.addBookDetail(bookDetail);

        log.info("[添加书籍] 添加书籍成功 id:{}", bookId + " .");

        return bookId;
    }

    @Override
    public String delBook(String bookId) {

        CheckUtils.notEmpty(bookId, "待删除书籍id为空");

        bookDao.deleteBookById(bookId);
        bookDetailDao.deleteBookDetailByBookId(bookId);

        log.info("[删除书籍] 成功删除 id:{}", bookId + " .");

        return bookId;
    }

    @Override
    public String updateBook(String bookId, BookDTO bookDTO) {
        CheckUtils.notEmpty(bookId, "待更新书籍id为空");
        CheckUtils.notNull(bookDTO, "待更新书籍的内容为空");

        log.info("[修改书籍] 新信息:{}", bookDTO + " .");

        Book book = bookDao.findBookById(bookId);
        BookDetail bookDetail = bookDetailDao.findBookDetailByBookId(bookId);

        BeanUtils.copyAttributeMissNull(bookDTO, book);
        BeanUtils.copyAttributeMissNull(bookDTO.getBookDetail(), bookDetail);

        bookDao.modifyBookById(bookId, book);
        bookDetailDao.modifyByBookId(bookId, bookDetail);

        log.info("[修改书籍] 更新成功 id:{}", bookId + " .");

        return bookId;
    }

    @Override
    public BookDTO findBookById(String bookId) {

        CheckUtils.notEmpty(bookId, "待查询书籍id为空");

        BookDTO bookDTO = new BookDTO();
        Book book = bookDao.findBookById(bookId);
        BookDetail bookDetail = bookDetailDao.findBookDetailByBookId(bookId);
        BeanUtils.copyAttribute(book, bookDTO);
        bookDTO.setBookDetail(bookDetail);

        log.info("[查询书籍] 查询书籍成功: {}", bookDTO + " .");

        return bookDTO;
    }

    @Override
    public List<BookDTO> findBooksByName(String name) {
        CheckUtils.notEmpty(name, "待查询书籍的名称为空");

        List<Book> books = bookDao.findBookListByName(name);
        List<BookDTO> bookDTOS = convertBooksToBookDTOs(books);

        log.info("[查询书籍] 成功查询书籍数量: {}", bookDTOS.size() + " .");

        return bookDTOS;
    }

    @Override
    public List<BookDTO> findAllBook() {
        List<Book> books = bookDao.findAllBook();
        List<BookDTO> bookDTOS = convertBooksToBookDTOs(books);

        log.info("[查询书籍] 成功查询书籍数量: {}", bookDTOS.size() + " .");

        return bookDTOS;
    }

    private List<BookDTO> convertBooksToBookDTOs(List<Book> books) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO();
            BookDetail bookDetail = bookDetailDao.findBookDetailByBookId(book.getId());
            BeanUtils.copyAttribute(book, bookDTO);
            bookDTO.setBookDetail(bookDetail);
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

}
