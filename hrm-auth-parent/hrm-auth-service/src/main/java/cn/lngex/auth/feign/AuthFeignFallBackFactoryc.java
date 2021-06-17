package cn.lngex.auth.feign;

import cn.lngex.auth.domain.LoginUser;
import cn.lngex.auth.domain.UserMeal;
import cn.lngex.utils.AjaxResult;
import feign.hystrix.FallbackFactory;

public class AuthFeignFallBackFactoryc implements FallbackFactory<IAuthFeign> {
    @Override
    public IAuthFeign create(Throwable throwable) {
        return new IAuthFeign() {
            @Override
            public AjaxResult addOrUpdate(LoginUser loginUser) {
                return AjaxResult.me().setSuccess(false).setMessage("loginuser异常");
            }

            @Override
            public AjaxResult addOrUpdate(UserMeal userMeal) {
                return AjaxResult.me().setSuccess(false).setMessage("userMeal异常");
            }
        };
    }
}
