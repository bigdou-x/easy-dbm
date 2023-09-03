package org.bigdou.dbm.graph.executor.query;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.bigdou.dbm.db.QTableColumnDO;
import org.bigdou.dbm.db.QTableInfoDO;
import org.bigdou.dbm.db.TableColumnDO;
import org.bigdou.dbm.db.TableInfoDO;
import org.bigdou.dbm.db.TableRepository;
import org.bigdou.dbm.domain.graph.GraphEdge;
import org.bigdou.dbm.domain.graph.GraphLayout;
import org.bigdou.dbm.domain.graph.GraphNode;
import org.bigdou.dbm.domain.graph.gateway.GraphLayoutGateway;
import org.bigdou.dbm.dto.TableGraphLayoutQry;
import org.bigdou.dbm.dto.data.TableGraphLayoutDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 接口描述: 执行查询表图命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class TableGraphLayoutQryExe {

    @Resource
    private GraphLayoutGateway graphLayoutGateway;

    @PersistenceContext
    private EntityManager entityManager;

    public SingleResponse<TableGraphLayoutDTO> execute(TableGraphLayoutQry cmd) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<TableInfoDO> tableInfoJPAQuery = jpaQueryFactory
                .select(QTableInfoDO.tableInfoDO)
                .from(QTableInfoDO.tableInfoDO)
                .where(
                        QTableInfoDO.tableInfoDO.dbId.eq(cmd.getDbId())
                );
        List<TableInfoDO> tableInfoDOs = tableInfoJPAQuery.fetch();
        Set<String> tableNames = tableInfoDOs.stream().map(TableInfoDO::getName).collect(Collectors.toSet());
        JPAQuery<TableColumnDO> columnInfoJPAQuery = jpaQueryFactory
                .select(QTableColumnDO.tableColumnDO)
                .from(QTableColumnDO.tableColumnDO)
                .where(
                        QTableColumnDO.tableColumnDO.tableName.in(tableNames)
                )
                .orderBy(
                        QTableColumnDO.tableColumnDO.ordinalPosition.asc()
                );
        Map<String, List<TableColumnDO>> columnInfoMap = columnInfoJPAQuery.fetch().stream()
                .collect(Collectors.groupingBy(TableColumnDO::getTableName));
        GraphLayout graphLayout = this.graphLayoutGateway.getById(cmd.getGraphLayoutId());
        Map<String, GraphNode> graphNodeMap = graphLayout.getGraphNodes().stream()
                .collect(Collectors.toMap(GraphNode::getNodeId, o -> o));
        TableGraphLayoutDTO tableGraphLayoutDTO = new TableGraphLayoutDTO();
        List<TableGraphLayoutDTO.TableDTO> tables = new ArrayList<>();
        for (TableInfoDO currentTable : tableInfoDOs) {
            TableGraphLayoutDTO.TableDTO tableGraphLayoutTableDTO = new TableGraphLayoutDTO.TableDTO();
            tableGraphLayoutTableDTO.setTableId(currentTable.getId());
            tableGraphLayoutTableDTO.setTableName(currentTable.getName());
            tableGraphLayoutTableDTO.setTableComment(currentTable.getComment());
            if (graphNodeMap.containsKey(currentTable.getName())) {
                tableGraphLayoutTableDTO.setPositionX(graphNodeMap.get(currentTable.getName()).getPositionX());
                tableGraphLayoutTableDTO.setPositionY(graphNodeMap.get(currentTable.getName()).getPositionY());
            }
            if (columnInfoMap.containsKey(currentTable.getName())) {
                tableGraphLayoutTableDTO.setColumns(
                        columnInfoMap.get(currentTable.getName()).stream().map(o -> {
                            TableGraphLayoutDTO.ColumnDTO columnDTO = new TableGraphLayoutDTO.ColumnDTO();
                            columnDTO.setId(o.getId());
                            columnDTO.setName(o.getName());
                            columnDTO.setType(o.getType());
                            columnDTO.setLength(o.getLength());
                            columnDTO.setPrimaryKey(o.getPrimaryKey());
                            columnDTO.setComment(o.getComment());
                            columnDTO.setOrdinalPosition(o.getOrdinalPosition());
                            return columnDTO;
                        }).collect(Collectors.toList())
                );
            }
            tables.add(tableGraphLayoutTableDTO);
        }
        tableGraphLayoutDTO.setTables(tables);
        tableGraphLayoutDTO.setGraphEdges(graphLayout.getGraphEdges().stream().map(o -> {
            TableGraphLayoutDTO.GraphEdgeDTO graphEdgeDTO = new TableGraphLayoutDTO.GraphEdgeDTO();
            graphEdgeDTO.setSourceCell(o.getSourceCell());
            graphEdgeDTO.setSourcePort(o.getSourcePort());
            graphEdgeDTO.setTargetCell(o.getTargetCell());
            graphEdgeDTO.setTargetPort(o.getTargetPort());
            return graphEdgeDTO;
        }).collect(Collectors.toList()));
        return SingleResponse.of(tableGraphLayoutDTO);
    }

}
