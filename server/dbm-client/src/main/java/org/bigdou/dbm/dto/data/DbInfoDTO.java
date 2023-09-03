package org.bigdou.dbm.dto.data;

import lombok.Data;

/**
 * 类描述: 数据源vo
 *
 * @author xuqing F00722
 * @date 2023/1/30 12:12
 */
@Data
public class DbInfoDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * jdbcUrl
     */
    private String jdbcUrl;

    /**
     * 用户名
     */
    private String username;

    /**
     * 数据库连接中
     */
    private boolean connected;

}
