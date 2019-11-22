package com.imooc.ad.vo;

import com.imooc.ad.constant.CommonStatus;
import com.imooc.ad.entity.Creative;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeRequest {
  private String name;
  private Integer type;
  private Integer materialType;
  private Integer height;
  private Integer width;
  private Long size;
  private Integer duration;
  private Long userId;
  private String url;

  public Creative ConvertToEntity(){
    Creative creative = new Creative();
    creative.setName(name);
    creative.setType(type);
    creative.setMaterialTpe(materialType);
    creative.setHeight(height);
    creative.setWidth(width);
    creative.setSize(size);
    creative.setDuration(duration);
    creative.setAuditStatus(CommonStatus.VALID.getStatus());
    creative.setUsrId(userId);
    creative.setUrl(url);
    creative.setCreateTime(new Date());
    creative.setUpdateTime(creative.getCreateTime());

    return creative;
  }
}
