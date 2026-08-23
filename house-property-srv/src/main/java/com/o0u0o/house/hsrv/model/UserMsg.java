package com.o0u0o.house.hsrv.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author aiuiot
 * @Date 2020/6/8 7:00 下午
 * @Descripton:
 **/
@Data
public class UserMsg implements Serializable {

    private static final long serialVersionUID = -2391504928479660520L;

    /** 主键ID */
    private Long id;

    /** 消息 */
    private String msg;

    /** 用户ID */
    private Long userId;

    /** 经纪人ID */
    private Long agentId;

    /** 房产ID */
    private Long houseId;

    /** 电子邮箱 */
    private String email;

    /** 用户名 */
    private String userName;

    /** 创建时间 */
    private Date createTime;
}
