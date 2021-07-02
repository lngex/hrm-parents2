package cn.lngex.course.service;

import cn.lngex.course.domain.Course;
import cn.lngex.course.dto.CourseDto;
import cn.lngex.course.dto.CoursePageQuery;
import cn.lngex.utils.AjaxResult;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-20
 */
public interface ICourseService extends IService<Course> {


    /**
     * 添加课程
     * @param courseDto
     * @return
     */
    AjaxResult save(CourseDto courseDto);


    /**
     * 修改状态
     * @param id
     * @return AjaxResult
     */
    AjaxResult onLineCourse(List<Long> id);


    /**
     * 批量下线
     * @param ids
     * @return AjaxResult
     */
    AjaxResult offLineCourse(List<Long> ids);


    /**
     * 批量删除
     * @param ids
     * @return
     */
    AjaxResult batchDel(List<Long> ids);


    /**
     * 面包屑
     * @param coursePageQuery
     * @return
     */
    AjaxResult queryCourses(CoursePageQuery coursePageQuery);
}
