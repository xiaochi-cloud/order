package com.zzjson.order.utils;


import com.zzjson.order.VO.ResultVO;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.product.util</li>
 * <li>Version     : 3.8.5</li>
 * <li>Creation    : 2018年09月21日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
public class ResultVoUtil {
    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }
}