package com.ease.data.config.result;


import com.ease.data.config.ResponseCodeEnum;

import static com.ease.data.config.ResponseCodeEnum.SUCCESS;

/**
 * @author Wagic
 */
public class ResultGenerator {

    private static <T> RestResult<T> createResult(Integer code, String msg, T data) {

        RestResult<T> result = RestResult.newInstance();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> RestResult<T> createSucResult() {
        return createResult(SUCCESS.getCode(), SUCCESS.getName(), null);
    }

    public static <T> RestResult<T> createSucResult(T data) {
        return createResult(SUCCESS.getCode(), SUCCESS.getName(), data);
    }


    public static <T> RestResult<T> createSucResult(String msg, T data) {
        return createResult(SUCCESS.getCode(), msg, data);
    }

    public static <T> RestResult<T> createFailResult(ResponseCodeEnum respCode) {
        return createResult(respCode.getCode(), respCode.getName(), null);
    }

    public static <T> RestResult<T> createFailResult(ResponseCodeEnum respCode, String msg) {
        return createResult(respCode.getCode(), msg, null);
    }

    public static <T> RestResult<T> createFailResult(ResponseCodeEnum respCode, T data) {
        return createResult(respCode.getCode(), respCode.getName(), data);
    }

}
