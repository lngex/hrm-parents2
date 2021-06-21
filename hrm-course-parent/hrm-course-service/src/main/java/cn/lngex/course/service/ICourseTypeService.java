package cn.lngex.course.service;

import cn.lngex.course.domain.CourseType;
import cn.lngex.utils.AjaxResult;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 课程目录 服务类
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-20
 */
public interface ICourseTypeService extends IService<CourseType> {

    /**
     * 获取类型级联
     * @return
     */
    AjaxResult treeTypeData();

}
