package com.wubole.fight.persistence;

import com.wubole.fight.entity.UserEntity;

/**
 * Created by baowp on 14-12-20.
 */
public interface UserMapper {

    public int insert(UserEntity user);

    public UserEntity getByMobile(String mobile);

    public int updateMobileValidateCode(UserEntity user);

    int updatePassword(UserEntity user);
}
