package org.bigdou.dbm.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 类描述: 表图查询
 *
 * @author xuqing F00722
 * @date 2023-8-23 15:51:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TableGraphLayoutQry extends Query {

    /**
     * 数据库id
     */
    @NotNull(message = "数据库id不能为空")
    private Long dbId;

    /**
     * 图id
     */
    @NotNull(message = "图布局id不能为空")
    private Long graphLayoutId;

}
