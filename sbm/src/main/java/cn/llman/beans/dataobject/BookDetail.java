package cn.llman.beans.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author
 * @date 2018/11/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetail {

    private String id;

    private String characters;

    private String introduction;

    private String other;

    private String bookId;

    private Date createTime;

    private Date updateTime;

}

