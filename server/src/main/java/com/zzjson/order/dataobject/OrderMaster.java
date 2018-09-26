package com.zzjson.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.dataobject</li>
 * <li>Version     : 3.8.5</li>
 * <li>Creation    : 2018年09月23日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Data
@Entity
public class OrderMaster {
    @Id
    private String orderId;
    /**
     * 买家名称
     */
    private String buyerName;
    /**
     * 买家手机号
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家 openid
     */
    private String buyerOpenid;
    /**
     * 数量
     */
    private BigDecimal orderAmount;
    /**
     * 买家订单状态 0为新下单
     */
    private Integer orderStatus;
    /**
     * 付款状态
     */
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;

}