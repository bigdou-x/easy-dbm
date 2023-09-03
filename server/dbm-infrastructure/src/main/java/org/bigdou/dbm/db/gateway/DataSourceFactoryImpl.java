package org.bigdou.dbm.db.gateway;

import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.ExceptionFactory;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.bigdou.dbm.domain.db.util.DataSourceFactoryI;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 类描述:
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:28
 */
@Slf4j
@Component
public class DataSourceFactoryImpl implements DataSourceFactoryI {

    private static final Map<Long, DruidDataSource> DATA_SOURCE_MAP = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void create(Long dbId, String url, String username, String password) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        DATA_SOURCE_MAP.put(dbId, druidDataSource);
    }

    @Override
    public Connection getConnection(Long dbId) {
        Assert.notNull(dbId, "dbId不能为空");
        Assert.isTrue(DATA_SOURCE_MAP.containsKey(dbId), "数据源未创建!");
        try {
            return DATA_SOURCE_MAP.get(dbId).getConnection();
        } catch (SQLException e) {
            log.error("Connection get failed!", e);
            throw ExceptionFactory.bizException("Connection get failed!");
        }
    }

    @Override
    public void close(Connection connection) {
        if (ObjectUtils.isEmpty(connection)) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Connection close failed!", e);
        }
    }

    @Override
    public boolean containsKey(Long dbId) {
        Assert.notNull(dbId, "dbId不能为空");
        return DATA_SOURCE_MAP.containsKey(dbId);
    }

    @Override
    public void remove(Long dbId) {
        DruidDataSource removeDataSource = DATA_SOURCE_MAP.remove(dbId);
        if (ObjectUtils.isEmpty(removeDataSource)) {
            log.warn("删除数据源失败,数据源不存在, dbId:{}", dbId);
            return;
        }
        if (removeDataSource.isClosed()) {
            log.warn("删除数据源失败,数据源已关闭, dbId:{}", dbId);
            return;
        }
        removeDataSource.close();
    }
}