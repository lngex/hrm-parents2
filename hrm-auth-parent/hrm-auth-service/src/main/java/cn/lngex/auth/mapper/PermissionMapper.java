package cn.lngex.auth.mapper;

import cn.lngex.auth.domain.Permission;
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

    List<Permission> getUserPermissionById(Long id);
}
