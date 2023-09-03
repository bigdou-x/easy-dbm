
package org.bigdou.dbm.db.executor;

import com.alibaba.cola.dto.Response;
import org.bigdou.dbm.db.DbRepository;
import org.bigdou.dbm.dto.DbRemoveCmd;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 接口描述: 执行删除数据库命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class DbRemoveCmdExe {

    @Resource
    private DbRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public Response execute(DbRemoveCmd cmd) {
        this.repository.deleteAllById(cmd.getIds());
        return Response.buildSuccess();
    }

}
