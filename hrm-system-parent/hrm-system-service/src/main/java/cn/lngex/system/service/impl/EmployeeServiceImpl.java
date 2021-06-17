package cn.lngex.system.service.impl;

import cn.lngex.system.domain.Employee;
import cn.lngex.system.mapper.EmployeeMapper;
import cn.lngex.system.service.IEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-17
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
