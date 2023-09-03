package org.bigdou.dbm.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 类描述: 创建数据源
 *
 * @author xuqing F00722
 * @date 2023/1/19 12:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DbSaveCmd extends Command {

    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * jdbcUrl
     */
    @NotBlank(message = "jdbcUrl不能为空")
    private String jdbcUrl;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

}
