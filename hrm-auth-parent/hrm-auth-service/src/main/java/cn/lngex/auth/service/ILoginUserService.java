package cn.lngex.auth.service;

import cn.lngex.auth.domain.LoginDto;
import cn.lngex.auth.domain.LoginUser;
import cn.lngex.utils.AjaxResult;
import com.baomidou.mybatisplus.service.IService;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-17
 */
public interface ILoginUserService extends IService<LoginUser> {


    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    AjaxResult login(LoginDto loginDto);


    /**
     * 用户token刷新
     * @param map
     * @return
     */
    AjaxResult refToken(HashMap<String, Object> map);
}
