package com.allst.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YiYa
 * @since 2020-07-19 下午 10:12
 */
@Data
/*@AllArgsConstructor*/
@NoArgsConstructor
public class CommonResult<T> {
    /**
     * 编码
     */
    private Integer code;
    /**
     * 消息体
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
        this.code = code;
        this.message = message;
    }

    public CommonResult(Integer code, String message, T o) {
        this.code = code;
        this.message = message;
        this.data = o;
    }
}
