package com.imooc.ad.index;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum CommonStatus {
  VALID(1, " 有效状态"),
  INVALID(2, " 无效状态");

  private Integer status;
  private String desc;

  CommonStatus(Integer status, String desc) {
    this.status = status;
    this.desc = desc;
  }
}
