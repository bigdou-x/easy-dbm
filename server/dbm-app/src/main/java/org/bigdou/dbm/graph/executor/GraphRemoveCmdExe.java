
package org.bigdou.dbm.graph.executor;

import com.alibaba.cola.dto.Response;
import org.bigdou.dbm.dto.GraphRemoveCmd;
import org.bigdou.dbm.graph.GraphRepository;
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
public class GraphRemoveCmdExe {

    @Resource
    private GraphRepository graphRepository;

    @Transactional(rollbackFor = Exception.class)
    public Response execute(GraphRemoveCmd cmd) {
        this.graphRepository.deleteAllById(cmd.getIds());
        return Response.buildSuccess();
    }

}
