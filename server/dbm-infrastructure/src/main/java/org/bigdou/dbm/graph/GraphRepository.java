package org.bigdou.dbm.graph;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * 类描述:
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:28
 */
public interface GraphRepository extends JpaRepository<GraphLayoutDO, Long>, QuerydslPredicateExecutor<GraphLayoutDO> {
}
