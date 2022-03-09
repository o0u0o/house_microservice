package com.o0u0o.house.api.dao;

import com.o0u0o.house.api.common.RestResponse;
import com.o0u0o.house.api.config.GenericRest;
import com.o0u0o.house.api.model.Blog;
import com.o0u0o.house.api.model.BlogQueryReq;
import com.o0u0o.house.api.model.ListResponse;
import com.o0u0o.house.api.utils.Rests;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h1>频论数据访问层</h1>
 * @author o0u0o
 * @date 2022/3/9 3:51 PM
 */
@Repository
public class CommentDao {

    @Autowired
    private GenericRest rest;

    @Value("${comment.service.name}")
    private String commentServiceName;


    public Pair<List<Blog>, Long> getBlogs(Blog query, Integer limit, Integer offset) {
        ListResponse<Blog> listResponse  = Rests.exc(() ->{
            String url = Rests.toUrl(commentServiceName, "/blog/list");
            BlogQueryReq blogQueryReq = new BlogQueryReq();
            blogQueryReq.setBlog(query);
            blogQueryReq.setLimit(limit);
            blogQueryReq.setOffset(offset);
            ResponseEntity<RestResponse<ListResponse<Blog>>> entity = rest.post(url, blogQueryReq, new ParameterizedTypeReference<RestResponse<ListResponse<Blog>>>() {});
            return entity.getBody();
        }).getResult();
        return ImmutablePair.of(listResponse.getList(), listResponse.getCount());
    }
}
