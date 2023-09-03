package org.bigdou.dbm.domain.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述: 表索引模型
 *
 * @author xuqing F00722
 * @date 2023/1/10 11:41
 */
@NoArgsConstructor
@Data
public class TableIndex implements Cloneable, Serializable {

    private static final long serialVersionUID = 8915535720264081428L;

    /**
     * 索引标识
     */
    private String key;

    /**
     * 索引名称
     */
    private String name;

    /**
     * 索引是否唯一
     */
    private boolean unique;

    /**
     * 索引注释
     */
    private String comment;

    /**
     * 索引字段模型
     */
    private List<IndexColumn> indexColumns = new ArrayList<>();

    @Override
    public Object clone() throws CloneNotSupportedException {
        final TableIndex clone = (TableIndex)super.clone();
        final TableIndex tableIndex = new TableIndex();
        tableIndex.setKey(clone.getKey());
        tableIndex.setName(clone.getName());
        tableIndex.setUnique(clone.isUnique());
        tableIndex.setComment(clone.getComment());
        tableIndex.setIndexColumns(clone.getIndexColumns());
        return tableIndex;
    }

    /**
     * 类描述: 表索引字段模型
     *
     * @author xuqing F00722
     * @date 2023/1/10 11:41
     */
    @Data
    public static class IndexColumn implements Cloneable, Serializable {

        /**
         * 列名
         */
        private String columnName;

        /**
         * 字段排序
         */
        private String ascOrDesc;

        @Override
        public Object clone() throws CloneNotSupportedException {
            IndexColumn clone = (IndexColumn) super.clone();
            IndexColumn indexColumn = new IndexColumn();
            indexColumn.setColumnName(clone.getColumnName());
            indexColumn.setAscOrDesc(clone.getAscOrDesc());
            return indexColumn;
        }

    }

}
