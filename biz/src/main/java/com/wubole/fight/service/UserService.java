package com.wubole.fight.service;

import com.wubole.fight.entity.UserEntity;

/**
 * Created by baowp on 14-12-20.
 */
public interface UserService {

    public int insert(UserEntity user);

    public UserEntity getByMobile(String mobile);

    public String validateCode(String mobile);

    int updatePassword(UserEntity user);
}
