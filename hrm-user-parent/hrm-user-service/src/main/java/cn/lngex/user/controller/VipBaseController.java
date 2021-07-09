package cn.lngex.user.controller;

import cn.lngex.user.domain.VipBase;
import cn.lngex.user.query.VipBaseQuery;
import cn.lngex.user.service.IVipBaseService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vipBase")
public class VipBaseController {
    @Autowired
    public IVipBaseService vipBaseService;


    /**
     * 保存和修改公用的
     * @param vipBase  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody VipBase vipBase){
        try {
            if( vipBase.getId()!=null)
                vipBaseService.updateById(vipBase);
            else
                vipBaseService.insert(vipBase);
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
            vipBaseService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public VipBase get(@PathVariable("id")Long id)
    {
        return vipBaseService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<VipBase> list(){

        return vipBaseService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<VipBase> json(@RequestBody VipBaseQuery query)
    {
        Page<VipBase> page = new Page<VipBase>(query.getPage(),query.getRows());
        page = vipBaseService.selectPage(page);
        return new PageList<VipBase>(page.getTotal(),page.getRecords());
    }
}
