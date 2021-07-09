package cn.lngex.auth.service.impl;

import cn.lngex.auth.domain.AuthClientType;
import cn.lngex.auth.domain.LoginDto;
import cn.lngex.auth.domain.LoginUser;
import cn.lngex.auth.mapper.LoginUserMapper;
import cn.lngex.auth.service.ILoginUserService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.encrypt.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-17
 */
@Service
public class LoginUserServiceImpl extends ServiceImpl<LoginUserMapper, LoginUser> implements ILoginUserService {
    /**
     * 用户登录
     *
     * @param loginDto
     * @return
     */
    @Override
    public AjaxResult login(LoginDto loginDto) {
        String url = "http://localhost:3020/oauth/token?client_id=%s&client_secret=%s&grant_type=password&redirect_uri=http://www.baidu.com&username=%s&password=%s";

        System.out.println(loginDto);
        AuthClientType instan = AuthClientType.getInstan(loginDto.getType());
        url = String.format(url,instan.getClientId(),instan.getClientSecret(),loginDto.getUsername(),loginDto.getPassword());
        System.out.println(url);
        System.out.println(loginDto);
        String str = HttpRequest.get(url);
        System.err.println("=======>"+str);
        Assert.notNull(str,"用户名或密码错误");
        Map mapTypes = JSON.parseObject(str);
        return AjaxResult.me().setResultObj(mapTypes);
    }

    /**
     * 用户token刷新
     *
     * @param map
     * @return
     */
    @Override
    public AjaxResult refToken(HashMap<String, Object> map) {
        String rtoken = map.get("rtoken").toString();
        String url = "http://localhost:3020/oauth/token?grant_type=refresh_token&refresh_token="+rtoken+"&client_id=website&client_secret=1";
        String str = HttpRequest.get(url);
        Map mapTypes = JSON.parseObject(str);
        return AjaxResult.me().setResultObj(mapTypes);
    }

}
