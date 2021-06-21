package cn.lngex.course.mapper;

import cn.lngex.course.domain.CourseType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程目录 Mapper 接口
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-20
 */
public interface CourseTypeMapper extends BaseMapper<CourseType> {

    /**
     * 嵌套查询
     * @param id
     * @return
     */
    List<CourseType> treeTypeData(Long id);
}
