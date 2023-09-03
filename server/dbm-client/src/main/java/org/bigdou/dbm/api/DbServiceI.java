package org.bigdou.dbm.api;


import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.bigdou.dbm.dto.DbConnectCmd;
import org.bigdou.dbm.dto.DbDetailByDbIdQry;
import org.bigdou.dbm.dto.DbDisConnectedCmd;
import org.bigdou.dbm.dto.DbRemoveCmd;
import org.bigdou.dbm.dto.DbSaveCmd;
import org.bigdou.dbm.dto.TableNameListByDbIdQry;
import org.bigdou.dbm.dto.TableSyncCmd;
import org.bigdou.dbm.dto.data.DbInfoDTO;
import org.bigdou.dbm.dto.data.DbDetailDTO;
import org.bigdou.dbm.dto.data.DbOptionDTO;

import java.util.List;

/**
 * 接口描述: 数据源应用
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
public interface DbServiceI {

    /**
     * 方法描述: 保存数据库
     * @author: xuqing
     * @date 2023-08-27 15:32
     * @param cmd
     * @return java.lang.Long
     */
    SingleResponse<Long> save(DbSaveCmd cmd);

    /**
     * 方法描述: 删除数据库
     * @author: xuqing
     * @date 2023-08-27 15:33
     * @param cmd
     */
    Response remove(DbRemoveCmd cmd);

    /**
     * 方法描述: 查询数据库列表
     * @author: xuqing
     * @param name
     * @date 2023-08-27 15:33
     * @return java.util.List<org.bigdou.dbm.dto.data.DbDTO>
     */
    MultiResponse<DbInfoDTO> list(String name);

    /**
     * 方法描述: 根据数据库id查询表名
     * @author: xuqing
     * @date 2023-08-27 15:34
     * @param cmd
     * @return java.util.List<java.lang.String>
     */
    MultiResponse<String> queryTableNames(TableNameListByDbIdQry cmd);

    /**
     * 方法描述: 根据数据库id查询数据库详细
     * @author: xuqing
     * @date 2023-08-27 15:34
     * @param id
     * @return org.bigdou.dbm.dto.data.DbDetailDTO
     */
    SingleResponse<DbDetailDTO> detail(DbDetailByDbIdQry cmd);

    /**
     * 方法描述: 表同步
     * @author: xuqing
     * @date 2023-08-27 15:35
     * @param cmd
     */
    Response sync(TableSyncCmd cmd);

    /**
     * 方法描述: 连接数据库
     * @author: xuqing
     * @date 2023-08-27 15:35
     * @param cmd
     */
    Response connect(DbConnectCmd cmd);

    /**
     * 方法描述: 关闭数据库连接
     * @author: xuqing
     * @date 2023-08-27 15:35
     * @param cmd
     */
    Response closeConnected(DbDisConnectedCmd cmd);

    /**
     * 方法描述: 下拉框
     * @author: xuqing
     * @date 2023-08-28 14:55
     * @return com.alibaba.cola.dto.MultiResponse<org.bigdou.dbm.dto.data.DbOptionDTO>
     */
    MultiResponse<DbOptionDTO> listOptions();

}
