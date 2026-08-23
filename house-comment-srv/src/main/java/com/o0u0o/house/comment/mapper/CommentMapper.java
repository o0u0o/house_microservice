package com.o0u0o.house.comment.mapper;

import com.o0u0o.house.comment.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <h1></h1>
 * @author o0u0o
 * @date 2022/3/9 11:07 PM
 */
@Mapper
public interface CommentMapper {

    int insert(Comment comment);

    List<Comment> selectComments(@Param("houseId") long houseId,
                                 @Param("size") int size);

    List<Comment> selectBlogComments(@Param("blogId") long blogId,@Param("size") int size);
}
