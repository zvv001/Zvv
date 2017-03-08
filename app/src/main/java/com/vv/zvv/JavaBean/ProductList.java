package com.vv.zvv.JavaBean;

import java.util.List;

/**
 * Created by zvv on 2017/1/20 14:48.
 */
public class ProductList {

    private PagerBean pager;
    private int status;

    public PagerBean getPager() {
        return pager;
    }

    public void setPager(PagerBean pager) {
        this.pager = pager;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class PagerBean {
        /**
         * pageNumber : 1
         * pageSize : 20
         * totalCount : 235
         * pageCount : 12
         * list : [{"modifyDate":null,"category":"YEAR","serial":"EP1701048747","name":"麦金所 年享73号","term":278,"interestRate":0.1,"bonusRate":0.008,"valueDate":0,"displayTotalAmount":null,"totalAmount":362000,"soldAmount":362000,"startTime":1483459392000,"endTime":1484063999000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":24,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019999,"publishDate":1483459687000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1435464,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":23932800000,"finalRate":0.108,"realValueDate":1484894856750,"dueDate":23904000000,"settlementDate":24019200000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"年享","remainsSeconds":-1435464,"productTags":["当日起息","收益保障"],"minRate":"0.100","maxRate":"0.108","finalMinRate":"0.100","finalMaxRate":"0.108","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"YEAR","serial":"EP1612302856","name":"麦金所 年享72号","term":280,"interestRate":0.1,"bonusRate":0.01,"valueDate":0,"displayTotalAmount":null,"totalAmount":489000,"soldAmount":489000,"startTime":1483027261000,"endTime":1483459199000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":28,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019999,"publishDate":1483027338000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1867595,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":true,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":24105600000,"finalRate":0.11,"realValueDate":1484894856750,"dueDate":24076800000,"settlementDate":24192000000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"年享","remainsSeconds":-1867595,"productTags":["当日起息","收益保障"],"minRate":"0.100","maxRate":"0.110","finalMinRate":"0.100","finalMaxRate":"0.110","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"HALF_A_YEAR","serial":"EP1612301834","name":"麦金所 双季享72号","term":198,"interestRate":0.09,"bonusRate":0.01,"valueDate":0,"displayTotalAmount":null,"totalAmount":266000,"soldAmount":266000,"startTime":1483027405000,"endTime":1484236799000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":9,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019998,"publishDate":1483027474000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1867451,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"公益","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":17020800000,"finalRate":0.1,"realValueDate":1484894856750,"dueDate":16992000000,"settlementDate":17107200000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"双季享","remainsSeconds":-1867451,"productTags":["当日起息","收益保障"],"minRate":"0.090","maxRate":"0.100","finalMinRate":"0.090","finalMaxRate":"0.100","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"QUARTER","serial":"EP1701118767","name":"麦金所 季享89号","term":98,"interestRate":0.08,"bonusRate":0.006,"valueDate":0,"displayTotalAmount":null,"totalAmount":358000,"soldAmount":358000,"startTime":1484115585000,"endTime":1484867819000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":21,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019997,"publishDate":1484152115000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-779271,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":8380800000,"finalRate":0.086,"realValueDate":1484894856750,"dueDate":8352000000,"settlementDate":8467200000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"季享","remainsSeconds":-779271,"productTags":["当日起息","收益保障"],"minRate":"0.080","maxRate":"0.086","finalMinRate":"0.080","finalMaxRate":"0.086","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"QUARTER","serial":"EP1612306288","name":"麦金所 季享86号","term":98,"interestRate":0.08,"bonusRate":0.006,"valueDate":0,"displayTotalAmount":null,"totalAmount":300000,"soldAmount":300000,"startTime":1483027543000,"endTime":1483631999000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":19,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019997,"publishDate":1483027596000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1867313,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":8380800000,"finalRate":0.086,"realValueDate":1484894856750,"dueDate":8352000000,"settlementDate":8467200000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"季享","remainsSeconds":-1867313,"productTags":["当日起息","收益保障"],"minRate":"0.080","maxRate":"0.086","finalMinRate":"0.080","finalMaxRate":"0.086","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"QUARTER","serial":"EP1701171689","name":"麦金所 季享90号","term":98,"interestRate":0.08,"bonusRate":0.006,"valueDate":0,"displayTotalAmount":null,"totalAmount":275000,"soldAmount":275000,"startTime":1484637139000,"endTime":1484868539000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":10,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019996,"publishDate":1484637098000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-257717,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":8380800000,"finalRate":0.086,"realValueDate":1484894856750,"dueDate":8352000000,"settlementDate":8467200000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"季享","remainsSeconds":-257717,"productTags":["当日起息","收益保障"],"minRate":"0.080","maxRate":"0.086","finalMinRate":"0.080","finalMaxRate":"0.086","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"DOUBLE_MONTH","serial":"EP1701095874","name":"麦金所 双月汇83号","term":68,"interestRate":0.075,"bonusRate":0.004,"valueDate":0,"displayTotalAmount":null,"totalAmount":293000,"soldAmount":293000,"startTime":1483891456000,"endTime":1484582399000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":9,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019996,"publishDate":1483891516000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1003400,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":5788800000,"finalRate":0.079,"realValueDate":1484894856750,"dueDate":5760000000,"settlementDate":5875200000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"双月汇","remainsSeconds":-1003400,"productTags":["当日起息","收益保障"],"minRate":"0.075","maxRate":"0.079","finalMinRate":"0.075","finalMaxRate":"0.079","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"QUARTER","serial":"EP1701052681","name":"麦金所 季享88号","term":98,"interestRate":0.08,"bonusRate":0.006,"valueDate":0,"displayTotalAmount":null,"totalAmount":500000,"soldAmount":500000,"startTime":1483632000000,"endTime":1484150399000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":17,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019996,"publishDate":1483632029000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1262856,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":8380800000,"finalRate":0.086,"realValueDate":1484894856750,"dueDate":8352000000,"settlementDate":8467200000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"季享","remainsSeconds":-1262856,"productTags":["当日起息","收益保障"],"minRate":"0.080","maxRate":"0.086","finalMinRate":"0.080","finalMaxRate":"0.086","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"QUARTER","serial":"EP1701044307","name":"麦金所 季享87号","term":98,"interestRate":0.08,"bonusRate":0.006,"valueDate":0,"displayTotalAmount":null,"totalAmount":357000,"soldAmount":357000,"startTime":1483515214000,"endTime":1483631999000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":6,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019996,"publishDate":1483515242000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1379642,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":8380800000,"finalRate":0.086,"realValueDate":1484894856750,"dueDate":8352000000,"settlementDate":8467200000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"季享","remainsSeconds":-1379642,"productTags":["当日起息","收益保障"],"minRate":"0.080","maxRate":"0.086","finalMinRate":"0.080","finalMaxRate":"0.086","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"DOUBLE_MONTH","serial":"EP1701046856","name":"麦金所 双月汇82号","term":68,"interestRate":0.075,"bonusRate":0.004,"valueDate":0,"displayTotalAmount":null,"totalAmount":500000,"soldAmount":500000,"startTime":1483514630000,"endTime":1483891199000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":11,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019996,"publishDate":1483514661000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1380226,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":5788800000,"finalRate":0.079,"realValueDate":1484894856750,"dueDate":5760000000,"settlementDate":5875200000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"双月汇","remainsSeconds":-1380226,"productTags":["当日起息","收益保障"],"minRate":"0.075","maxRate":"0.079","finalMinRate":"0.075","finalMaxRate":"0.079","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"DOUBLE_MONTH","serial":"EP1612301245","name":"麦金所 双月汇81号","term":68,"interestRate":0.075,"bonusRate":0.004,"valueDate":0,"displayTotalAmount":null,"totalAmount":500000,"soldAmount":500000,"startTime":1483027657000,"endTime":1483542459000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":16,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019996,"publishDate":1483027697000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1867199,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":5788800000,"finalRate":0.079,"realValueDate":1484894856750,"dueDate":5760000000,"settlementDate":5875200000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"双月汇","remainsSeconds":-1867199,"productTags":["当日起息","收益保障"],"minRate":"0.075","maxRate":"0.079","finalMinRate":"0.075","finalMaxRate":"0.079","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"MONTH","serial":"EP1701064395","name":"麦金所 月享90号","term":31,"interestRate":0.07,"bonusRate":0,"valueDate":0,"displayTotalAmount":null,"totalAmount":119000,"soldAmount":119000,"startTime":1483632481000,"endTime":1483891199000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":10,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019995,"publishDate":1483632542000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1262375,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":2592000000,"finalRate":0.07,"realValueDate":1484894856750,"dueDate":2563200000,"settlementDate":2678400000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"月享","remainsSeconds":-1262375,"productTags":["当日起息","收益保障"],"minRate":"0.070","maxRate":"0.070","finalMinRate":"0.070","finalMaxRate":"0.070","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"MONTH","serial":"EP1701017489","name":"麦金所 月享87号","term":35,"interestRate":0.07,"bonusRate":0.002,"valueDate":0,"displayTotalAmount":null,"totalAmount":345000,"soldAmount":345000,"startTime":1483201245000,"endTime":1483545599000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":18,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019995,"publishDate":1483201847000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1693611,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":2937600000,"finalRate":0.072,"realValueDate":1484894856750,"dueDate":2908800000,"settlementDate":3024000000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"月享","remainsSeconds":-1693611,"productTags":["当日起息","收益保障"],"minRate":"0.070","maxRate":"0.072","finalMinRate":"0.070","finalMaxRate":"0.072","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"MONTH","serial":"EP1701152035","name":"麦金所 月享95号","term":31,"interestRate":0.07,"bonusRate":0,"valueDate":0,"displayTotalAmount":null,"totalAmount":300000,"soldAmount":300000,"startTime":1484465023000,"endTime":1484841599000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":23,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019994,"publishDate":1484465723000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-429833,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":2592000000,"finalRate":0.07,"realValueDate":1484894856750,"dueDate":2563200000,"settlementDate":2678400000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"月享","remainsSeconds":-429833,"productTags":["当日起息","收益保障"],"minRate":"0.070","maxRate":"0.070","finalMinRate":"0.070","finalMaxRate":"0.070","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"MONTH","serial":"EP1701106794","name":"麦金所 月享94号","term":31,"interestRate":0.07,"bonusRate":0,"valueDate":0,"displayTotalAmount":null,"totalAmount":500000,"soldAmount":500000,"startTime":1484032377000,"endTime":1484582399000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":31,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019994,"publishDate":1484032452000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-862479,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":2592000000,"finalRate":0.07,"realValueDate":1484894856750,"dueDate":2563200000,"settlementDate":2678400000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"月享","remainsSeconds":-862479,"productTags":["当日起息","收益保障"],"minRate":"0.070","maxRate":"0.070","finalMinRate":"0.070","finalMaxRate":"0.070","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"MONTH","serial":"EP1701065887","name":"麦金所 月享91号","term":31,"interestRate":0.07,"bonusRate":0,"valueDate":0,"displayTotalAmount":null,"totalAmount":172000,"soldAmount":172000,"startTime":1483691119000,"endTime":1483863922000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":5,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019994,"publishDate":1483691167000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1203737,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":2592000000,"finalRate":0.07,"realValueDate":1484894856750,"dueDate":2563200000,"settlementDate":2678400000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"月享","remainsSeconds":-1203737,"productTags":["当日起息","收益保障"],"minRate":"0.070","maxRate":"0.070","finalMinRate":"0.070","finalMaxRate":"0.070","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"HALF_MONTH","serial":"EP1701069038","name":"麦金所 半月享76号","term":15,"interestRate":0.06,"bonusRate":0,"valueDate":0,"displayTotalAmount":null,"totalAmount":191000,"soldAmount":191000,"startTime":1483632698000,"endTime":1483891199000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":8,"minAmount":2000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019994,"publishDate":1483632799000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1262158,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"抢购","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":1209600000,"finalRate":0.06,"realValueDate":1484894856750,"dueDate":1180800000,"settlementDate":1296000000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"半月享","remainsSeconds":-1262158,"productTags":["当日起息","收益保障"],"minRate":"0.060","maxRate":"0.060","finalMinRate":"0.060","finalMaxRate":"0.060","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"MONTH","serial":"EP1701040879","name":"麦金所 月享89号","term":35,"interestRate":0.07,"bonusRate":0.002,"valueDate":0,"displayTotalAmount":null,"totalAmount":137000,"soldAmount":137000,"startTime":1483514465000,"endTime":1483631999000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":1,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019994,"publishDate":1483514492000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1380391,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":2937600000,"finalRate":0.072,"realValueDate":1484894856750,"dueDate":2908800000,"settlementDate":3024000000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"月享","remainsSeconds":-1380391,"productTags":["当日起息","收益保障"],"minRate":"0.070","maxRate":"0.072","finalMinRate":"0.070","finalMaxRate":"0.072","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"MONTH","serial":"EP1701039033","name":"麦金所 月享88号","term":35,"interestRate":0.07,"bonusRate":0.002,"valueDate":0,"displayTotalAmount":null,"totalAmount":268000,"soldAmount":268000,"startTime":1483416820000,"endTime":1483542823000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":9,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019994,"publishDate":1483416856000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-1478036,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":2937600000,"finalRate":0.072,"realValueDate":1484894856750,"dueDate":2908800000,"settlementDate":3024000000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"月享","remainsSeconds":-1478036,"productTags":["当日起息","收益保障"],"minRate":"0.070","maxRate":"0.072","finalMinRate":"0.070","finalMaxRate":"0.072","repaid":false,"transferConditionTip":"不可转让"},{"modifyDate":null,"category":"MONTH","serial":"EP1701184950","name":"麦金所 月享96号","term":31,"interestRate":0.07,"bonusRate":0,"valueDate":0,"displayTotalAmount":null,"totalAmount":326000,"soldAmount":326000,"startTime":1484730314000,"endTime":1484867939000,"riskLevel":null,"riskDescription":null,"numberOfShoppers":7,"minAmount":1000,"sumAmount":null,"maxAmount":500000,"sequnce":2017019993,"publishDate":1484730444000,"status":"SOLDOUT","remark":null,"statusValue":"未知","supplier":null,"type":null,"remainsTime":-164542,"acceptingBank":null,"cashTime":null,"activitys":null,"tag":4,"rookie":false,"agreementNumber":null,"enterpriseCode":null,"channel":null,"productModel":0,"label":"","userAssetAmountLimit":null,"vip":false,"vipLimit":null,"canTransfer":false,"canTransferDays":10,"kind":null,"earningsRules":[],"expiredDate":2592000000,"finalRate":0.07,"realValueDate":1484894856750,"dueDate":2563200000,"settlementDate":2678400000,"finalStatus":"SOLDOUT","valueDateStr":"购买成功后即日开始计算收益","categoryName":"月享","remainsSeconds":-164542,"productTags":["当日起息","收益保障"],"minRate":"0.070","maxRate":"0.070","finalMinRate":"0.070","finalMaxRate":"0.070","repaid":false,"transferConditionTip":"不可转让"}]
         * startRow : null
         * startNumber : 0
         * endNumber : 20
         * p : 1
         * startRecord : 0
         * next : true
         * forward : false
         */

        private int pageNumber;
        private int pageSize;
        private int totalCount;
        private int pageCount;
        private Object startRow;
        private int startNumber;
        private int endNumber;
        private int p;
        private int startRecord;
        private boolean next;
        private boolean forward;
        private List<ListBean> list;

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public Object getStartRow() {
            return startRow;
        }

        public void setStartRow(Object startRow) {
            this.startRow = startRow;
        }

        public int getStartNumber() {
            return startNumber;
        }

        public void setStartNumber(int startNumber) {
            this.startNumber = startNumber;
        }

        public int getEndNumber() {
            return endNumber;
        }

        public void setEndNumber(int endNumber) {
            this.endNumber = endNumber;
        }

        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }

        public int getStartRecord() {
            return startRecord;
        }

        public void setStartRecord(int startRecord) {
            this.startRecord = startRecord;
        }

        public boolean isNext() {
            return next;
        }

        public void setNext(boolean next) {
            this.next = next;
        }

        public boolean isForward() {
            return forward;
        }

        public void setForward(boolean forward) {
            this.forward = forward;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * modifyDate : null
             * category : YEAR
             * serial : EP1701048747
             * name : 麦金所 年享73号
             * term : 278
             * interestRate : 0.1
             * bonusRate : 0.008
             * valueDate : 0
             * displayTotalAmount : null
             * totalAmount : 362000.0
             * soldAmount : 362000.0
             * startTime : 1483459392000
             * endTime : 1484063999000
             * riskLevel : null
             * riskDescription : null
             * numberOfShoppers : 24
             * minAmount : 1000.0
             * sumAmount : null
             * maxAmount : 500000
             * sequnce : 2017019999
             * publishDate : 1483459687000
             * status : SOLDOUT
             * remark : null
             * statusValue : 未知
             * supplier : null
             * type : null
             * remainsTime : -1435464
             * acceptingBank : null
             * cashTime : null
             * activitys : null
             * tag : 4
             * rookie : false
             * agreementNumber : null
             * enterpriseCode : null
             * channel : null
             * productModel : 0
             * label :
             * userAssetAmountLimit : null
             * vip : false
             * vipLimit : null
             * canTransfer : false
             * canTransferDays : 10
             * kind : null
             * earningsRules : []
             * expiredDate : 23932800000
             * finalRate : 0.108
             * realValueDate : 1484894856750
             * dueDate : 23904000000
             * settlementDate : 24019200000
             * finalStatus : SOLDOUT
             * valueDateStr : 购买成功后即日开始计算收益
             * categoryName : 年享
             * remainsSeconds : -1435464
             * productTags : ["当日起息","收益保障"]
             * minRate : 0.100
             * maxRate : 0.108
             * finalMinRate : 0.100
             * finalMaxRate : 0.108
             * repaid : false
             * transferConditionTip : 不可转让
             */

            private Object modifyDate;
            private String category;
            private String serial;
            private String name;
            private int term;
            private double interestRate;
            private double bonusRate;
            private int valueDate;
            private Object displayTotalAmount;
            private double totalAmount;
            private double soldAmount;
            private long startTime;
            private long endTime;
            private Object riskLevel;
            private Object riskDescription;
            private int numberOfShoppers;
            private double minAmount;
            private Object sumAmount;
            private int maxAmount;
            private int sequnce;
            private long publishDate;
            private String status;
            private Object remark;
            private String statusValue;
            private Object supplier;
            private Object type;
            private int remainsTime;
            private Object acceptingBank;
            private Object cashTime;
            private Object activitys;
            private int tag;
            private boolean rookie;
            private Object agreementNumber;
            private Object enterpriseCode;
            private Object channel;
            private int productModel;
            private String label;
            private Object userAssetAmountLimit;
            private boolean vip;
            private Object vipLimit;
            private boolean canTransfer;
            private int canTransferDays;
            private Object kind;
            private long expiredDate;
            private double finalRate;
            private long realValueDate;
            private long dueDate;
            private long settlementDate;
            private String finalStatus;
            private String valueDateStr;
            private String categoryName;
            private int remainsSeconds;
            private String minRate;
            private String maxRate;
            private String finalMinRate;
            private String finalMaxRate;
            private boolean repaid;
            private String transferConditionTip;
            private List<?> earningsRules;
            private List<String> productTags;

            public Object getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(Object modifyDate) {
                this.modifyDate = modifyDate;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getSerial() {
                return serial;
            }

            public void setSerial(String serial) {
                this.serial = serial;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getTerm() {
                return term;
            }

            public void setTerm(int term) {
                this.term = term;
            }

            public double getInterestRate() {
                return interestRate;
            }

            public void setInterestRate(double interestRate) {
                this.interestRate = interestRate;
            }

            public double getBonusRate() {
                return bonusRate;
            }

            public void setBonusRate(double bonusRate) {
                this.bonusRate = bonusRate;
            }

            public int getValueDate() {
                return valueDate;
            }

            public void setValueDate(int valueDate) {
                this.valueDate = valueDate;
            }

            public Object getDisplayTotalAmount() {
                return displayTotalAmount;
            }

            public void setDisplayTotalAmount(Object displayTotalAmount) {
                this.displayTotalAmount = displayTotalAmount;
            }

            public double getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(double totalAmount) {
                this.totalAmount = totalAmount;
            }

            public double getSoldAmount() {
                return soldAmount;
            }

            public void setSoldAmount(double soldAmount) {
                this.soldAmount = soldAmount;
            }

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public Object getRiskLevel() {
                return riskLevel;
            }

            public void setRiskLevel(Object riskLevel) {
                this.riskLevel = riskLevel;
            }

            public Object getRiskDescription() {
                return riskDescription;
            }

            public void setRiskDescription(Object riskDescription) {
                this.riskDescription = riskDescription;
            }

            public int getNumberOfShoppers() {
                return numberOfShoppers;
            }

            public void setNumberOfShoppers(int numberOfShoppers) {
                this.numberOfShoppers = numberOfShoppers;
            }

            public double getMinAmount() {
                return minAmount;
            }

            public void setMinAmount(double minAmount) {
                this.minAmount = minAmount;
            }

            public Object getSumAmount() {
                return sumAmount;
            }

            public void setSumAmount(Object sumAmount) {
                this.sumAmount = sumAmount;
            }

            public int getMaxAmount() {
                return maxAmount;
            }

            public void setMaxAmount(int maxAmount) {
                this.maxAmount = maxAmount;
            }

            public int getSequnce() {
                return sequnce;
            }

            public void setSequnce(int sequnce) {
                this.sequnce = sequnce;
            }

            public long getPublishDate() {
                return publishDate;
            }

            public void setPublishDate(long publishDate) {
                this.publishDate = publishDate;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public String getStatusValue() {
                return statusValue;
            }

            public void setStatusValue(String statusValue) {
                this.statusValue = statusValue;
            }

            public Object getSupplier() {
                return supplier;
            }

            public void setSupplier(Object supplier) {
                this.supplier = supplier;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public int getRemainsTime() {
                return remainsTime;
            }

            public void setRemainsTime(int remainsTime) {
                this.remainsTime = remainsTime;
            }

            public Object getAcceptingBank() {
                return acceptingBank;
            }

            public void setAcceptingBank(Object acceptingBank) {
                this.acceptingBank = acceptingBank;
            }

            public Object getCashTime() {
                return cashTime;
            }

            public void setCashTime(Object cashTime) {
                this.cashTime = cashTime;
            }

            public Object getActivitys() {
                return activitys;
            }

            public void setActivitys(Object activitys) {
                this.activitys = activitys;
            }

            public int getTag() {
                return tag;
            }

            public void setTag(int tag) {
                this.tag = tag;
            }

            public boolean isRookie() {
                return rookie;
            }

            public void setRookie(boolean rookie) {
                this.rookie = rookie;
            }

            public Object getAgreementNumber() {
                return agreementNumber;
            }

            public void setAgreementNumber(Object agreementNumber) {
                this.agreementNumber = agreementNumber;
            }

            public Object getEnterpriseCode() {
                return enterpriseCode;
            }

            public void setEnterpriseCode(Object enterpriseCode) {
                this.enterpriseCode = enterpriseCode;
            }

            public Object getChannel() {
                return channel;
            }

            public void setChannel(Object channel) {
                this.channel = channel;
            }

            public int getProductModel() {
                return productModel;
            }

            public void setProductModel(int productModel) {
                this.productModel = productModel;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public Object getUserAssetAmountLimit() {
                return userAssetAmountLimit;
            }

            public void setUserAssetAmountLimit(Object userAssetAmountLimit) {
                this.userAssetAmountLimit = userAssetAmountLimit;
            }

            public boolean isVip() {
                return vip;
            }

            public void setVip(boolean vip) {
                this.vip = vip;
            }

            public Object getVipLimit() {
                return vipLimit;
            }

            public void setVipLimit(Object vipLimit) {
                this.vipLimit = vipLimit;
            }

            public boolean isCanTransfer() {
                return canTransfer;
            }

            public void setCanTransfer(boolean canTransfer) {
                this.canTransfer = canTransfer;
            }

            public int getCanTransferDays() {
                return canTransferDays;
            }

            public void setCanTransferDays(int canTransferDays) {
                this.canTransferDays = canTransferDays;
            }

            public Object getKind() {
                return kind;
            }

            public void setKind(Object kind) {
                this.kind = kind;
            }

            public long getExpiredDate() {
                return expiredDate;
            }

            public void setExpiredDate(long expiredDate) {
                this.expiredDate = expiredDate;
            }

            public double getFinalRate() {
                return finalRate;
            }

            public void setFinalRate(double finalRate) {
                this.finalRate = finalRate;
            }

            public long getRealValueDate() {
                return realValueDate;
            }

            public void setRealValueDate(long realValueDate) {
                this.realValueDate = realValueDate;
            }

            public long getDueDate() {
                return dueDate;
            }

            public void setDueDate(long dueDate) {
                this.dueDate = dueDate;
            }

            public long getSettlementDate() {
                return settlementDate;
            }

            public void setSettlementDate(long settlementDate) {
                this.settlementDate = settlementDate;
            }

            public String getFinalStatus() {
                return finalStatus;
            }

            public void setFinalStatus(String finalStatus) {
                this.finalStatus = finalStatus;
            }

            public String getValueDateStr() {
                return valueDateStr;
            }

            public void setValueDateStr(String valueDateStr) {
                this.valueDateStr = valueDateStr;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public int getRemainsSeconds() {
                return remainsSeconds;
            }

            public void setRemainsSeconds(int remainsSeconds) {
                this.remainsSeconds = remainsSeconds;
            }

            public String getMinRate() {
                return minRate;
            }

            public void setMinRate(String minRate) {
                this.minRate = minRate;
            }

            public String getMaxRate() {
                return maxRate;
            }

            public void setMaxRate(String maxRate) {
                this.maxRate = maxRate;
            }

            public String getFinalMinRate() {
                return finalMinRate;
            }

            public void setFinalMinRate(String finalMinRate) {
                this.finalMinRate = finalMinRate;
            }

            public String getFinalMaxRate() {
                return finalMaxRate;
            }

            public void setFinalMaxRate(String finalMaxRate) {
                this.finalMaxRate = finalMaxRate;
            }

            public boolean isRepaid() {
                return repaid;
            }

            public void setRepaid(boolean repaid) {
                this.repaid = repaid;
            }

            public String getTransferConditionTip() {
                return transferConditionTip;
            }

            public void setTransferConditionTip(String transferConditionTip) {
                this.transferConditionTip = transferConditionTip;
            }

            public List<?> getEarningsRules() {
                return earningsRules;
            }

            public void setEarningsRules(List<?> earningsRules) {
                this.earningsRules = earningsRules;
            }

            public List<String> getProductTags() {
                return productTags;
            }

            public void setProductTags(List<String> productTags) {
                this.productTags = productTags;
            }
        }
    }
}
