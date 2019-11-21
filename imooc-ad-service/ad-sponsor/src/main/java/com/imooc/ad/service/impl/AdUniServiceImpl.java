package com.imooc.ad.service.impl;

import com.imooc.ad.constant.Constants;
import com.imooc.ad.dao.AdPlanRepository;
import com.imooc.ad.dao.AdUnitRepository;
import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.entity.AdUnit;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.service.IAdUnitService;
import com.imooc.ad.vo.AdUnitRequest;
import com.imooc.ad.vo.AdUnitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //标注为一个 bean
public class AdUniServiceImpl implements IAdUnitService {

  @Autowired
  private final AdPlanRepository planRepository;
  private final AdUnitRepository unitRepository;

  public AdUniServiceImpl(AdPlanRepository planRepository, AdUnitRepository unitRepository) {
    this.planRepository = planRepository;
    this.unitRepository = unitRepository;
  }


  @Override
  public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {
    if(!request.createValidate()){
      throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
    }
    Optional<AdPlan> adPlan = planRepository.findById(request.getPlanId());
    if(!adPlan.isPresent()){
      throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
    }
    AdUnit oldAddUnit = unitRepository.findByPlanIdAndUnitName(
      request.getPlanId(), request.getUnitName()
    );
    if(oldAddUnit != null){
      throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
    }
    AdUnit newAdUnit = unitRepository.save(
      new AdUnit(request.getPlanId(),request.getUnitName(),
        request.getPositionType(), request.getBudget())
    );
    return new AdUnitResponse(newAdUnit.getId(), newAdUnit.getUnitName());
  }
}
