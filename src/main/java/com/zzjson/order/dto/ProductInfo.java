package com.zzjson.order.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.product.dataobject</li>
 * <li>Version     : 3.8.5</li>
 * <li>Creation    : 2018年09月21日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Data
public class ProductInfo {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    /**
     * 商品库存
     */
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    /**
     * 状态 0正常 1下架
     */
    private Integer productStatus;
    /**
     * 类目标号
     */
    private Integer categoryType;
}