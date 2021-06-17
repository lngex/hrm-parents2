package cn.lngex.system.service.impl;

import cn.lngex.system.domain.TenantType;
import cn.lngex.system.mapper.TenantTypeMapper;
import cn.lngex.system.service.ITenantTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户(机构)类型表 服务实现类
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-17
 */
@Service
public class TenantTypeServiceImpl extends ServiceImpl<TenantTypeMapper, TenantType> implements ITenantTypeService {

}
