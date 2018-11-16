package cn.llman.beans.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @author
 * @date 2018/11/15
 */
@Data
public class BookForm {

    private String name;

    private String author;

    private String version;

    private Integer pages;

    private BigDecimal price;

    private String characters;

    private String introduction;

    private String other;


}

