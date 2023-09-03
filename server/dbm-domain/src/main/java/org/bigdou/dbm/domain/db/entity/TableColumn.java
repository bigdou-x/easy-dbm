package org.bigdou.dbm.domain.db.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 类描述: 字段模型 <br>
 *
 * @author xuqing
 * @date 2023-06-25 20:02
 */
@Data
public class TableColumn implements Cloneable, Serializable {

    private static final long serialVersionUID = 4168547579111317935L;

    /**
     * 状态
     */
    private ColumnStatusEnum status;

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段长度
     */
    private Integer length;

    /**
     * 是否为空
     */
    private boolean nullable;

    /**
     * 是否主键
     */
    private boolean primaryKey;

    /**
     * 是否自增
     */
    private boolean autoincrement;

    /**
     * 字段注释
     */
    private String comment;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 列排序
     */
    private Integer ordinalPosition;

    /**
     * 精度
     */
    private Integer decimalDigits;

    @Override
    public Object clone() throws CloneNotSupportedException {
        final TableColumn clone = (TableColumn)super.clone();
        final TableColumn tableColumn = new TableColumn();
        tableColumn.setStatus(clone.getStatus());
        tableColumn.setName(clone.getName());
        tableColumn.setType(clone.getType());
        tableColumn.setLength(clone.getLength());
        tableColumn.setNullable(clone.isNullable());
        tableColumn.setPrimaryKey(clone.isPrimaryKey());
        tableColumn.setComment(clone.getComment());
        tableColumn.setDefaultValue(clone.getDefaultValue());
        return tableColumn;
    }

    /**
     * 字段状态
     */
    public enum ColumnStatusEnum {

        /**
         * 没改变
         */
        NOT_CHANGE,

        /**
         * 新增
         */
        ADD,

        /**
         * 更新
         */
        CHANGE,

        /**
         * 删除
         */
        DROP
    }

}
