package cn.lngex.course.controller;

import cn.lngex.course.domain.CourseCollect;
import cn.lngex.course.query.CourseCollectQuery;
import cn.lngex.course.service.ICourseCollectService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseCollect")
public class CourseCollectController {
    @Autowired
    public ICourseCollectService courseCollectService;


    /**
     * 保存和修改公用的
     * @param courseCollect  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody CourseCollect courseCollect){
        try {
            if( courseCollect.getId()!=null)
                courseCollectService.updateById(courseCollect);
            else
                courseCollectService.insert(courseCollect);
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
            courseCollectService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public CourseCollect get(@PathVariable("id")Long id)
    {
        return courseCollectService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<CourseCollect> list(){

        return courseCollectService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<CourseCollect> json(@RequestBody CourseCollectQuery query)
    {
        Page<CourseCollect> page = new Page<CourseCollect>(query.getPage(),query.getRows());
        page = courseCollectService.selectPage(page);
        return new PageList<CourseCollect>(page.getTotal(),page.getRecords());
    }
}
