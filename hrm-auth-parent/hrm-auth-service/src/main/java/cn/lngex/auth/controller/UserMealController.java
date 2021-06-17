package cn.lngex.auth.controller;

import cn.lngex.auth.domain.UserMeal;
import cn.lngex.auth.query.UserMealQuery;
import cn.lngex.auth.service.IUserMealService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userMeal")
public class UserMealController {
    @Autowired
    public IUserMealService userMealService;


    /**
     * 保存和修改公用的
     * @param userMeal  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody UserMeal userMeal){
        try {
            /*if( userMeal.getMealId()!=null)
                userMealService.updateById(userMeal);
            else*/
            userMealService.insert(userMeal);
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
            userMealService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public UserMeal get(@PathVariable("id")Long id)
    {
        return userMealService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<UserMeal> list(){

        return userMealService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<UserMeal> json(@RequestBody UserMealQuery query)
    {
        Page<UserMeal> page = new Page<UserMeal>(query.getPage(),query.getRows());
        page = userMealService.selectPage(page);
        return new PageList<UserMeal>(page.getTotal(),page.getRecords());
    }
}
