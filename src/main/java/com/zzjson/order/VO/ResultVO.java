package com.zzjson.order.VO;

import lombok.Data;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.product.VO</li>
 * <li>Version     : 3.8.5</li>
 * <li>Creation    : 2018年09月21日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 具体内容
     */
    private T data;

    /**
     * 提示信息
     */
    private String msg;

}