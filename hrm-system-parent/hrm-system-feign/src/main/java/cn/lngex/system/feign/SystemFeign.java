package cn.lngex.system.feign;


import cn.lngex.utils.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "SYSTEM-SERVER",fallbackFactory = SystemFeignFallbackFactory.class)
public interface SystemFeign {


    /**
     * 获取tenant_id，tenant_name，user_id，user_name
     * @param loginId
     * @return
     */
    @GetMapping("/tenant/getEmpTen/{loginId}")
    AjaxResult getEmpTen(@PathVariable("loginId") Long loginId);
}
