package org.bigdou.dbm.domain.db.entity;

import java.io.Serializable;

/**
 * 类描述: 数据模型 <br>
 *
 * @author xuqing
 * @date 2023-06-27 19:55
 */
@lombok.Data
public class TableData implements Cloneable, Serializable {

    private static final long serialVersionUID = 6955314356854078868L;

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段值
     */
    private Object value;

    @Override
    public Object clone() throws CloneNotSupportedException {
        final TableData clone = (TableData)super.clone();
        final TableData tableData = new TableData();
        tableData.setName(clone.getName());
        tableData.setValue(clone.getValue());
        return tableData;
    }

}
