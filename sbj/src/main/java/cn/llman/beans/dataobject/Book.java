package cn.llman.beans.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @date 2018/11/19
 */
@Entity
@DynamicUpdate
@Proxy(lazy = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;

    private String name;

    private String author;

    private String version;

    private Integer pages;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;

}