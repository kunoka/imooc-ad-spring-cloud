package com.imooc.ad.search;

import com.imooc.ad.search.vo.SearchRequest;
import com.imooc.ad.search.vo.SearchResponse;

// 用于广告的检索请求
public interface ISearch {
  SearchResponse fetchAds(SearchRequest request);
}
