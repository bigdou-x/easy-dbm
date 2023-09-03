
package org.bigdou.dbm.db.executor;

import com.alibaba.cola.dto.Response;
import org.bigdou.dbm.db.ColumnRepository;
import org.bigdou.dbm.db.DbRepository;
import org.bigdou.dbm.db.IndexRepository;
import org.bigdou.dbm.db.TableColumnDO;
import org.bigdou.dbm.db.TableIndexDO;
import org.bigdou.dbm.db.TableInfoDO;
import org.bigdou.dbm.db.TableRepository;
import org.bigdou.dbm.domain.db.dialect.DatabaseDialect;
import org.bigdou.dbm.domain.db.entity.TableIndex;
import org.bigdou.dbm.domain.db.entity.TableInfo;
import org.bigdou.dbm.dto.TableSyncCmd;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 接口描述: 执行同步表命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class TableSyncCmdExe {

    @Resource
    private TableRepository tableRepository;

    @Resource
    private ColumnRepository columnRepository;

    @Resource
    private IndexRepository indexRepository;

    @Transactional(rollbackFor = Exception.class)
    public Response execute(TableSyncCmd cmd) {
        DatabaseDialect databaseDialect = new DatabaseDialect();
        List<TableInfo> tableInfos = new ArrayList<>();
        try {
            for (String tableName : cmd.getTableNames()) {
                tableInfos.add(databaseDialect.table(cmd.getDbId(), tableName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<TableInfoDO> tables = new ArrayList<>();
        List<TableColumnDO> tableColumns = new ArrayList<>();
        List<TableIndexDO> tableIndices = new ArrayList<>();
        for (TableInfo tableInfo : tableInfos) {
            TableInfoDO tableInfoDO = new TableInfoDO();
            tableInfoDO.setDbId(cmd.getDbId());
            tableInfoDO.setName(tableInfo.getName());
            tableInfoDO.setComment(tableInfo.getComment());
            tables.add(tableInfoDO);
            tableColumns.addAll(
                    tableInfo.getTableColumns().stream().map(o -> {
                        TableColumnDO tableColumnDO = new TableColumnDO();
                        tableColumnDO.setDbId(cmd.getDbId());
                        tableColumnDO.setTableName(tableInfo.getName());
                        tableColumnDO.setName(o.getName());
                        tableColumnDO.setType(o.getType());
                        tableColumnDO.setLength(o.getLength());
                        tableColumnDO.setNullable(o.isNullable());
                        tableColumnDO.setPrimaryKey(o.isPrimaryKey());
                        tableColumnDO.setAutoincrement(o.isAutoincrement());
                        tableColumnDO.setComment(o.getComment());
                        tableColumnDO.setDefaultValue(o.getDefaultValue());
                        tableColumnDO.setOrdinalPosition(o.getOrdinalPosition());
                        tableColumnDO.setDecimalDigits(o.getDecimalDigits());
                        return tableColumnDO;
                    }).collect(Collectors.toList())
            );

            tableIndices.addAll(
                    tableInfo.getIndices().stream().map(o -> {
                        TableIndexDO tableIndexDO = new TableIndexDO();
                        tableIndexDO.setDbId(cmd.getDbId());
                        tableIndexDO.setTableName(tableInfo.getName());
                        tableIndexDO.setIndexKey(o.getKey());
                        tableIndexDO.setName(o.getName());
                        tableIndexDO.setUniquely(o.isUnique());
                        tableIndexDO.setComment(o.getComment());
                        tableIndexDO.setColumns(o.getIndexColumns().stream().map(TableIndex.IndexColumn::getColumnName).collect(Collectors.joining(",")));
                        return tableIndexDO;
                    }).collect(Collectors.toList())
            );
        }
        if (!CollectionUtils.isEmpty(tables)) {
            this.tableRepository.saveAll(tables);
        }
        if (!CollectionUtils.isEmpty(tableColumns)) {
            this.columnRepository.saveAll(tableColumns);
        }
        if (!CollectionUtils.isEmpty(tableIndices)) {
            this.indexRepository.saveAll(tableIndices);
        }
        return Response.buildSuccess();
    }

}
