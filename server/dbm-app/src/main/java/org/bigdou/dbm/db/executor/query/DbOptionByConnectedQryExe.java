
package org.bigdou.dbm.db.executor.query;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import org.bigdou.dbm.db.DbInfoDO;
import org.bigdou.dbm.db.DbRepository;
import org.bigdou.dbm.domain.db.util.DataSourceFactoryI;
import org.bigdou.dbm.dto.DbDetailByDbIdQry;
import org.bigdou.dbm.dto.DbOptionByConnectedQry;
import org.bigdou.dbm.dto.data.DbDetailDTO;
import org.bigdou.dbm.dto.data.DbOptionDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * 接口描述: 执行查询数据库详情命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class DbOptionByConnectedQryExe {

    @Resource
    private DbRepository repository;

    @Resource
    private DataSourceFactoryI dataSourceFactory;

    public MultiResponse<DbOptionDTO> execute(DbOptionByConnectedQry cmd) {
        return MultiResponse.of(
                this.repository.findAll().stream()
                        .filter(o -> this.dataSourceFactory.containsKey(o.getId()))
                        .map(o -> {
                            DbOptionDTO dbOptionDTO = new DbOptionDTO();
                            dbOptionDTO.setId(o.getId());
                            dbOptionDTO.setName(o.getName());
                            return dbOptionDTO;
                        }).collect(Collectors.toList())
        );
    }

}
