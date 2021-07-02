package cn.lngex.user.service;

import cn.lngex.user.dto.SmsCodeDeto;
import cn.lngex.utils.AjaxResult;

public interface IVerifycodeService {

    /**
     * 验证码生成
     * @param key
     * @return
     */
    AjaxResult imageCode(String key);


    /**
     * 发送手机验证码
     * @param smsCodeDeto
     * @return
     */
    AjaxResult sendSmsCode(SmsCodeDeto smsCodeDeto);
}
