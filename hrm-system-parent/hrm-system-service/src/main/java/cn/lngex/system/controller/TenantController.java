package cn.lngex.system.controller;

import cn.lngex.system.domain.Tenant;
import cn.lngex.system.query.TenantQuery;
import cn.lngex.system.service.ITenantService;
import cn.lngex.system.vo.EnteringVo;
import cn.lngex.utils.AjaxResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tenant")
@Api(value = "/tenant", description = "机构接口")
public class TenantController {
    @Autowired
    public ITenantService tenantService;


    /**
     * 保存和修改公用的
     * @param tenant  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Tenant tenant){
        try {
            if( tenant.getId()!=null)
                tenantService.updateById(tenant);
            else
                tenantService.insert(tenant);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            tenantService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public Tenant get(@PathVariable("id")Long id)
    {
        return tenantService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<Tenant> list(){

        return tenantService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public AjaxResult json(@RequestBody TenantQuery query)
    {
        /*Page<Tenant> page = new Page<Tenant>(query.getPage(),query.getRows());
        page = tenantService.selectPage(page);
        return new PageList<Tenant>(page.getTotal(),page.getRecords());*/
        return tenantService.queryList(query);
    }

    /**
     * 商家入驻
     * @param enteringVo
     * @return
     */
    @PostMapping("/entering")
    public AjaxResult entering(@RequestBody @Valid EnteringVo enteringVo){
        return tenantService.entering(enteringVo);
    }


    /**
     * 获取tenant_id，tenant_name，user_id，user_name
     * @param loginId
     * @return
     */
    @GetMapping("/getEmpTen/{loginId}")
    public AjaxResult getEmpTen(@PathVariable("loginId") Long loginId){
        System.out.println("==================="+loginId);
        return tenantService.getEmpTen(loginId);
    }
}
