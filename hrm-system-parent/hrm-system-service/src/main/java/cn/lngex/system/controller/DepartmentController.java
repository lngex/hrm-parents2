package cn.lngex.system.controller;

import cn.lngex.system.domain.Department;
import cn.lngex.system.query.DepartmentQuery;
import cn.lngex.system.service.IDepartmentService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@Api(value = "/department", description = "部门接口")
public class DepartmentController {
    @Autowired
    public IDepartmentService departmentService;


    /**
     * 保存和修改公用的
     * @param department  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Department department){
        try {
            if( department.getId()!=null)
                departmentService.updateById(department);
            else
                departmentService.insert(department);
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
            departmentService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public Department get(@PathVariable("id")Long id)
    {
        return departmentService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<Department> list(){

        return departmentService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<Department> json(@RequestBody DepartmentQuery query)
    {
        Page<Department> page = new Page<Department>(query.getPage(),query.getRows());
        page = departmentService.selectPage(page);
        return new PageList<Department>(page.getTotal(),page.getRecords());
    }
}
