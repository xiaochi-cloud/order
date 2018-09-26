package com.zzjson.order.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.form</li>
 * <li>Version     : 3.8.5</li>
 * <li>Creation    : 2018年09月23日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Data
public class OrderForm {
    /**
     * 姓名
     */
    @NotEmpty
    private String name;
    /**
     * 手机号
     */
    @NotEmpty
    private String phone;
    /**
     * 地址
     */
    @NotEmpty
    private String address;
    /**
     * 买家微信openid
     */
    @NotEmpty
    private String openid;
    /**
     * 购物车
     */
    @NotEmpty
    private String items;

}