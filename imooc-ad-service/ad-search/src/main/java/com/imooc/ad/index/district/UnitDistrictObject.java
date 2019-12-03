package com.imooc.ad.index.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// unitId -> province + city
// province+ '-' + city => unitId
public class UnitDistrictObject {
  private Long unitId;
  private String Province;
  private String City;
}
