package cn.lngex.user.controller;

import cn.lngex.user.dto.SmsCodeDeto;
import cn.lngex.user.service.IVerifycodeService;
import cn.lngex.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verifycode")
public class VerifycodeController {

    @Autowired
    private IVerifycodeService verifycodeService;

    @GetMapping ("/imageCode/{key}")
    public AjaxResult imageCode(@PathVariable("key") String key){
        return verifycodeService.imageCode(key);
    }


    /**
     * 发送手机验证码
     * @param smsCodeDeto
     * @return
     */
    @PostMapping("/sendSmsCode")
    public AjaxResult sendSmsCode(@RequestBody SmsCodeDeto smsCodeDeto){
        return verifycodeService.sendSmsCode(smsCodeDeto);
    }
}
