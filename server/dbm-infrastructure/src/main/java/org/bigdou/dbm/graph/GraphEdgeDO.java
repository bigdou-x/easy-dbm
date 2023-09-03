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
 * 类描述: 图边
 *
 * @author xuqing F00722
 * @date 2023/1/10 16:15
 */
@Data
@Entity(name = "graph_edge")
@Table(appliesTo = "graph_edge", comment = "图边")
public class GraphEdgeDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("数据库id")
    private Long dbId;

    @Comment("图布局id")
    private Long graphLayoutId;

    @Column(length = 64)
    @Comment("源cell")
    private String sourceCell;

    @Column(length = 64)
    @Comment("源port")
    private String sourcePort;

    @Column(length = 64)
    @Comment("目标cell")
    private String targetCell;

    @Column(length = 64)
    @Comment("目标port")
    private String targetPort;

    @CreatedDate
    @Comment("创建时间")
    private Date createTime;

}
