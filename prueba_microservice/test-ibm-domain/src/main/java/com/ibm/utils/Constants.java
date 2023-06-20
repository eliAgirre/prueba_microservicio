package com.ibm.utils;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants
public final class Constants {

    public static final String BASE_MAPPING = "/api/prices";
    public static final String PATH_PRICES_FILTER_BY_DATES_BRAND_PRODUCT = "/filterByDatesBrandProduct";
    public static final int BRAND_ID = 1;
    public static final String BRAND_DESCRIPTION = "Zara";
    public static final int PRODUCT_ID = 35435;
    public static final String PRODUCT_NAME = "Abrigo";
    public static final String EMPTY = "";

    public static final String CURR = "EUR";
    public static final String FORMAT_DATE_TIME_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd-HH.mm.ss";
    public static final String END_DATE_STRING = "2020-12-31-23.59.59";
    public static final String START_DATE_STRING_1 = "2020-06-14-10.00.00";
    public static final String START_DATE_STRING_2 = "2020-06-14-15.00.00";
    public static final String START_DATE_STRING_3 = "2020-06-15-00.00.00";
    public static final String START_DATE_STRING_4 = "2020-06-15-16.00.00";

    public static final String END_DATE_STRING_1 = "2020-12-31-23.59.59";
    public static final String END_DATE_STRING_2 = "2020-06-14-18.30.00";
    public static final String END_DATE_STRING_3 = "2020-06-15-11.00.00";
    public static final String END_DATE_STRING_4 = "2020-12-31-23.59.59";

    public static final String TEST_1_START_DATE_STRING = "2020-06-14-10.00.00";
    public static final String TEST_2_START_DATE_STRING = "2020-06-14-16.00.00";
    public static final String TEST_3_START_DATE_STRING = "2020-06-14-21.00.00";
    public static final String TEST_4_START_DATE_STRING = "2020-06-15-10.00.00";
    public static final String TEST_5_START_DATE_STRING = "2020-06-16-21.00.00";

    public static final String LOG_SERVICE = "getPricesBetweenDatesAndBrandAndProduct startDate {} endDate {} brandId {} productId {} ";

    public static final String LOG_SERVICE_TEST1 = "test1_getPricesAt10ByDate14BrandId1AndProductId35435_should_call_pricesRepository";
    public static final String LOG_SERVICE_TEST2 = "test2_getPricesAt16ByDate14BrandId1AndProductId35435_should_call_pricesRepository";
    public static final String LOG_SERVICE_TEST3 = "test3_getPricesAt21ByDate14BrandId1AndProductId35435_should_call_pricesRepository";
    public static final String LOG_SERVICE_TEST4 = "test4_getPricesAt10ByDate15BrandId1AndProductId35435_should_call_pricesRepository";
    public static final String LOG_SERVICE_TEST5 = "test5_getPricesAt21ByDate16BrandId1AndProductId35435_should_call_pricesRepository";
    public static final String LOG_SERVICE_TEST_SERVICE_EXCEPTION = "getPricesByStartDateEmptyAndEndDateAndBrandId1AndProductId35435_should_throw_ServiceException";

    public static final String LOG_CONTROLLER = "REST filterByDatesBrandProduct startDate {}, endDate {}, brandId {}, productId {} ";

    public static final String LOG_CONTROLLER_TEST1 = "Test1_Given_StartDateAt10Date14AndEndDateAndBrandAndProduct_When_getPrices_Then_returns_PricesList";
    public static final String LOG_CONTROLLER_TEST2 = "Test2_Given_StartDateAt16Date14AndEndDateAndBrandAndProduct_When_getPrices_Then_returns_PricesList";
    public static final String LOG_CONTROLLER_TEST3 = "Test3_Given_StartDateAt21Date14AndEndDateAndBrandAndProduct_When_getPrices_Then_returns_PricesList";
    public static final String LOG_CONTROLLER_TEST4 = "Test4_Given_StartDateAt10Date15AndEndDateAndBrandAndProduct_When_getPrices_Then_returns_PricesList";
    public static final String LOG_CONTROLLER_TEST5 = "Test5_Given_StartDateAt21Date16AndEndDateAndBrandAndProduct_When_getPrices_Then_returns_PricesList";
    public static final String LOG_CONTROLLER_TEST_SERVICE_EXCEPTION = "Given_StartDateEmptyAndEndDateAndBrandId1AndProductId35435_When_getPrice_Then_throws_ServiceException";

}
