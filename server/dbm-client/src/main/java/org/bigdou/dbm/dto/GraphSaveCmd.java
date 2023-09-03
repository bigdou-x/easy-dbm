package org.bigdou.dbm.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 类描述: 保存图表
 *
 * @author xuqing F00722
 * @date 2023/1/19 12:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GraphSaveCmd extends Command {

    /**
     * id
     */
    private Long id;

    /**
     * 数据库id
     */
    @NotNull(message = "数据库id不能为空")
    private Long dbId;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 描述
     */
    private String description;

}
