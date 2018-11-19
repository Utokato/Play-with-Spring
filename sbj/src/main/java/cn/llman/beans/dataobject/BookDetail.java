package cn.llman.beans.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author
 * @date 2018/11/19
 */
@DynamicUpdate
@Entity
@Proxy(lazy = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetail {

    @Id
    private String id;

    private String characters;

    private String introduction;

    private String other;

    private String bookId;

    private Date createTime;

    private Date updateTime;

}

