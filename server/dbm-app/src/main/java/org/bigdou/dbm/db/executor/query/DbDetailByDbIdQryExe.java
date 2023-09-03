
package org.bigdou.dbm.db.executor.query;

import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import org.bigdou.dbm.db.DbInfoDO;
import org.bigdou.dbm.db.DbRepository;
import org.bigdou.dbm.dto.DbDetailByDbIdQry;
import org.bigdou.dbm.dto.data.DbDetailDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 接口描述: 执行查询数据库详情命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class DbDetailByDbIdQryExe {

    @Resource
    private DbRepository repository;

    public SingleResponse<DbDetailDTO> execute(DbDetailByDbIdQry cmd) {
        final DbInfoDO dbInfo = this.repository.getReferenceById(cmd.getDbId());
        Assert.notNull(dbInfo, "数据库信息不存在");
        final DbDetailDTO dbDetailDTO = new DbDetailDTO();
        BeanUtils.copyProperties(dbInfo, dbDetailDTO);
        return SingleResponse.of(
                dbDetailDTO
        );
    }

}
