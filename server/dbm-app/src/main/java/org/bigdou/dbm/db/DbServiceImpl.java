package org.bigdou.dbm.db;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.bigdou.dbm.api.DbServiceI;
import org.bigdou.dbm.db.executor.DbConnectCmdExe;
import org.bigdou.dbm.db.executor.DbDisConnectedCmdExe;
import org.bigdou.dbm.db.executor.DbRemoveCmdExe;
import org.bigdou.dbm.db.executor.DbSaveCmdExe;
import org.bigdou.dbm.db.executor.TableSyncCmdExe;
import org.bigdou.dbm.db.executor.query.DbDetailByDbIdQryExe;
import org.bigdou.dbm.db.executor.query.DbListQueryExe;
import org.bigdou.dbm.db.executor.query.DbOptionByConnectedQryExe;
import org.bigdou.dbm.db.executor.query.TableNameListByDbIdQryExe;
import org.bigdou.dbm.dto.DbConnectCmd;
import org.bigdou.dbm.dto.DbDetailByDbIdQry;
import org.bigdou.dbm.dto.DbDisConnectedCmd;
import org.bigdou.dbm.dto.DbOptionByConnectedQry;
import org.bigdou.dbm.dto.DbRemoveCmd;
import org.bigdou.dbm.dto.DbSaveCmd;
import org.bigdou.dbm.dto.TableNameListByDbIdQry;
import org.bigdou.dbm.dto.TableSyncCmd;
import org.bigdou.dbm.dto.data.DbInfoDTO;
import org.bigdou.dbm.dto.data.DbDetailDTO;
import org.bigdou.dbm.dto.data.DbOptionDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 接口描述: 数据源应用
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Slf4j
@Component
public class DbServiceImpl implements DbServiceI {

    @Resource
    private DbSaveCmdExe dbSaveCmdExe;

    @Resource
    private DbRemoveCmdExe dbRemoveCmdExe;

    @Resource
    private DbListQueryExe dbListQueryExe;

    @Resource
    private TableNameListByDbIdQryExe tableNameListByDbIdQryExe;

    @Resource
    private DbDetailByDbIdQryExe dbDetailByDbIdQryExe;

    @Resource
    private TableSyncCmdExe tableSyncCmdExe;

    @Resource
    private DbConnectCmdExe dbConnectCmdExe;

    @Resource
    private DbDisConnectedCmdExe dbDisConnectedCmdExe;

    @Resource
    private DbOptionByConnectedQryExe dbOptionByConnectedQryExe;

    @Override
    public SingleResponse<Long> save(DbSaveCmd cmd) {
        return this.dbSaveCmdExe.execute(cmd);
    }

    @Override
    public Response remove(DbRemoveCmd cmd) {
        return this.dbRemoveCmdExe.execute(cmd);
    }

    @Override
    public MultiResponse<DbInfoDTO> list(String name) {
        return this.dbListQueryExe.execute(name);
    }

    @Override
    public MultiResponse<String> queryTableNames(TableNameListByDbIdQry cmd) {
        return this.tableNameListByDbIdQryExe.execute(cmd);
    }

    @Override
    public SingleResponse<DbDetailDTO> detail(DbDetailByDbIdQry cmd) {
        return this.dbDetailByDbIdQryExe.execute(cmd);
    }

    @Override
    public Response sync(TableSyncCmd cmd) {
        return this.tableSyncCmdExe.execute(cmd);
    }

    @Override
    public Response connect(DbConnectCmd cmd) {
        return this.dbConnectCmdExe.execute(cmd);
    }

    @Override
    public Response closeConnected(DbDisConnectedCmd cmd) {
        return this.dbDisConnectedCmdExe.execute(cmd);
    }

    @Override
    public MultiResponse<DbOptionDTO> listOptions() {
        return this.dbOptionByConnectedQryExe.execute(new DbOptionByConnectedQry());
    }
}
