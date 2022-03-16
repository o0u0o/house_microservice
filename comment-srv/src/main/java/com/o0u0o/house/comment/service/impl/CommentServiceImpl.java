package com.o0u0o.house.comment.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Strings;
import com.o0u0o.house.comment.common.BeanHelper;
import com.o0u0o.house.comment.common.CommonConstants;
import com.o0u0o.house.comment.dao.UserDao;
import com.o0u0o.house.comment.mapper.CommentMapper;
import com.o0u0o.house.comment.model.Comment;
import com.o0u0o.house.comment.model.User;
import com.o0u0o.house.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <h1>评论业务实现类</h1>
 * @author o0u0o
 * @date 2022/3/15 9:54 PM
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserDao userDao;

    /**
     * 添加房产评论
     * @param houseId
     * @param content
     * @param userId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addHouseComment(Long houseId, String content, Long userId) {
        addComment(houseId,null, content, userId,1);
    }

    /**
     * 添加百科评论
     * @param blogId
     * @param content
     * @param userId
     */
    @Override
    public void addBlogComment(Integer blogId, String content, Long userId) {
        addComment(null,blogId, content, userId, CommonConstants.COMMENT_BLOG_TYPE);
    }

    /**
     * <h2>查询房产列表</h2>
     *
     * @param houseId
     * @param size
     * @return
     */
    @Override
    public List<Comment> getHouseComments(Long houseId, Integer size) {
        String key  ="house_comments" + "_" + houseId + "_" + size;
        String json = redisTemplate.opsForValue().get(key);
        List<Comment> lists = null;
        if (Strings.isNullOrEmpty(json)) {
            lists = doGetHouseComments(houseId, size);
            redisTemplate.opsForValue().set(key, JSON.toJSONString(lists));
            redisTemplate.expire(key, 5, TimeUnit.MINUTES);
        }else {
            lists =  JSON.parseObject(json,new TypeReference<List<Comment>>(){});
        }
        return lists;
    }

    /**
     * <h2>查询百科列表</h2>
     *
     * @param blogId
     * @param size
     * @return
     */
    @Override
    public List<Comment> getBlogComments(Integer blogId, Integer size) {
        return null;
    }


    private void addComment(Long houseId,Integer blogId, String content, Long userId,int type) {
        String key = null;
        Comment comment = new Comment();
        if (type == 1) {
            comment.setHouseId(houseId);
            key = "house_comments_" + houseId ;
        }else {
            comment.setBlogId(blogId);
            key = "blog_comments_" + blogId ;
        }
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setType(type);
        BeanHelper.onInsert(comment);
        BeanHelper.setDefaultProp(comment, Comment.class);
        commentMapper.insert(comment);
        redisTemplate.delete(redisTemplate.keys(key + "*"));
    }

    /**
     * 数据库获取评论列表
     * 调用用户服务获取头像
     * @param houseId
     * @param size
     * @return
     */
    public List<Comment> doGetHouseComments(Long houseId, Integer size) {
        List<Comment>  comments = commentMapper.selectComments(houseId, size);
        comments.forEach(comment -> {
            User user = userDao.getUserDetail(comment.getUserId());
            comment.setAvatar(user.getAvatar());
            comment.setUserName(user.getName());
        });
        return comments;
    }


}
