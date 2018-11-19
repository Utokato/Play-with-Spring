package cn.llman.common.utils.converter;

import cn.llman.beans.dataobject.BookDetail;
import cn.llman.beans.dto.BookDTO;
import cn.llman.beans.form.BookForm;
import org.springframework.beans.BeanUtils;

/**
 * @author
 * @date 2018/11/19
 */
public class BookFormToBookDTOConverter {


    public static BookDTO convert(BookForm bookForm) {
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(bookForm, bookDTO);
        BookDetail bookDetail = new BookDetail();
        BeanUtils.copyProperties(bookForm, bookDetail);
        bookDTO.setBookDetail(bookDetail);
        return bookDTO;
    }

}
