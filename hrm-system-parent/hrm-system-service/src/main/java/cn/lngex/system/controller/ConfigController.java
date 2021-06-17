package cn.lngex.system.controller;

import cn.lngex.system.domain.Config;
import cn.lngex.system.query.ConfigQuery;
import cn.lngex.system.service.IConfigService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config")
@Api(value = "/config", description = "配置接口")
public class ConfigController {
    @Autowired
    public IConfigService configService;


    /**
     * 保存和修改公用的
     * @param config  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Config config){
        try {
            if( config.getConfigId()!=null)
                configService.updateById(config);
            else
                configService.insert(config);
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
            configService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public Config get(@PathVariable("id")Long id)
    {
        return configService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<Config> list(){

        return configService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<Config> json(@RequestBody ConfigQuery query)
    {
        Page<Config> page = new Page<Config>(query.getPage(),query.getRows());
        page = configService.selectPage(page);
        return new PageList<Config>(page.getTotal(),page.getRecords());
    }
}
