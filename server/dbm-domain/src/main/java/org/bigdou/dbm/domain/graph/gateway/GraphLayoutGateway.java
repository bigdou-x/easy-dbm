package org.bigdou.dbm.domain.graph.gateway;

import org.bigdou.dbm.domain.graph.GraphLayout;

/**
 * 类描述: 图布局 <br>
 *
 * @author xuqing
 * @date 2023-08-30 13:04
 */
public interface GraphLayoutGateway {

    /**
     * 方法描述: 保存图布局
     * @author: xuqing
     * @date 2023-08-30 16:35
     * @param dbId
     * @param graphLayout
     */
    void save(Long dbId, GraphLayout graphLayout);

    /**
     * 方法描述: 通过id获取图布局
     * @author: xuqing
     * @date 2023-08-30 16:37
     * @param graphLayoutId
     * @return org.bigdou.dbm.domain.graph.GraphLayout
     */
    GraphLayout getById(Long graphLayoutId);

}
