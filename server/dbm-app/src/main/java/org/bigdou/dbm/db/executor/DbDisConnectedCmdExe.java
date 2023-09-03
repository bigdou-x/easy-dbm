
package org.bigdou.dbm.db.executor;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import org.bigdou.dbm.db.DbInfoDO;
import org.bigdou.dbm.db.DbRepository;
import org.bigdou.dbm.domain.db.util.DataSourceFactoryI;
import org.bigdou.dbm.dto.DbConnectCmd;
import org.bigdou.dbm.dto.DbDisConnectedCmd;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 接口描述: 执行连接数据库命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class DbDisConnectedCmdExe {

    @Resource
    private DbRepository dbRepository;

    @Resource
    private DataSourceFactoryI dataSourceFactory;

    @Transactional(rollbackFor = Exception.class)
    public Response execute(DbDisConnectedCmd cmd) {
        final DbInfoDO dbInfo = this.dbRepository.getReferenceById(cmd.getDbId());
        Assert.notNull(dbInfo, "数据库不存在!");
        this.dataSourceFactory.remove(dbInfo.getId());
        return Response.buildSuccess();
    }

}
