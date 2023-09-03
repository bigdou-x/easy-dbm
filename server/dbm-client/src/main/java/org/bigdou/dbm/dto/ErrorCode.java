package org.bigdou.dbm.dto;

public enum ErrorCode {
    B_CUSTOMER_companyNameConflict("B_CUSTOMER_companyNameConflict", "客户公司名冲突"),
    DB_NAME_CONFLICT("DB_NAME_CONFLICT", "数据库名称冲突"),
    TABLE_NAME_QUERY_ERROR("TABLE_NAME_QUERY_ERROR", "表名查询失败"),

    GRAPH_NAME_CONFLICT("DB_NAME_CONFLICT", "图表名称冲突"),;

    private final String errCode;
    private final String errDesc;

    private ErrorCode(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrDesc() {
        return errDesc;
    }
}
