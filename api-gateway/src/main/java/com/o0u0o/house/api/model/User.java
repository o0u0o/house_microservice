package com.o0u0o.house.api.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author aiuiot
 * @Date 2020/6/8 5:14 下午
 * @Descripton: 用户实体
 **/
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 7316641144254429647L;

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
    private String confirmPassword;

    /** 1-普通用户 2-经纪人 */
    private Integer type;

    /** 创建时间 */
    private Date createTime;

    /** 是否激活 */
    private Integer enable;

    /** 头像 url地址 */
    private String avatar;

    /** 接收用户上传的文件(用户头像) */
    private MultipartFile avatarFile;

    /** 新密码 用于修改密码使用 */
    private String newPassword;

    /** 激活码 */
    private String key;

    /** 经纪机构的ID */
    private Long agencyId;

    /** 关于我 */
    private String aboutme;

    /** 激活链接 */
    private String enableUrl;

    /** 经纪人姓名 */
    private String agencyName;

}
