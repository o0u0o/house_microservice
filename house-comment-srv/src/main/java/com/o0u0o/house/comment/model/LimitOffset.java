package com.o0u0o.house.comment.model;

/**
 * <h1>LimitOffset</h1>
 * @author o0u0o
 * @date 2022/3/9 10:41 PM
 */
public class LimitOffset {

    private Integer limit;

    private Integer offset;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }


    public static LimitOffset build(Integer limit,Integer offset) {
        LimitOffset limitOffset = new LimitOffset();
        limitOffset.setLimit(limit);
        limitOffset.setOffset(offset);
        return limitOffset;
    }
}
