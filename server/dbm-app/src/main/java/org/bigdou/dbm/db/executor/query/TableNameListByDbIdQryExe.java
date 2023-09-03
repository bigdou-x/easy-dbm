
package org.bigdou.dbm.db.executor.query;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.exception.ExceptionFactory;
import org.bigdou.dbm.domain.db.dialect.DatabaseDialect;
import org.bigdou.dbm.dto.TableNameListByDbIdQry;
import org.bigdou.dbm.dto.ErrorCode;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 接口描述: 执行查询表名命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class TableNameListByDbIdQryExe {

    public MultiResponse<String> execute(TableNameListByDbIdQry cmd) {
        DatabaseDialect databaseDialect = new DatabaseDialect();
        try {
            return MultiResponse.of(databaseDialect.tableNames(cmd.getDbId()));
        } catch (SQLException e) {
            throw ExceptionFactory.bizException(ErrorCode.TABLE_NAME_QUERY_ERROR.getErrCode(), ErrorCode.TABLE_NAME_QUERY_ERROR.getErrDesc());
        }
    }

}
