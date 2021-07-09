package cn.lngex.user.service;

import cn.lngex.user.domain.VipUser;
import cn.lngex.user.dto.UserRegDto;
import cn.lngex.utils.AjaxResult;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 会员登录账号 服务类
 * </p>
 *
 * @author ï¿½ï¿½Ä³
 * @since 2021-06-25
 */
public interface IVipUserService extends IService<VipUser> {

    /**
     * 用户注册
     * @param userRegDto
     * @return
     */
    AjaxResult register(UserRegDto userRegDto);
}
