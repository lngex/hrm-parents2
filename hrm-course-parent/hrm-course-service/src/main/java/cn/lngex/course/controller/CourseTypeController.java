package cn.lngex.course.controller;

import cn.lngex.course.domain.CourseType;
import cn.lngex.course.query.CourseTypeQuery;
import cn.lngex.course.service.ICourseTypeService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseType")
public class CourseTypeController {
    @Autowired
    public ICourseTypeService courseTypeService;


    /**
     * 保存和修改公用的
     * @param courseType  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody CourseType courseType){
        try {
            if( courseType.getId()!=null)
                courseTypeService.updateById(courseType);
            else
                courseTypeService.insert(courseType);
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
            courseTypeService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public CourseType get(@PathVariable("id")Long id)
    {
        return courseTypeService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public AjaxResult list(){
        List<CourseType> courseTypes = courseTypeService.selectList(null);
        return AjaxResult.me().setResultObj(courseTypes);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<CourseType> json(@RequestBody CourseTypeQuery query)
    {
        Page<CourseType> page = new Page<CourseType>(query.getPage(),query.getRows());
        page = courseTypeService.selectPage(page);
        return new PageList<CourseType>(page.getTotal(),page.getRecords());
    }

    @PostMapping("/treedata")
    public AjaxResult treeTypeData(){
        return courseTypeService.treeTypeData();
    }
}
