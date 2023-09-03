package org.bigdou.dbm.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 类描述: 查询建立着数据库连接的选项
 *
 * @author xuqing F00722
 * @date 2023-8-23 15:51:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DbOptionByConnectedQry extends Query{

}
