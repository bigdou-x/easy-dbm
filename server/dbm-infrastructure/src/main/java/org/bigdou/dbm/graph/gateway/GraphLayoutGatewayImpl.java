package org.bigdou.dbm.graph.gateway;

import com.alibaba.cola.exception.Assert;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.bigdou.dbm.domain.graph.GraphEdge;
import org.bigdou.dbm.domain.graph.GraphLayout;
import org.bigdou.dbm.domain.graph.GraphNode;
import org.bigdou.dbm.domain.graph.gateway.GraphLayoutGateway;
import org.bigdou.dbm.graph.GraphEdgeDO;
import org.bigdou.dbm.graph.GraphEdgeRepository;
import org.bigdou.dbm.graph.GraphNodeDO;
import org.bigdou.dbm.graph.GraphNodeRepository;
import org.bigdou.dbm.graph.QGraphEdgeDO;
import org.bigdou.dbm.graph.QGraphLayoutDO;
import org.bigdou.dbm.graph.QGraphNodeDO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类描述:  <br>
 *
 * @author xuqing
 * @date 2023-08-30 13:13
 */
@Component
public class GraphLayoutGatewayImpl implements GraphLayoutGateway {

    @Resource
    private GraphNodeRepository graphNodeRepository;

    @Resource
    private GraphEdgeRepository graphEdgeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Long dbId, GraphLayout graphLayout) {
        Assert.notNull(graphLayout, "图布局不能为空");
        graphLayout.checkEmpty();
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        jpaQueryFactory
                .delete(QGraphNodeDO.graphNodeDO)
                .where(
                        QGraphNodeDO.graphNodeDO.dbId.eq(dbId),
                        QGraphNodeDO.graphNodeDO.graphLayoutId.eq(graphLayout.getLayoutId())
                ).execute();
        jpaQueryFactory
                .delete(QGraphEdgeDO.graphEdgeDO)
                .where(
                        QGraphEdgeDO.graphEdgeDO.dbId.eq(dbId),
                        QGraphEdgeDO.graphEdgeDO.graphLayoutId.eq(graphLayout.getLayoutId())
                ).execute();
        List<GraphEdgeDO> edges = graphLayout.getGraphEdges().stream().map(o -> {
            GraphEdgeDO graphEdgeDO = new GraphEdgeDO();
            graphEdgeDO.setDbId(dbId);
            graphEdgeDO.setGraphLayoutId(graphLayout.getLayoutId());
            graphEdgeDO.setSourceCell(o.getSourceCell());
            graphEdgeDO.setSourcePort(o.getSourcePort());
            graphEdgeDO.setTargetCell(o.getTargetCell());
            graphEdgeDO.setTargetPort(o.getTargetPort());
            return graphEdgeDO;
        }).collect(Collectors.toList());
        this.graphEdgeRepository.saveAll(edges);
        List<GraphNodeDO> nodes = graphLayout.getGraphNodes().stream().map(o -> {
            GraphNodeDO graphNodeDO = new GraphNodeDO();
            graphNodeDO.setDbId(dbId);
            graphNodeDO.setGraphLayoutId(graphLayout.getLayoutId());
            graphNodeDO.setNodeKey(o.getNodeId());
            graphNodeDO.setPositionX(o.getPositionX());
            graphNodeDO.setPositionY(o.getPositionY());
            return graphNodeDO;
        }).collect(Collectors.toList());
        this.graphNodeRepository.saveAll(nodes);
    }

    @Override
    public GraphLayout getById(Long graphLayoutId) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        List<GraphNode> graphNodes = jpaQueryFactory
                .selectFrom(QGraphNodeDO.graphNodeDO)
                .where(
                        QGraphNodeDO.graphNodeDO.graphLayoutId.eq(graphLayoutId)
                ).fetch().stream().map(o -> {
                    GraphNode graphNode = new GraphNode();
                    graphNode.setNodeId(o.getNodeKey());
                    graphNode.setPositionX(o.getPositionX());
                    graphNode.setPositionY(o.getPositionY());
                    return graphNode;
                }).collect(Collectors.toList());
        List<GraphEdge> graphEdges = jpaQueryFactory
                .selectFrom(QGraphEdgeDO.graphEdgeDO)
                .where(
                        QGraphEdgeDO.graphEdgeDO.graphLayoutId.eq(graphLayoutId)
                ).fetch().stream().map(o -> {
                    GraphEdge graphEdge = new GraphEdge();
                    graphEdge.setSourceCell(o.getSourceCell());
                    graphEdge.setSourcePort(o.getSourcePort());
                    graphEdge.setTargetCell(o.getTargetCell());
                    graphEdge.setTargetPort(o.getTargetPort());
                    return graphEdge;
                }).collect(Collectors.toList());
        GraphLayout graphLayout = new GraphLayout();
        graphLayout.setLayoutId(graphLayoutId);
        graphLayout.setGraphNodes(graphNodes);
        graphLayout.setGraphEdges(graphEdges);
        return graphLayout;
    }
}
