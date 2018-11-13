package cn.llman.controller;

import cn.llman.beans.dto.BookDTO;
import cn.llman.beans.form.BookForm;
import cn.llman.beans.vo.ResultBean;
import cn.llman.exception.BookException;
import cn.llman.service.BookService;
import cn.llman.utils.ResultBeanUtils;
import cn.llman.utils.converter.BookFormToBookDTOConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author
 * @date 2018/11/13
 */
@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/list")
    public ResultBean<List<BookDTO>> list() {
        List<BookDTO> allBook = bookService.findAllBook();
        return ResultBeanUtils.success(allBook);
    }

    @RequestMapping("/oflist")
    public ResultBean<List<BookDTO>> namelist(@RequestParam("name") String name) {
        List<BookDTO> nameBooks = bookService.findBooksByName(name);
        return ResultBeanUtils.success(nameBooks);
    }

    @RequestMapping("abook")
    public ResultBean<BookDTO> aBook(@RequestParam("bookId") String bookId) {
        BookDTO bookDTO = bookService.findBookById(bookId);
        return ResultBeanUtils.success(bookDTO);
    }

    @RequestMapping("/save")
    public ResultBean<String> save(@Valid BookForm bookForm,
                                   @RequestParam(value = "bookId", required = false) String bookId,
                                   BindingResult bindingResult) {
        // 校验表单
        if (bindingResult.hasErrors()) {
            log.error("[保存书籍] 参数不正确，bookForm={}", bookForm);
            throw new BookException(bindingResult.getFieldError().getDefaultMessage());
        }
        if (StringUtils.isEmpty(bookId)) {
            // bookId 为空，新增
            BookDTO bookDTO = BookFormToBookDTOConverter.convert(bookForm);
            bookService.addBook(bookDTO);
        } else {
            // bookId 不为空，修改
            BookDTO bookDTO = BookFormToBookDTOConverter.convert(bookForm);
            bookService.updateBook(bookId, bookDTO);
        }

        return ResultBeanUtils.success("保存书籍成功!");
    }

    @RequestMapping("/delete")
    public ResultBean delBook(@RequestParam("bookId") String bookId) {
        bookService.delBook(bookId);
        return ResultBeanUtils.success();
    }

}
