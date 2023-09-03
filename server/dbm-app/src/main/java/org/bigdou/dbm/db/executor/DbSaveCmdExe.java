
package org.bigdou.dbm.db.executor;

import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.ExceptionFactory;
import org.apache.commons.lang3.ObjectUtils;
import org.bigdou.dbm.db.DbInfoDO;
import org.bigdou.dbm.db.DbRepository;
import org.bigdou.dbm.db.QDbInfoDO;
import org.bigdou.dbm.dto.DbSaveCmd;
import org.bigdou.dbm.dto.ErrorCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * 接口描述: 执行保存数据库命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class DbSaveCmdExe {

    @Resource
    private DbRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public SingleResponse<Long> execute(DbSaveCmd cmd) {
        DbInfoDO existsDbInfo = this.repository.findOne(QDbInfoDO.dbInfoDO.name.eq(cmd.getName())).orElse(null);
        if (ObjectUtils.isNotEmpty(existsDbInfo) && !existsDbInfo.getId().equals(cmd.getId())) {
            throw ExceptionFactory.bizException(ErrorCode.DB_NAME_CONFLICT.getErrCode(), ErrorCode.DB_NAME_CONFLICT.getErrDesc());
        }
        DbInfoDO dbInfoDO = new DbInfoDO();
        BeanUtils.copyProperties(cmd, dbInfoDO);
        return SingleResponse.of(this.repository.save(dbInfoDO).getId());
    }

}
