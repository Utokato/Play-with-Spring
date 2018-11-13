package cn.llman.beans.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author
 * @date 2018/11/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String id;

    private String name;

    private String author;

    private String version;

    private Integer pages;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;

}