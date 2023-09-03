package org.bigdou.dbm.graph.executor.query;

import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import org.bigdou.dbm.dto.GraphDetailQry;
import org.bigdou.dbm.dto.data.GraphDetailDTO;
import org.bigdou.dbm.graph.GraphLayoutDO;
import org.bigdou.dbm.graph.GraphRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 接口描述: 执行查询表图命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class GraphDetailQryExe {

    @Resource
    private GraphRepository repository;

    public SingleResponse<GraphDetailDTO> execute(GraphDetailQry cmd) {
        GraphLayoutDO graphLayoutDO = this.repository.getReferenceById(cmd.getId());
        Assert.notNull(graphLayoutDO, "图表信息不存在");
        GraphDetailDTO graphDetailDTO = new GraphDetailDTO();
        BeanUtils.copyProperties(graphLayoutDO, graphDetailDTO);
        return SingleResponse.of(graphDetailDTO);
    }

}
