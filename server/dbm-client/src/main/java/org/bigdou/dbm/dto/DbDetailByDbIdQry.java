package org.bigdou.dbm.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class DbDetailByDbIdQry extends Query{

   @NotNull(message = "数据库id不能为空")
   private Long dbId;

}
