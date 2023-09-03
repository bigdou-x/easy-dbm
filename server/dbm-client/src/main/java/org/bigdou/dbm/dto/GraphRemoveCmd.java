package org.bigdou.dbm.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 类描述: 删除图表
 *
 * @author xuqing F00722
 * @date 2023/1/19 12:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GraphRemoveCmd extends Command {

    /**
     * ids
     */
    @NotEmpty(message = "ids不能为空")
    private List<Long> ids;

}
