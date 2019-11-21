package com.imooc.ad.service;

import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.AdPLanRequest;
import com.imooc.ad.vo.AdPlanGetRequest;
import com.imooc.ad.vo.AdPlanResponse;

import java.util.List;

public interface IAdPlanService {
  /**
   * 创建推广计划
   * @param request
   * @return
   * @throws AdException
   */
  AdPlanResponse createAdPlan(AdPLanRequest request) throws AdException;
  /**
   * 获取推广计划
   */
  List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;
  /**
   * 更新推广计划
   */
  AdPlanResponse updateAdPlan(AdPLanRequest request) throws AdException;
  /**
   * 删除推广计划
   */
  void deleteAdPlan(AdPLanRequest request) throws AdException;
}
