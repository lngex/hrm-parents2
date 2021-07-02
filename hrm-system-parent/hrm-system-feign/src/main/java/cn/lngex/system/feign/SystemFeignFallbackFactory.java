package cn.lngex.system.feign;

import cn.lngex.utils.AjaxResult;
import feign.hystrix.FallbackFactory;

public class SystemFeignFallbackFactory implements FallbackFactory<SystemFeign> {
    @Override
    public SystemFeign create(Throwable cause) {
        return new SystemFeign() {
            @Override
            public AjaxResult getEmpTen(Long loginId) {
                return AjaxResult.me().setResultObj("添加获取信息失败").setSuccess(false);
            }
        };
    }
}
