package cn.llman.beans.dto;

import cn.llman.beans.dataobject.BookDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author
 * @date 2018/11/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    /**
     * 书籍表内容
     */
    private String name;

    private String author;

    private String version;

    private Integer pages;

    private BigDecimal price;

    private BookDetail bookDetail;

}
