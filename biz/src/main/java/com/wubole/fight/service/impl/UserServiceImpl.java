package com.wubole.fight.service.impl;

import com.wubole.fight.entity.UserEntity;
import com.wubole.fight.persistence.UserMapper;
import com.wubole.fight.service.UserService;
import com.wubole.fight.utils.sms.MessageSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by baowp on 14-12-20.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Random r = new Random();
    @Resource
    private UserMapper userMapper;
    @Resource
    private MessageSender messageSender;

    @Override
    public int insert(UserEntity user) {
        return userMapper.insert(user);
    }

    public UserEntity getByMobile(String mobile) {
        return userMapper.getByMobile(mobile);
    }

    public String validateCode(String mobile) {
        UserEntity user = getByMobile(mobile);
        String validateCode = String.valueOf(r.nextInt(999999));
        int flag = -1;
        if (user == null) {
            user = new UserEntity();
            user.setMobile(mobile);
            user.setMobileValidateCode(validateCode);
            flag = insert(user);
        } else {
            user.setMobileValidateCode(validateCode);
            flag = userMapper.updateMobileValidateCode(user);
        }
        if (flag > -1) {
            messageSender.sendValidateCode(validateCode, mobile);
            return validateCode;
        }
        return null;
    }

    @Override
    public int updatePassword(UserEntity user) {
        return userMapper.updatePassword(user);
    }
}
