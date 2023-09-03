package org.bigdou.dbm.domain.db.util;

import java.sql.Connection;

/**
 * 类描述:
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:40
 */
public interface DataSourceFactoryI {

    /**
     * 方法描述: 创建数据源
     * @author: xuqing
     * @date 2023-08-28 12:29
     * @param dbId
     * @param url
     * @param username
     * @param password
     */
    void create(Long dbId, String url, String username, String password);

    /**
     * 方法描述: 获取数据源连接
     * @author: xuqing
     * @date 2023-08-28 12:29
     * @param dbId
     */
    Connection getConnection(Long dbId);

    /**
     * 方法描述: 关闭数据源连接
     * @author: xuqing
     * @date 2023-08-28 12:30
     * @param connection
     */
    void close(Connection connection);

    /**
     * 方法描述: 判断数据源是否被创建
     * @author: xuqing
     * @date 2023-08-28 12:30
     * @param dbId
     * @return boolean
     */
    boolean containsKey(Long dbId);

    /**
     * 方法描述: 删除数据源
     * @author: xuqing
     * @date 2023-08-28 14:02
     * @param dbId
     */
    void remove(Long dbId);;

}
