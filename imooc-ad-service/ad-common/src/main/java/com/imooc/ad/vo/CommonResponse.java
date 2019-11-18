package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data //对各个属性实现set, get
@NoArgsConstructor //无参的构造函数
@AllArgsConstructor //全参的构造函数
public class CommonResponse<T> implements Serializable {
  private Integer code;
  private String message;
  private T data;

  public CommonResponse(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
