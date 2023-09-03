package org.bigdou.dbm.graph;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.bigdou.dbm.api.GraphServiceI;
import org.bigdou.dbm.dto.GraphDetailQry;
import org.bigdou.dbm.dto.GraphListQry;
import org.bigdou.dbm.dto.GraphRemoveCmd;
import org.bigdou.dbm.dto.GraphSaveCmd;
import org.bigdou.dbm.dto.TableGraphLayoutQry;
import org.bigdou.dbm.dto.data.GraphDetailDTO;
import org.bigdou.dbm.dto.data.GraphInfoDTO;
import org.bigdou.dbm.dto.data.TableGraphLayoutDTO;
import org.bigdou.dbm.graph.executor.GraphRemoveCmdExe;
import org.bigdou.dbm.graph.executor.GraphSaveCmdExe;
import org.bigdou.dbm.graph.executor.query.GraphDetailQryExe;
import org.bigdou.dbm.graph.executor.query.GraphListQryExe;
import org.bigdou.dbm.graph.executor.query.TableGraphLayoutQryExe;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 类描述: 图 <br>
 *
 * @author xuqing
 * @date 2023-08-27 16:56
 */
@EnableAsync
@Component
public class GraphServiceImpl implements GraphServiceI {

    @Resource
    private TableGraphLayoutQryExe tableGraphLayoutQryExe;

    @Resource
    private GraphListQryExe graphListQryExe;

    @Resource
    private GraphSaveCmdExe graphSaveCmdExe;

    @Resource
    private GraphDetailQryExe graphDetailQryExe;

    @Resource
    private GraphRemoveCmdExe graphRemoveCmdExe;

    @Override
    public SingleResponse<TableGraphLayoutDTO> queryTableGraphLayout(TableGraphLayoutQry cmd) {
        return this.tableGraphLayoutQryExe.execute(cmd);
    }

    @Override
    public MultiResponse<GraphInfoDTO> list(GraphListQry cmd) {
        return this.graphListQryExe.execute(cmd);
    }

    @Override
    public SingleResponse<Long> save(GraphSaveCmd cmd) {
        return this.graphSaveCmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<GraphDetailDTO> detail(GraphDetailQry cmd) {
        return this.graphDetailQryExe.execute(cmd);
    }

    @Override
    public Response remove(GraphRemoveCmd cmd) {
        return this.graphRemoveCmdExe.execute(cmd);
    }

}
