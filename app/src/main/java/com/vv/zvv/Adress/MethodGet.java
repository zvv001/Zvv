package com.vv.zvv.Adress;

/**
 * Created by zvv on 2017/1/20 14:33.
 */
public class MethodGet extends BaseAddress {

    /**
     * 获取产品列表
     */
    public String getProductList() {
        return getBaseAddress() + "/api/v2/products/more/4.json";
    }

}
