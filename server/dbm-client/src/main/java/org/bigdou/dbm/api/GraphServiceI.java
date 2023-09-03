package org.bigdou.dbm.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.bigdou.dbm.dto.GraphDetailQry;
import org.bigdou.dbm.dto.GraphListQry;
import org.bigdou.dbm.dto.GraphRemoveCmd;
import org.bigdou.dbm.dto.GraphSaveCmd;
import org.bigdou.dbm.dto.TableGraphLayoutQry;
import org.bigdou.dbm.dto.data.GraphDetailDTO;
import org.bigdou.dbm.dto.data.GraphInfoDTO;
import org.bigdou.dbm.dto.data.TableGraphLayoutDTO;

/**
 * 类描述: 图 <br>
 *
 * @author xuqing
 * @date 2023-08-27 16:55
 */
public interface GraphServiceI {

    /**
     * 方法描述: 查询表图
     * @author: xuqing
     * @param cmd
     * @date 2023-08-27 16:56
     * @return com.alibaba.cola.dto.SingleResponse<org.bigdou.dbm.dto.data.TableGraphDTO>
     */
    SingleResponse<TableGraphLayoutDTO> queryTableGraphLayout(TableGraphLayoutQry cmd);

    /**
     * 方法描述: 查询表列表
     * @author: xuqing
     * @date 2023-08-28 15:41
     * @param cmd
     * @return com.alibaba.cola.dto.MultiResponse<org.bigdou.dbm.dto.data.GraphInfoDTO>
     */
    MultiResponse<GraphInfoDTO> list(GraphListQry cmd);

    /**
     * 方法描述: 保存图表
     * @author: xuqing
     * @date 2023-08-28 16:47
     * @param cmd
     * @return com.alibaba.cola.dto.Response
     */
    SingleResponse<Long> save(GraphSaveCmd cmd);

    /**
     * 方法描述: 图表详情信息
     * @author: xuqing
     * @date 2023-08-28 17:24
     * @param cmd
     * @return com.alibaba.cola.dto.SingleResponse<org.bigdou.dbm.dto.data.GraphDetailDTO>
     */
    SingleResponse<GraphDetailDTO> detail(GraphDetailQry cmd);

    /**
     * 方法描述: 删除图
     * @author: xuqing
     * @date 2023-08-28 17:29
     * @param cmd
     * @return com.alibaba.cola.dto.Response
     */
    Response remove(GraphRemoveCmd cmd);

}
