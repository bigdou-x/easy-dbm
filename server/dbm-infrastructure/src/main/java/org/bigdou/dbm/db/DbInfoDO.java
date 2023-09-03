package org.bigdou.dbm.db;

import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类描述: 数据库信息
 *
 * @author xuqing F00722
 * @date 2023/1/10 16:15
 */
@Data
@Entity(name = "db_info")
@Table(appliesTo = "db_info", comment = "数据库信息")
public class DbInfoDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    @Comment("名称")
    private String name;

    @Comment("jdbcUrl")
    private String jdbcUrl;

    @Column(length = 20)
    @Comment("用户名")
    private String username;

    @Column(length = 20)
    @Comment("密码")
    private String password;

    @CreatedDate
    @Comment("创建时间")
    private Date createTime;

    @LastModifiedDate
    @Comment("修改时间")
    private Date updateTime;

}
