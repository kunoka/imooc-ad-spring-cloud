package com.imooc.ad.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.IgnoreResponseAdvice;
import com.imooc.ad.client.SponsorClient;
import com.imooc.ad.client.vo.AdPlan;
import com.imooc.ad.client.vo.AdPlanGetRequest;
import com.imooc.ad.search.ISearch;
import com.imooc.ad.search.vo.SearchRequest;
import com.imooc.ad.search.vo.SearchResponse;
import com.imooc.ad.vo.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
public class SearchController {

  private final RestTemplate restTemplate;
  private final SponsorClient sponsorClient;
  private final ISearch search;

  @Autowired
  public SearchController(RestTemplate restTemplate, SponsorClient sponsorClient, ISearch search) {
    this.restTemplate = restTemplate;
    this.sponsorClient = sponsorClient;
    this.search = search;
  }

  // post 方法 定义 url 为 fetchAds
//  @PostMapping("/fetchAds")
//  public SearchResponse fetchAds(@RequestBody SearchRequest request) {
//    log.info("ad search: fetchAds -> {}",
//      JSON.toJSONString(request));
//    return search.fetchAds(request);
//  }

  @IgnoreResponseAdvice
  @PostMapping("/getAdPlans")
  public CommonResponse<List<AdPlan>> getAdPlans(
    @RequestBody AdPlanGetRequest request
  ) {
    log.info("ad-search: getAdPlans -> {}",
      JSON.toJSONString(request));
    return sponsorClient.getAdPlans(request);
  }

  @SuppressWarnings("all") // 屏蔽警告信息
  @IgnoreResponseAdvice // 不想使用统一的响应
  @PostMapping("ad/getAdPlansByRibben")
  public CommonResponse<List<AdPlan>> getAdPlanByRibbon(
    @RequestBody AdPlanGetRequest request
  ) {
    log.info("ad-search: getAdPlansByRibbon -> {}",
      JSON.toJSONString(request));
    return restTemplate.postForEntity(
      "http://eureka-client-ad-sponsor/ad-sponsor/get/adPlan",
      request,
      CommonResponse.class //提供的序列化的格式
    ).getBody();
  }
}
