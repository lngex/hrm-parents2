package cn.lngex.system.controller;

import cn.lngex.system.domain.OperLog;
import cn.lngex.system.query.OperLogQuery;
import cn.lngex.system.service.IOperLogService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operLog")
@Api(value = "/operLog", description = "操作日志接口")
public class OperLogController {
    @Autowired
    public IOperLogService operLogService;


    /**
     * 保存和修改公用的
     * @param operLog  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody OperLog operLog){
        try {
            if( operLog.getOperId()!=null)
                operLogService.updateById(operLog);
            else
                operLogService.insert(operLog);
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
            operLogService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public OperLog get(@PathVariable("id")Long id)
    {
        return operLogService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<OperLog> list(){

        return operLogService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<OperLog> json(@RequestBody OperLogQuery query)
    {
        Page<OperLog> page = new Page<OperLog>(query.getPage(),query.getRows());
        page = operLogService.selectPage(page);
        return new PageList<OperLog>(page.getTotal(),page.getRecords());
    }
}
