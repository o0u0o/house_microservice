package com.o0u0o.model.user;

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

    /** 用户ID */
    private Long id;

    /** 姓名 */
    private String name;

    /** 邮箱 */
    private String email;

    /** 手机 */
    private String phone;

    /** 密码 */
    private String password;

    /** 确认密码 */
    private String confirmPasswd;

    /**  用户类型 1-普通用户 2-经纪人 */
    private Integer type;

    /** 创建时间 */
    private Date createTime;

    /** 是否激活 */
    private Integer enable;

    /** 头像 url地址 */
    private String avatar;

    /** 接收用户上传的文件(用户头像) */
    @JSONField(deserialize = false, serialize = false)
    private MultipartFile avatarFile;

    /** 新密码 用于修改密码使用 */
    private String newPassword;

    private String key; // 激活码

    private Long agencyId; // 经纪机构的ID

    private String token;   //Token

    /**  关于我 */
    private String aboutme;

    private String enableUrl;

    private String agencyName; // 经纪人姓名
}
