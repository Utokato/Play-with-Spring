package cn.llman.controller;

import cn.llman.beans.dto.BookDTO;
import cn.llman.beans.form.BookForm;
import cn.llman.beans.vo.ResultBean;
import cn.llman.common.utils.converter.BookFormToBookDTOConverter;
import cn.llman.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @date 2018/11/15
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResultBean<String> add(BookForm bookForm) {
        return new ResultBean<>(bookService.addBook(BookFormToBookDTOConverter.convert(bookForm)));
    }

    @GetMapping("/delete/{id}")
    public ResultBean<String> delete(@PathVariable("id") String bookId) {
        return new ResultBean<>(bookService.delBook(bookId));
    }

    @PostMapping("/modify/{id}")
    public ResultBean<String> modify(@PathVariable("id") String bookId, BookForm bookForm) {
        return new ResultBean<>(bookService.updateBook(bookId, BookFormToBookDTOConverter.convert(bookForm)));
    }

    @GetMapping("/list")
    public ResultBean<List<BookDTO>> list() {
        return new ResultBean<>(bookService.findAllBook());
    }

    @GetMapping("/list/{name}")
    public ResultBean<List<BookDTO>> listWithName(@PathVariable("name") String name) {
        return new ResultBean<>(bookService.findBooksByName(name));
    }

    @GetMapping("/one/{id}")
    public ResultBean<BookDTO> abook(@PathVariable("id") String bookId) {
        return new ResultBean<>(bookService.findBookById(bookId));
    }

}
