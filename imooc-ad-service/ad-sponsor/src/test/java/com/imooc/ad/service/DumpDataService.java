package com.imooc.ad.service;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.Application;
import com.imooc.ad.constant.CommonStatus;
import com.imooc.ad.dao.AdPlanRepository;
import com.imooc.ad.dao.AdUnitRepository;
import com.imooc.ad.dao.CreativeRepository;
import com.imooc.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.imooc.ad.dao.unit_condition.AdUnitItRepository;
import com.imooc.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.imooc.ad.dao.unit_condition.CreativeUnitRepository;
import com.imooc.ad.dump.table.AdCreativeTable;
import com.imooc.ad.dump.table.AdPlanTable;
import com.imooc.ad.dump.table.AdUnitTable;
import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.entity.AdUnit;
import com.imooc.ad.entity.Creative;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
  webEnvironment = SpringBootTest.WebEnvironment.NONE
)
public class DumpDataService {
  @Autowired
  private AdPlanRepository planRepository;
  @Autowired
  private AdUnitRepository unitRepository;
  @Autowired
  private CreativeRepository creativeRepository;
  @Autowired
  private CreativeUnitRepository creativeUnitRepository;
  @Autowired
  private AdUnitDistrictRepository districtRepository;
  @Autowired
  private AdUnitItRepository itRepository;
  @Autowired
  private AdUnitKeywordRepository keywordRepository;
  private void dumpAdPlanTable(String fileName) {
    List<AdPlan> adPlans = planRepository.findAllByPlanStatus(
      CommonStatus.VALID.getStatus()
    );
    if (CollectionUtils.isEmpty(adPlans)) {
      return;
      ;
    }
    List<AdPlanTable> planTables = new ArrayList<>();
    adPlans.forEach(p -> planTable.add(
      new AdPlanTable(
        p.getId(),
        p.getUserId(),
        p.getPlanStatus(),
        p.getStartDate(),
        p.getEndDate()
      )
    ));
    Path path = Paths.get(fileName);
    try (BufferedWriter writer = Files.newBufferedWriter(path)){
      for(AdPlanTable planTable: planTables ){
        writer.write(JSON.toJSONString(planTable));
        writer.newLine();
      }
      writer.close();
    }catch (IOException ex){
      log.error("dumpAdPlanTable error");
    }
  }
  private void dumpAdUnitTable(String fileName){
    List<AdUnit> adUnits = unitRepository.findAllByUnitStatus(
      CommonStatus.VALID.getStatus()
    );
    if(CollectionUtils.isEmpty(adUnits)){
      return;
    }
    List<AdUnitTable> unitTables = new ArrayList<>();
    adUnits.forEach(u -> unitTables.add(
      new AdUnitTable(
        u.getId(),
        u.getUnitStatus(),
        u.getPositionType(),
        u.getPlanId()
      )
    ));
    Path path = Paths.get(fileName);
    try (BufferedWriter writer = Files.newBufferedWriter(path)){
      for(AdUnitTable unitTable: unitTables ){
        writer.write(JSON.toJSONString(unitTable));
        writer.newLine();
      }
      writer.close();
    }catch (IOException ex){
      log.error("dumpAdUnitTable error");
    }
  }
  private void dumpAdCreativeTable(String fileName){
    List<Creative> creatives = creativeRepository.findAll();
    if(CollectionUtils.isEmpty(creatives)){
      return;
    }
    List <AdCreativeTable> creativeTables = new ArrayList<>();
    creatives.forEach(c -> creativeTables.add(
      new AdCreativeTable(
        c.getId(),
        c.getName(),
        c.getType(),
        c.getMaterialTpe(),
        c.getWidth(),
        c.getHeight(),
        c.getAuditStatus(),
        c.getUrl()
      )
    ));
    Path path = Paths.get(fileName);
    try (BufferedWriter writer = Files.newBufferedWriter(path)){
      for(AdCreativeTable creativeTable: creativeTables ){
        writer.write(JSON.toJSONString(creativeTable));
        writer.newLine();
      }
      writer.close();
    }catch (IOException ex){
      log.error("dumpAdCreativeTable error");
    }
  }
}
