package org.bigdou.dbm.db;

import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类描述: 数据库同步信息
 *
 * @author xuqing F00722
 * @date 2023/1/10 16:15
 */
@Data
@Entity(name = "db_sync")
@Table(appliesTo = "db_sync", comment = "数据库同步信息")
public class DbSyncDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("数据库id")
    private Long dbId;

    @Column(length = 200)
    @Comment("描述")
    private String description;

    @Comment("同步类型(多种同步类型1|2|3) 1-比对表(1-1比对主键 1-2比对外键 1-3比对索引 1-4比对触发器) 2-比对视图 3-比对函数 4-比对事件")
    private String types;

    @Comment("同步时间")
    private Date syncTime;

}
