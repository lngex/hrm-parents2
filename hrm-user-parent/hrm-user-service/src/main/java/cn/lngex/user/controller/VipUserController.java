package cn.lngex.user.controller;

import cn.lngex.user.domain.VipUser;
import cn.lngex.user.dto.UserRegDto;
import cn.lngex.user.query.VipUserQuery;
import cn.lngex.user.service.IVipUserService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vipUser")
public class VipUserController {
    @Autowired
    public IVipUserService vipUserService;


    /**
     * 保存和修改公用的
     * @param vipUser  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody VipUser vipUser){
        try {
            if( vipUser.getId()!=null)
                vipUserService.updateById(vipUser);
            else
                vipUserService.insert(vipUser);
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
            vipUserService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public VipUser get(@PathVariable("id")Long id)
    {
        return vipUserService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<VipUser> list(){

        return vipUserService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<VipUser> json(@RequestBody VipUserQuery query)
    {
        Page<VipUser> page = new Page<VipUser>(query.getPage(),query.getRows());
        page = vipUserService.selectPage(page);
        return new PageList<VipUser>(page.getTotal(),page.getRecords());
    }

    /**
     * 用户注册
     * @param userRegDto
     * @return
     */
    @RequestMapping("/register")
    public AjaxResult register(@RequestBody @Valid UserRegDto userRegDto){
        return vipUserService.register(userRegDto);
    }
}
