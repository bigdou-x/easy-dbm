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
 * 类描述: 图节点
 *
 * @author xuqing F00722
 * @date 2023/1/10 16:15
 */
@Data
@Entity(name = "graph_node")
@Table(appliesTo = "graph_node", comment = "图节点")
public class GraphNodeDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("数据库id")
    private Long dbId;

    @Comment("图布局id")
    private Long graphLayoutId;

    @Column(length = 64)
    @Comment("节点key")
    private String nodeKey;

    @Comment("定位x")
    private Integer positionX;

    @Comment("定位y")
    private Integer positionY;

    @CreatedDate
    @Comment("创建时间")
    private Date createTime;

}
