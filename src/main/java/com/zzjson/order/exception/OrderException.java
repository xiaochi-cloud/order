package com.zzjson.order.exception;

import com.zzjson.order.enums.ResultEnum;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.exception</li>
 * <li>Version     : 3.8.5</li>
 * <li>Creation    : 2018年09月23日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code, String defaultMessage) {
        super(defaultMessage);
        this.code = code;
    }

    public OrderException(ResultEnum paramError) {
        super(paramError.getMsg());
        this.code = paramError.getCode();
    }
}