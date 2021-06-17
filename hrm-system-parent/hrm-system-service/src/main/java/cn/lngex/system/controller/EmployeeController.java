package cn.lngex.system.controller;

import cn.lngex.system.domain.Employee;
import cn.lngex.system.query.EmployeeQuery;
import cn.lngex.system.service.IEmployeeService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Api(value = "/employee", description = "员工接口")
public class EmployeeController {
    @Autowired
    public IEmployeeService employeeService;


    /**
     * 保存和修改公用的
     * @param employee  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Employee employee){
        try {
            if( employee.getId()!=null)
                employeeService.updateById(employee);
            else
                employeeService.insert(employee);
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
            employeeService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public Employee get(@PathVariable("id")Long id)
    {
        return employeeService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<Employee> list(){

        return employeeService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<Employee> json(@RequestBody EmployeeQuery query)
    {
        Page<Employee> page = new Page<Employee>(query.getPage(),query.getRows());
        page = employeeService.selectPage(page);
        return new PageList<Employee>(page.getTotal(),page.getRecords());
    }
}
