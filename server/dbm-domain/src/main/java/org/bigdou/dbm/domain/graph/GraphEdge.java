package org.bigdou.dbm.domain.graph;

import lombok.Data;

/**
 * 类描述: 图边 <br>
 *
 * @author xuqing
 * @date 2023-08-30 12:55
 */
@Data
public class GraphEdge {

    private String sourceCell;

    private String sourcePort;

    private String targetCell;

    private String targetPort;
}
