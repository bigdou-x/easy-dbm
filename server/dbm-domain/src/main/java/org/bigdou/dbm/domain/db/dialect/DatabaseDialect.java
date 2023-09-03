package org.bigdou.dbm.domain.db.dialect;

import com.alibaba.cola.domain.DomainFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.bigdou.dbm.domain.db.TableConstants;
import org.bigdou.dbm.domain.db.entity.TableColumn;
import org.bigdou.dbm.domain.db.entity.TableIndex;
import org.bigdou.dbm.domain.db.entity.TableInfo;
import org.bigdou.dbm.domain.db.util.DataSourceFactoryI;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 类描述: 数据库方言 <br>
 *
 * @author xuqing
 * @date 2023-06-16 16:53
 */
public class DatabaseDialect {

    public List<String> tableNames(Long dataSourceId) throws SQLException {
        DataSourceFactoryI dataSourceFactoryI = DomainFactory.create(DataSourceFactoryI.class);
        Connection connection = dataSourceFactoryI.getConnection(dataSourceId);
        try (
                ResultSet rs = connection.getMetaData().getTables(connection.getCatalog(), connection.getSchema(), "%", new String[]{"TABLE"})
                ) {
            List<String> tableNames = new ArrayList<>();
            while (rs.next()) {
                tableNames.add(rs.getString("TABLE_NAME"));
            }
            return tableNames;
        } finally {
            dataSourceFactoryI.close(connection);
        }
    }

    public List<TableColumn> columns(Long dataSourceId, String tableName) throws SQLException {
        DataSourceFactoryI dataSourceFactoryI = DomainFactory.create(DataSourceFactoryI.class);
        Connection connection = dataSourceFactoryI.getConnection(dataSourceId);
        DatabaseMetaData connMetaData = connection.getMetaData();
        try (
                ResultSet rs = connMetaData.getColumns(connection.getCatalog(), connection.getSchema(), tableName, "%");
                ResultSet pkRs = connMetaData.getPrimaryKeys(connection.getCatalog(), connection.getSchema(), tableName)
                ) {
            List<TableColumn> tableColumns = new ArrayList<>();
            Set<String> pkSet = new HashSet<>();
            while (pkRs.next()) {
                pkSet.add(pkRs.getString("COLUMN_NAME"));
            }
            while (rs.next()) {
                TableColumn tableColumn = new TableColumn();
                tableColumn.setStatus(TableColumn.ColumnStatusEnum.NOT_CHANGE);
                String columnName = rs.getString("COLUMN_NAME");
                tableColumn.setName(columnName);
                tableColumn.setType(rs.getString("TYPE_NAME"));
                tableColumn.setLength(rs.getInt("COLUMN_SIZE"));
                tableColumn.setNullable(TableConstants.YES.equals(rs.getString("IS_NULLABLE")));
                tableColumn.setPrimaryKey(pkSet.contains(columnName));
                tableColumn.setComment(rs.getString("REMARKS"));
                tableColumn.setAutoincrement(TableConstants.YES.equals(rs.getString("IS_AUTOINCREMENT")));
                tableColumn.setDefaultValue(rs.getString("COLUMN_DEF"));
                tableColumn.setOrdinalPosition(rs.getInt("ORDINAL_POSITION"));
                tableColumn.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
                tableColumns.add(tableColumn);
            }
            return tableColumns;
        } finally {
            dataSourceFactoryI.close(connection);
        }
    }

    public TableInfo table(Long dbId, String tableName) throws SQLException {
        DataSourceFactoryI dataSourceFactoryI = DomainFactory.create(DataSourceFactoryI.class);
        Connection connection = dataSourceFactoryI.getConnection(dbId);
        DatabaseMetaData connMetaData = connection.getMetaData();
        try (ResultSet tableRs = connMetaData.getTables(
                connection.getCatalog(),
                connection.getSchema(),
                tableName, new String[]{"TABLE"});
             ResultSet indexRs = connMetaData.getIndexInfo(
                     connection.getCatalog(),
                     connection.getSchema(),
                     tableName, false, false)) {
            TableInfo tableInfo = new TableInfo();
            while (tableRs.next()) {
                String currentTableName = tableRs.getString("TABLE_NAME");
                String remarks = StringUtils.trim(tableRs.getString("REMARKS"));
                if (StringUtils.isNoneBlank(remarks)) {
                    remarks = StringUtils.normalizeSpace(remarks);
                }
                tableInfo.setName(currentTableName);
                tableInfo.setComment(remarks);
                tableInfo.setTableColumns(this.columns(dbId, tableName));
            }
            while (indexRs.next()) {
                String currentTableName = indexRs.getString("TABLE_NAME");
                String indexName = indexRs.getString("INDEX_NAME");
                String columnName = indexRs.getString("COLUMN_NAME");
                String nonUnique = indexRs.getString("NON_UNIQUE");
                String ascOrDesc = indexRs.getString("ASC_OR_DESC");
                TableIndex existsTableIndex = tableInfo.getTableIndex(indexName);
                if (ObjectUtils.isEmpty(existsTableIndex)) {
                    existsTableIndex = new TableIndex();
                    existsTableIndex.setKey(indexName);
                    existsTableIndex.setName(indexName);
                    existsTableIndex.setUnique(!"1".equalsIgnoreCase(nonUnique));
//                    indexModel.setComment();
                    tableInfo.getIndices().add(existsTableIndex);
                }
                TableIndex.IndexColumn indexColumn = new TableIndex.IndexColumn();
                indexColumn.setColumnName(columnName);
                indexColumn.setAscOrDesc(ascOrDesc);
                existsTableIndex.getIndexColumns().add(indexColumn);
            }
            return tableInfo;
        } finally {
            dataSourceFactoryI.close(connection);
        }
    }


}
