package cn.lngex.auth.feign;

import cn.lngex.auth.domain.LoginUser;
import cn.lngex.auth.domain.UserMeal;
import cn.lngex.utils.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "AUTH-SERVER",fallbackFactory = AuthFeignFallBackFactoryc.class)
public interface IAuthFeign {
    /**
     * 保存和修改公用的
     * @param loginUser  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping("/loginUser")
    AjaxResult addOrUpdate(@RequestBody LoginUser loginUser);

    /**
     * 保存和修改公用的
     * @param userMeal  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping("/userMeal")
    AjaxResult addOrUpdate(@RequestBody UserMeal userMeal);
}
