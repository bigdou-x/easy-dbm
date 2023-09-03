
package org.bigdou.dbm.graph.executor;

import com.alibaba.cola.dto.Response;
import org.bigdou.dbm.domain.graph.GraphEdge;
import org.bigdou.dbm.domain.graph.GraphLayout;
import org.bigdou.dbm.domain.graph.GraphNode;
import org.bigdou.dbm.domain.graph.gateway.GraphLayoutGateway;
import org.bigdou.dbm.dto.GraphLayoutSaveCmd;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * 接口描述: 执行保存图边命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class GraphLayoutSaveCmdExe {

    @Resource
    private GraphLayoutGateway graphLayoutGateway;

    @Transactional(rollbackFor = Exception.class)
    public Response execute(GraphLayoutSaveCmd cmd) {
        GraphLayout graphLayout = new GraphLayout();
        graphLayout.setLayoutId(cmd.getGraphLayoutId());
        graphLayout.setGraphNodes(
                cmd.getGraphNodes().stream().map(o -> {
                    GraphNode graphNode = new GraphNode();
                    graphNode.setNodeId(o.getNodeKey());
                    graphNode.setPositionX(o.getPositionX());
                    graphNode.setPositionY(o.getPositionY());
                    return graphNode;
                }).collect(Collectors.toList())
        );
        graphLayout.setGraphEdges(
                cmd.getGraphEdges().stream().map(o -> {
                    GraphEdge graphEdge = new GraphEdge();
                    graphEdge.setSourceCell(o.getSourceCell());
                    graphEdge.setSourcePort(o.getSourcePort());
                    graphEdge.setTargetCell(o.getTargetCell());
                    graphEdge.setTargetPort(o.getTargetPort());
                    return graphEdge;
                }).collect(Collectors.toList())
        );
        this.graphLayoutGateway.save(cmd.getDbId(), graphLayout);
        return Response.buildSuccess();
    }

}
