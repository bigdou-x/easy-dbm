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
 * 类描述: 列信息
 *
 * @author xuqing F00722
 * @date 2023/1/10 16:15
 */
@Data
@Entity(name = "table_column")
@Table(appliesTo = "table_column", comment = "表列信息")
public class TableColumnDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("主键id")
    private Long id;

    @Comment("数据库id")
    private Long dbId;

    @Comment("数据库同步id")
    private Long syncId;

    @Column(length = 64)
    @Comment("表名")
    private String tableName;

    @Column(length = 64)
    @Comment("字段名")
    private String name;

    @Column(length = 10)
    @Comment("字段类型")
    private String type;

    @Comment("字段长度")
    private Integer length;

    @Comment("是否为空")
    private Boolean nullable;

    @Comment("是否主键")
    private Boolean primaryKey;

    @Comment("是否自增")
    private Boolean autoincrement;

    @Column(length = 60)
    @Comment("字段注释")
    private String comment;

    @Column(length = 10)
    @Comment("默认值")
    private String defaultValue;

    @Comment("列排序")
    private Integer ordinalPosition;

    @Comment("精度")
    private Integer decimalDigits;

    @CreatedDate
    @Comment("创建时间")
    private Date createTime;

}
