package org.bigdou.dbm.dto.data;

import lombok.Data;

import java.util.List;

/**
 * 类描述: 图信息
 *
 * @author xuqing F00722
 * @date 2023/1/30 12:12
 */
@Data
public class GraphInfoDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 数据库id
     */
    private Long dbId;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

}
