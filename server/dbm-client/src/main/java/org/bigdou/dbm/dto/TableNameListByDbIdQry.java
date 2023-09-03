package org.bigdou.dbm.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;

@Data
public class TableNameListByDbIdQry extends Query{
   private Long dbId;
}
