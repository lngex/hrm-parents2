package cn.lngex.course.controller;

import cn.lngex.course.domain.CourseView;
import cn.lngex.course.query.CourseViewQuery;
import cn.lngex.course.service.ICourseViewService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseView")
public class CourseViewController {
    @Autowired
    public ICourseViewService courseViewService;


    /**
     * 保存和修改公用的
     * @param courseView  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody CourseView courseView){
        try {
            if( courseView.getId()!=null)
                courseViewService.updateById(courseView);
            else
                courseViewService.insert(courseView);
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
            courseViewService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public CourseView get(@PathVariable("id")Long id)
    {
        return courseViewService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<CourseView> list(){

        return courseViewService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<CourseView> json(@RequestBody CourseViewQuery query)
    {
        Page<CourseView> page = new Page<CourseView>(query.getPage(),query.getRows());
        page = courseViewService.selectPage(page);
        return new PageList<CourseView>(page.getTotal(),page.getRecords());
    }
}
