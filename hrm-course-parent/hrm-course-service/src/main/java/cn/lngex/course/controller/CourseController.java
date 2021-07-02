package cn.lngex.course.controller;

import cn.lngex.course.domain.Course;
import cn.lngex.course.dto.CourseDto;
import cn.lngex.course.dto.CoursePageQuery;
import cn.lngex.course.query.CourseQuery;
import cn.lngex.course.service.ICourseService;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    public ICourseService courseService;


    /**
     * 保存和修改公用的
     * @param course  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    @PreAuthorize("hasAnyAuthority('course:add')")
    public AjaxResult addOrUpdate(@RequestBody Course course){
        try {
            if( course.getId()!=null)
                courseService.updateById(course);
            else
                courseService.insert(course);
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
    @PreAuthorize("hasAnyAuthority('course:delete')")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            courseService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('course:list')")
    public Course get(@PathVariable("id")Long id)
    {
        return courseService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    @PreAuthorize("hasAnyAuthority('course:list')")
    public List<Course> list(){

        return courseService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    @PreAuthorize("hasAnyAuthority('course:list')")
    public PageList<Course> json(@RequestBody CourseQuery query)
    {
        Page<Course> page = new Page<Course>(query.getPage(),query.getRows());
        page = courseService.selectPage(page);
        return new PageList<Course>(page.getTotal(),page.getRecords());
    }

    /**
     * 添加课程
     * @param courseDto
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('course:save')")
    public AjaxResult save(@RequestBody @Valid CourseDto courseDto){
        return courseService.save(courseDto);
    }

    /**
     * 批量上线
     * @param ids
     * @return
     */
    @PostMapping("/onLineCourse")
    @PreAuthorize("hasAnyAuthority('course:onLine')")
    public AjaxResult onLineCourse(@RequestBody List<Long> ids){
        ids.forEach(System.out::println);

        return courseService.onLineCourse(ids);
    }

    /**
     * 批量下线
     * @param ids
     * @return
     */
    @PostMapping("/offLineCourse")
    @PreAuthorize("hasAnyAuthority('course:offLine')")
    public AjaxResult offLineCourse(@RequestBody List<Long> ids){
        return courseService.offLineCourse(ids);
    }

    @PostMapping("/batchdel")
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        return courseService.batchDel(ids);
    }

    /**
     * 面包屑
     * @param coursePageQuery
     * @return
     */
    @PostMapping("/queryCourses")
    @PreAuthorize("hasAnyAuthority('course:list')")
    public AjaxResult queryCourses(@RequestBody CoursePageQuery coursePageQuery){
        System.err.println(coursePageQuery);
        return courseService.queryCourses(coursePageQuery);
    }
}
