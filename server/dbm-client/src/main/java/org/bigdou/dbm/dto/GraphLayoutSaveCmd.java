package org.bigdou.dbm.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 类描述: 图边保存命令
 *
 * @author xuqing F00722
 * @date 2023/1/19 12:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GraphLayoutSaveCmd extends Command {

    /**
     * 数据库id
     */
    @NotNull(message = "数据库id不能为空")
    private Long dbId;

    /**
     * 图id
     */
    @NotNull(message = "图布局id不能为空")
    private Long graphLayoutId;

    /**
     * 图边关系
     */
    @NotEmpty(message = "图边关系不能为空")
    private List<GraphEdgeRelation> graphEdges;

    /**
     * 图节点
     */
    @NotEmpty(message = "图节点不能为空")
    private List<GraphNode> graphNodes;

    @Data
    public static class GraphEdgeRelation {

        /**
         * source cell
         */
        private String sourceCell;

        /**
         * source port
         */
        private String sourcePort;

        /**
         * target cell
         */
        private String targetCell;

        /**
         * target port
         */
        private String targetPort;

    }

    @Data
    public static class GraphNode {

        /**
         * 节点key
         */
        private String nodeKey;

        /**
         * 定位 x
         */
        private Integer positionX;

        /**
         * 定位 y
         */
        private Integer positionY;

    }

}
