package cn.lngex.system.service;

import cn.lngex.system.domain.Tenant;
import cn.lngex.system.query.TenantQuery;
import cn.lngex.system.vo.EnteringVo;
import cn.lngex.utils.AjaxResult;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-17
 */
public interface ITenantService extends IService<Tenant> {

    AjaxResult queryList(TenantQuery query);

    /**
     * 商家入驻
     * @param enteringVo
     * @return
     */
    AjaxResult entering(EnteringVo enteringVo);


    /**
     * 获取tenant_id，tenant_name，user_id，user_name
     * @param loginId
     * @return
     */
    AjaxResult getEmpTen(Long loginId);
}
