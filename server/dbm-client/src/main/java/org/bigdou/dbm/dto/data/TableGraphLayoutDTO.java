package org.bigdou.dbm.dto.data;

import lombok.Data;

import java.util.List;

/**
 * 类描述: 表图布局
 *
 * @author xuqing F00722
 * @date 2023/1/30 12:12
 */
@Data
public class TableGraphLayoutDTO {

    /**
     * 表
     */
    private List<TableDTO> tables;

    /**
     * 图边
     */
    private List<GraphEdgeDTO> graphEdges;

    @Data
    public static class TableDTO {
        /**
         * 表id
         */
        private Long tableId;

        /**
         * 表名称
         */
        private String tableName;

        /**
         * 表注释
         */
        private String tableComment;

        /**
         * 字段集合
         */
        private List<ColumnDTO> columns;

        /**
         * 定位 x
         */
        private Integer positionX;

        /**
         * 定位 y
         */
        private Integer positionY;


    }

    @Data
    public static class ColumnDTO {

        /**
         * 字段id
         */
        private Long id;

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
         * 是否主键
         */
        private boolean primaryKey;

        /**
         * 字段注释
         */
        private String comment;

        /**
         * 列排序
         */
        private Integer ordinalPosition;

    }

    @Data
    public static class GraphEdgeDTO {

        private String sourceCell;

        private String sourcePort;

        private String targetCell;

        private String targetPort;
    }

}
