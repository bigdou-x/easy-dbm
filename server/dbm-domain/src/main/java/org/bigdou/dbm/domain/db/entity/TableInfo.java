package org.bigdou.dbm.domain.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类描述: 表信息
 *
 * @author xuqing F00722
 * @date 2023/1/10 11:41
 */
@NoArgsConstructor
@Data
public class TableInfo implements Cloneable, Serializable {

    private static final long serialVersionUID = 8915535720264081428L;

    /**
     * 表名
     */
    private String name;

    /**
     * 表注释
     */
    private String comment;

    /**
     * 字段信息
     */
    private List<TableColumn> tableColumns = new ArrayList<>();

    /**
     * 联合主键
     */
    private List<TableIndex> indices = new ArrayList<>();

    /**
     * 数据模型列表
     */
    private List<TableData> data = new ArrayList<>();

    @Override
    public Object clone() throws CloneNotSupportedException {
        final TableInfo clone = (TableInfo)super.clone();
        final TableInfo tableInfo = new TableInfo();
        tableInfo.setName(clone.getName());
        tableInfo.setComment(clone.getComment());

        if (!CollectionUtils.isEmpty(clone.getTableColumns())) {
            tableInfo.setTableColumns(clone.getTableColumns().stream().map(ObjectUtils::clone).collect(Collectors.toList()));
        }
        if (!CollectionUtils.isEmpty(clone.getData())) {
            tableInfo.setData(clone.getData().stream().map(ObjectUtils::clone).collect(Collectors.toList()));
        }
        if (!CollectionUtils.isEmpty(clone.getIndices())) {
            tableInfo.setIndices(clone.getIndices().stream().map(ObjectUtils::clone).collect(Collectors.toList()));
        }
        return tableInfo;
    }

    public TableIndex getTableIndex(String indexKey) {
        for (TableIndex tableIndex : indices) {
            if (indexKey.equals(tableIndex.getKey())) {
                return tableIndex;
            }
        }
        return null;
    }

}
