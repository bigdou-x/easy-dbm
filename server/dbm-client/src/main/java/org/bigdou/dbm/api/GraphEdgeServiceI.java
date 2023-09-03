package org.bigdou.dbm.api;

import com.alibaba.cola.dto.Response;
import org.bigdou.dbm.dto.GraphLayoutSaveCmd;

/**
 * 类描述: 图边 <br>
 *
 * @author xuqing
 * @date 2023-08-27 16:55
 */
public interface GraphEdgeServiceI {

    /**
     * 方法描述: 布局保存
     * @author: xuqing
     * @date 2023-08-30 11:23
     * @param cmd
     * @return com.alibaba.cola.dto.Response
     */
    Response saveLayout(GraphLayoutSaveCmd cmd);

}
