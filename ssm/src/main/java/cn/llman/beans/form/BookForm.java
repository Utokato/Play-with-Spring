package cn.llman.beans.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @author
 * @date 2018/11/13
 */
@Data
public class BookForm {

    @NotEmpty(message = "书名必填")
    private String name;

    @NotEmpty(message = "作者必填")
    private String author;

    @NotEmpty(message = "版本必填")
    private String version;

    @NotEmpty(message = "页数必填")
    private Integer pages;

    @NotEmpty(message = "价格必填")
    private BigDecimal price;

    private String characters;

    private String introduction;

    private String other;

}
