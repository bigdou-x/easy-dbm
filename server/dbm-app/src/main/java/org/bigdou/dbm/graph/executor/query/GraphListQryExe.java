package org.bigdou.dbm.graph.executor.query;

import com.alibaba.cola.dto.MultiResponse;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.bigdou.dbm.dto.GraphListQry;
import org.bigdou.dbm.dto.data.GraphInfoDTO;
import org.bigdou.dbm.graph.QGraphLayoutDO;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.stream.Collectors;

/**
 * 接口描述: 执行查询表图命令
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:43
 */
@Component
public class GraphListQryExe {

    @PersistenceContext
    private EntityManager entityManager;

    public MultiResponse<GraphInfoDTO> execute(GraphListQry cmd) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<Tuple> graphJPAQuery = jpaQueryFactory
                .select(QGraphLayoutDO.graphLayoutDO.id, QGraphLayoutDO.graphLayoutDO.dbId, QGraphLayoutDO.graphLayoutDO.name, QGraphLayoutDO.graphLayoutDO.description)
                .from(QGraphLayoutDO.graphLayoutDO)
                .where(
                        QGraphLayoutDO.graphLayoutDO.dbId.eq(cmd.getDbId())
                );
        return MultiResponse.of(
                graphJPAQuery.fetch().stream().map(o -> {
                    GraphInfoDTO graphInfoDTO = new GraphInfoDTO();
                    graphInfoDTO.setId(o.get(QGraphLayoutDO.graphLayoutDO.id));
                    graphInfoDTO.setDbId(o.get(QGraphLayoutDO.graphLayoutDO.dbId));
                    graphInfoDTO.setName(o.get(QGraphLayoutDO.graphLayoutDO.name));
                    graphInfoDTO.setDescription(o.get(QGraphLayoutDO.graphLayoutDO.description));
                    return graphInfoDTO;
                }).collect(Collectors.toList())
        );
    }

}
