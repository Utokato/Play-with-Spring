package cn.llman.service.impl;

import cn.llman.beans.dataobject.Book;
import cn.llman.beans.dataobject.BookDetail;
import cn.llman.beans.dto.BookDTO;
import cn.llman.common.exception.CheckException;
import cn.llman.common.utils.BeanUtils;
import cn.llman.common.utils.CheckUtils;
import cn.llman.common.utils.KeyUtils;
import cn.llman.repository.BookDetailRepository;
import cn.llman.repository.BookRepository;
import cn.llman.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * @author
 * @date 2018/11/19
 */
@Transactional()
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookDetailRepository bookDetailRepository;

    @Override
    public String addBook(BookDTO bookDTO) {
        // 校验 必填 字段
        CheckUtils.notAnyEmpty(bookDTO);
        log.info("[添加书籍] 书籍信息为: {}", bookDTO + " .");

        String bookId = KeyUtils.nextId();
        Book book = new Book();
        BeanUtils.copyAttribute(bookDTO, book);
        book.setId(bookId);
        Book result = bookRepository.save(book);
        BookDetail bookDetail = bookDTO.getBookDetail();
        bookDetail.setBookId(bookId);
        bookDetail.setId(KeyUtils.nextId());
        bookDetailRepository.save(bookDetail);

        log.info("[添加书籍] 添加书籍成功 id:{}", result.getId() + " .");
        return result.getId();
    }

    @Override
    public String delBook(String bookId) {
        CheckUtils.notEmpty(bookId, "待删除书籍id为空");

        if (bookRepository.existsById(bookId) && bookDetailRepository.existsByBookId(bookId)) {
            bookRepository.deleteById(bookId);
            bookDetailRepository.deleteByBookId(bookId);
        } else {
            log.info("[删除书籍] 成功失败，书籍不存在 id:{}", bookId + " .");
            throw new CheckException("待删除书籍不存在!");
        }

        log.info("[删除书籍] 成功删除 id:{}", bookId + " .");
        return bookId;
    }

    @Override
    public String updateBook(String bookId, BookDTO bookDTO) {
        CheckUtils.notEmpty(bookId, "待更新书籍id为空");
        CheckUtils.notNull(bookDTO, "待更新书籍的内容为空");
        log.info("[修改书籍] 新信息:{}", bookDTO + " .");

        Book toUpdateBook = bookRepository.findById(bookId).get();
        BookDetail toUpdateBookDetail = bookDetailRepository.findByBookId(bookId);
        BeanUtils.copyAttributeMissNull(bookDTO, toUpdateBook);
        BeanUtils.copyAttributeMissNull(bookDTO.getBookDetail(), toUpdateBookDetail);
        bookRepository.save(toUpdateBook);
        bookDetailRepository.save(toUpdateBookDetail);

        log.info("[修改书籍] 更新成功 id:{}", bookId + " .");
        return bookId;
    }

    @Override
    public BookDTO findBookById(String bookId) {
        CheckUtils.notEmpty(bookId, "待查询书籍id为空");

        BookDTO bookDTO = new BookDTO();
        Book book = bookRepository.findById(bookId).get();
        BookDetail bookDetail = bookDetailRepository.findByBookId(bookId);
        BeanUtils.copyAttribute(book, bookDTO);
        bookDTO.setBookDetail(bookDetail);

        log.info("[查询书籍] 查询书籍成功: {}", bookDTO + " .");
        return bookDTO;
    }

    @Override
    public List<BookDTO> findBooksByName(String name) {
        CheckUtils.notEmpty(name, "待查询书籍的名称为空");

        List<Book> books = bookRepository.findAllByNameContaining(name);
        List<BookDTO> bookDTOS = convertBooksToBookDTOs(books);

        log.info("[查询书籍] 成功查询书籍数量: {}", bookDTOS.size() + " .");
        return bookDTOS;
    }

    @Override
    public List<BookDTO> findAllBook() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOS = convertBooksToBookDTOs(books);

        log.info("[查询书籍] 成功查询书籍数量: {}", bookDTOS.size() + " .");
        return bookDTOS;
    }

    private List<BookDTO> convertBooksToBookDTOs(List<Book> books) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO();
            BookDetail bookDetail = bookDetailRepository.findByBookId(book.getId());
            BeanUtils.copyAttribute(book, bookDTO);
            bookDTO.setBookDetail(bookDetail);
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

}
