package cn.lngex.system.service.impl;

import cn.lngex.auth.domain.EmpTen;
import cn.lngex.auth.domain.LoginUser;
import cn.lngex.auth.domain.UserMeal;
import cn.lngex.auth.feign.IAuthFeign;
import cn.lngex.system.domain.Employee;
import cn.lngex.system.domain.Tenant;
import cn.lngex.system.mapper.EmployeeMapper;
import cn.lngex.system.mapper.TenantMapper;
import cn.lngex.system.query.TenantQuery;
import cn.lngex.system.service.ITenantService;
import cn.lngex.system.vo.EnteringVo;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-17
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private IAuthFeign authFeign;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public AjaxResult queryList(TenantQuery query) {
        /* 构建查询对象 */
        Page<Tenant> page = new Page<Tenant>(query.getPage(),query.getRows());
        /* 查询数据 */
        List<Tenant> list = tenantMapper.loadList(query,page);
        return AjaxResult.me().setResultObj(new PageList<Tenant>(page.getTotal(),list));
    }

    /**
     * 机构入驻
     * @param enteringVo
     * @return AjaxResult
     */
    @Override
    public AjaxResult entering(EnteringVo enteringVo) {
        AjaxResult me = AjaxResult.me();
        /*try {*/
            /* 保存t_login_user */
            LoginUser loginUser = new LoginUser();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(enteringVo.getEmployee().getPassword());
        loginUser.setType(0)
                    .setUsername(enteringVo.getEmployee().getUsername())
                    .setPassword(encode);
            AjaxResult ajaxResult = authFeign.addOrUpdate(loginUser);
            Integer id = (Integer) ajaxResult.getResultObj();
            Long loginUserId = Long.valueOf(id);
            /* 保存user_meal */
            UserMeal userMeal = new UserMeal();
            userMeal.setMealId(enteringVo.getMealId()).setState(0).setLoginId(loginUserId);
            authFeign.addOrUpdate(userMeal);
            /* 保存tenant */
            Tenant tenant = enteringVo.getTenant();
            tenantMapper.insert(tenant);
            /* 保存Employee */
            Employee employee = enteringVo.getEmployee()
                    .setLoginId(loginUserId)
                    .setState(0)
                    .setType(0)
                    .setPassword(encode)
                    .setTenantId(tenant.getId());
            employeeMapper.insert(employee);
            /* 修改信息 */
            tenantMapper.updateById(tenant.setAdminId(employee.getId()));
/*        } catch (Exception e) {
            me.setMessage("系统繁忙").setSuccess(false);
            e.printStackTrace();
        }*/
        return me;
    }


    @Override
    public AjaxResult getEmpTen(Long loginId) {
        EmpTen empTen= tenantMapper.getEmpTen(loginId);
        return AjaxResult.me().setResultObj(empTen);
    }
}
