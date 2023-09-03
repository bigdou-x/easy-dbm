
package org.bigdou.dbm.graph.executor;

import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.ExceptionFactory;
import org.apache.commons.lang3.ObjectUtils;
import org.bigdou.dbm.dto.GraphSaveCmd;
import org.bigdou.dbm.dto.ErrorCode;
import org.bigdou.dbm.graph.GraphLayoutDO;
import org.bigdou.dbm.graph.GraphRepository;
import org.bigdou.dbm.graph.QGraphLayoutDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 接口描述: 执行保存图表命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class GraphSaveCmdExe {

    @Resource
    private GraphRepository graphRepository;

    @Transactional(rollbackFor = Exception.class)
    public SingleResponse<Long> execute(GraphSaveCmd cmd) {
        GraphLayoutDO existsGraph = this.graphRepository.findOne(QGraphLayoutDO.graphLayoutDO.name.eq(cmd.getName())).orElse(null);
        if (ObjectUtils.isNotEmpty(existsGraph) && !existsGraph.getId().equals(cmd.getId())) {
            throw ExceptionFactory.bizException(ErrorCode.GRAPH_NAME_CONFLICT.getErrCode(), ErrorCode.GRAPH_NAME_CONFLICT.getErrDesc());
        }
        GraphLayoutDO graphLayoutDO = new GraphLayoutDO();
        BeanUtils.copyProperties(cmd, graphLayoutDO);
        return SingleResponse.of(this.graphRepository.save(graphLayoutDO).getId());
    }

}
