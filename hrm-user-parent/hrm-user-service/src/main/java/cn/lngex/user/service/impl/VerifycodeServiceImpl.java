package cn.lngex.user.service.impl;

import cn.lngex.user.constant.UserConstant;
import cn.lngex.user.domain.VipUser;
import cn.lngex.user.dto.SmsCodeDeto;
import cn.lngex.user.mapper.VipUserMapper;
import cn.lngex.user.service.IVerifycodeService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.VerifyCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class VerifycodeServiceImpl implements IVerifycodeService {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private VipUserMapper vipUserMapper;
    /**
     * 验证码生成
     *
     * @param key
     * @return
     */
    @Override
    public AjaxResult imageCode(String key) {
        AjaxResult me = AjaxResult.me();
        Map<String, String> map = null;
        try {
            map = VerifyCodeUtils.VerifyCode(100, 40, 4);
            redisTemplate.opsForValue().set(key,map.get("code"),3, TimeUnit.MINUTES);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return me.setResultObj(map.get("img"));
    }

    /**
     * 发送手机验证码
     *
     * @param smsCodeDeto
     * @return
     */
    @Override
    public AjaxResult sendSmsCode(SmsCodeDeto smsCodeDeto) {
        /* 非空判断 */
        if(StringUtils.isEmpty(smsCodeDeto.getImageCode()) || StringUtils.isEmpty(smsCodeDeto.getImageCodeKey()) || StringUtils.isEmpty(smsCodeDeto.getMobile())){
            throw new RuntimeException("请输入完整");
        }
        /* 判断验证码 */
        String imageCodeKey = smsCodeDeto.getImageCodeKey();
        String o = (String)redisTemplate.opsForValue().get(imageCodeKey);
        if(!smsCodeDeto.getImageCode().equalsIgnoreCase(o)){
            throw new RuntimeException("验证码错误");
        }
        /* 判断用户是否注册 */
        VipUser vipUser = new VipUser();
        vipUser.setPhone(smsCodeDeto.getMobile());
        VipUser vipUser1 = vipUserMapper.selectOne(vipUser);
        if(vipUser1 != null){
            throw new RuntimeException("用户已注册");
        }
        String code = VerifyCodeUtils.generateVerifyCode(6, "0123456789");
        redisTemplate.opsForValue().set(smsCodeDeto.getMobile()+"::"+ UserConstant.REG_CODE,code,5,TimeUnit.MINUTES);
        System.out.println(code);
        return AjaxResult.me();
    }
}
