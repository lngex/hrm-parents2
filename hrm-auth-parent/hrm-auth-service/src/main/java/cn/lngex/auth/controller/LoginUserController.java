package cn.lngex.auth.controller;

import cn.lngex.auth.domain.LoginDto;
import cn.lngex.auth.domain.LoginUser;
import cn.lngex.auth.query.LoginUserQuery;
import cn.lngex.auth.service.ILoginUserService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/loginUser")
public class LoginUserController {
    @Autowired
    public ILoginUserService loginUserService;


    /**
     * 保存和修改公用的
     * @param loginUser  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody LoginUser loginUser){
        try {
            if( loginUser.getId()!=null)
                loginUserService.updateById(loginUser);
            else
                loginUserService.insert(loginUser);
            return AjaxResult.me().setResultObj(loginUser.getId());
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
            loginUserService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public LoginUser get(@PathVariable("id")Long id)
    {
        return loginUserService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<LoginUser> list(){

        return loginUserService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<LoginUser> json(@RequestBody LoginUserQuery query)
    {
        Page<LoginUser> page = new Page<LoginUser>(query.getPage(),query.getRows());
        page = loginUserService.selectPage(page);
        return new PageList<LoginUser>(page.getTotal(),page.getRecords());
    }

    @RequestMapping("/loginsuccess")
    public AjaxResult loginSucess(){
        return AjaxResult.me().setMessage("登陆成功");
    }


    /**
     * 用户登录接口
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody @Valid LoginDto loginDto){
        return loginUserService.login(loginDto);
    }

    @PostMapping("/reftoken")
    public AjaxResult refToken(@RequestBody HashMap<String,Object> map){
        return loginUserService.refToken(map);
    }
}
