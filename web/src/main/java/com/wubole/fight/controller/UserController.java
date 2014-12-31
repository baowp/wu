package com.wubole.fight.controller;

import com.wubole.fight.domain.ValidateCodeResult;
import com.wubole.fight.entity.UserEntity;
import com.wubole.fight.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by baowp on 14-12-20.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/validateCode")
    @ResponseBody
    public ValidateCodeResult validateCode(String mobile) {
        ValidateCodeResult result = new ValidateCodeResult();
        result.setMobile(mobile);
        UserEntity existUser = userService.getByMobile(mobile);
        if (existUser != null && existUser.getPassword() != null) {
            result.setErrorMsg("该手机号码已经注册");
            return result;
        } else {
            String validateCode = userService.validateCode(mobile);
            result.setValidateCode(validateCode);
            result.setSuccess(true);
            return result;
        }
    }

    @RequestMapping("/setPassword")
    @ResponseBody
    public int setPassword(String mobile, String password,String validateCode) {
        String encodedPass = DigestUtils.md5Hex(password);
        UserEntity user = userService.getByMobile(mobile);
        //if()
        user.setMobile(mobile);
        user.setPassword(encodedPass);
        return userService.updatePassword(user);
    }
}
