package cn.lngex.securitydemo.mapper;


import cn.lngex.securitydemo.domain.Permission;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-28
 */
public interface PermissionMapper extends BaseMapper<Permission> {


    /**
     * 查询用户权限
     * @param id
     * @return
     */
    List<Permission> getUserPermissionById(Long id);
}
