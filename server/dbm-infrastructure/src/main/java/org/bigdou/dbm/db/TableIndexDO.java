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
 * 类描述: 索引信息
 *
 * @author xuqing F00722
 * @date 2023/1/10 16:15
 */
@Data
@Entity(name = "table_index")
@Table(appliesTo = "table_index", comment = "表索引")
public class TableIndexDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("数据库id")
    private Long dbId;

    @Comment("数据库同步id")
    private Long syncId;

    @Column(length = 64)
    @Comment("表名")
    private String tableName;

    @Column(length = 64)
    @Comment("索引标识")
    private String indexKey;

    @Column(length = 64)
    @Comment("索引名称")
    private String name;

    @Comment("索引是否唯一")
    private Boolean uniquely;

    @Column(length = 60)
    @Comment("索引注释")
    private String comment;

    @Comment("索引列")
    private String columns;

    @CreatedDate
    @Comment("创建时间")
    private Date createTime;

}
