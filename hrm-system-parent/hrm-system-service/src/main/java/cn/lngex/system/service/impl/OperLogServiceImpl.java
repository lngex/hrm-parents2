package cn.lngex.system.service.impl;

import cn.lngex.system.domain.OperLog;
import cn.lngex.system.mapper.OperLogMapper;
import cn.lngex.system.service.IOperLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-17
 */
@Service
public class OperLogServiceImpl extends ServiceImpl<OperLogMapper, OperLog> implements IOperLogService {

}
