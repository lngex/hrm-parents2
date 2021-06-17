package cn.lngex.auth.service.impl;

import cn.lngex.auth.domain.LoginUser;
import cn.lngex.auth.mapper.LoginUserMapper;
import cn.lngex.auth.service.ILoginUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
