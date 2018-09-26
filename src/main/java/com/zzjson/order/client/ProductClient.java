package com.zzjson.order.client;

import com.zzjson.order.dto.CartDTO;
import com.zzjson.order.dto.ProductInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.client</li>
 * <li>Version     : 3.8.5</li>
 * <li>Creation    : 2018年09月25日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@FeignClient(name = "product")
public interface ProductClient {
    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(List<CartDTO> CartDTO);
}