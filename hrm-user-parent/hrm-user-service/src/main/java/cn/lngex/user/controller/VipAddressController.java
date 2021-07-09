package cn.lngex.user.controller;

import cn.lngex.user.domain.VipAddress;
import cn.lngex.user.query.VipAddressQuery;
import cn.lngex.user.service.IVipAddressService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vipAddress")
public class VipAddressController {
    @Autowired
    public IVipAddressService vipAddressService;


    /**
     * 保存和修改公用的
     * @param vipAddress  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody VipAddress vipAddress){
        try {
            if( vipAddress.getId()!=null)
                vipAddressService.updateById(vipAddress);
            else
                vipAddressService.insert(vipAddress);
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
            vipAddressService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public VipAddress get(@PathVariable("id")Long id)
    {
        return vipAddressService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<VipAddress> list(){

        return vipAddressService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<VipAddress> json(@RequestBody VipAddressQuery query)
    {
        Page<VipAddress> page = new Page<VipAddress>(query.getPage(),query.getRows());
        page = vipAddressService.selectPage(page);
        return new PageList<VipAddress>(page.getTotal(),page.getRecords());
    }
}
