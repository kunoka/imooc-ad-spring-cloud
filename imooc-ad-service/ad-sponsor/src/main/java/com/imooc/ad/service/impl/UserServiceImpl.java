package com.imooc.ad.service.impl;

import com.imooc.ad.constant.Constants;
import com.imooc.ad.dao.AdUserRepository;
import com.imooc.ad.entity.AdUser;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.service.IUserService;
import com.imooc.ad.utils.CommonUtils;
import com.imooc.ad.vo.CreateUserRequest;
import com.imooc.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j //日志的属性
@Service //标记为一个 java bean
public class UserServiceImpl implements IUserService {
  private final AdUserRepository userRepository;

  @Autowired
  public UserServiceImpl(AdUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional //开户一个事务
  public CreateUserResponse createUser(CreateUserRequest request)
    throws AdException {
    if(!request.validate()){
      throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
    }
    AdUser oldUser = userRepository.findByUsername(request.getUserName());
    if(oldUser!= null){
      throw new AdException(Constants.ErrorMsg.SAVE_NAME_ERROR);
    }
    AdUser newUser = userRepository.save(new AdUser(
      request.getUserName(),
      CommonUtils.md5(request.getUserName())
    ));
    return new CreateUserResponse(
      newUser.getId(),
      newUser.getUsername(),
      newUser.getToken(),
      newUser.getCreateTime(),
      newUser.getUpdateTime()
    );
  }
}
