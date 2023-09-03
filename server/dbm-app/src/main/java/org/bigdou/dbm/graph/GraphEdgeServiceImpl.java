package org.bigdou.dbm.graph;

import com.alibaba.cola.dto.Response;
import org.bigdou.dbm.api.GraphEdgeServiceI;
import org.bigdou.dbm.dto.GraphLayoutSaveCmd;
import org.bigdou.dbm.graph.executor.GraphLayoutSaveCmdExe;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 类描述: 图边 <br>
 *
 * @author xuqing
 * @date 2023-08-27 16:56
 */
@Component
public class GraphEdgeServiceImpl implements GraphEdgeServiceI {

    @Resource
    private GraphLayoutSaveCmdExe graphLayoutSaveCmdExe;

    @Override
    public Response saveLayout(GraphLayoutSaveCmd cmd) {
        return this.graphLayoutSaveCmdExe.execute(cmd);
    }
}
