package com.imooc.ad.service.impl;

import com.imooc.ad.dao.CreativeRepository;
import com.imooc.ad.entity.Creative;
import com.imooc.ad.service.ICreativeService;
import com.imooc.ad.vo.CreativeRequest;
import com.imooc.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreativeServiceImpl implements ICreativeService {

  private final CreativeRepository creativeRepository;

  public CreativeServiceImpl(CreativeRepository creativeRepository) {
    this.creativeRepository = creativeRepository;
  }

  @Override
  public CreativeResponse createCreative(CreativeRequest request) {
    Creative creative = creativeRepository.save(
      request.ConvertToEntity()
    );
    return new CreativeResponse(creative.getId(), creative.getName());
  }
}
