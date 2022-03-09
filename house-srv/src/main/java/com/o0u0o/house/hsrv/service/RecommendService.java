package com.o0u0o.house.hsrv.service;

/**
 * <h1>推荐业务接口</h1>
 * @author o0u0o
 * @date 2022/3/9 11:32 AM
 */
public interface RecommendService {

    /**
     * 新增热度
     * @param id
     */
    void increaseHot(long id);
}
