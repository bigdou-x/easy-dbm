package org.bigdou.dbm.domain.graph;

import lombok.Data;

/**
 * 类描述: 图节点 <br>
 *
 * @author xuqing
 * @date 2023-08-30 12:55
 */
@Data
public class GraphNode {

    /**
     * 节点id
     */
    private String nodeId;

    /**
     * 定位 x
     */
    private Integer positionX;

    /**
     * 定位 y
     */
    private Integer positionY;

}
