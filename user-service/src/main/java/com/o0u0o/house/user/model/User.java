package com.o0u0o.house.user.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @Author aiuiot
 * @Date 2020/1/6 9:56 下午
 * @Descripton: 用户实体
 **/
@Data
public class User {
    private Long id; // 用户ID

    private String name; // 姓名

    private String email; // 邮箱

    private String phone; // 手机

    private String password; // 密码

    private String confirmPasswd; // 确认密码

    private Integer type; // 1-普通用户 2-经纪人

    private Date createTime; // 创建时间

    private Integer enable; // 是否激活

    private String avatar; // 头像 url地址

    @JSONField(deserialize = false, serialize = false)
    private MultipartFile avatarFile; // 接收用户上传的文件(用户头像)

    private String newPassword; // 新密码 用于修改密码使用

    private String key; // 激活码

    private Long agencyId; // 经纪机构的ID

    private String token;   //Token

    private String aboutme; // 关于我

    private String enableUrl;

    private String agencyName; // 经纪人姓名
}
