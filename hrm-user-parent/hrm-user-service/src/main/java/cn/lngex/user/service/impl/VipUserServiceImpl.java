package cn.lngex.user.service.impl;

import cn.lngex.auth.domain.LoginUser;
import cn.lngex.auth.feign.IAuthFeign;
import cn.lngex.user.constant.UserConstant;
import cn.lngex.user.domain.VipUser;
import cn.lngex.user.dto.UserRegDto;
import cn.lngex.user.mapper.VipUserMapper;
import cn.lngex.user.service.IVipUserService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.BitStatesUtils;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

/**
 * <p>
 * 会员登录账号 服务实现类
 * </p>
 *
 * @author ï¿½ï¿½Ä³
 * @since 2021-06-25
 */
@Service
public class VipUserServiceImpl extends ServiceImpl<VipUserMapper, VipUser> implements IVipUserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IAuthFeign authFeign;

    @Autowired
    private VipUserMapper vipUserMapper;

    /**
     * 用户注册
     *
     * @param userRegDto
     * @return
     */
    @Override
    public AjaxResult register(UserRegDto userRegDto) {
        /* 判断验证码是否正确 */
        String o = (String)redisTemplate.opsForValue().get(userRegDto.getMobile() + "::" + UserConstant.REG_CODE);
        if(!userRegDto.getSmsCode().equals(o)){
            throw new RuntimeException("手机验证码错误");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setType(1).setUsername(userRegDto.getMobile()).setPassword(userRegDto.getPassword());
        AjaxResult ajaxResult = authFeign.addOrUpdate(loginUser);
        if(!ajaxResult.isSuccess()){
            throw new RuntimeException("注册失败");
        }
        Integer resultObj = (Integer)ajaxResult.getResultObj();
        VipUser vipUser = new VipUser();
        /* 设置太状态 */
        long state = BitStatesUtils.OP_ACTIVED;
        /* 添加以验证手机号 */
        state = BitStatesUtils.addState(state,BitStatesUtils.OP_AUTHED_PHONE);
        vipUser.setPhone(userRegDto.getMobile()).setPassword(userRegDto.getPassword()).setCreateTime(System.currentTimeMillis())
                .setLoginId(Long.valueOf(resultObj)).setBitState(state);
        vipUserMapper.insert(vipUser);
        return ajaxResult;
    }
}
