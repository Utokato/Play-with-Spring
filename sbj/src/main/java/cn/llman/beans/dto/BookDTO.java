package cn.llman.beans.dto;

import cn.llman.beans.annotation.INotEmpty;
import cn.llman.beans.dataobject.BookDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author
 * @date 2018/11/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    /**
     * 书籍表内容
     */
    @INotEmpty("书籍名不能为空")
    private String name;

    @INotEmpty("作者不能为空")
    private String author;

    @INotEmpty("书籍版本不能为空")
    private String version;

    @INotEmpty("页数不能为空")
    private Integer pages;

    @INotEmpty("价格不能为空")
    private BigDecimal price;

    @INotEmpty("书籍详情不能为空")
    private BookDetail bookDetail;

}
