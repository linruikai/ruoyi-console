package com.ruoyi.controller.view;

import lombok.Data;

/**
 * @author: linruikai
 * @date: 2020/6/27 18:17
 */
@Data
public class BaseResponse<T> {

  private Integer code;
  private Boolean success;
  private String msg;
  private T data;

  public BaseResponse(Integer code, Boolean success, String msg, T data) {
    this.code = code;
    this.success = success;
    this.msg = msg;
    this.data = data;
  }

  public BaseResponse(Integer code, Boolean success, String msg) {
    this.code = code;
    this.success = success;
    this.msg = msg;
  }

  public static BaseResponse success() {
    return new BaseResponse(0, true, "success");
  }

  public static <T> BaseResponse success(T data) {
    return new BaseResponse(0, true, "success", data);
  }

  public static BaseResponse fail(String msg) {
    return new BaseResponse(0, false, msg);
  }
}
