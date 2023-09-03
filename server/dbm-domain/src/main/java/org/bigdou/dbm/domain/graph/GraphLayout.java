package org.bigdou.dbm.domain.graph;

import com.alibaba.cola.exception.Assert;
import lombok.Data;

import java.util.List;

/**
 * 类描述: 图布局 <br>
 *
 * @author xuqing
 * @date 2023-08-30 12:58
 */
@Data
public class GraphLayout {

    /**
     * 图布局id
     */
    private Long layoutId;

    /**
     * 一组图节点
     */
    private List<GraphNode> graphNodes;

    /**
     * 一组图边
     */
    private List<GraphEdge> graphEdges;

    public void checkEmpty() {
        Assert.notNull(layoutId, "图布局id不能为空");
        Assert.notEmpty(graphNodes, "图布局节点组不能为空");
        Assert.notEmpty(graphEdges, "图布局边组不能为空");
    }

}
