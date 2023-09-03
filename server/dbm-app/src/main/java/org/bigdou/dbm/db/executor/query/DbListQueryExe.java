
package org.bigdou.dbm.db.executor.query;

import com.alibaba.cola.dto.MultiResponse;
import org.apache.commons.lang3.StringUtils;
import org.bigdou.dbm.db.DbInfoDO;
import org.bigdou.dbm.db.DbRepository;
import org.bigdou.dbm.domain.db.util.DataSourceFactoryI;
import org.bigdou.dbm.dto.data.DbInfoDTO;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.stream.Collectors;

/**
 * 接口描述: 执行查询数据库列表命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class DbListQueryExe {

    @Resource
    private DbRepository repository;

    @Resource
    private DataSourceFactoryI dataSourceFactory;

    public MultiResponse<DbInfoDTO> execute(String name) {
        DbInfoDO dbInfoDO = new DbInfoDO();
        if (StringUtils.isNotBlank(name)) {
            dbInfoDO.setName(name);
        }
        return MultiResponse.of(
                this.repository.findAll(Example.of(dbInfoDO)).stream().map(o -> {
                    DbInfoDTO dbInfoDTO = new DbInfoDTO();
                    dbInfoDTO.setId(o.getId());
                    dbInfoDTO.setName(o.getName());
                    dbInfoDTO.setJdbcUrl(o.getJdbcUrl());
                    dbInfoDTO.setUsername(o.getUsername());
                    dbInfoDTO.setConnected(dataSourceFactory.containsKey(o.getId()));
                    return dbInfoDTO;
                }).collect(Collectors.toList())
        );
    }

}
