package org.bigdou.dbm.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 类描述: 同步表
 *
 * @author xuqing F00722
 * @date 2023-8-23 15:51:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TableSyncCmd extends Command {

    /**
     * 表名
     */
    @NotEmpty(message = "表名不能为空")
    private List<String> tableNames;

    /**
     * 数据库id
     */
    @NotNull(message = "数据库id不能为空")
    private Long dbId;

}
