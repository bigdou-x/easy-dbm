package org.bigdou.dbm.graph;

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
 * 类描述: 图布局
 *
 * @author xuqing F00722
 * @date 2023/1/10 16:15
 */
@Data
@Entity(name = "graph_layout")
@Table(appliesTo = "graph_layout", comment = "图布局")
public class GraphLayoutDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("数据库id")
    private Long dbId;

    @Column(length = 20)
    @Comment("名称")
    private String name;

    @Column(length = 200)
    @Comment("描述")
    private String description;

    @CreatedDate
    @Comment("创建时间")
    private Date createTime;

    @LastModifiedDate
    @Comment("修改时间")
    private Date updateTime;

}
