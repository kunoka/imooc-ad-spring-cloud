package com.imooc.ad.index;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.dump.DConstant;
import com.imooc.ad.dump.table.*;
import com.imooc.ad.handler.AdLevelDataHandler;
import com.imooc.ad.mysql.constant.OpType;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 根据数据表导出的文件，读取文件，加载索引
 */
@Component
@DependsOn("dataTable") //定义依赖关系
public class IndexFileLoader {
  @PostConstruct
  public void init(){
    List<String> adPlanString = loadDumpData(
      String.format("%s%s",
        DConstant.DATA_ROOT_DIR,
        DConstant.AD_PLAN)
    );
    // 第2层级
    adPlanString.forEach(p -> AdLevelDataHandler.handleLevel2(
      JSON.parseObject(p, AdPlanTable.class),
      OpType.ADD
    ));
    List<String> adCreativeStrings = loadDumpData(
      String.format("%s%s",
        DConstant.DATA_ROOT_DIR,
        DConstant.AD_CREATIVE)
    );
    adCreativeStrings.forEach(c -> AdLevelDataHandler.handleLevel2(
      JSON.parseObject(c, AdCreativeTable.class),
      OpType.ADD
    ));
    //第3层级
    List<String> adUnitStrings = loadDumpData(
      String.format("%s%s",
        DConstant.DATA_ROOT_DIR,
        DConstant.AD_UNIT)
    );
    adUnitStrings.forEach(u -> AdLevelDataHandler.handleLevel3(
      JSON.parseObject(u, AdUnitTable.class),
      OpType.ADD
    ));
    List<String> adCreativeUnitStrings = loadDumpData(
      String.format("%s%s",
        DConstant.DATA_ROOT_DIR,
        DConstant.AD_CREATIVE_UNIT)
    );
    adCreativeUnitStrings.forEach(c -> AdLevelDataHandler.handleLevel3(
      JSON.parseObject(c, AdCreativeUnitTable.class),
      OpType.ADD
    ));
    //第4层级 - 地域
    List<String> adUnitDistrictStrings  =loadDumpData(
      String.format("%s%s",
        DConstant.DATA_ROOT_DIR,
        DConstant.AD_UNIT_DISTRICT)
    );
    adUnitDistrictStrings.forEach(u -> AdLevelDataHandler.handleLevel4(
      JSON.parseObject(u, AdUnitDistrictTable.class),
      OpType.ADD
    ));
    //第4层级 - 兴趣
    List<String> adUnitItStrings = loadDumpData(
      String.format("%s%s",
        DConstant.DATA_ROOT_DIR,
        DConstant.AD_UNIT_IT)
    );
    adUnitItStrings.forEach(i -> AdLevelDataHandler.handleLevel4(
      JSON.parseObject(i, AdUnitItTable.class),
      OpType.ADD
    ));
    //第4层级 - 关键字
    List<String> adUnitKeywordStrings = loadDumpData(
      String.format("%s%s",
        DConstant.DATA_ROOT_DIR,
        DConstant.AD_UNIT_KEYWORD)
    );
    adUnitKeywordStrings.forEach(k -> AdLevelDataHandler.handleLevel4(
      JSON.parseObject(k, AdUnitKeywordTable.class),
      OpType.ADD
    ));
  }
  private List<String> loadDumpData(String fileName){
    try(BufferedReader br = Files.newBufferedReader(
      Paths.get(fileName)
    )){
      return br.lines().collect(Collectors.toList());
    }catch (IOException ex){
      throw new RuntimeException(ex.getMessage());
    }
  }
}
