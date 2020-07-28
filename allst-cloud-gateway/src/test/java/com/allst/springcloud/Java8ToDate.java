package com.allst.springcloud;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

/**
 * @author YiYa
 * @since 2020-07-28 下午 11:16
 */
public class Java8ToDate {

    @Test
    public void test1() {
        // 默认时区
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }

}
