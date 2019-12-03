package com.imooc.ad.index.creativeunit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// adId - UnitId
public class CreativeUnitObject {
  private Long unitId;
  private Long adId;
}
