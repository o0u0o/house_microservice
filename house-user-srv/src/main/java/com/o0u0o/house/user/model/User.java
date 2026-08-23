package com.o0u0o.house.user.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author aiuiot
 * @Date 2020/6/8 7:44 下午
 * @Descripton:
 **/
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -2776907615383625133L;

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
