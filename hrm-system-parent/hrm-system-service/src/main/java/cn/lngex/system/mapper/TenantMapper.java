package cn.lngex.system.mapper;

import cn.lngex.auth.domain.EmpTen;
import cn.lngex.system.domain.Tenant;
import cn.lngex.system.query.TenantQuery;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-17
 */
public interface TenantMapper extends BaseMapper<Tenant> {


    EmpTen getEmpTen(Long loginId);

    /**
     * 分高级联表查询
     * @param query
     * @param page
     * @return
     */
    List<Tenant> loadList(TenantQuery query, Page<Tenant> page);
}
